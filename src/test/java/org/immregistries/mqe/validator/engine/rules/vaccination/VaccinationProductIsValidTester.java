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

public class VaccinationProductIsValidTester {

  private VaccinationProductIsValid rule = new VaccinationProductIsValid();

  // Parts required for the test
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqeVaccination v = new MqeVaccination();
  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

  private static final Logger logger = LoggerFactory.getLogger(VaccinationProductIsValidTester.class);

  /**
   * Sets up the objects needed for the test.
   */
  @Before
  public void setUpTheObjects() {
    mh.setMessageDate(new Date());
    mr.setMessageHeader(mh);
    mr.getVaccinations().add(v);
    v.setAdministered(true);
    v.setProduct("BIOTHRAX");
    v.setAdminCvxCode("24");
  }

  @Test
  public void testValidProduct() {
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(0, r.getValidationDetections().size());
    assertEquals("This is passing", true, r.isRulePassed());
  }
  
  @Test
  public void testNoProduct() {
	v.setProduct(null);
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationProductIsMissing,
        r.getValidationDetections().get(0).getDetection());
    
    v.setProduct(" ");
    r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationProductIsMissing,
        r.getValidationDetections().get(0).getDetection());
  }
  
  @Test
  public void testWrongProduct() {
	v.setProduct("dfjdkal");
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationProductIsUnrecognized,
        r.getValidationDetections().get(0).getDetection());
  }
  
  @Test
  public void testDatesForProduct() {
	  /*
	   *  <not-before>20110101</not-before>
          <not-expected-before>20110316</not-expected-before>
	   */
			  
	v.setProduct("ADENOVIRUS TYPES 4 AND 7");
	v.setAdminCvxCode("143");
	Calendar adminDate = Calendar.getInstance();
	adminDate.set(2010, 01, 01);
	v.setAdminDateString(dateFormat.format(adminDate.getTime()));
	v.setAdminDate(adminDate.getTime());
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationProductIsInvalidForDateAdministered,
        r.getValidationDetections().get(0).getDetection());
    
	adminDate.set(2011, 02, 01);
	v.setAdminDateString(dateFormat.format(adminDate.getTime()));
	v.setAdminDate(adminDate.getTime());
    r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationProductIsUnexpectedForDateAdministered,
        r.getValidationDetections().get(0).getDetection());
  }
}

