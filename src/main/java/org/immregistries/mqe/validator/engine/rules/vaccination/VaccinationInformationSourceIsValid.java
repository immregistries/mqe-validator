package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.VxuField;

public class VaccinationInformationSourceIsValid extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationIsAdministeredOrHistorical.class};
  }

  public VaccinationInformationSourceIsValid() {
    this.addRuleDetection(Detection.VaccinationInformationSourceIsDeprecated);
    this.addRuleDetection(Detection.VaccinationInformationSourceIsInvalid);
    this.addRuleDetection(Detection.VaccinationInformationSourceIsMissing);
    this.addRuleDetection(Detection.VaccinationInformationSourceIsPresent);
    this.addRuleDetection(Detection.VaccinationInformationSourceIsUnrecognized);
    this.addRuleDetection(Detection.VaccinationInformationSourceIsValuedAsAdministered);
    this.addRuleDetection(Detection.VaccinationInformationSourceIsValuedAsHistorical);
  }

  /*
   * This is the money:
   */

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    String sourceCd = target.getInformationSourceCode();

    issues.addAll(this.codr.handleCodeOrMissing(target.getInformationSource(),
        VxuField.VACCINATION_INFORMATION_SOURCE, target));

    passed = verifyPassed(issues);

    switch (sourceCd) {
      case MqeVaccination.INFO_SOURCE_ADMIN:
        issues.add(
            Detection.VaccinationInformationSourceIsValuedAsAdministered.build((sourceCd), target));
        break;
      default:
        issues.add(
            Detection.VaccinationInformationSourceIsValuedAsHistorical.build((sourceCd), target));
        break;
    }


    return buildResults(issues, passed);
  }
}
