package eu.europeana.enrichment.rest.client;

import eu.europeana.enrichment.utils.EntityMergeEngine;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * This class is able to construct {@link EnrichmentWorker} instances and is as such the main
 * gateway to dereference and enrichment functionality.
 */
public class EnrichmentWorkerBuilder {

  /**
   * The default value of the maximum amount of time, in milliseconds, we wait for a connection to a
   * resource before timing out. It's currently set to {@value EnrichmentWorkerBuilder#DEFAULT_CONNECT_TIMEOUT}
   * milliseconds.
   */
  public static final int DEFAULT_CONNECT_TIMEOUT = 10_000;

  /**
   * The default value of the maximum amount of time, in milliseconds, we wait for the response
   * before timing out. It's currently set to {@value EnrichmentWorkerBuilder#DEFAULT_RESPONSE_TIMEOUT}
   * milliseconds.
   */
  public static final int DEFAULT_RESPONSE_TIMEOUT = 60_000;

  /**
   * The default value of the batch size with which we query the enrichment service. It's currently
   * set to {@value EnrichmentWorkerBuilder#DEFAULT_BATCH_SIZE_ENRICHMENT} values.
   */
  public static final int DEFAULT_BATCH_SIZE_ENRICHMENT = 20;

  private String dereferenceUrl = null;
  private String enrichmentUrl = null;
  private int connectTimeout = DEFAULT_CONNECT_TIMEOUT;
  private int responseTimeout = DEFAULT_RESPONSE_TIMEOUT;
  private int batchSizeEnrichment = DEFAULT_BATCH_SIZE_ENRICHMENT;

  /**
   * Set the URL of the dereferencing service. The default is null. If set to a blank value, the
   * enrichment worker will not be configured to perform dereferencing.
   *
   * @param dereferenceUrl The URL of the dereferencing service.
   * @return This instance, for convenience.
   */
  public EnrichmentWorkerBuilder setDereferenceUrl(String dereferenceUrl) {
    this.dereferenceUrl = dereferenceUrl;
    return this;
  }

  /**
   * Set the URL of the enrichment service. The default is null. If set to a blank value, the
   * enrichment worker will not be configured to perform enrichment.
   *
   * @param enrichmentUrl The URL of the dereferencing service.
   * @return This instance, for convenience.
   */
  public EnrichmentWorkerBuilder setEnrichmentUrl(String enrichmentUrl) {
    this.enrichmentUrl = enrichmentUrl;
    return this;
  }

  /**
   * Set the maximum amount of time, in milliseconds, we wait for a connection before timing out.
   * The default (when not calling this method) is {@value EnrichmentWorkerBuilder#DEFAULT_CONNECT_TIMEOUT}
   * milliseconds.
   *
   * @param connectTimeout The maximum amount of time, in milliseconds, we wait for a connection
   * before timing out. If not positive, this signifies that the connection does not time out.
   * @return This instance, for convenience.
   */
  public EnrichmentWorkerBuilder setConnectTimeout(int connectTimeout) {
    this.connectTimeout = connectTimeout;
    return this;
  }

  /**
   * Set the maximum amount of time, in milliseconds, we wait for the response. The default (when
   * not calling this method is {@value EnrichmentWorkerBuilder#DEFAULT_RESPONSE_TIMEOUT}
   * milliseconds.
   *
   * @param responseTimeout The maximum amount of time, in milliseconds, we wait for the response
   * before timing out. If not positive, this signifies that the response does not time out.
   * @return This instance, for convenience.
   */
  public EnrichmentWorkerBuilder setResponseTimeout(int responseTimeout) {
    this.responseTimeout = responseTimeout;
    return this;
  }

  /**
   * Set the batch size with which we query the enrichment service. The default (when not calling
   * this method) is {@value EnrichmentWorkerBuilder#DEFAULT_BATCH_SIZE_ENRICHMENT} values.
   *
   * @param batchSizeEnrichment The batch size. Must be strictly positive.
   * @return This instance, for convenience.
   */
  public EnrichmentWorkerBuilder setBatchSizeEnrichment(int batchSizeEnrichment) {
    if (batchSizeEnrichment < 1) {
      throw new IllegalArgumentException("Batch size cannot be 0 or negative.");
    }
    this.batchSizeEnrichment = batchSizeEnrichment;
    return this;
  }

  /**
   * Builds an {@link EnrichmentWorker} according to the parameters that are set.
   *
   * @return An instance.
   * @throws IllegalStateException When both the enrichment and dereference URLs are blank.
   */
  public EnrichmentWorker build() {

    // Make sure that the worker can do something.
    if (StringUtils.isBlank(dereferenceUrl) && StringUtils.isBlank(enrichmentUrl)) {
      throw new IllegalStateException(
              "Either dereferencing or enrichment (or both) must be enabled.");
    }

    // Create the request factory
    final HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
    requestFactory.setConnectTimeout(Math.max(connectTimeout, 0));
    requestFactory.setReadTimeout(Math.max(responseTimeout, 0));

    // Create the dereference client if needed
    final DereferenceClient dereferenceClient;
    if (StringUtils.isNotBlank(dereferenceUrl)) {
      dereferenceClient = new DereferenceClient(new RestTemplate(requestFactory), dereferenceUrl);
    } else {
      dereferenceClient = null;
    }

    // Create the enrichment client if needed
    final EnrichmentClient enrichmentClient;
    if (StringUtils.isNotBlank(enrichmentUrl)) {
      enrichmentClient = new EnrichmentClient(new RestTemplate(requestFactory), enrichmentUrl,
              batchSizeEnrichment);
    } else {
      enrichmentClient = null;
    }

    // Done.
    return new EnrichmentWorkerImpl(dereferenceClient, enrichmentClient, new EntityMergeEngine());
  }
}
