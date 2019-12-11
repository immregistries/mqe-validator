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
import org.immregistries.mqe.vxu.VxuField;

public class VaccinationAdministeredUnitIsValid extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationAdministeredAmtIsValid.class,
        VaccinationSourceIsAdministered.class};
  }

  public VaccinationAdministeredUnitIsValid() {
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationAdministeredUnitIsDeprecated);
      id.setHowToFix("The administered amount unit indicated should no longer be used. Please contact your vendor and request that they submit the amount using the correct units.  ");
      id.setWhyToFix("The amount is likely to be not understood properly if it is reported in the wrong unit. The amount is important for creating a complete vaccination record and for properly recording the dosage given for certain vaccines that have different dosages for adults vs children. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationAdministeredUnitIsInvalid);
      id.setHowToFix("The administered amount unit indicated should no longer be used. Please contact your vendor and request that they submit the amount using the correct units.  ");
      id.setWhyToFix("The amount is likely to be not understood properly if it is reported in the wrong unit. The amount is important for creating a complete vaccination record and for properly recording the dosage given for certain vaccines that have different dosages for adults vs children. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationAdministeredUnitIsMissing);
      id.setHowToFix("The amount of vaccination given is indicated but not the unit. Please contact your vendor and request that they ensure the unit is always indicated when reporting the amount. ");
      id.setWhyToFix("The amount is important for creating a complete vaccination record and for properly recording the dosage given for certain vaccines that have different dosages for adults vs children. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationAdministeredUnitIsUnrecognized);
      id.setImplementationDescription("Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
      id.setHowToFix("The amount of vaccination given is indicated but not the unit is not recognized. Please contact your vendor and request that they ensure the correct unit is always indicated when reporting the amount. ");
      id.setWhyToFix("The amount is important for creating a complete vaccination record and for properly recording the dosage given for certain vaccines that have different dosages for adults vs children. ");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = false;

    issues.addAll(codr.handleCodeOrMissing(target.getAmountUnit(),
        VxuField.VACCINATION_ADMINISTERED_UNIT, target));

    passed = (issues.size() == 0);
    return buildResults(issues, passed);

  }
}
