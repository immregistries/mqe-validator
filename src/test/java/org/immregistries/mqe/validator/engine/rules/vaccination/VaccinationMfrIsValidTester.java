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

public class VaccinationMfrIsValidTester {

  private VaccinationMfrIsValid rule = new VaccinationMfrIsValid();

  // Parts required for the test
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqeVaccination v = new MqeVaccination();
  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

  private static final Logger logger = LoggerFactory.getLogger(VaccinationMfrIsValidTester.class);

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
  public void testValidMfr() {
	v.setManufacturerCode("ZLB");
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(0, r.getValidationDetections().size());
    assertEquals("Should pass", true, r.isRulePassed());
  }
  
  @Test
  public void testForNoMfg() {
	v.setManufacturerCode(" ");
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationManufacturerCodeIsMissing,
        r.getValidationDetections().get(0).getDetection());

    v.setManufacturerCode(null);
    r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationManufacturerCodeIsMissing,
        r.getValidationDetections().get(0).getDetection());
    
    v.setManufacturerCode("3424323");
    r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationManufacturerCodeIsUnrecognized,
        r.getValidationDetections().get(0).getDetection());
  }
  
  @Test
  public void testAdminDatesForMfr() {
	  /*
	   *        <not-expected-after>20100528</not-expected-after>
                <not-after>20100528</not-after>
	   */
	v.setManufacturerCode("WA"); 
	
	Calendar adminDate = Calendar.getInstance();
	adminDate.set(2009, 01, 01);
	v.setAdminDateString(dateFormat.format(adminDate.getTime()));
	v.setAdminDate(adminDate.getTime());
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(0, r.getValidationDetections().size());
    assertEquals("Should pass", true, r.isRulePassed());
    
	adminDate.set(2019, 01, 01);
	v.setAdminDateString(dateFormat.format(adminDate.getTime()));
	v.setAdminDate(adminDate.getTime());
    r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(2, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationManufacturerCodeIsInvalidForDateAdministered,
            r.getValidationDetections().get(0).getDetection());
    assertEquals(Detection.VaccinationManufacturerCodeIsUnexpectedForDateAdministered,
            r.getValidationDetections().get(0).getDetection());
  }
  
}

