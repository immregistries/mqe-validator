package org.immregistries.mqe.validator.engine.rules.nextofkin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeAddress;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeNextOfKin;
import org.immregistries.mqe.vxu.MqePatient;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NextOfKinAddressIsSameAsPatientAddressTester {

  private NextOfKinAddressIsSameAsPatientAddress nokRule = new NextOfKinAddressIsSameAsPatientAddress();

  // Parts required for the test
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqeNextOfKin nok = new MqeNextOfKin();
  private MqePatient p = new MqePatient();

  private static final Logger logger = LoggerFactory.getLogger(NextOfKinAddressIsSameAsPatientAddressTester.class);

  private MqeAddress addr1;
  private MqeAddress addr2;
  /**
   * Sets up the objects needed for the test. Next of kin and patient need the same address.
   */
  @Before
  public void setUpTheObjects() {
    addr1 = new MqeAddress();
    addr1.setStreet("233 Cherokee Ln");
    addr1.setStreet2("Apt 106");
    addr1.setCity("Flint");
    addr1.setStateCode("MI");
    addr1.setZip("49501");
    addr1.setCountryCode("USA");
    addr1.setCountyParishCode("73");
    addr1.setTypeCode("P");

    addr2 = new MqeAddress();
    addr2.setStreet("123 Main St.");
    addr2.setStreet2("Apt 106");
    addr2.setCity("Flint");
    addr2.setStateCode("MI");
    addr2.setZip("49501");
    addr2.setCountryCode("USA");
    addr2.setCountyParishCode("73");
    addr2.setTypeCode("P");

    nok.setAddress(addr1);

    List<MqeNextOfKin> noks = new ArrayList<>();
    noks.add(nok);
    mr.setNextOfKins(noks);

    p.getPatientAddressList().add(new MqeAddress(addr2));

    mh.setMessageDate(new Date());
    mr.setMessageHeader(mh);
    mr.setPatient(p);
  }

  /**
   * Test with address different from patient address. ("different" is defined as city, state,
   * street, or street 2 being different)
   */
  @Test
  public void testRuleDifferentFromPatient() {
    ValidationRuleResult r = nokRule.executeRule(nok, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.NextOfKinAddressIsDifferentFromPatientAddress,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test with address different from patient address. ("different" is defined as city, state,
   * street, or street 2 being different)
   */
  @Test
  public void testRuleSameAsPatient() {
    //Set up for this rule:
    p.getPatientAddressList().remove(0);
    p.getPatientAddressList().add(addr1);
    nok.setAddress(addr1);

    ValidationRuleResult r = nokRule.executeRule(nok, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(0, r.getValidationDetections().size());
    assertEquals("Should pass!", true, r.isRulePassed());
  }

}
