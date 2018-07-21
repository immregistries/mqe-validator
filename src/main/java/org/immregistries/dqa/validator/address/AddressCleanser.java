package org.immregistries.dqa.validator.address;

import java.util.Map;
import org.immregistries.dqa.vxu.DqaAddress;

/**
 * The purpose of this is to provide a point to inject an address cleansing system into the DQA
 * process.
 *
 * @author Josh Hull
 */
public interface AddressCleanser {

  /*
   * Categories for what we want to say about the address
   * 
   * PASS (Info, warning) FAIL (error)
   */
  public Map<DqaAddress, DqaAddress> cleanThese(DqaAddress ... list);
}