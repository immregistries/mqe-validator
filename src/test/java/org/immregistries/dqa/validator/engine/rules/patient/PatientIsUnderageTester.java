package org.immregistries.dqa.validator.engine.rules.patient;

import static org.junit.Assert.*;

import java.util.Date;

import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.vxu.DqaMessageHeader;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

public class PatientIsUnderageTester {

	PatientIsUnderage rule = new PatientIsUnderage();
	
	/*
	 * Parts required for the test: 
	 */
	DqaMessageHeader mh = new DqaMessageHeader();
	DqaMessageReceived mr = new DqaMessageReceived();
	DqaPatient p = new DqaPatient();
	
	@Before
	public void setUpTheObjects() {
		//This sets up the objects with a birthdate, and message date of today. 
		mh.setMessageDate(new Date());//todays date.
		p.setBirthDate(new Date());//todays date.
		mr.setMessageHeader(mh);
		mr.setPatient(p);
	}
	
	@Test
	public void testRule() {
		ValidationRuleResult r = rule.executeRule(p, mr);
		//using today's date, the patient should be underage. 
		assertTrue(r.isRulePassed());
	}
	
	@Test
	public void testRuleAge40Patient() {
		//Set up the test. 
;		DateTime age40 = new DateTime().minusYears(40);
		Date age40Date = new Date(age40.getMillis());
		p.setBirthDate(age40Date);
		
		ValidationRuleResult r = rule.executeRule(p, mr);
		//using today's date, the patient should be underage. 
		assertFalse(r.isRulePassed());
	}
	
	@Test
	public void testNullDateRule() {
		p.setBirthDate(null);
		ValidationRuleResult r = rule.executeRule(p, mr);
		//using today's date, the patient should be underage. 
		assertFalse(r.isRulePassed());
	}
	

}
