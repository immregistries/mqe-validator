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

public class VaccinationBodyRouteAndSiteAreValidTester {

  private VaccinationBodyRouteAndSiteAreValid rule = new VaccinationBodyRouteAndSiteAreValid();

  // Parts required for the test
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqeVaccination v = new MqeVaccination();

  private static final Logger logger = LoggerFactory.getLogger(VaccinationBodyRouteAndSiteAreValidTester.class);

  /**
   * Sets up the objects needed for the test.
   */
  @Before
  public void setUpTheObjects() {
    mh.setMessageDate(new Date());
    mr.setMessageHeader(mh);
    mr.getVaccinations().add(v);
    v.setAdministered(true);    
    v.setBodyRouteCode("SC");
    v.setBodySiteCode("LT");
    v.setAdminCvxCode("21");
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
  public void testRuleRouteFail() {
	v.setBodyRouteCode("IN");
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationBodyRouteIsDeprecated,
            r.getValidationDetections().get(0).getDetection());
    
	v.setBodyRouteCode("TD1");
    r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationBodyRouteIsUnrecognized,
            r.getValidationDetections().get(0).getDetection());
    
	v.setBodyRouteCode(null);
    r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationBodyRouteIsMissing,
            r.getValidationDetections().get(0).getDetection());
    
    // test with vaccines
    
    // Varicella Route->SC Site->LT RT
    v.setBodyRouteCode("IM");
    r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationBodyRouteIsInvalidForVaccineIndicated,
            r.getValidationDetections().get(0).getDetection());
  }

  @Test
  public void testRuleSiteFail() {    
	v.setBodySiteCode("TD1");
	ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationBodySiteIsUnrecognized,
            r.getValidationDetections().get(0).getDetection());
    
	v.setBodySiteCode(null);
    r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationBodySiteIsMissing,
            r.getValidationDetections().get(0).getDetection());
    
    // Varicella Route->SC Site->LT RT
    v.setBodySiteCode("BN");
    r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationBodySiteIsInvalidForVaccineIndicated,
            r.getValidationDetections().get(0).getDetection());
  }
}

