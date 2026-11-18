package org.immregistries.mqe.validator.engine.rules.patient;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openimmunizationsoftware.cdsi.core.data.DataModelLoader;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Checks that the bundled supporting-data zip files are available at runtime and
 * prints their names so the expected list can be filled in below.
 */
public class SupportingDataZipAvailabilityTester {

  private static final Logger logger =
      LoggerFactory.getLogger(SupportingDataZipAvailabilityTester.class);

  // TODO: fill in the expected bundled supporting-data zip names here.
  // private static final List<String> EXPECTED_ZIP_NAMES = Arrays.asList(...);

  @Test
  public void bundledSupportingDataZipsAreAvailable() throws Exception {
    List<String> names = DataModelLoader.listBundledSupportingDataZipNames();
    assertNotNull("listBundledSupportingDataZipNames() should not return null", names);
    assertTrue("at least one bundled supporting-data zip should be available", !names.isEmpty());

    logger.info("Bundled supporting-data zip names (" + names.size() + "):");
    for (String name : names) {
      logger.info("  " + name);
      System.out.println(name);
    }

    // TODO: replace with exact expected-names assertion once EXPECTED_ZIP_NAMES is filled in.
    // assertEquals(new HashSet<>(EXPECTED_ZIP_NAMES), new HashSet<>(names));
  }
}