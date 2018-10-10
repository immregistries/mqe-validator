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

public class VaccinationUseCvxTester {

  private VaccinationUseCvx rule = new VaccinationUseCvx();

  // Parts required for the test
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqeVaccination v = new MqeVaccination();

  private static final Logger logger = LoggerFactory.getLogger(VaccinationUseCvxTester.class);

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
  public void testNoCvx() {
	v.setAdminCvxCode(null);
    ValidationRuleResult r = rule.executeRule(v, mr);
    assertEquals(false, r.isRulePassed());
    
	v.setAdminCvxCode("");
    r = rule.executeRule(v, mr);
    assertEquals(false, r.isRulePassed());
    
	v.setAdminCvxCode("dfdsaf");
    r = rule.executeRule(v, mr);
    assertEquals(false, r.isRulePassed());
  }
  
  @Test
  public void testUsableCvx() {
	v.setAdminCvxCode("43"); // valid
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(true, r.isRulePassed());
    
	v.setAdminCptCode("76"); // invalid but is an actual cvx
    r = rule.executeRule(v, mr);
    assertEquals(true, r.isRulePassed());
  }
}

