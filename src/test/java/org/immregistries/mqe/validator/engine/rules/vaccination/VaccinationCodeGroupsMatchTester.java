package org.immregistries.mqe.validator.engine.rules.vaccination;

import static org.junit.Assert.assertEquals;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VaccinationCodeGroupsMatchTester {

	private VaccinationCodeGroupsMatch rule = new VaccinationCodeGroupsMatch();

	private MqeMessageReceived mr = new MqeMessageReceived();
	private MqePatient p = new MqePatient();
	private MqeVaccination v = new MqeVaccination();
	
	private static final Logger logger = LoggerFactory.getLogger(VaccinationCodeGroupsMatchTester.class);

	/**
	 * Sets up the objects with valid information before EACH TEST.
	 */
	@Before
	public void setUpTheObjects() {
		mr.setPatient(p);
		mr.getVaccinations().add(v);
		v.setAdminCptCode("90702");
		v.setAdminCvxCode("28");
	}

	@Test
	public void testInvalid() {
		v.setAdminCptCode("90702");
		v.setAdminCvxCode("24");
		ValidationRuleResult r = rule.executeRule(v, mr);
		assertEquals(1, r.getValidationDetections().size());
		assert(!r.isRulePassed());
		assertEquals(Detection.VaccinationCvxCodeAndCptCodeAreInconsistent, r.getValidationDetections().get(0).getDetection());
	}

	@Test
	public void testValid() {
		
		ValidationRuleResult r = rule.executeRule(v, mr);
		assertEquals(0, r.getValidationDetections().size());
		assert(r.isRulePassed());
	}

}
