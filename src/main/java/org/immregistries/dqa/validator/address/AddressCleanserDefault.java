package org.immregistries.dqa.validator.address;

import java.util.HashMap;
import java.util.Map;
import org.immregistries.dqa.vxu.DqaAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This does nothing except provide a default implementation.
 *
 * @author Josh Hull
 */
public enum AddressCleanserDefault implements AddressCleanser {
  INSTANCE;
  private static final Logger LOGGER = LoggerFactory.getLogger(AddressCleanserDefault.class);

  public Map<DqaAddress, DqaAddress> cleanThese(DqaAddress ... list) {
    LOGGER.info("AddressCleansing not implemented. ");

    Map<DqaAddress, DqaAddress> results = new HashMap<>();

    for (DqaAddress in : list) {
      in.setClean(true);
      in.setCleansingResultCode("NOTCLEANSED");
      in.setCleansingAttempted(false);
      results.put(in, in);
    }

    return results;
  }
}
