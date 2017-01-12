package com.openimmunizationsoftware.dqa.validator;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.openimmunizationsoftware.dqa.TestMessageGenerator;
import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.parser.HL7ToObjectsServiceImpl;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.rules.patient.PatientBirthDateIsValid;
import com.openimmunizationsoftware.dqa.validator.rules.patient.PatientExists;
import com.openimmunizationsoftware.dqa.validator.rules.patient.PatientIsUnderage;

public class ValidationRunnerTest {

	ValidationUtility util = ValidationUtility.INSTANCE;
	
	/**
	 * This is testing the validation runner class specifically.  
	 */
	@Test
	public void validatePatient() {
		
		List<ValidationRule> testRules = Arrays.asList(new ValidationRule[] {
				new PatientBirthDateIsValid(), 
				new PatientExists(),
				new PatientIsUnderage()	
		});
		
		MessageReceived m = getFreshMessage();
		m.getPatient().setBirthDateString("2016-01-01");
		
		ValidationRunner vr = ValidationRunner.INSTANCE;
		
		List<ValidationRulePair> rpList = util.buildRulePairs(testRules, m.getPatient(),  m);

		List<ValidationRuleResult> results = vr.processValidationRules(rpList, null);
		
		assertEquals("Should have about three rules that ran", 3, results.size());
		
		//let's make the birth date bad!
		m.getPatient().setBirthDateString("");
		results = vr.processValidationRules(rpList, null);
		assertEquals("Should have about two rules that ran", 2, results.size());

		List<Class> passed = util.getPassedFromResults(results);
		assertEquals("only one that passes though", 1, passed.size());
		
		assertEquals("passed should only contain PatientExists.class", PatientExists.class, passed.get(0));

		List<Class> failed = util.getFailedFromResults(results);
		assertEquals("only one that fails too", 1, failed.size());
		assertEquals("failure should be PatientBirthDateIsValid.class", PatientBirthDateIsValid.class, failed.get(0)); 
		
	}

	private MessageReceived getFreshMessage() {
		MessageReceived mr = new MessageReceived();
		return mr;
	}
	
	private MessageReceived getFullMessage() {
		TestMessageGenerator gen = new TestMessageGenerator();
		String vxu = gen.getExampleVXU_1();
		HL7ToObjectsServiceImpl hl7conv = new HL7ToObjectsServiceImpl();
		MessageReceived mr = hl7conv.extractFromMessage(vxu);
		return mr;
	}
	@Test
	public void validationWorkflowFull() {
		MessageReceived mr = getFullMessage();
		MessageValidator v = new MessageValidator();
		List<ValidationRuleResult> violations = v.validateMessage(mr);
		System.out.println("Rules Run..." + violations.size());
		System.out.println("Reporting on " + violations.size() + " results");
		
		for (ValidationRuleResult vrr : violations) {
			if (vrr.isRulePassed()) {
//				System.out.println("PASSED!" + vrr.getRuleClass());
			} else {
				System.out.println("RULE: " + vrr.getRuleClass() + " ISSUEs " + vrr.getIssues().size());
				for (IssueFound f: vrr.getIssues()) {
					if (f.isError()) {
						System.out.println("     ERROR Issue: " + f.getIssue());
					} else {
						System.out.println("     WARN  Issue: " + f.getIssue());
					}
				}
			}
		}
	}
	
	
}
