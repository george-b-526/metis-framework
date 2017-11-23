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
import eu.europeana.metis.core.dataset.Dataset;
import eu.europeana.metis.core.mongo.MorphiaDatastoreProvider;
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
 * DAO for the datasets Created by ymamakis on 2/17/16.
 */
@Repository
public class DatasetDao implements MetisDao<Dataset, String> {

  private static final Logger LOGGER = LoggerFactory.getLogger(DatasetDao.class);
  private static final String DATASET_NAME = "datasetName";
  private int datasetsPerRequest = 5;

  private final MorphiaDatastoreProvider morphiaDatastoreProvider;

  @Autowired
  public DatasetDao(MorphiaDatastoreProvider morphiaDatastoreProvider) {
    this.morphiaDatastoreProvider = morphiaDatastoreProvider;
  }

  @Override
  public String create(Dataset dataset) {
    Key<Dataset> datasetKey = morphiaDatastoreProvider.getDatastore().save(dataset);
    LOGGER.debug(
        "Dataset '{}' created with OrganizationId '{}' and Provider '{}' and Description '{}' in Mongo",
        dataset.getDatasetName(), dataset.getOrganizationId(), dataset.getDataProvider(),
        dataset.getDescription());
    return datasetKey.getId().toString();
  }

  @Override
  public String update(Dataset dataset) {
    UpdateOperations<Dataset> ops = morphiaDatastoreProvider.getDatastore().createUpdateOperations(Dataset.class);
    Query<Dataset> q = morphiaDatastoreProvider.getDatastore().find(Dataset.class)
        .filter(DATASET_NAME, dataset.getDatasetName());
    ops.set("country", dataset.getCountry());
    ops.set("dataProvider", dataset.getDataProvider());
    ops.set("description", dataset.getDescription());
    if (dataset.getDqas() != null) {
      ops.set("ecloudDatasetId", dataset.getEcloudDatasetId());
    } else {
      ops.unset("ecloudDatasetId");
    }
    if (dataset.getDqas() != null) {
      ops.set("dqas", dataset.getDqas());
    } else {
      ops.unset("dqas");
    }
    if (dataset.getFirstPublished() != null) {
      ops.set("firstPublished", dataset.getFirstPublished());
    } else {
      ops.unset("firstPublished");
    }
    if (dataset.getHarvestedAt() != null) {
      ops.set("harvestedAt", dataset.getHarvestedAt());
    } else {
      ops.unset("harvestedAt");
    }
    ops.set("language", dataset.getLanguage());
    if (dataset.getLastPublished() != null) {
      ops.set("lastPublished", dataset.getLastPublished());
    } else {
      ops.unset("lastPublished");
    }
    ops.set("harvestingMetadata", dataset.getHarvestingMetadata());
    ops.set("notes", dataset.getNotes());
    ops.set("publishedRecords", dataset.getPublishedRecords());
    ops.set("submittedRecords", dataset.getSubmittedRecords());
    if (dataset.getReplacedBy() != null) {
      ops.set("replacedBy", dataset.getReplacedBy());
    } else {
      ops.unset("replacedBy");
    }

    if (dataset.getSources() != null) {
      ops.set("sources", dataset.getSources());
    } else {
      ops.unset("sources");
    }
    if (dataset.getSubjects() != null) {
      ops.set("subjects", dataset.getSubjects());
    } else {
      ops.unset("subjects");
    }
    if (dataset.getSubmissionDate() != null) {
      ops.set("submissionDate", dataset.getSubmissionDate());
    } else {
      ops.unset("submissionDate");
    }

    ops.set("updatedDate", dataset.getUpdatedDate());
    ops.set("datasetStatus", dataset.getDatasetStatus());
    ops.set("accepted", dataset.isAccepted());
    ops.set("deaSigned", dataset.isDeaSigned());
    UpdateResults updateResults = morphiaDatastoreProvider.getDatastore().update(q, ops);

    LOGGER.debug(
        "Dataset '{}' updated with Provider '{}' and Description '{}' in Mongo",
        dataset.getDatasetName(), dataset.getDataProvider(), dataset.getDescription());
    return String.valueOf(updateResults.getUpdatedCount());
  }

  @Override
  public Dataset getById(String id) {
    return morphiaDatastoreProvider.getDatastore().find(Dataset.class).filter("_id", new ObjectId(id)).get();
  }

  @Override
  public boolean delete(Dataset dataset) {
    morphiaDatastoreProvider.getDatastore().delete(
        morphiaDatastoreProvider.getDatastore().createQuery(Dataset.class).field(DATASET_NAME)
            .equal(dataset.getDatasetName()));
    LOGGER
        .debug("Dataset '{}' deleted with organizationId '{}' from Mongo", dataset.getDatasetName(),
            dataset.getOrganizationId());
    return true;
  }

  public void updateDatasetName(String datasetName, String newDatasetName) {
    UpdateOperations<Dataset> datasetUpdateOperations = morphiaDatastoreProvider.getDatastore()
        .createUpdateOperations(Dataset.class);
    Query<Dataset> query = morphiaDatastoreProvider.getDatastore().find(Dataset.class)
        .filter(DATASET_NAME, datasetName);
    datasetUpdateOperations.set(DATASET_NAME, newDatasetName);
    UpdateResults updateResults = morphiaDatastoreProvider
        .getDatastore().update(query, datasetUpdateOperations);
    LOGGER.debug("Dataset with datasetName '{}' renamed to '{}'. (UpdateResults: {})", datasetName,
        newDatasetName, updateResults.getUpdatedCount());
  }

  public boolean deleteDatasetByDatasetName(String datasetName) {
    Query<Dataset> query = morphiaDatastoreProvider.getDatastore().createQuery(Dataset.class);
    query.field(DATASET_NAME).equal(datasetName);
    WriteResult delete = morphiaDatastoreProvider.getDatastore().delete(query);
    LOGGER.debug("Dataset '{}' deleted from Mongo", datasetName);
    return delete.getN() == 1;
  }

  public Dataset getDatasetByDatasetName(String datasetName) {
    return morphiaDatastoreProvider.getDatastore().find(Dataset.class).field(DATASET_NAME).equal(datasetName)
        .get();
  }

  public boolean existsDatasetByDatasetName(String datasetName) {
    return morphiaDatastoreProvider.getDatastore().find(Dataset.class).field(DATASET_NAME).equal(datasetName)
        .project("_id", true).get() != null;
  }

  public List<Dataset> getAllDatasetsByDataProvider(String dataProvider, String nextPage) {
    Query<Dataset> query = morphiaDatastoreProvider.getDatastore().createQuery(Dataset.class);
    query.field("dataProvider").equal(dataProvider).order("_id");
    if (StringUtils.isNotEmpty(nextPage)) {
      query.field("_id").greaterThan(new ObjectId(nextPage));
    }
    return query.asList(new FindOptions().limit(datasetsPerRequest));
  }

  public List<Dataset> getAllDatasetsByOrganizationId(String organizationId, String nextPage) {
    Query<Dataset> query = morphiaDatastoreProvider.getDatastore().createQuery(Dataset.class);
    query.field("organizationId").equal(organizationId).order("_id");
    if (StringUtils.isNotEmpty(nextPage)) {
      query.field("_id").greaterThan(new ObjectId(nextPage));
    }
    return query.asList(new FindOptions().limit(datasetsPerRequest));
  }

  public int getDatasetsPerRequest() {
    return datasetsPerRequest;
  }

  public void setDatasetsPerRequest(int datasetsPerRequest) {
    this.datasetsPerRequest = datasetsPerRequest;
  }
}
