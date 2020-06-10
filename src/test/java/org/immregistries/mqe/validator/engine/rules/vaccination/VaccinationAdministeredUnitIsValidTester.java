package org.immregistries.mqe.validator.engine.rules.vaccination;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Date;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VaccinationAdministeredUnitIsValidTester {

  private VaccinationAdministeredUnitIsValid rule = new VaccinationAdministeredUnitIsValid();

  // Parts required for the test
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqeVaccination v = new MqeVaccination();

  private static final Logger logger = LoggerFactory.getLogger(VaccinationAdministeredUnitIsValidTester.class);

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

  /**
   * Test the basic rule with a valid type code.
   * (should be true)
   */
  @Test
  public void testPass() {
	v.setAmountUnitCode("mL");
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(0, r.getValidationDetections().size());
    assertTrue(r.isRulePassed());
  }


  @Test
  public void testRuleFail() {
	v.setAmountUnitCode("CC");
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationAdministeredUnitIsDeprecated,
            r.getValidationDetections().get(0).getDetection());
    
	v.setAmountUnitCode("123");
    r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationAdministeredUnitIsUnrecognized,
            r.getValidationDetections().get(0).getDetection());
    
	v.setAmountUnitCode(null);
    r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationAdministeredUnitIsMissing,
            r.getValidationDetections().get(0).getDetection());
  }


}

