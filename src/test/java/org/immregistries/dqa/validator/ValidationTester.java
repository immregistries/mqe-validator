package org.immregistries.dqa.validator;

import java.util.Calendar;
import java.util.List;

import org.immregistries.dqa.hl7util.SeverityLevel;
import org.immregistries.dqa.validator.engine.MessageValidator;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.parse.HL7MessageParser;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidationTester {
	private static final Logger logger = LoggerFactory.getLogger(ValidationTester.class);
	private HL7MessageParser parser = HL7MessageParser.INSTANCE;
	private MessageValidator validator = MessageValidator.INSTANCE;
	
	TestMessageGenerator genr = new TestMessageGenerator();
	
	@Test
	public void test1() {
		validateAndReport(genr.getAiraTestMsg());
	}

	@Test
	public void test12() {
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
		System.out.println("MESSAGE: ***********************************************");
		String[] lines = message.split("\\r");
		for (String line : lines) {
			System.out.println("         " + line);
		}
		System.out.println("********************************************************");
		DqaMessageReceived mr = parser.extractMessageFromText(message);

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
				if (a == i.getSeverity()) {
					System.out.println(i.getIssue() + "[" + i.getValueReceived() + "]");
				}
			}
		}
	}

}
