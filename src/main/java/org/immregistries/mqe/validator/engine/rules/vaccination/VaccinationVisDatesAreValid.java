package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.VaccinationVIS;

public class VaccinationVisDatesAreValid extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationVisIsPresent.class, VaccinationIsAdministered.class};
  }

  public VaccinationVisDatesAreValid() {
    ruleDetections.addAll(Arrays.asList(Detection.VaccinationVisPublishedDateIsMissing,
        Detection.VaccinationVisPresentedDateIsInvalid,
        Detection.VaccinationVisPresentedDateIsMissing,
        Detection.VaccinationVisPublishedDateIsInvalid,
        Detection.VaccinationVisPresentedDateIsAfterAdminDate,
        Detection.VaccinationVisPresentedDateIsNotAdminDate,
        Detection.VaccinationVisPresentedDateIsBeforePublishedDate));
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = false;

    VaccinationVIS vis = target.getVaccinationVis();

    Date publishedDate = vis.getPublishedDate();
    Date presentedDate = vis.getPresentedDate();
    String presentedDateString = vis.getPresentedDateString();
    String publishedDateString = vis.getPublishedDateString();

    // If the published date string is NOT null, but it's not a valid date, you'll get into the ELSE
    // IF block.
    if (publishedDateString == null) {
      issues.add(Detection.VaccinationVisPublishedDateIsMissing.build(target));
    } else if (publishedDate == null) {// it didn't parse to a date.
      issues.add(Detection.VaccinationVisPublishedDateIsInvalid.build(vis.getPublishedDateString(),
          target));
    }

    if (presentedDateString == null) {
      issues.add(Detection.VaccinationVisPresentedDateIsMissing.build(target));
    } else if (presentedDate == null) {// it didn't parse to a date.
      issues.add(Detection.VaccinationVisPresentedDateIsInvalid.build(vis.getPresentedDateString(),
          target));
    }
    
    if (publishedDate != null) {
    	Calendar now = Calendar.getInstance();
    	 if (datr.isAfterDate(publishedDate, now.getTime())) {
    		 issues.add(Detection.VaccinationVisPublishedDateIsInFuture.build(vis.getPresentedDateString(),
    		          target));
    	 }
    }
    
    if (publishedDate != null && presentedDate != null) {
        if (datr.isBeforeDate(presentedDate, publishedDate)) {
          issues.add(Detection.VaccinationVisPresentedDateIsBeforePublishedDate.build(target));
        }
    }


    Date adminDate = target.getAdminDate();
    if (adminDate != null) {
      if (presentedDate != null) {
        if (datr.isAfterDate(presentedDate, adminDate)) {
          issues.add(Detection.VaccinationVisPresentedDateIsAfterAdminDate.build(
              (presentedDateString), target));
        } else if (datr.isBeforeDate(presentedDate, adminDate)) {
          issues.add(Detection.VaccinationVisPresentedDateIsNotAdminDate.build(
              (presentedDateString), target));
        }
      }
    }

    passed = issues.isEmpty();
    return buildResults(issues, passed);
  }
}
