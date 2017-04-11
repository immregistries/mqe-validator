package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.IssueType;
import org.immregistries.dqa.validator.issue.MessageAttribute;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.validator.issue.VxuField;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;

public class PatientCodesAreValid extends ValidationRule<DqaPatient> {

	@Override
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		
		if (target == null) {
			return buildResults(issues, false);
		}

		if ("Y".equals(target.getBirthMultipleInd())) {
			issues.addAll(this.codr.handleCode(target.getBirthOrderNumber(), VxuField.PATIENT_BIRTH_ORDER));
		}

		issues.addAll(this.codr.handleCode(target.getEthnicity(), VxuField.PATIENT_ETHNICITY));
		issues.addAll(this.codr.handleCode(target.getSex(), VxuField.PATIENT_GENDER));
		issues.addAll(this.codr.handleCode(target.getName().getType(), VxuField.PATIENT_NAME_TYPE_CODE));

		if (target.getFacility() != null) {
			String facilityId = target.getFacility().getId();
			if (StringUtils.isBlank(facilityId)) {
				ValidationIssue ifnd = MessageAttribute.buildIssue(VxuField.PATIENT_PRIMARY_FACILITY_ID
						, IssueType.MISSING, facilityId);
				issues.add(ifnd);
			}
		}
		
		issues.addAll(this.codr.handleCode(target.getPrimaryLanguage(), VxuField.PATIENT_PRIMARY_LANGUAGE));
		issues.addAll(this.codr.handleCode(target.getPhysician().getNumber(), VxuField.PATIENT_PRIMARY_PHYSICIAN_ID));
		issues.addAll(this.codr.handleCode(target.getProtection(), VxuField.PATIENT_PROTECTION_INDICATOR));
		issues.addAll(this.codr.handleCode(target.getPublicity(), VxuField.PATIENT_PUBLICITY_CODE));
		issues.addAll(this.codr.handleCode(target.getRace(), VxuField.PATIENT_RACE));
		issues.addAll(this.codr.handleCode(target.getFinancialEligibility(), VxuField.PATIENT_VFC_STATUS));

		// mark passed if there's no issues.
		passed = (issues.size() == 0);
		return buildResults(issues, passed);
	}

}
