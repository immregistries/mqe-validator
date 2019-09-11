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

public class VaccinationUseCptInsteadOfCvxTester {

  private VaccinationUseCptInsteadOfCvx rule = new VaccinationUseCptInsteadOfCvx();

  // Parts required for the test
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqeVaccination v = new MqeVaccination();

  private static final Logger logger = LoggerFactory.getLogger(VaccinationUseCptInsteadOfCvxTester.class);

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
  public void testNeitherCptOrCvx() {
	v.setAdminCvxCode(null);
	v.setAdminCptCode(null);
    ValidationRuleResult r = rule.executeRule(v, mr);
    assertEquals(false, r.isRulePassed());
  }
  
  @Test
  public void testOnlyUsableCpt() {
	v.setAdminCptCode("90700");
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(0, r.getValidationDetections().size());
    assertEquals("This is a pass", true, r.isRulePassed());
  }
  
  @Test 
  public void testOnlyUnusableCpt() {
	v.setAdminCptCode("asdfdsa");
	ValidationRuleResult r = rule.executeRule(v, mr);
    assertEquals(false, r.isRulePassed());
    
	v.setAdminCptCode(null);
    r = rule.executeRule(v, mr);
    assertEquals(false, r.isRulePassed());
    
	v.setAdminCptCode(null);
    r = rule.executeRule(v, mr);
    assertEquals(false, r.isRulePassed());
  }
  

  @Test
  public void testWithBothCptAndCvx() {
	v.setAdminCptCode("90700"); //valid
	v.setAdminCvxCode("43"); // valid
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(false, r.isRulePassed());
    
	v.setAdminCptCode("90700"); //valid
	v.setAdminCvxCode("76"); // invalid
    r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(true, r.isRulePassed());
    
	v.setAdminCptCode("abcds"); // unrecognized
	v.setAdminCvxCode("43"); // valid
    r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(false, r.isRulePassed());
  }
  
}

