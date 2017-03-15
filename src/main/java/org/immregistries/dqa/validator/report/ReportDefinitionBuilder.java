package org.immregistries.dqa.validator.report;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.issue.IssueObject;
import org.immregistries.dqa.validator.issue.IssueType;
import org.immregistries.dqa.validator.issue.VxuField;

/**
 * The goal of this class is to generate a report definition
 * through various means. 
 * 
 * There will be a default implementation. 
 * There will be an XML consumer that takes the XML spec. 
 * There will be a way to get the spec from an object model 
 * (based on the spec) so that we can store it in a database too. 
 * @author Josh Hull
 */
public enum ReportDefinitionBuilder {
	INSTANCE;
	
	public ReportDefinition getDeafult() {
		ReportDefinition def = new ReportDefinition();
		
		//build a section for the patient. 
		ReportCompletenessSectionDefinition section = new ReportCompletenessSectionDefinition();
		section.setLabel("Patient");
		section.setSectionObject(IssueObject.PATIENT);
		section.setReportFields(getPatientReportFields());

		def.getQualitySections().add(section);
		return def;
	}
	
	public List<DqaReportFieldDefinition> getPatientReportFields() {
		List<DqaReportFieldDefinition> fields = new ArrayList<>();
		//here's the list: 
	//Required
		//patient-id 40
		fields.add(generateFieldDefinition("Patient Id", Requirement.REQUIRED, VxuField.PATIENT_SUBMITTER_ID, 20, true, getDefaultFieldIssues()));
		//first name 20
		fields.add(generateFieldDefinition("First Name", Requirement.REQUIRED, VxuField.PATIENT_NAME_FIRST, 20, true, getDefaultFieldIssues()));
		//last name  20
		fields.add(generateFieldDefinition("Last Name", Requirement.REQUIRED, VxuField.PATIENT_NAME_LAST, 20, true, getDefaultFieldIssues()));
		//patient name - no default issues. dont' check presense.  100pts?
			//Possible Test Name 1x
			//Possible Baby Name 1x
		//Birth Date
		fields.add(generateFieldDefinition("Birth Date", Requirement.REQUIRED, VxuField.PATIENT_BIRTH_DATE, 20, true, getDefaultFieldIssues()));
		//gender
		fields.add(generateFieldDefinition("Sex", Requirement.REQUIRED, VxuField.PATIENT_GENDER, 20, true, getDefaultFieldIssues()));
		//Complete Address
		fields.add(generateFieldDefinition("Complete Address", Requirement.REQUIRED, VxuField.PATIENT_ADDRESS, 20, true, getDefaultFieldIssues()));
		//street
		fields.add(generateFieldDefinition("Street", Requirement.REQUIRED, VxuField.PATIENT_ADDRESS_STREET, 20, true, getDefaultFieldIssues()));
		//city
		fields.add(generateFieldDefinition("City", Requirement.REQUIRED, VxuField.PATIENT_ADDRESS_CITY, 20, true, getDefaultFieldIssues()));
		//state
		fields.add(generateFieldDefinition("State", Requirement.REQUIRED, VxuField.PATIENT_ADDRESS_STATE, 20, true, getDefaultFieldIssues()));
		//zip
		fields.add(generateFieldDefinition("Zip", Requirement.REQUIRED, VxuField.PATIENT_ADDRESS_ZIP, 20, true, getDefaultFieldIssues()));

		//expected
		//middle name
		fields.add(generateFieldDefinition("Middle Name", Requirement.EXPECTED, VxuField.PATIENT_MIDDLE_NAME, 20, true, getDefaultFieldIssues()));
		//phone
		fields.add(generateFieldDefinition("Phone", Requirement.EXPECTED, VxuField.PATIENT_PHONE, 20, true, getDefaultFieldIssues()));
		//mother's maiden
		fields.add(generateFieldDefinition("Mother's Maiden", Requirement.EXPECTED, VxuField.PATIENT_MOTHERS_MAIDEN_NAME, 20, true, getDefaultFieldIssues()));
		return fields;
	}
	
	public DqaReportFieldDefinition generateFieldDefinition(String label, Requirement requirement, VxuField field, int weight,  boolean checkForPresent, List<ReportIssue> issues) {
		DqaReportFieldDefinition def = new DqaReportFieldDefinition();
		def.setLabel(label);
		def.setCheckForPresent(checkForPresent);
		def.setField(field);
		def.setIssues(issues);
		def.setWeight(weight);
		def.setRequirement(requirement);
		
		return def;
	}
	
	public List<ReportIssue> getDefaultFieldIssues() {
		List<ReportIssue> defaults = new ArrayList<>();
		defaults.add(generateReportIssue(IssueType.INVALID, 100));
		defaults.add(generateReportIssue(IssueType.UNRECOGNIZED, 100));
		defaults.add(generateReportIssue(IssueType.DEPRECATED, 100));
		return defaults;
	}
	
	public ReportIssue generateReportIssue(IssueType type, int multiplier) {
		ReportIssue ri = new ReportIssue();
		ri.setMultiplierPercent(multiplier);
		ri.setType(type);
		return ri;
	}
	
}
