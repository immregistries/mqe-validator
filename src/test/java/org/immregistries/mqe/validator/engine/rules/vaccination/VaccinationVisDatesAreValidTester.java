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
import org.immregistries.mqe.vxu.VaccinationVIS;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VaccinationVisDatesAreValidTester {

  private VaccinationVisDatesAreValid rule = new VaccinationVisDatesAreValid();

  // Parts required for the test
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqeVaccination v = new MqeVaccination();
  private VaccinationVIS vis = new VaccinationVIS();
  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

  private static final Logger logger = LoggerFactory.getLogger(VaccinationVisDatesAreValidTester.class);

  /**
   * Sets up the objects needed for the test.
   */
  @Before
  public void setUpTheObjects() {
    mh.setMessageDate(new Date());
    mr.setMessageHeader(mh);
    mr.getVaccinations().add(v);
    v.setAdministered(true);
    v.setVaccinationVis(vis);
    vis.setDocumentCode("253088698300003511070517");
	Calendar pubDate = Calendar.getInstance();
	vis.setPublishedDateString(dateFormat.format(pubDate.getTime()));
	vis.setPresentedDateString(dateFormat.format(pubDate.getTime()));
  }

  @Test
  public void testValidPublishedAndPresentedDates() {
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(0, r.getValidationDetections().size());
    assertEquals("This is passing", true, r.isRulePassed());
  }
  
  @Test
  public void testBadPublishedDatesFormats() {
	vis.setPublishedDateString("fdsafd");
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationVisPublishedDateIsInvalid,
        r.getValidationDetections().get(0).getDetection());
    
    vis.setPublishedDateString(null);
    r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationVisPublishedDateIsMissing,
        r.getValidationDetections().get(0).getDetection());
    
    vis.setPublishedDateString("");
    r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationVisPublishedDateIsInvalid,
        r.getValidationDetections().get(0).getDetection());
  }
  
  @Test
  public void testFuturePublishedDate() {
    Calendar pubDate = Calendar.getInstance();
    pubDate.add(Calendar.DAY_OF_YEAR, 5);
    vis.setPublishedDateString(dateFormat.format(pubDate.getTime()));
    vis.setPresentedDateString(dateFormat.format(pubDate.getTime()));
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationVisPublishedDateIsInFuture,
        r.getValidationDetections().get(0).getDetection());
  }
  
  @Test
  public void testBadPresentedDateFormats() {
	vis.setPresentedDateString("fdsafd");
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationVisPresentedDateIsInvalid,
        r.getValidationDetections().get(0).getDetection());
    
    vis.setPresentedDateString(null);
    r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationVisPresentedDateIsMissing,
        r.getValidationDetections().get(0).getDetection());
    
    vis.setPresentedDateString("");
    r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationVisPresentedDateIsInvalid,
        r.getValidationDetections().get(0).getDetection());
  }
  
  @Test 
  public void testPresentedBeforePublished() {
	Calendar pubDate = Calendar.getInstance();
    pubDate.add(Calendar.DAY_OF_YEAR, -2);
	Calendar presDate = Calendar.getInstance();
	presDate.add(Calendar.DAY_OF_YEAR, -5);
	vis.setPublishedDateString(dateFormat.format(pubDate.getTime()));
    vis.setPresentedDateString(dateFormat.format(presDate.getTime()));
    
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationVisPresentedDateIsBeforePublishedDate,
        r.getValidationDetections().get(0).getDetection());
  }
  
  @Test 
  public void testPresentedAfterAdmin() {
	Calendar pubDate = Calendar.getInstance();
    pubDate.add(Calendar.DAY_OF_YEAR, -6);
	Calendar presDate = Calendar.getInstance();
	presDate.add(Calendar.DAY_OF_YEAR, -3);
	Calendar adminDate = Calendar.getInstance();
	adminDate.add(Calendar.DAY_OF_YEAR, -6);
	v.setAdminDateString(dateFormat.format(adminDate.getTime()));
	v.setAdminDate(adminDate.getTime());
	vis.setPublishedDateString(dateFormat.format(pubDate.getTime()));
    vis.setPresentedDateString(dateFormat.format(presDate.getTime()));
    
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationVisPresentedDateIsAfterAdminDate,
        r.getValidationDetections().get(0).getDetection());
  }
  
  @Test 
  public void testPresentedBeforeAdmin() {
	Calendar pubDate = Calendar.getInstance();
    pubDate.add(Calendar.DAY_OF_YEAR, -6);
	Calendar presDate = Calendar.getInstance();
	presDate.add(Calendar.DAY_OF_YEAR, -6);
	Calendar adminDate = Calendar.getInstance();
	adminDate.add(Calendar.DAY_OF_YEAR, -3);
	v.setAdminDateString(dateFormat.format(adminDate.getTime()));
	v.setAdminDate(adminDate.getTime());
	vis.setPublishedDateString(dateFormat.format(pubDate.getTime()));
    vis.setPresentedDateString(dateFormat.format(presDate.getTime()));
    
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationVisPresentedDateIsNotAdminDate,
        r.getValidationDetections().get(0).getDetection());
  }
}

