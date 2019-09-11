package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.joda.time.DateTime;
import org.joda.time.Days;

public class VaccinationCreationTimeliness extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationSourceIsAdministered.class, VaccinationCreationDateIsValid.class,
        VaccinationAdminDateIsValid.class};
  }

  public VaccinationCreationTimeliness() {
    super();
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationCreationIsVeryLate);
      id.setImplementationDescription(
          "Vaccination Administered Date and System Entry Date are more than 14 days but less than or equal to 30 days apart.");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }


    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationCreationIsTooLate);
      id.setImplementationDescription(
          "Vaccination Administered Date and System Entry Date are over 30 days apart.");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationCreationIsOnTime);
      id.setImplementationDescription(
          "Vaccination Administered Date and System Entry Date less than or equal to 3 days of eachother.");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationCreationIsLate);
      id.setImplementationDescription(
          "Vaccination Administered Date and System Entry Date are are more than 3 days but less than or equal to 14 days apart.");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<>();

    int w1 = 3;
    int w2 = 14;
    int w3 = 30;

    DateTime admin = new DateTime(target.getAdminDate());
    DateTime entry = new DateTime(target.getSystemEntryDate());
    Days difference = Days.daysBetween(admin.toLocalDate(), entry.toLocalDate());

    if (difference.getDays() <= w1) {
      issues.add(
          Detection.VaccinationCreationIsOnTime.build(target.getSystemEntryDateString(), target));
    } else if (difference.getDays() > w1 && difference.getDays() <= w2) {
      issues.add(
          Detection.VaccinationCreationIsLate.build(target.getSystemEntryDateString(), target));
    } else if (difference.getDays() > w2 && difference.getDays() <= w3) {
      issues.add(
          Detection.VaccinationCreationIsVeryLate.build(target.getSystemEntryDateString(), target));
    } else if (difference.getDays() > w3) {
      issues.add(
          Detection.VaccinationCreationIsTooLate.build(target.getSystemEntryDateString(), target));
    }

    return buildResults(issues, true);
  }

}
