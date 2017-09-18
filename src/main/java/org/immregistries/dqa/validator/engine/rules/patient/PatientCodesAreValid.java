package org.immregistries.dqa.validator.engine.rules.patient;

import org.apache.commons.lang3.StringUtils;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.validator.issue.VxuField;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;

import java.util.ArrayList;
import java.util.List;

/**
 * Covers several cases. Replaces patient ethnicity, gender, and name type validation classes.
 */
public class PatientCodesAreValid extends ValidationRule<DqaPatient> {
    @Override
    protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
        List<ValidationIssue> issues = new ArrayList<>();
        boolean passed;

        if (target == null) {
            return buildResults(issues, false);
        }

        issues.addAll(this.codr.handleCode(target.getEthnicity(), VxuField.PATIENT_ETHNICITY));
        issues.addAll(this.codr.handleCode(target.getSex(), VxuField.PATIENT_GENDER));

        // name type
        issues.addAll(this.codr.handleCode(target.getName().getType(), VxuField.PATIENT_NAME_TYPE_CODE));

        // name code is supposed to be L for legal
        if (!"L".equals(target.getNameTypeCode())) {
            issues.add(Detection.PatientNameTypeCodeIsNotValuedLegal.build());
        }

        // facility
        String facilityId = target.getFacilityIdNumber();
        String facilityName = target.getFacilityName();

        if (StringUtils.isBlank(facilityId)) {
            issues.add(Detection.PatientPrimaryFacilityIdIsMissing.build(facilityId));
        }

        if (StringUtils.isBlank(facilityName)) {
            issues.add(Detection.PatientPrimaryFacilityNameIsMissing.build(facilityName));
        }

        // language
        issues.addAll(this.codr.handleCode(target.getPrimaryLanguage(), VxuField.PATIENT_PRIMARY_LANGUAGE));

        // physician
        // TODO: primary physician isn't in the list of validation rules at all...
//        issues.addAll(this.codr.handleCode(target.getPhysician().getNumber(), VxuField.PATIENT_PRIMARY_PHYSICIAN_ID));

        // publicity code
        issues.addAll(this.codr.handleCode(target.getPublicity(), VxuField.PATIENT_PUBLICITY_CODE));

        // race
        issues.addAll(this.codr.handleCode(target.getRace(), VxuField.PATIENT_RACE));

        // VFC/financial eligibility status
        issues.addAll(this.codr.handleCode(target.getFinancialEligibility(), VxuField.PATIENT_VFC_STATUS));

        // mark passed if there's no issues
        passed = (issues.size() == 0);
        return buildResults(issues, passed);
    }

}
