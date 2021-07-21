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
public class VaccinationAdminCodeCptIsValid extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationUseCptInsteadOfCvx.class};
  }

  public VaccinationAdminCodeCptIsValid() {
    this.addRuleDetection(Detection.VaccinationCptCodeIsDeprecated);
    this.addRuleDetection(Detection.VaccinationCptCodeIsInvalid);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationCptCodeIsUnrecognized);
      id.setImplementationDescription(
          "Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
    }
    this.addRuleDetection(Detection.VaccinationCptCodeIsMissing);
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    String cpt = target.getAdminCptCode();

    issues.addAll(codr.handleCode(cpt, VxuField.VACCINATION_CPT_CODE, target));

    passed = (issues.size() == 0);

    return buildResults(issues, passed);
  }

}
