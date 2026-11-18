package org.immregistries.mqe.validator.engine.rules.patient;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.openimmunizationsoftware.cdsi.core.data.DataModel;
import org.openimmunizationsoftware.cdsi.core.data.DataModelLoader;
import org.openimmunizationsoftware.cdsi.core.domain.Observation;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Checks that the bundled supporting data populates the DataModel observation map(s).
 */
public class DataModelObservationMapTester {

  private static final Logger logger =
      LoggerFactory.getLogger(DataModelObservationMapTester.class);

  @Test
  public void observationMapIsPopulated() throws Exception {
    DataModel dataModel = DataModelLoader.createDataModel(DataModelLoader.listBundledSupportingDataZipNames().get(0));

    Map<String, Observation> observationMap = dataModel.getObservationMap();
    assertNotNull("observationMap should not be null", observationMap);
    assertTrue("observationMap should not be empty", !observationMap.isEmpty());

    logger.info("Observation map entries (" + observationMap.size() + "):");
    for (Map.Entry<String, Observation> e : observationMap.entrySet()) {
      logger.info("  " + e.getKey() + " -> " + e.getValue().getObservationTitle());
      System.out.println(e.getKey());
    }

    // TODO: fill in expected observation codes / assertions.
  }
}