package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.validator.engine.rules.ValidationRuleEntry;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.TargetType;
import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 * Exact-day granularity companion to {@link VaccinationCreationTimeliness} (issue #99): fires
 * when an administered dose's system entry date is <b>exactly</b> N days after its admin date,
 * for N = 2..7 - not "at least N days," which is how {@link VaccinationCreationTimeliness}'s
 * On Time/Late/Very Late/Too Late buckets work. Sits alongside that rule unchanged; this doesn't
 * replace or alter its behavior. Experimental - see {@code docs/detection-status-lifecycle.md}.
 */
@ValidationRuleEntry(TargetType.Vaccination)
public class VaccinationCreationExactDayTimeliness extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationSourceIsAdministered.class, VaccinationCreationDateIsValid.class,
        VaccinationAdminDateIsValid.class};
  }

  public VaccinationCreationExactDayTimeliness() {
    super();
    addExactDayDetection(Detection.VaccinationCreationIsExactly2Days, 2);
    addExactDayDetection(Detection.VaccinationCreationIsExactly3Days, 3);
    addExactDayDetection(Detection.VaccinationCreationIsExactly4Days, 4);
    addExactDayDetection(Detection.VaccinationCreationIsExactly5Days, 5);
    addExactDayDetection(Detection.VaccinationCreationIsExactly6Days, 6);
    addExactDayDetection(Detection.VaccinationCreationIsExactly7Days, 7);
  }

  private void addExactDayDetection(Detection detection, int days) {
    ImplementationDetail id = this.addRuleDetection(detection);
    id.setImplementationDescription("Vaccination Administered Date and System Entry Date are exactly "
        + days + " days apart.");
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<>();

    DateTime admin = new DateTime(target.getAdminDate());
    DateTime entry = new DateTime(target.getSystemEntryDate());
    int diffDays = Days.daysBetween(admin.toLocalDate(), entry.toLocalDate()).getDays();

    switch (diffDays) {
      case 2:
        issues.add(
            Detection.VaccinationCreationIsExactly2Days.build(target.getSystemEntryDateString(), target));
        break;
      case 3:
        issues.add(
            Detection.VaccinationCreationIsExactly3Days.build(target.getSystemEntryDateString(), target));
        break;
      case 4:
        issues.add(
            Detection.VaccinationCreationIsExactly4Days.build(target.getSystemEntryDateString(), target));
        break;
      case 5:
        issues.add(
            Detection.VaccinationCreationIsExactly5Days.build(target.getSystemEntryDateString(), target));
        break;
      case 6:
        issues.add(
            Detection.VaccinationCreationIsExactly6Days.build(target.getSystemEntryDateString(), target));
        break;
      case 7:
        issues.add(
            Detection.VaccinationCreationIsExactly7Days.build(target.getSystemEntryDateString(), target));
        break;
      default:
        break;
    }

    return buildResults(issues, true);
  }

}
