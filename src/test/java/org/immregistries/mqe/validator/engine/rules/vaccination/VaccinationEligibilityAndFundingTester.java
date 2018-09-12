package org.immregistries.mqe.validator.engine.rules.vaccination;

import static org.junit.Assert.assertEquals;
import java.util.Date;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VaccinationEligibilityAndFundingTester {

  private VaccinationFundingAndEligibilityConflict vaccFundingAndEligibilityConflict =
      new VaccinationFundingAndEligibilityConflict();

  // Parts required for the test
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqeVaccination v = new MqeVaccination();

  private static final Logger logger = LoggerFactory.getLogger(VaccineProductIsValidTester.class);

  /**
   * Sets up the objects needed for the test.
   */
  @Before
  public void setUpTheObjects() {
    mh.setMessageDate(new Date());
    mr.setMessageHeader(mh);
    mr.getVaccinations().add(v);
    v.setAdministered(true);
  }

  @Test
  public void testBasicPass() {
    v.setFinancialEligibilityCode("V01");
    v.setFundingSourceCode("VXC1");
    ValidationRuleResult r = vaccFundingAndEligibilityConflict.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(0, r.getValidationDetections().size());
    assertEquals("Should pass", true, r.isRulePassed());
  }
  
  @Test
  public void testBasicFail() {
    v.setFinancialEligibilityCode("V01");
    v.setFundingSourceCode("VXC50");
    ValidationRuleResult r = vaccFundingAndEligibilityConflict.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals("Should fail", false, r.isRulePassed());
  }


  
}

