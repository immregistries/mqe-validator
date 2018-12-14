package org.immregistries.mqe.validator.engine.rules.vaccination;

import static org.junit.Assert.assertEquals;

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

public class VaccinationIsAdministeredTester {

  private VaccinationIsAdministered rule = new VaccinationIsAdministered();

  // Parts required for the test
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqeVaccination v = new MqeVaccination();

  private static final Logger logger = LoggerFactory.getLogger(VaccinationIsAdministeredTester.class);

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
  public void testIsActuallyAdministered() {
	createAdministeredLikeShot();
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals("Should pass", true, r.isRulePassed());
  }
  
  @Test
  public void testIsAdministeredButAppearsHistorical() {
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationInformationSourceIsAdministeredButAppearsToHistorical,
        r.getValidationDetections().get(0).getDetection());

  }
  
  @Test
  public void testIsHistoricalButAppearsAdministered() {
	v.setAdministered(false);
	createAdministeredLikeShot();
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationInformationSourceIsHistoricalButAppearsToBeAdministered,
        r.getValidationDetections().get(0).getDetection());
  }
  
  private void createAdministeredLikeShot() {
	Calendar adminDate = Calendar.getInstance();
	adminDate.add(Calendar.DAY_OF_YEAR, -1);
	v.setAdminDate(adminDate.getTime());
	mr.setReceivedDate(adminDate.getTime());
	v.setLotNumber("lotNumber");
	v.setManufacturerCode("MFG");
	Calendar expireDate = Calendar.getInstance();
	expireDate.add(Calendar.YEAR, 5);
	v.setExpirationDate(expireDate.getTime());
  }
  
}

