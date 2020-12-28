package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.VaccinationVIS;
import org.immregistries.mqe.vxu.VxuField;

public class VaccinationVisCvxIsValid extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationVisIsPresent.class, VaccinationSourceIsAdministered.class};
  }

  public VaccinationVisCvxIsValid() {
    this.addRuleDetection(Detection.VaccinationVisCvxIsDeprecated);
    this.addRuleDetection(Detection.VaccinationVisCvxIsInvalid);
    this.addRuleDetection(Detection.VaccinationVisCvxIsMissing);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationVisCvxIsUnrecognized);
      id.setImplementationDescription(
          "Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
    }
  }


  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = false;

    VaccinationVIS vis = target.getVaccinationVis();
    String visCvx = vis.getCvxCode();

    issues.addAll(codr.handleCodeOrMissing(visCvx, VxuField.VACCINATION_VIS_CVX_CODE, target));

    passed = issues.isEmpty();
    return buildResults(issues, passed);
  }
}
