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

@ValidationRuleEntry(TargetType.Vaccination)
public class VaccinationCvxIsValid extends ValidationRule<MqeVaccination> {

  public VaccinationCvxIsValid() {
    this.addRuleDetection(Detection.VaccinationCvxCodeIsDeprecated);
    this.addRuleDetection(Detection.VaccinationCvxCodeIsInvalid);
    this.addRuleDetection(Detection.VaccinationCvxCodeIsMissing);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationCvxCodeIsUnrecognized);
      id.setImplementationDescription(
          "Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<>(this.codr
        .handleCodeOrMissing(target.getAdminCvxCode(), VxuField.VACCINATION_CVX_CODE, target));
    LOGGER.info("issues: " + issues);

    boolean passed = issues.isEmpty();

    return buildResults(issues, passed);
  }
}
