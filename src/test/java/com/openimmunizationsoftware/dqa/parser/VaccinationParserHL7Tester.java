package com.openimmunizationsoftware.dqa.parser;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import com.openimmunizationsoftware.dqa.TestMessageGenerator;
import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.validator.MessageValidator;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueAction;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;

public class VaccinationParserHL7Tester {
	HL7ToObjectsServiceImpl parser = new HL7ToObjectsServiceImpl();
	
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
		MessageReceived mr = parser.extractFromMessage(message);

		System.out.println("MESSAGE: ***********************************************");
		System.out.println(message);
		System.out.println("********************************************************");
		
		MessageValidator v = new MessageValidator();
		
		long start = Calendar.getInstance().getTimeInMillis();
		List<ValidationRuleResult> list = v.validateMessage(mr);
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
		reportResults(list, IssueAction.ERROR);
	}
	
	private void reportAcceptResults(List<ValidationRuleResult> list) {
		reportResults(list, IssueAction.ACCEPT);
	}

	private void reportWarnResults(List<ValidationRuleResult> list) {
		reportResults(list, IssueAction.WARN);
	}
	
	private void reportResults(List<ValidationRuleResult> list, IssueAction a) {
		for (ValidationRuleResult vrr : list) {
			for (IssueFound i : vrr.getIssues()) {
				if (a == i.getIssueAction()) {
					System.out.println(i.getIssue() + "[" + i.getCodeReceived().getCodeValue() + "]");
				}
			}
		}
	}

}
