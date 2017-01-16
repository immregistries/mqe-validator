package org.immregistries.dqa.validator.parser;

import java.util.Calendar;
import java.util.List;

import org.immregistries.dqa.hl7util.SeverityLevel;
import org.immregistries.dqa.validator.TestMessageGenerator;
import org.immregistries.dqa.validator.engine.MessageValidator;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.immregistries.dqa.validator.model.DqaMessageReceived;
import org.junit.Test;

public class VaccinationParserHL7Tester {
	private HL7MessageParser parser = HL7MessageParser.INSTANCE;
	private MessageValidator validator = MessageValidator.INSTANCE;
	
	TestMessageGenerator genr = new TestMessageGenerator();
	
	@Test
	public void test1() {
		validateAndReport(genr.getExampleVXU_1());
	}
	
	@Test
	public void test2() {
		validateAndReport(genr.getExampleVXU_2());
	}
	
	@Test
	public void test3() {
		validateAndReport(genr.getAiraTestMsg());
	}
	
	private void validateAndReport(String message) {
		DqaMessageReceived mr = parser.extractMessageFromText(message);

		System.out.println("MESSAGE: ***********************************************");
		System.out.println(message);
		System.out.println("********************************************************");
		
		long start = Calendar.getInstance().getTimeInMillis();
		List<ValidationRuleResult> list = validator.validateMessage(mr);
		long finish = Calendar.getInstance().getTimeInMillis();
		
		System.out.println("IT TOOK: " + (finish - start) + " ms to validate");
		
		System.out.println("ACCEPT: ***********************************************");
		reportAcceptResults(list);
		System.out.println("WARN  : ***********************************************");
		reportWarnResults(list);
		System.out.println("ERROR : ***********************************************");
		reportErrorResults(list);
	}
	
	private void reportErrorResults(List<ValidationRuleResult> list) {
		reportResults(list, SeverityLevel.ERROR);
	}
	
	private void reportAcceptResults(List<ValidationRuleResult> list) {
		reportResults(list, SeverityLevel.ACCEPT);
	}

	private void reportWarnResults(List<ValidationRuleResult> list) {
		reportResults(list, SeverityLevel.WARN);
	}
	
	private void reportResults(List<ValidationRuleResult> list, SeverityLevel a) {
		for (ValidationRuleResult vrr : list) {
			for (ValidationIssue i : vrr.getIssues()) {
				if (a == i.getSeverityLevel()) {
					System.out.println(i.getIssue() + "[" + i.getCodeReceived() + "]");
				}
			}
		}
	}

}
