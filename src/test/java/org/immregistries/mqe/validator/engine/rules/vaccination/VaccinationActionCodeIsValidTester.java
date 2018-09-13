package org.immregistries.mqe.validator.engine.rules.vaccination;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.hl7.Observation;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Allison on 5/9/2017.
 */
public class VaccinationActionCodeIsValidTester {

  private VaccinationActionCodeIsValid rule = new VaccinationActionCodeIsValid();

  // Parts required for the test
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqeVaccination v = new MqeVaccination();

  private static final Logger logger = LoggerFactory
      .getLogger(VaccinationActionCodeIsValidTester.class);

  /**
   * Sets up the objects needed for the test.
   */
  @Before
  public void setUpTheObjects() {
    mh.setMessageDate(new Date());
    mr.setMessageHeader(mh);
    mr.getVaccinations().add(v);
  }

  /**
   * Test the basic rule with a valid type code.
   * (should be true)
   */
  @Test
  public void testValid() {
	v.setActionCode(MqeVaccination.ACTION_CODE_ADD);
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertTrue(r.isRulePassed());
    assertEquals(Detection.VaccinationActionCodeIsValuedAsAdd,
            r.getValidationDetections().get(0).getDetection());
    assertEquals(Detection.VaccinationActionCodeIsValuedAsAddOrUpdate,
            r.getValidationDetections().get(1).getDetection());
    
	v.setActionCode(MqeVaccination.ACTION_CODE_DELETE);
    r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertTrue(r.isRulePassed());
    assertEquals(Detection.VaccinationActionCodeIsValuedAsDelete,
            r.getValidationDetections().get(0).getDetection());
    
	v.setActionCode(MqeVaccination.ACTION_CODE_UPDATE);
    r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertTrue(r.isRulePassed());
    assertEquals(Detection.VaccinationActionCodeIsValuedAsUpdate,
            r.getValidationDetections().get(0).getDetection());
    assertEquals(Detection.VaccinationActionCodeIsValuedAsAddOrUpdate,
            r.getValidationDetections().get(1).getDetection());
  }

  @Test
  public void testInvalid() {
	v.setActionCode("#");
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationActionCodeIsUnrecognized,
        r.getValidationDetections().get(0).getDetection());
    
	v.setActionCode(null);
    r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationActionCodeIsMissing,
        r.getValidationDetections().get(0).getDetection());
  }
}