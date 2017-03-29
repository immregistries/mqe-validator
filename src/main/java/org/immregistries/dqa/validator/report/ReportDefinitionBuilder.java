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
		
		def.getQualitySections().add(getDefaultPatientSection());
		def.getQualitySections().add(getDefaultVaccinationSection());
		return def;
	}
	
	
	public ReportCompletenessSectionDefinition getDefaultVaccinationSection() {
		//build a section for the patient. 
				ReportCompletenessSectionDefinition section = new ReportCompletenessSectionDefinition();
				section.setLabel("Vaccination");
				section.setSectionObject(IssueObject.VACCINATION);
				section.setReportFields(getVaccinationReportFields());

				return section;
	}
	
	public ReportCompletenessSectionDefinition getDefaultPatientSection() {
		//build a section for the patient. 
				ReportCompletenessSectionDefinition section = new ReportCompletenessSectionDefinition();
				section.setLabel("Patient");
				section.setSectionObject(IssueObject.PATIENT);
				section.setReportFields(getPatientReportFields());

				return section;
	}
	public List<DqaReportFieldDefinition> getVaccinationReportFields() {
		List<DqaReportFieldDefinition> fields = new ArrayList<>();
		boolean checkForMissing = true;
		boolean dontCheckForMissing = true;
		
		//required
		fields.add(generateFieldDefinition("Vaccination Date", Requirement.REQUIRED, VxuField.VACCINATION_ADMIN_DATE, 45, checkForMissing, getDefaultFieldIssues()));
		fields.add(generateFieldDefinition("Vaccination Code", Requirement.REQUIRED, VxuField.VACCINATION_ADMIN_CODE, 45, checkForMissing, getDefaultFieldIssues()));
		fields.add(generateFieldDefinition("Information Source", Requirement.REQUIRED, VxuField.VACCINATION_INFORMATION_SOURCE, 45, checkForMissing, getDefaultFieldIssues()));
		fields.add(generateFieldDefinition("VFC Status", Requirement.REQUIRED, VxuField.PATIENT_VFC_STATUS, 22, checkForMissing, getDefaultFieldIssues()));
		
		//expected
		fields.add(generateFieldDefinition("CVX Code", Requirement.EXPECTED, VxuField.VACCINATION_CVX_CODE, 15, checkForMissing, getDefaultFieldIssues()));
		fields.add(generateFieldDefinition("Lot Number", Requirement.EXPECTED, VxuField.VACCINATION_LOT_NUMBER, 22, checkForMissing, getDefaultFieldIssues()));
		fields.add(generateFieldDefinition("Manufacturer", Requirement.EXPECTED, VxuField.VACCINATION_MANUFACTURER_CODE, 22, checkForMissing, getDefaultFieldIssues()));
		
		//recommended
		fields.add(generateFieldDefinition("Admin Amount", Requirement.RECCOMENDED, VxuField.VACCINATION_ADMINISTERED_AMOUNT, 16, checkForMissing, getDefaultFieldIssues()));
		fields.add(generateFieldDefinition("Completion Status", Requirement.RECCOMENDED, VxuField.VACCINATION_COMPLETION_STATUS, 7, checkForMissing, getDefaultFieldIssues()));
		
		//optional
		fields.add(generateFieldDefinition("Facility Id", Requirement.OPTIONAL, VxuField.VACCINATION_FACILITY_ID, 0, checkForMissing, getDefaultFieldIssues()));
		fields.add(generateFieldDefinition("Action Code", Requirement.OPTIONAL, VxuField.VACCINATION_ACTION_CODE, 0, checkForMissing, getDefaultFieldIssues()));
		fields.add(generateFieldDefinition("Refusal Reason", Requirement.OPTIONAL, VxuField.VACCINATION_REFUSAL_REASON, 0, checkForMissing, getDefaultFieldIssues()));
		
		return fields;
	}
	
	public List<DqaReportFieldDefinition> getPatientReportFields() {
		List<DqaReportFieldDefinition> fields = new ArrayList<>();
		boolean checkForMissing = true;
		boolean dontCheckForMissing = true;
		//here's the list: 
	//Required
		//patient-id 40
		fields.add(generateFieldDefinition("Patient Id", Requirement.REQUIRED, VxuField.PATIENT_SUBMITTER_ID, 30, checkForMissing, getDefaultFieldIssues()));
		//first name 20
		fields.add(generateFieldDefinition("First Name", Requirement.REQUIRED, VxuField.PATIENT_NAME_FIRST, 20, checkForMissing, getDefaultFieldIssues()));
		//last name  20
		fields.add(generateFieldDefinition("Last Name", Requirement.REQUIRED, VxuField.PATIENT_NAME_LAST, 20, checkForMissing, getDefaultFieldIssues()));
		//patient name - no default issues. dont' check presense.  100pts?
			//Possible Test Name 1x
			//Possible Baby Name 1x
		//Birth Date
		fields.add(generateFieldDefinition("Birth Date", Requirement.REQUIRED, VxuField.PATIENT_BIRTH_DATE, 20, checkForMissing, getDefaultFieldIssues()));
		//gender
		fields.add(generateFieldDefinition("Sex", Requirement.REQUIRED, VxuField.PATIENT_GENDER, 20, checkForMissing, getDefaultFieldIssues()));
		//Complete Address
		fields.add(generateFieldDefinition("Complete Address", Requirement.REQUIRED, VxuField.PATIENT_ADDRESS, 20, checkForMissing, getDefaultFieldIssues()));
		//street
		fields.add(generateFieldDefinition("Street", Requirement.REQUIRED, VxuField.PATIENT_ADDRESS_STREET, 20, checkForMissing, getDefaultFieldIssues()));
		//city
		fields.add(generateFieldDefinition("City", Requirement.REQUIRED, VxuField.PATIENT_ADDRESS_CITY, 20, checkForMissing, getDefaultFieldIssues()));
		//state
		fields.add(generateFieldDefinition("State", Requirement.REQUIRED, VxuField.PATIENT_ADDRESS_STATE, 20, checkForMissing, getDefaultFieldIssues()));
		//zip
		fields.add(generateFieldDefinition("Zip", Requirement.REQUIRED, VxuField.PATIENT_ADDRESS_ZIP, 20, checkForMissing, getDefaultFieldIssues()));

		//expected
		//middle name
		fields.add(generateFieldDefinition("Middle Name", Requirement.EXPECTED, VxuField.PATIENT_MIDDLE_NAME, 20, checkForMissing, getDefaultFieldIssues()));
		//phone
		fields.add(generateFieldDefinition("Phone", Requirement.EXPECTED, VxuField.PATIENT_PHONE, 20, checkForMissing, getDefaultFieldIssues()));
		//mother's maiden
		fields.add(generateFieldDefinition("Mother's Maiden", Requirement.EXPECTED, VxuField.PATIENT_MOTHERS_MAIDEN_NAME, 20, checkForMissing, getDefaultFieldIssues()));

		//recommended fields
		fields.add(generateFieldDefinition("Ethnicity", Requirement.RECCOMENDED, VxuField.PATIENT_ETHNICITY, 7, checkForMissing, getDefaultFieldIssues()));
		fields.add(generateFieldDefinition("Race", Requirement.RECCOMENDED, VxuField.PATIENT_RACE, 7, checkForMissing, getDefaultFieldIssues()));
		fields.add(generateFieldDefinition("Complete Responsible Party", Requirement.RECCOMENDED, VxuField.PATIENT_GUARDIAN_RESPONSIBLE_PARTY, 1, checkForMissing, getDefaultFieldIssues()));
		fields.add(generateFieldDefinition("First Name", Requirement.RECCOMENDED, VxuField.PATIENT_GUARDIAN_NAME_FIRST, 3, checkForMissing, getDefaultFieldIssues()));
		fields.add(generateFieldDefinition("Last Name", Requirement.RECCOMENDED, VxuField.PATIENT_GUARDIAN_NAME_LAST, 3, checkForMissing, getDefaultFieldIssues()));
		fields.add(generateFieldDefinition("Relationship", Requirement.RECCOMENDED, VxuField.PATIENT_GUARDIAN_RELATIONSHIP, 1, checkForMissing, getDefaultFieldIssues()));
		
		//optional fields
		fields.add(generateFieldDefinition("Birth Indicator", Requirement.OPTIONAL, VxuField.PATIENT_BIRTH_INDICATOR, 0, checkForMissing, getDefaultFieldIssues()));
		fields.add(generateFieldDefinition("Complete Responsible Party Address", Requirement.OPTIONAL, VxuField.PATIENT_GUARDIAN_ADDRESS, 0, checkForMissing, getDefaultFieldIssues()));
		fields.add(generateFieldDefinition("Street", Requirement.OPTIONAL, VxuField.PATIENT_GUARDIAN_ADDRESS_STREET, 0, checkForMissing, getDefaultFieldIssues()));
		fields.add(generateFieldDefinition("City", Requirement.OPTIONAL, VxuField.PATIENT_GUARDIAN_ADDRESS_CITY, 0, checkForMissing, getDefaultFieldIssues()));
		fields.add(generateFieldDefinition("State", Requirement.OPTIONAL, VxuField.PATIENT_GUARDIAN_ADDRESS_STATE, 0, checkForMissing, getDefaultFieldIssues()));
		fields.add(generateFieldDefinition("Zip", Requirement.OPTIONAL, VxuField.PATIENT_GUARDIAN_ADDRESS_ZIP, 0, checkForMissing, getDefaultFieldIssues()));
		fields.add(generateFieldDefinition("Responsible Party Phone", Requirement.OPTIONAL, VxuField.PATIENT_GUARDIAN_PHONE, 0, checkForMissing, getDefaultFieldIssues()));
		fields.add(generateFieldDefinition("Address County", Requirement.OPTIONAL, VxuField.PATIENT_ADDRESS_COUNTY, 0, checkForMissing, getDefaultFieldIssues()));
		fields.add(generateFieldDefinition("Financial Class", Requirement.OPTIONAL, VxuField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE, 0, checkForMissing, getDefaultFieldIssues()));
		fields.add(generateFieldDefinition("Protection Indicator", Requirement.OPTIONAL, VxuField.PATIENT_PROTECTION_INDICATOR, 0, checkForMissing, getDefaultFieldIssues()));
		
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
