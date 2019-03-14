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
import org.immregistries.mqe.vxu.VaccinationVIS;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VaccineVisDatesAreValidTester {

  private VaccinationVisDatesAreValid rule = new VaccinationVisDatesAreValid();

  // Parts required for the test
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqeVaccination v = new MqeVaccination();
  private VaccinationVIS vis = new VaccinationVIS();

  private static final Logger logger = LoggerFactory.getLogger(VaccineVisDatesAreValidTester.class);

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
	vis.setPresentedDateString("20170101");
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertTrue(r.isRulePassed());
  }
  
  @Test
  public void testMissingVisPubDate() {
	vis.setDocumentCode("253088698300003511070517");
	vis.setPresentedDateString("20170101");
	vis.setPublishedDateString(null);
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationVisPublishedDateIsMissing,
            r.getValidationDetections().get(0).getDetection());
  }

  @Test
  public void testInvalidVisPubDate() {
	vis.setDocumentCode("253088698300003511070517");
	vis.setPublishedDateString("abdsc");
	vis.setPresentedDateString("20170101");
	ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationVisPublishedDateIsInvalid,
        r.getValidationDetections().get(0).getDetection());
  }
  
  @Test
  public void testFutureVisPubDate() {
	vis.setDocumentCode("253088698300003511070517");
	vis.setPublishedDateString("20300101");
	vis.setCvxCode("107");
	vis.setPresentedDateString("20170101");
	ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertTrue(r.issuesContainsDetection(Detection.VaccinationVisPublishedDateIsInFuture));
  }
  
  @Test
  public void testMissingVisPresDate() {
	vis.setDocumentCode("253088698300003511070517");
	vis.setPublishedDateString("20170101");
	vis.setCvxCode("107");
	vis.setPresentedDateString(null);
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertTrue(r.issuesContainsDetection(Detection.VaccinationVisPresentedDateIsMissing));
  }

  @Test
  public void testInvalidVisPresDate() {
	vis.setDocumentCode("253088698300003511070517");
	vis.setPublishedDateString("20170101");
	vis.setCvxCode("107");
	vis.setPresentedDateString("20dsf0101");
	ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationVisPresentedDateIsInvalid,
        r.getValidationDetections().get(0).getDetection());
  }
  
  @Test
  public void testVisPresBeforePubDate() {
	vis.setDocumentCode("253088698300003511070517");
	vis.setPublishedDateString("20170101");
	vis.setCvxCode("107");
	vis.setPresentedDateString("20160101");
	ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationVisPresentedDateIsBeforePublishedDate,
        r.getValidationDetections().get(0).getDetection());
  }

  @Test
  public void testVisPresAfterAdminDate() {
	vis.setDocumentCode("253088698300003511070517");
	vis.setPublishedDateString("20170101");
	vis.setCvxCode("107");
	vis.setPresentedDateString("20180501");
	Calendar adminDate = Calendar.getInstance();
	adminDate.set(Calendar.YEAR, 2018);
	adminDate.set(Calendar.MONTH, 1);
	v.setAdminDate(adminDate.getTime());
	ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertTrue(r.issuesContainsDetection(Detection.VaccinationVisPresentedDateIsAfterAdminDate));
  
  }
  
  @Test
  public void testVisPresNotSameAdminDate() {
	vis.setDocumentCode("253088698300003511070517");
	vis.setPublishedDateString("20170101");
	vis.setCvxCode("107");
	vis.setPresentedDateString("20170101");
	Calendar adminDate = Calendar.getInstance();
	adminDate.set(Calendar.YEAR, 2018);
	adminDate.set(Calendar.MONTH, 1);
	v.setAdminDate(adminDate.getTime());
	ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertTrue(r.issuesContainsDetection(Detection.VaccinationVisPresentedDateIsNotAdminDate));
  }
}

