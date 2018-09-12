package org.immregistries.mqe.validator.engine.rules.vaccination;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
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

public class VaccineCvxUseIsValidTester {

  private VaccinationCvxUseIsValid rule = new VaccinationCvxUseIsValid();

  // Parts required for the test
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqeVaccination v = new MqeVaccination();
  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

  private static final Logger logger = LoggerFactory.getLogger(VaccinationCvxUseIsValid.class);

  /**
   * Sets up the objects needed for the test.
   */
  @Before
  public void setUpTheObjects() {
    mh.setMessageDate(new Date());
    mr.setMessageHeader(mh);
    mr.getVaccinations().add(v);
    v.setAdminCvxCode("143");
  

  }

  /**
   * Test the rule with a null type code.
   */
  @Test
  public void testRuleValid() {
	Calendar cal = Calendar.getInstance(); 
	cal.set(Calendar.YEAR, 2013);
	v.setAdminDateString(dateFormat.format(cal.getTime()));
	v.setAdminDate(cal.getTime());
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.warn("Detections: " + r.getValidationDetections().toString());
    assertEquals(0, r.getValidationDetections().size());
  }

  /**
   * Test the rule with a null type code.
   */
  @Test
  public void testRuleNotExpected() {
	Calendar cal = Calendar.getInstance(); 
	cal.set(Calendar.YEAR, 2010);
	v.setAdminDateString(dateFormat.format(cal.getTime()));
	v.setAdminDate(cal.getTime());
	
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationCptCodeIsInvalidForDateAdministered,
        r.getValidationDetections().get(0).getDetection());
  }
  /**
   * Test the rule with a null type code.
   */
  @Test
  public void testRuleNotBefore() {
	Calendar cal = Calendar.getInstance(); 
	cal.set(Calendar.YEAR, 2000);
	v.setAdminDateString(dateFormat.format(cal.getTime()));
	v.setAdminDate(cal.getTime());
	
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationCptCodeIsInvalidForDateAdministered,
        r.getValidationDetections().get(0).getDetection());
  }

}

