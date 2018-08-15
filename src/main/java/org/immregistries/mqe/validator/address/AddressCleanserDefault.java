package org.immregistries.mqe.validator.address;

import java.util.HashMap;
import java.util.Map;
import org.immregistries.mqe.vxu.MqeAddress;
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

  public Map<MqeAddress, MqeAddress> cleanThese(MqeAddress ... list) {
    LOGGER.info("AddressCleansing not enabled. ");

    Map<MqeAddress, MqeAddress> results = new HashMap<>();

    for (MqeAddress in : list) {
      in.setClean(true);
      in.setCleansingResultCode("NOTCLEANSED");
      in.setCleansingAttempted(false);
      results.put(in, in);
    }

    return results;
  }
}
