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
import org.immregistries.mqe.vxu.VxuField;
import org.immregistries.mqe.vxu.hl7.Id;

@ValidationRuleEntry(TargetType.Vaccination)
public class VaccinationOrdererIsValid extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationSourceIsAdministered.class};
  }

  public VaccinationOrdererIsValid() {
    this.addRuleDetection(Detection.VaccinationOrderedByIsDeprecated);
    this.addRuleDetection(Detection.VaccinationOrderedByIsInvalid);
    this.addRuleDetection(Detection.VaccinationOrderedByIsMissing);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationOrderedByIsUnrecognized);
      id.setImplementationDescription(
          "Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
    }
    this.addRuleDetection(Detection.VaccinationRecordedByIsDeprecated);
    this.addRuleDetection(Detection.VaccinationRecordedByIsInvalid);
    this.addRuleDetection(Detection.VaccinationRecordedByIsMissing);
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationRecordedByIsUnrecognized);
      id.setImplementationDescription(
          "Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
    }

  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {
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
