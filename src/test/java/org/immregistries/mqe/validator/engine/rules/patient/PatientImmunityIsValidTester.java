package org.immregistries.mqe.validator.engine.rules.patient;

import static org.junit.Assert.assertEquals;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.PatientImmunity;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PatientImmunityIsValidTester {
	  private PatientImmunityIsValid rule = new PatientImmunityIsValid();

	  private MqeMessageHeader mh = new MqeMessageHeader();
	  private MqeMessageReceived mr = new MqeMessageReceived();
	  private MqePatient p = new MqePatient();

	  private static final Logger logger = LoggerFactory.getLogger(PatientCodesAreValidTester.class);
	  
	  /**
	   * Tests deprecated immunity code.
	   */
	  @Test
	  public void testRuleDeprecatedImmunityCode() {
		PatientImmunity pi = new PatientImmunity();
		pi.setCode("24");
		pi.setType("");
		p.getPatientImmunityList().add(pi);
		

	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientImmunityCodeIsDeprecated,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests unrecognized immunity code.
	   */
	  @Test
	  public void testRuleInvalidImmunityCode() {
		PatientImmunity pi = new PatientImmunity();
		pi.setCode("p");
		pi.setType("");
		p.getPatientImmunityList().add(pi);
		

	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientImmunityCodeIsUnrecognized,
	        r.getValidationDetections().get(0).getDetection());
	  }
}
