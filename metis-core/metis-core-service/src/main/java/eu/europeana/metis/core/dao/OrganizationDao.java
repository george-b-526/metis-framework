/*
 * Copyright 2007-2013 The Europeana Foundation
 *
 *  Licenced under the EUPL, Version 1.1 (the "Licence") and subsequent versions as approved
 *  by the European Commission;
 *  You may not use this work except in compliance with the Licence.
 *
 *  You may obtain a copy of the Licence at:
 *  http://joinup.ec.europa.eu/software/page/eupl
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under
 *  the Licence is distributed on an "AS IS" basis, without warranties or conditions of
 *  any kind, either express or implied.
 *  See the Licence for the specific language governing permissions and limitations under
 *  the Licence.
 */
package eu.europeana.metis.core.dao;

import com.mongodb.WriteResult;
import eu.europeana.metis.core.common.Country;
import eu.europeana.metis.core.common.OrganizationRole;
import eu.europeana.metis.core.mongo.MorphiaDatastoreProvider;
import eu.europeana.metis.core.organization.Organization;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Organization DAO
 * Created by ymamakis on 2/17/16.
 */
@Repository
public class OrganizationDao implements MetisDao<Organization, String> {

  private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationDao.class);
  private static final String ORGANIZATION_ID_FIELD = "organizationId";
  private static final String ORGANIZATION_ROLES = "organizationRoles";
  private static final String COUNTRY = "country";
  private int organizationsPerRequest = 5;

  private final MorphiaDatastoreProvider morphiaDatastoreProvider;

  @Autowired
  public OrganizationDao(MorphiaDatastoreProvider morphiaDatastoreProvider) {
    this.morphiaDatastoreProvider = morphiaDatastoreProvider;
  }

  @Override
  public String create(Organization organization) {
    Key<Organization> organizationKey = morphiaDatastoreProvider.getDatastore().save(organization);
    LOGGER.info("Organization '{}' created in Mongo", organization.getOrganizationId());
    return organizationKey.getId().toString();
  }

  @Override
  public String update(Organization organization) {
    Query<Organization> q = morphiaDatastoreProvider.getDatastore().find(Organization.class)
        .field(ORGANIZATION_ID_FIELD).equal(organization.getOrganizationId());
    UpdateOperations<Organization> ops = morphiaDatastoreProvider.getDatastore()
        .createUpdateOperations(Organization.class);
    if (organization.getHarvestingMetadata() != null) {
      ops.set("harvestingMetadata", organization.getHarvestingMetadata());
    } else {
      ops.unset("harvestingMetadata");
    }
    if (organization.getOrganizationUri() != null) {
      ops.set("organizationUri", organization.getOrganizationUri());
    } else {
      ops.unset("organizationUri");
    }
    ops.set("name", organization.getName());
    if (organization.getOrganizationRoles() != null) {
      ops.set(ORGANIZATION_ROLES, organization.getOrganizationRoles());
    } else {
      ops.unset(ORGANIZATION_ROLES);
    }
    if (organization.getCreatedByLdapId() != null) {
      ops.set("createdByLdapId", organization.getCreatedByLdapId());
    }

    if (organization.getUpdatedByLdapId() != null) {
      ops.set("updatedByLdapId", organization.getUpdatedByLdapId());
    } else {
      ops.unset("updatedByLdapId");
    }

    if (organization.getPrefLabel() != null) {
      ops.set("prefLabel", organization.getPrefLabel());
    } else {
      ops.unset("prefLabel");
    }

    if (organization.getAltLabel() != null) {
      ops.set("altLabel", organization.getAltLabel());
    } else {
      ops.unset("altLabel");
    }

    if (organization.getSameAs() != null) {
      ops.set("sameAs", organization.getSameAs());
    } else {
      ops.unset("sameAs");
    }

    if (organization.getDescription() != null) {
      ops.set("description", organization.getDescription());
    } else {
      ops.unset("description");
    }

    if (organization.getLogoLocation() != null) {
      ops.set("logoLocation", organization.getLogoLocation());
    } else {
      ops.unset("logoLocation");
    }
    if (organization.getDomain() != null) {
      ops.set("domain", organization.getDomain());
    } else {
      ops.unset("domain");
    }
    if (organization.getSector() != null) {
      ops.set("sector", organization.getSector());
    } else {
      ops.unset("sector");
    }
    if (organization.getGeographicLevel() != null) {
      ops.set("geographicLevel", organization.getGeographicLevel());
    } else {
      ops.unset("geographicLevel");
    }
    if (organization.getWebsite() != null) {
      ops.set("website", organization.getWebsite());
    } else {
      ops.unset("website");
    }
    if (organization.getCountry() != null) {
      ops.set(COUNTRY, organization.getCountry());
    } else {
      ops.unset(COUNTRY);
    }
    if (organization.getLanguage() != null) {
      ops.set("language", organization.getLanguage());
    } else {
      ops.unset("language");
    }

    ops.set("acronym", organization.getAcronym());
    ops.set("modified", new Date());
    UpdateResults updateResults = morphiaDatastoreProvider.getDatastore().update(q, ops);
    LOGGER.info("Organization '{}' updated in Mongo", organization.getOrganizationId());
    return String.valueOf(updateResults.getUpdatedCount());
  }

  @Override
  public Organization getById(String id) {
    return morphiaDatastoreProvider.getDatastore().find(Organization.class).field("_id").equal(new ObjectId(id))
        .get();
  }

  @Override
  public boolean delete(Organization organization) {
    morphiaDatastoreProvider.getDatastore().delete(organization);
    LOGGER.info("Organization '{}' deleted from Mongo", organization.getName());
    return true;
  }

  public String updateOrganizationDatasetNamesList(String organizationId, String datasetName) {
    Query<Organization> query = morphiaDatastoreProvider.getDatastore().find(Organization.class)
        .field(ORGANIZATION_ID_FIELD).equal(organizationId);
    UpdateOperations<Organization> organizationUpdateOperations = morphiaDatastoreProvider.getDatastore()
        .createUpdateOperations(Organization.class);
    organizationUpdateOperations.addToSet("datasetNames", datasetName);
    UpdateResults updateResults = morphiaDatastoreProvider.getDatastore()
        .update(query, organizationUpdateOperations);
    LOGGER.info("Organization '{}' datasetNames updated in Mongo", organizationId);
    return String.valueOf(updateResults.getUpdatedCount());

  }

  public void removeOrganizationDatasetNameFromList(String organizationId, String datasetName) {
    Query<Organization> query = morphiaDatastoreProvider.getDatastore().find(Organization.class)
        .field(ORGANIZATION_ID_FIELD).equal(organizationId);
    UpdateOperations<Organization> organizationUpdateOperations = morphiaDatastoreProvider.getDatastore()
        .createUpdateOperations(Organization.class);
    organizationUpdateOperations.removeAll("datasetNames", datasetName);
    UpdateResults updateResults = morphiaDatastoreProvider.getDatastore()
        .update(query, organizationUpdateOperations);
    LOGGER.info("DatasetName '{}' removed from Organization's '{}' datasetNames. (UpdateResults: {})",
        datasetName, organizationId, updateResults.getUpdatedCount());

  }

  public void deleteByOrganizationId(String organizationId) {
    Query<Organization> query = morphiaDatastoreProvider.getDatastore().createQuery(Organization.class);
    query.field(ORGANIZATION_ID_FIELD).equal(organizationId);
    WriteResult delete = morphiaDatastoreProvider.getDatastore().delete(query);
    LOGGER.info("Organization '{}' deleted from Mongo. (WriteResult: {})", organizationId,
            delete.getN());
  }

  public List<Organization> getAllOrganizations(String nextPage) {
    Query<Organization> query = morphiaDatastoreProvider.getDatastore().createQuery(Organization.class);
    query.order("_id");
    if (StringUtils.isNotEmpty(nextPage)) {
      query.field("_id").greaterThan(new ObjectId(nextPage));
    }
    return query.asList(new FindOptions().limit(organizationsPerRequest));
  }

  public List<Organization> getAllOrganizationsByCountry(Country country, String nextPage) {
    Query<Organization> query = morphiaDatastoreProvider.getDatastore().createQuery(Organization.class);
    query.field(COUNTRY).equal(country).order("_id");
    if (StringUtils.isNotEmpty(nextPage)) {
      query.field("_id").greaterThan(new ObjectId(nextPage));
    }
    return query.asList(new FindOptions().limit(organizationsPerRequest));
  }


  public List<Organization> getAllOrganizationsByOrganizationRole(
      List<OrganizationRole> organizationRoles, String nextPage) {
    Query<Organization> query = morphiaDatastoreProvider.getDatastore().createQuery(Organization.class);
    query.field(ORGANIZATION_ROLES)
        .hasAnyOf(organizationRoles).order("_id");
    if (StringUtils.isNotEmpty(nextPage)) {
      query.field("_id").greaterThan(new ObjectId(nextPage));
    }
    return query.asList(new FindOptions().limit(organizationsPerRequest));
  }

  public Organization getOrganizationByOrganizationId(String organizationId) {
    return morphiaDatastoreProvider.getDatastore().find(Organization.class).field(ORGANIZATION_ID_FIELD)
        .equal(organizationId)
        .get();
  }


  public boolean existsOrganizationByOrganizationId(String organizationId) {
    return morphiaDatastoreProvider.getDatastore().find(Organization.class).field("organizationId").equal(organizationId)
        .project("_id", true).get() != null;
  }

  public Organization getOrganizationOptInIIIFByOrganizationId(String organizationId) {
    Query<Organization> query = morphiaDatastoreProvider.getDatastore().createQuery(Organization.class);
    query.field(ORGANIZATION_ID_FIELD).equal(organizationId).project("optInIIIF", true);
    return query.get();
  }

  public int getOrganizationsPerRequest() {
    return organizationsPerRequest;
  }

  public void setOrganizationsPerRequest(int organizationsPerRequest) {
    this.organizationsPerRequest = organizationsPerRequest;
  }
}
