package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.vxu.VxuField;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;
import org.immregistries.dqa.vxu.VaccinationVIS;

public class VaccinationVisDatesAreValid extends
		ValidationRule<DqaVaccination> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {VaccinationVisIsPresent.class, VaccinationIsAdministered.class };
	}
	
	public VaccinationVisDatesAreValid() {
		ruleDetections.addAll(Arrays.asList(
				Detection.VaccinationVisPublishedDateIsMissing,
				Detection.VaccinationVisPresentedDateIsInvalid,
				Detection.VaccinationVisPresentedDateIsMissing,
				Detection.VaccinationVisPublishedDateIsInvalid,
				Detection.VaccinationVisPresentedDateIsAfterAdminDate,
				Detection.VaccinationVisPresentedDateIsNotAdminDate,
				Detection.VaccinationVisPresentedDateIsBeforePublishedDate
		));
	}

	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = false;

		VaccinationVIS vis = target.getVaccinationVis();

		Date publishedDate = vis.getPublishedDate();
		Date presentedDate = vis.getPresentedDate();
		String presentedDateString = vis.getPresentedDateString();
		String publishedDateString = vis.getPublishedDateString();

		//If the published date string is NOT null, but it's not a valid date, you'll get into the ELSE IF block. 
		if (publishedDateString == null) {
			issues.add(Detection.VaccinationVisPublishedDateIsMissing.build());
		} else if (publishedDate == null) {//it didn't parse to a date. 
			issues.add(Detection.VaccinationVisPresentedDateIsInvalid.build(vis.getPublishedDateString()));
		}
		
		if (presentedDateString == null) {
			issues.add(Detection.VaccinationVisPresentedDateIsMissing.build());
		} else if (presentedDate == null) {//it didn't parse to a date. 
			issues.add(Detection.VaccinationVisPublishedDateIsInvalid.build(vis.getPresentedDateString()));
		} 
				
		Date adminDate = target.getAdminDate();
		if (adminDate != null) {
			if (presentedDate != null) {
				if (datr.isAfterDate(presentedDate, adminDate)) {
					issues.add(Detection.VaccinationVisPresentedDateIsAfterAdminDate.build(presentedDateString));
				}
			}
			
			if (presentedDate != null) {
				if (datr.isAfterDate(presentedDate, adminDate)) {
					issues.add(Detection.VaccinationVisPresentedDateIsAfterAdminDate.build(presentedDateString));
				} else if (datr.isBeforeDate(presentedDate,  adminDate)) {
					issues.add(Detection.VaccinationVisPresentedDateIsNotAdminDate.build(presentedDateString));
				}
			}
			
			if (publishedDate != null && presentedDate != null) {
				if (datr.isBeforeDate(presentedDate, publishedDate)) {
					issues.add(Detection.VaccinationVisPresentedDateIsBeforePublishedDate.build());
				}
			}
			
		}	
		
		passed = issues.isEmpty();
		return buildResults(issues, passed);
	}
}
