package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.immregistries.dqa.codebase.client.generated.Code;
import org.immregistries.dqa.codebase.client.generated.UseDate;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.VxuField;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;

public class VaccinationMfrIsValid extends ValidationRule<DqaVaccination> {

	// dependency: VaccinationIsAdministered

	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;

		if (target.isAdministered()) {
			issues.addAll(codr.handleCode(target.getManufacturer(), VxuField.VACCINATION_MANUFACTURER_CODE));
			passed = (issues.size() == 0);
		}

		Code vaccineMvx = repo.getMfrForCode(target.getManufacturerCode());
		
		if (vaccineMvx != null) {
			UseDate ud = vaccineMvx.getUseDate();
		
			if (target.isAdministered() && ud != null && target.getAdminDate() != null) {
					String notBeforeString = ud.getNotBefore();
					String notAfterString = ud.getNotAfter();
					
					Date notBeforeDate = datr.parseDate(notBeforeString);
					Date notAfterDate = datr.parseDate(notAfterString);
					
					String notExpectedBeforeString = ud.getNotExpectedBefore();
					String notExpectedAfterString = ud.getNotExpectedAfter();
					
					Date notExpectedBeforeDate = datr.parseDate(notExpectedBeforeString);
					Date notExpectedAfterDate = datr.parseDate(notExpectedAfterString);
					
					if (datr.isAfterDate(target.getAdminDate(), notAfterDate) 
					 || datr.isBeforeDate(target.getAdminDate(), notBeforeDate)) {
						
						issues.add(Detection.VaccinationManufacturerCodeIsInvalidForDateAdministered.build(target.getManufacturer()));
						passed = false;
						
					} else if (datr.isAfterDate(target.getAdminDate(), notExpectedAfterDate) 
						    || datr.isBeforeDate(target.getAdminDate(), notExpectedBeforeDate)) {
						issues.add(Detection.VaccinationManufacturerCodeIsUnexpectedForDateAdministered.build(target.getManufacturer()));
					}
			}
		}
		passed = issues.isEmpty();

		return buildResults(issues, passed);
	}
}
