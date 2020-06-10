package org.immregistries.mqe.validator.engine.rules.vaccination;

import static org.junit.Assert.assertEquals;
import java.util.Date;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VaccinationIsAdministeredOrHistoricalTester {

  private VaccinationIsAdministeredOrHistorical rule = new VaccinationIsAdministeredOrHistorical();

  // Parts required for the test
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqeVaccination v = new MqeVaccination();

  private static final Logger logger = LoggerFactory.getLogger(VaccinationIsAdministeredOrHistoricalTester.class);

  /**
   * Sets up the objects needed for the test.
   */
  @Before
  public void setUpTheObjects() {
    mh.setMessageDate(new Date());
    mr.setMessageHeader(mh);
    mr.getVaccinations().add(v);
    v.setAdministered(true);
    v.setAdminCvxCode("143");
  }

  @Test
  public void testCompletionOfShot() {
	v.setCompletionCode(null);
    ValidationRuleResult r = rule.executeRule(v, mr);
    assertEquals("Should fail", false, r.isRulePassed());
	    
	v.setCompletionCode("");
    r = rule.executeRule(v, mr);
    assertEquals("Should pass", true, r.isRulePassed());
    
	v.setCompletionCode("CP");
    r = rule.executeRule(v, mr);
    assertEquals("Should pass", true, r.isRulePassed());
    
	v.setCompletionCode("NA");
    r = rule.executeRule(v, mr);
    assertEquals("Should fail", false, r.isRulePassed());
    
	v.setCompletionCode("PA");
    r = rule.executeRule(v, mr);
    assertEquals("Should pass", true, r.isRulePassed());
    
	v.setCompletionCode("RE");
    r = rule.executeRule(v, mr);
    assertEquals("Should fail", false, r.isRulePassed());
    
	v.setCompletionCode("ddfdsf");
    r = rule.executeRule(v, mr);
    assertEquals("Should fail", false, r.isRulePassed());
  }
  
  @Test
  public void testAdminCVX() {
	v.setAdminCvxCode("998");
	v.setCompletionCode("CP");
    ValidationRuleResult r = rule.executeRule(v, mr);
    assertEquals("Should fail", false, r.isRulePassed());
    
	v.setAdminCvxCode(null);
	v.setCompletionCode("CP");
    r = rule.executeRule(v, mr);
    assertEquals("Should fail", false, r.isRulePassed());
    
	v.setAdminCvxCode("143");
    r = rule.executeRule(v, mr);
    assertEquals("Should pass", true, r.isRulePassed());
  }
  
}

