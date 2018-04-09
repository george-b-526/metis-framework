package eu.europeana.indexing.solr;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import eu.europeana.corelib.definitions.jibx.Aggregation;
import eu.europeana.corelib.definitions.jibx.EuropeanaAggregationType;
import eu.europeana.corelib.definitions.jibx.HasView;
import eu.europeana.corelib.definitions.jibx.RDF;
import eu.europeana.corelib.definitions.jibx.ResourceType;
import eu.europeana.corelib.definitions.jibx.Type1;
import eu.europeana.corelib.definitions.jibx.WebResourceType;
import eu.europeana.corelib.solr.bean.impl.FullBeanImpl;
import eu.europeana.corelib.solr.entity.AggregationImpl;
import eu.europeana.corelib.solr.entity.LicenseImpl;
import eu.europeana.indexing.solr.crf.ImageTagExtractor;
import eu.europeana.indexing.solr.crf.MediaType;
import eu.europeana.indexing.solr.crf.SoundTagExtractor;
import eu.europeana.indexing.solr.crf.TagExtractor;
import eu.europeana.indexing.solr.crf.TechnicalFacetUtils;
import eu.europeana.indexing.solr.crf.TextTagExtractor;
import eu.europeana.indexing.solr.crf.VideoTagExtractor;
import eu.europeana.indexing.solr.property.AgentSolrCreator;
import eu.europeana.indexing.solr.property.AggregationSolrCreator;
import eu.europeana.indexing.solr.property.ConceptSolrCreator;
import eu.europeana.indexing.solr.property.EuropeanaAggregationSolrCreator;
import eu.europeana.indexing.solr.property.LicenseSolrCreator;
import eu.europeana.indexing.solr.property.PlaceSolrCreator;
import eu.europeana.indexing.solr.property.ProvidedChoSolrCreator;
import eu.europeana.indexing.solr.property.ProxySolrCreator;
import eu.europeana.indexing.solr.property.ServiceSolrCreator;
import eu.europeana.indexing.solr.property.SolrPropertyUtils;
import eu.europeana.indexing.solr.property.TimespanSolrCreator;

public class SolrDocumentPopulator {

  private static final Logger LOGGER = LoggerFactory.getLogger(SolrDocumentPopulator.class);

  /**
   * Populates a Solr document with the properties of the full bean. Please note: this method should
   * only be called once on a given document, otherwise the behavior is not defined.
   * 
   * @param document The Solr document to populate.
   * @param fullBean The FullBean to populate from.
   * @return The SolrInputDocument representation of the FullBean
   */
  public SolrInputDocument populateWithProperties(SolrInputDocument document,
      FullBeanImpl fullBean) {

    final Set<String> licenseIds;
    if (fullBean.getLicenses() != null) {
      licenseIds =
          fullBean.getLicenses().stream().map(LicenseImpl::getAbout).collect(Collectors.toSet());
    } else {
      licenseIds = Collections.emptySet();
    }

    new ProvidedChoSolrCreator().addToDocument(document, fullBean.getProvidedCHOs().get(0));
    new AggregationSolrCreator(licenseIds::contains).addToDocument(document,
        fullBean.getAggregations().get(0));
    new EuropeanaAggregationSolrCreator().addToDocument(document,
        fullBean.getEuropeanaAggregation());
    new ProxySolrCreator().addAllToDocument(document, fullBean.getProxies());
    new ConceptSolrCreator().addAllToDocument(document, fullBean.getConcepts());
    new TimespanSolrCreator().addAllToDocument(document, fullBean.getTimespans());
    new AgentSolrCreator().addAllToDocument(document, fullBean.getAgents());
    new PlaceSolrCreator().addAllToDocument(document, fullBean.getPlaces());
    new ServiceSolrCreator().addAllToDocument(document, fullBean.getServices());

    final Set<String> defRights = fullBean.getAggregations().stream()
        .map(AggregationImpl::getEdmRights).filter(Objects::nonNull)
        .flatMap(SolrPropertyUtils::getRightsFromMap).collect(Collectors.toSet());
    new LicenseSolrCreator(license -> defRights.contains(license.getAbout()))
        .addAllToDocument(document, fullBean.getLicenses());

    document.addField(EdmLabel.EUROPEANA_COMPLETENESS.toString(),
        fullBean.getEuropeanaCompleteness());
    document.addField(EdmLabel.EUROPEANA_COLLECTIONNAME.toString(),
        fullBean.getEuropeanaCollectionName()[0]);
    document.addField(EdmLabel.TIMESTAMP_CREATED.toString(), fullBean.getTimestampCreated());
    document.addField(EdmLabel.TIMESTAMP_UPDATED.toString(), fullBean.getTimestampUpdated());

    return document;
  }

  /**
   * Populates a Solr document with the CRF fields of the RDF. Please note: this method should only
   * be called once on a given document, otherwise the behavior is not defined.
   * 
   * @param document The document to populate.
   * @param rdf The RDF to populate from.
   */
  public void populateWithCrfFields(SolrInputDocument document, RDF rdf) {

    // Check Europeana aggregation list.
    final List<EuropeanaAggregationType> aggregationList =
        rdf.getEuropeanaAggregationList() != null ? rdf.getEuropeanaAggregationList()
            : Collections.emptyList();
    if (aggregationList.size() > 1) {
      LOGGER
          .info("Multiple Europeana aggregations found in RDF: ignoring all except the last one.");
    }
    final EuropeanaAggregationType aggregation =
        aggregationList.isEmpty() ? null : aggregationList.get(aggregationList.size() - 1);

    // has_thumbnails is true if and only if edm:EuropeanaAggregation/edm:preview is filled.
    final ResourceType preview = aggregation == null ? null : aggregation.getPreview();
    document.addField(EdmLabel.CRF_HAS_THUMBNAILS.toString(), !isEmpty(preview));

    // has_landingpage is true if and only if edm:EuropeanaAggregation/edm:landingpage is filled.
    final ResourceType landingPage = aggregation == null ? null : aggregation.getLandingPage();
    document.addField(EdmLabel.CRF_HAS_LANDING_PAGE.toString(), !isEmpty(landingPage));

    // Extract the web resources that constitute views.
    final List<WebResourceType> views = getViews(rdf);

    // has_media is true if and only if there is at least one web resource of type 'view'
    // representing technical metadata (except those of type 'text/html')
    final boolean hasMedia = views.stream().map(TechnicalFacetUtils::getMimeType)
        .filter(mimeType -> !"text/html".equals(mimeType)).map(MediaType::getType)
        .anyMatch(type -> !type.equals(MediaType.OTHER));
    document.addField(EdmLabel.CRF_HAS_MEDIA.toString(), hasMedia);

    // is_fulltext is true if and only if there is at least one web resource of type 'view'
    // with 'rdf:type' equal to 'edm:FullTextResource'.
    final boolean isFullText = views.stream().map(WebResourceType::getType).filter(Objects::nonNull)
        .map(Type1::getResource)
        .anyMatch("http://www.europeana.eu/schemas/edm/FullTextResource"::equals);
    document.addField(EdmLabel.CRF_IS_FULL_TEXT.toString(), isFullText);

    // Compose the filter and facet tags.
    final Set<Integer> filterTags = new HashSet<>();
    final Set<Integer> facetTags = new HashSet<>();
    for (WebResourceType webResource : views) {
      final TagExtractor tagExtractor = getTagExtractor(webResource);
      filterTags.addAll(tagExtractor.getFilterTags(webResource));
      facetTags.addAll(tagExtractor.getFacetTags(webResource));
    }

    // Add the filter and facet tags to the Solr document.
    for (Integer tag : filterTags) {
      document.addField(EdmLabel.CRF_FILTER_TAGS.toString(), tag);
    }
    for (Integer tag : facetTags) {
      document.addField(EdmLabel.CRF_FACET_TAGS.toString(), tag);
    }
  }

  private final TagExtractor getTagExtractor(WebResourceType webResource) {
    // TODO JOCHEN do this more elegantly. Maybe have it as part of the MediaType enum?
    final String mimeType = TechnicalFacetUtils.getMimeType(webResource);
    final TagExtractor result;
    switch (MediaType.getType(mimeType)) {
      case IMAGE:
        result = new ImageTagExtractor();
        break;
      case VIDEO:
        result = new VideoTagExtractor();
        break;
      case AUDIO:
        result = new SoundTagExtractor();
        break;
      case TEXT:
        result = new TextTagExtractor();
        break;
      default:
        result = null;
        break;
    }
    return result;
  }

  private final List<WebResourceType> getViews(RDF rdf) {

    // Obtain the URLs of type view.
    // TODO JOCHEN: can be done by potential library method EdmObject.getResourceUrls:
    // urls = getResourceUrls(Collections.singleton(UrlType.HAS_VIEW)).keySet()
    final Set<String> urls;
    if (rdf.getAggregationList() != null) {
      urls = rdf.getAggregationList().stream().map(Aggregation::getHasViewList)
          .filter(Objects::nonNull).flatMap(List::stream).map(HasView::getResource)
          .filter(Objects::nonNull).collect(Collectors.toSet());
    } else {
      urls = Collections.emptySet();
    }

    // Return the web resources that match the view URLs.
    final List<WebResourceType> result;
    if (rdf.getWebResourceList() == null) {
      result = Collections.emptyList();
    } else {
      result = rdf.getWebResourceList().stream()
          .filter(resource -> urls.contains(resource.getAbout())).collect(Collectors.toList());
    }
    return result;
  }

  private boolean isEmpty(ResourceType resourceType) {
    return resourceType == null || resourceType.getResource() == null
        || resourceType.getResource().trim().isEmpty();
  }
}
