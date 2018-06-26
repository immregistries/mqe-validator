package org.immregistries.mqe.validator.address;

import java.util.Map;
import org.immregistries.mqe.vxu.MqeAddress;

/**
 * The purpose of this is to provide a point to inject an address cleansing system into the MQE
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
  public Map<MqeAddress, MqeAddress> cleanThese(MqeAddress ... list);
}
