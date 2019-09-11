package org.immregistries.mqe.validator.address;

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.immregistries.mqe.validator.ValidatorProperties;
import org.immregistries.mqe.vxu.MqeAddress;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.smartystreets.api.us_street.Candidate;

public class AddressCleanserSmartyStreetsTest {

  private static final Logger logger = LoggerFactory
      .getLogger(AddressCleanserSmartyStreetsTest.class);
  AddressCleanserSmartyStreets ssac = AddressCleanserSmartyStreets.INSTANCE;
  ValidatorProperties props = ValidatorProperties.INSTANCE;

  @Test
  public void test1() {
    if (props.isAddressCleanserEnabled()
        && props.getSsApiAuthId() != null
        && props.getSsApiAuthToken() != null) {
      MqeAddress d = new MqeAddress();
      d.setCity("Lansing");
      d.setStateCode("MI");
      d.setStreet("1110 Main");
      d.setZip("48912");
      List<Candidate> cList = ssac.getSSResults(d);
      assertEquals("cList should have one entry", 1, cList.size());
      Candidate c = cList.get(0);
      logger.warn("dl1: " + c.getDeliveryLine1());
      logger.warn("active: " + c.getAnalysis().getActive());
      logger.warn("dpvMatchCode: " + c.getAnalysis().getDpvMatchCode());
    } else {
      logger.error("NO Address Cleansing tests executed.  Cleanser disabled");
    }
  }
}
