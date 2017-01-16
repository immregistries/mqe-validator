package org.immregistries.dqa.validator.engine.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.immregistries.dqa.codebase.client.generated.Code;
import org.immregistries.dqa.codebase.client.reference.CodesetType;
import org.immregistries.dqa.validator.engine.codes.CodeRepository;
import org.immregistries.dqa.validator.engine.common.CodeHandler;
import org.immregistries.dqa.validator.engine.common.DateUtility;
import org.immregistries.dqa.validator.engine.issues.IssueField;
import org.immregistries.dqa.validator.engine.issues.MessageAttribute;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.junit.Before;
import org.junit.Test;


public class CodeHandlerTest {
	private CodeRepository repo = CodeRepository.INSTANCE;
	private Code codeAnthrax;
	private Code codeAdenovirus;
	private CodeHandler codr = CodeHandler.INSTANCE;
	private DateUtility datr = DateUtility.INSTANCE;
	
	@Before
	public void buildNewCode() {
		this.codeAnthrax = repo.getCodeFromValue("24", CodesetType.VACCINATION_CVX_CODE);
		this.codeAdenovirus = repo.getCodeFromValue("82", CodesetType.VACCINATION_CVX_CODE);
	}
	
	@Test
	public void testUseDates() {
		String adminDate = "20100101";
		List<ValidationIssue> list = codr.handleUseDate(codeAnthrax, adminDate, IssueField.VACCINATION_ADMIN_DATE);
		assertEquals("Shouldn't have an issue with a current date, even though it doesn't have an end date", 0, list.size());
		
		adminDate = "19650101";
		list = codr.handleUseDate(codeAnthrax, adminDate, IssueField.VACCINATION_ADMIN_DATE);
		assertEquals("Should have an issue with adminDate of " + adminDate, 1, list.size());
		
		boolean theIssueIsCorrect = MessageAttribute.VaccinationAdminDateIsBeforeOrAfterLicensedVaccineRange == list.get(0).getIssue();
		assertTrue("the one issue should be VaccinationAdminDateIsBeforeOrAfterLicensedVaccineRange", theIssueIsCorrect);
	}
	
	@Test
	public void testAgeDates() {
		//adenovirus has "not before month 1 and not after month 1440"
		Date birthDate = datr.parseDate("20160101");
		Date adminDate = datr.parseDate("20160115");
		//This should violate the age date, because its before month 1. 
		List<ValidationIssue> list = codr.handleAgeDate(this.codeAdenovirus, birthDate, adminDate, IssueField.VACCINATION_ADMIN_DATE);
		assertEquals("Should have an issue", 1, list.size());
		
		//This should violate the age date because its after month 1440
		adminDate = datr.parseDate("21500115");
		list = codr.handleAgeDate(this.codeAdenovirus, birthDate, adminDate, IssueField.VACCINATION_ADMIN_DATE);
		assertEquals("Should have an issue", 1, list.size());
		
		//This should NOT violate the age date because its null
		adminDate = null;
		list = codr.handleAgeDate(this.codeAdenovirus, birthDate, adminDate, IssueField.VACCINATION_ADMIN_DATE);
		assertEquals("Should not have an issue because its null", 0, list.size());

		//This should NOT violate the age date because its within the range
		adminDate = datr.parseDate("20160515");
		list = codr.handleAgeDate(this.codeAdenovirus, birthDate, adminDate, IssueField.VACCINATION_ADMIN_DATE);
		assertEquals("Should not have an issue because its within the range", 0, list.size());
	}
	
	@Test
	public void testAgeDatesNull() {
		//adenovirus has "not before month 1 and not after month 1440"
		Date birthDate = datr.parseDate("20160101");
		Date adminDate = datr.parseDate("20160115");
		
		List<ValidationIssue> list = codr.handleAgeDate(this.codeAdenovirus, birthDate, null, IssueField.VACCINATION_ADMIN_DATE);
		assertEquals("Should not have an issue because admin date is null", 0, list.size());

		//This should NOT violate the age date because birth date is null
		list = codr.handleAgeDate(this.codeAdenovirus, null, adminDate, IssueField.VACCINATION_ADMIN_DATE);
		assertEquals("Should not have an issue because reference date is null", 0, list.size());
		
		//This should NOT violate the age date because both are null
		list = codr.handleAgeDate(this.codeAdenovirus, null, null, IssueField.VACCINATION_ADMIN_DATE);
		assertEquals("Should not have an issue because both dates are null", 0, list.size());
		
		//This should NOT violate the age date because the code is null
		list = codr.handleAgeDate(null, null, null, IssueField.VACCINATION_ADMIN_DATE);
		assertEquals("Should not have an issue because all is null", 0, list.size());
		
		//This should NOT violate the age date because the code is null
		list = codr.handleAgeDate(null, birthDate, adminDate, IssueField.VACCINATION_ADMIN_DATE);
		assertEquals("Should not have an issue because the code is null", 0, list.size());
		
	}
}
