package org.immregistries.mqe.validator.engine.rules.vaccination;

import static org.junit.Assert.assertEquals;
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

public class VaccinationInformationSourceIsValidTester {

  private VaccinationInformationSourceIsValid rule = new VaccinationInformationSourceIsValid();

  // Parts required for the test
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqeVaccination v = new MqeVaccination();

  private static final Logger logger = LoggerFactory.getLogger(VaccinationInformationSourceIsValidTester.class);

  /**
   * Sets up the objects needed for the test.
   */
  @Before
  public void setUpTheObjects() {
    mh.setMessageDate(new Date());
    mr.setMessageHeader(mh);
    mr.getVaccinations().add(v);
    v.setAdministered(true);
    v.setInformationSourceCode("06");
  }

  @Test
  public void testInfoSourceValid() {
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    
    assertEquals("Should pass", true, r.isRulePassed());
  }
  
  @Test
  public void testInfoSourceMissing() {
	v.setInformationSourceCode(null);
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationInformationSourceIsMissing,
        r.getValidationDetections().get(0).getDetection());
    
	v.setInformationSourceCode("  ");
    r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationInformationSourceIsMissing,
        r.getValidationDetections().get(0).getDetection());
  }
  
  @Test
  public void testInfoSourceUnRecognized() {
	v.setInformationSourceCode("ASDF");
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(2, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationInformationSourceIsUnrecognized,
        r.getValidationDetections().get(0).getDetection());
    assertEquals(Detection.VaccinationInformationSourceIsValuedAsHistorical,
    r.getValidationDetections().get(1).getDetection());
  }
  
  @Test
  public void testInfoSourceAdmin() {
	v.setInformationSourceCode("00");
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationInformationSourceIsValuedAsAdministered,
        r.getValidationDetections().get(0).getDetection());
  }
  
  /*
   * compiled.xml has 8 different valid historical codes....the rule is only 
   * considering 01 as historical so the remaining 7 codes fail this test.
   */
  @Test
  public void testInfoSourceHistorical() {
	v.setInformationSourceCode("01");
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationInformationSourceIsValuedAsHistorical,
        r.getValidationDetections().get(0).getDetection());
    
	v.setInformationSourceCode("02");
    r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationInformationSourceIsValuedAsHistorical,
    r.getValidationDetections().get(0).getDetection());
    
	v.setInformationSourceCode("03");
    r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationInformationSourceIsValuedAsHistorical,
    r.getValidationDetections().get(0).getDetection());
    
	v.setInformationSourceCode("04");
    r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationInformationSourceIsValuedAsHistorical,
    r.getValidationDetections().get(0).getDetection());
    
	v.setInformationSourceCode("05");
    r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationInformationSourceIsValuedAsHistorical,
    r.getValidationDetections().get(0).getDetection());
    
	v.setInformationSourceCode("06");
    r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationInformationSourceIsValuedAsHistorical,
    r.getValidationDetections().get(0).getDetection());
    
	v.setInformationSourceCode("07");
    r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationInformationSourceIsValuedAsHistorical,
    r.getValidationDetections().get(0).getDetection());
    
	v.setInformationSourceCode("08");
    r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationInformationSourceIsValuedAsHistorical,
    r.getValidationDetections().get(0).getDetection());
  }
  
}

