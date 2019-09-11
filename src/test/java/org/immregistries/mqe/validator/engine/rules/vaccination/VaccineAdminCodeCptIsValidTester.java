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

public class VaccineAdminCodeCptIsValidTester {

  private VaccinationAdminCodeCptIsValid rule = new VaccinationAdminCodeCptIsValid();

  // Parts required for the test
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqeVaccination v = new MqeVaccination();
  
  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

  private static final Logger logger = LoggerFactory.getLogger(VaccinationCptIsValid.class);

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
   * Test the vaccinationCptIsValid with invalid.
   */
  @Test
  public void testRuleInvalid() {
    v.setAdminCptCode("01010");
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationCptCodeIsUnrecognized,
            r.getValidationDetections().get(0).getDetection());

    Calendar adminDate = Calendar.getInstance();
    adminDate.set(1970, 1, 1);
    v.setAdminCptCode("90585");
    v.setAdminDateString(dateFormat.format(adminDate.getTime()));
    r = rule.executeRule(v, mr);
    logger.warn("Detections: " + r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationCptCodeIsInvalidForDateAdministered,
            r.getValidationDetections().get(0).getDetection());
  }

  @Test
  public void testRuleValidValue() {
    v.setAdminCptCode("90476");
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.warn("Detections: " + r.getValidationDetections().toString());
    assertEquals(0, r.getValidationDetections().size());
    
    Calendar adminDate = Calendar.getInstance();
    adminDate.set(2000, 1, 1);
    v.setAdminCptCode("90585");
    v.setAdminDateString(dateFormat.format(adminDate.getTime()));
    r = rule.executeRule(v, mr);
    logger.warn("Detections: " + r.getValidationDetections().toString());
    assertEquals(0, r.getValidationDetections().size());
  }


}

