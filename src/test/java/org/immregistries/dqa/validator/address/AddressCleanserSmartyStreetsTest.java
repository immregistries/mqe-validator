package org.immregistries.dqa.validator.address;

import static org.junit.Assert.assertEquals;

import com.smartystreets.api.us_street.Candidate;
import java.util.List;
import org.immregistries.dqa.vxu.DqaAddress;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddressCleanserSmartyStreetsTest {
  private static final Logger logger = LoggerFactory.getLogger(AddressCleanserSmartyStreetsTest.class);
  AddressCleanserSmartyStreets ssac = AddressCleanserSmartyStreets.INSTANCE;

  @Test
  public void test1() {
    DqaAddress d = new DqaAddress();
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
  }
}
