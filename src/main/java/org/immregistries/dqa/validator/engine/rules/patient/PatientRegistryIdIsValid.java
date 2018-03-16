package org.immregistries.dqa.validator.engine.rules.patient;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.VxuField;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;

import java.util.ArrayList;
import java.util.List;

public class PatientRegistryIdIsValid extends ValidationRule<DqaPatient> {

	public PatientRegistryIdIsValid() {
		ruleDetections.addAll(codr.getDetectionsForField(VxuField.PATIENT_REGISTRY_ID));
	}
	
    @Override
    protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
        List<ValidationIssue> issues = new ArrayList<>();
        boolean passed = true;

        String regNum = target.getIdRegistryNumber();
        issues.addAll(codr.handleCode(regNum, VxuField.PATIENT_REGISTRY_ID));

//		if (common.isEmpty(regNum)) {
//			issues.add(MessageAttribute.get(VxuField.PATIENT_REGISTRY_ID, IssueType.MISSING).build());
//	    }

        // TODO PatientRegistryIdIsUnrecognized--can we use codr instead? I can't get it to work

        passed = issues.size() == 0;
        return buildResults(issues, passed);
    }

}
