package org.immregistries.mqe.validator.engine.rules.vaccination;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.VaccinationVIS;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VaccineVisIsPresentTester {

  private VaccinationVisIsPresent rule = new VaccinationVisIsPresent();

  // Parts required for the test
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqeVaccination v = new MqeVaccination();
  private VaccinationVIS vis = new VaccinationVIS();

  private static final Logger logger = LoggerFactory.getLogger(VaccineVisIsPresentTester.class);

  /**
   * Sets up the objects needed for the test.
   */
  @Before
  public void setUpTheObjects() {
    mh.setMessageDate(new Date());
    mr.setMessageHeader(mh);
    mr.getVaccinations().add(v);
    v.setVaccinationVis(vis);
    v.setAdministered(true);
  }

  /**
   * Test the basic rule with a valid type code.
   * (should be true)
   */
  @Test
  public void testFullVis() {
	vis.setDocumentCode("253088698300003511070517");
	vis.setPublishedDateString("20170101");
	vis.setCvxCode("107");
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertTrue(r.isRulePassed());
  }
  
  @Test
  public void testPartialVis() {
	vis.setDocumentCode("253088698300003511070517");
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertTrue(r.isRulePassed());
  }

  @Test
  public void testNullVis() {
	v.setVaccinationVis(null);
	ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationVisIsMissing,
        r.getValidationDetections().get(0).getDetection());
  }
  
  @Test
  public void testEmptyVis() {
	vis.setDocumentCode("");
	vis.setPublishedDateString("");
	vis.setCvxCode("");
	ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationVisIsMissing,
        r.getValidationDetections().get(0).getDetection());
  }

}

