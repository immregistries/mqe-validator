package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationReport;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;

public class VaccinationSystemEntryTimeIsValid extends ValidationRule<DqaVaccination> {

  // dependency: VaccinationIsAdministered

  public VaccinationSystemEntryTimeIsValid() {
    ruleDetections.addAll(Arrays.asList(Detection.VaccinationSystemEntryTimeIsMissing,
        Detection.VaccinationSystemEntryTimeIsInFuture));
  }

  @Override
  protected ValidationRuleResult executeRule(DqaVaccination target, DqaMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    if (target.getSystemEntryDate() == null) {
      issues.add(Detection.VaccinationSystemEntryTimeIsMissing.build(target));
    } else if (datr.isBeforeDate(m.getReceivedDate(), target.getSystemEntryDate())) {
      issues.add(Detection.VaccinationSystemEntryTimeIsInFuture.build(target));
    }

    passed = (issues.size() == 0);

    return buildResults(issues, passed);
  }
}
