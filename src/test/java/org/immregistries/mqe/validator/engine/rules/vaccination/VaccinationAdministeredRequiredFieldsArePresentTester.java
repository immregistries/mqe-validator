package org.immregistries.mqe.validator.engine.rules.vaccination;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Calendar;
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

public class VaccinationAdministeredRequiredFieldsArePresentTester {

  private VaccinationAdministeredRequiredFieldsArePresent rule = new VaccinationAdministeredRequiredFieldsArePresent();

  // Parts required for the test
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqeVaccination v = new MqeVaccination();

  private static final Logger logger = LoggerFactory.getLogger(VaccinationAdministeredRequiredFieldsArePresentTester.class);

  /**
   * Sets up the objects needed for the test.
   */
  @Before
  public void setUpTheObjects() {
    mh.setMessageDate(new Date());
    mr.setMessageHeader(mh);
    mr.getVaccinations().add(v);
    v.setAdministered(true);
    
    v.setFacilityName("Facility Name");
    v.setExpirationDate(Calendar.getInstance().getTime());
    v.setLotNumber("A12345");
  }

  /**
   * Test the basic rule with a valid type code.
   * (should be true)
   */
  @Test
  public void testPass() {
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(0, r.getValidationDetections().size());
    assertTrue(r.isRulePassed());
  }


  @Test
  public void testRuleFacilityNameFail() {
	v.setFacilityName(null);
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationFacilityNameIsMissing,
            r.getValidationDetections().get(0).getDetection());
  }

  @Test
  public void testRuleLotExpirationFail() {
	v.setExpirationDate(null);
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationLotExpirationDateIsMissing,
            r.getValidationDetections().get(0).getDetection());
  }
  
}

