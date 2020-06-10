package org.immregistries.mqe.validator.engine.rules.patient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeNextOfKin;
import org.immregistries.mqe.vxu.MqePatient;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PatientHasResponsiblePartyTester {
	
	  private PatientHasResponsibleParty rule = new PatientHasResponsibleParty();

	  private MqeMessageHeader mh = new MqeMessageHeader();
	  private MqeMessageReceived mr = new MqeMessageReceived();
	  private MqePatient p = new MqePatient();

	  private static final Logger logger = LoggerFactory.getLogger(PatientHasResponsiblePartyTester.class);
	  
	  /**
	   * Tests empty patient responsible party.
	   */
	  @Test
	  public void testRuleEmptyPatientHasResponsibleParty() {
		p.setResponsibleParty(null);
		

	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientGuardianResponsiblePartyIsMissing,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests patient responsible party.
	   * (should be true)
	   */
	  @Test
	  public void testRulePatientHasResponsibleParty() {
		MqeNextOfKin prp = new MqeNextOfKin();
		prp.setRelationshipCode("PARENTS");
		p.setResponsibleParty(prp);

	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertTrue(r.isRulePassed());
	  }
}
