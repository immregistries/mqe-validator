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
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationVisCvxIsDeprecated);
      id.setHowToFix("The CVX code used to identify the Vaccine Information Statement should no longer be used to identify this statement. Please review the codes used to report VIS statements presented and correct. ");
      id.setWhyToFix("Properly recording the Vaccine Information Statement in the originating medical system is important to qualify for coverage under the Vaccine Compensation Program. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationVisCvxIsInvalid);
      id.setHowToFix("The CVX code used to identify the Vaccine Information Statement should no longer be used to identify this statement. Please review the codes used to report VIS statements presented and correct. ");
      id.setWhyToFix("Properly recording the Vaccine Information Statement in the originating medical system is important to qualify for coverage under the Vaccine Compensation Program. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationVisCvxIsMissing);
      id.setHowToFix("The CVX code used to identify the Vaccine Information Statement was not indicated. Please review the codes used to report VIS statements presented and correct. ");
      id.setWhyToFix("Properly recording the Vaccine Information Statement in the originating medical system is important to qualify for coverage under the Vaccine Compensation Program. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationVisCvxIsUnrecognized);
      id.setImplementationDescription("Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
      id.setHowToFix("The CVX code used to identify the Vaccine Information Statement should no longer be used to identify this statement. Please review the codes used to report VIS statements presented and correct. ");
      id.setWhyToFix("Properly recording the Vaccine Information Statement in the originating medical system is important to qualify for coverage under the Vaccine Compensation Program. ");
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
