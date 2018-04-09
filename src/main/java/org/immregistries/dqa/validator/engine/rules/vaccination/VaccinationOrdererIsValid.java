package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.dqa.validator.detection.ValidationReport;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;
import org.immregistries.dqa.vxu.VxuField;
import org.immregistries.dqa.vxu.hl7.Id;

public class VaccinationOrdererIsValid extends ValidationRule<DqaVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationIsAdministered.class};
  }

  public VaccinationOrdererIsValid() {
    ruleDetections.addAll(codr.getDetectionsForField(VxuField.VACCINATION_ORDERED_BY));
    ruleDetections.addAll(codr.getDetectionsForField(VxuField.VACCINATION_RECORDED_BY));
  }

  @Override
  protected ValidationRuleResult executeRule(DqaVaccination target, DqaMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    // Orderer
    Id orderedBy = target.getOrderedBy();
    String ordererNum = null;
    if (orderedBy != null) {
      ordererNum = orderedBy.getNumber();
    }

    issues.addAll(codr.handleCode(ordererNum, VxuField.VACCINATION_ORDERED_BY, target));

    // Recorder
    Id enteredBy = target.getEnteredBy();
    String recorderNum = null;
    if (enteredBy != null) {
      recorderNum = enteredBy.getNumber();
    }

    issues.addAll(codr.handleCode(recorderNum, VxuField.VACCINATION_RECORDED_BY, target));

    // mark passed if there's no issues.
    passed = (issues.size() == 0);

    return buildResults(issues, passed);
  }

}
