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

public class VaccinationIsPresentTester {

  private VaccinationIsPresent rule = new VaccinationIsPresent();

  // Parts required for the test
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqeVaccination v = new MqeVaccination();

  private static final Logger logger = LoggerFactory.getLogger(VaccinationIsPresentTester.class);

  /**
   * Sets up the objects needed for the test.
   */
  @Before
  public void setUpTheObjects() {
    mh.setMessageDate(new Date());
    mr.setMessageHeader(mh);
    mr.getVaccinations().add(v);
  }

  @Test
  public void testPass() {
	v = new MqeVaccination();
    ValidationRuleResult r = rule.executeRule(v, mr);
    assertEquals("Should pass", true, r.isRulePassed());
  }
  
  @Test
  public void testFail() {
	mr.getVaccinations().clear();
	v = null;
    ValidationRuleResult r = rule.executeRule(v, mr);
    assertEquals("Should fail", false, r.isRulePassed());
  }
  
}

