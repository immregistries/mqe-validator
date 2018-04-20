package org.immregistries.dqa.validator.address;

import org.immregistries.dqa.validator.ValidatorProperties;

/**
 * The purpose of this is to provide a point to inject an address cleansing system into the DQA
 * process.
 *
 * @author Josh Hull
 */
public enum AddressCleanserFactory {
  INSTANCE;
  private ValidatorProperties props = ValidatorProperties.INSTANCE;

  public AddressCleanser getAddressCleanser() {
    if (props.isAddressCleanserEnabled()) {
      return AddressCleanserSmartyStreets.INSTANCE;
    } else {
      return AddressCleanserDefault.INSTANCE;
    }
  }
}
