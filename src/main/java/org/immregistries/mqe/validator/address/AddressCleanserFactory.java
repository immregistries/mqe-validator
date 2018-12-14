package org.immregistries.mqe.validator.address;

import org.immregistries.mqe.validator.ValidatorProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The purpose of this is to provide a point to inject an address cleansing system into the MQE
 * process.
 *
 * @author Josh Hull
 */
public enum AddressCleanserFactory {
  INSTANCE;
  private static final Logger logger = LoggerFactory.getLogger(AddressCleanserFactory.class);
  private ValidatorProperties props = ValidatorProperties.INSTANCE;

  public AddressCleanser getAddressCleanser() {
    if (props.isAddressCleanserEnabled()) {
      if (logger.isInfoEnabled()) {
        logger.info("Address Cleansing is enabled, using SmartyStreets as the cleansing provider");
      }
      return AddressCleanserSmartyStreets.INSTANCE;
    } else {
      return AddressCleanserDefault.INSTANCE;
    }
  }
}
