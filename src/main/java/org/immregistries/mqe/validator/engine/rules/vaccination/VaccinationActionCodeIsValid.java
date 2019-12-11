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

public class VaccinationActionCodeIsValid extends ValidationRule<MqeVaccination> {


  public VaccinationActionCodeIsValid() {
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationActionCodeIsDeprecated);
      id.setHowToFix("This vaccination action code should no longer be used. Please contact your software vendor and request that the vaccination action code be updated to the correct value.");
      id.setWhyToFix("Sending the right action code will ensure that the patient's vaccinations are properly recorded.");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationActionCodeIsInvalid);
      id.setHowToFix("This vaccination action code should no longer be used. Please contact your software vendor and request that the vaccination action code be updated to the correct value.");
      id.setWhyToFix("Sending the right action code will ensure that the patient's vaccinations are properly recorded.");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationActionCodeIsMissing);
      id.setHowToFix("Vaccination information was submitted without indicating whether it is to be added or removed from the official vaccination record. Pelase contact your software vendor and request that the vaccination action code be set on all vaccinations so that the complete record can be properly recorded. ");
      id.setWhyToFix("Sending the right action code will ensure that the patient's vaccinations are properly recorded.");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationActionCodeIsUnrecognized);
      id.setImplementationDescription("Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
      id.setHowToFix("This vaccination action code was not recognized. Please contact your software vendor and request that the vaccination action code be updated to the correct value.");
      id.setWhyToFix("Sending the right action code will ensure that the patient's vaccinations are properly recorded.");
    }
    String baseMessage = "Vaccination Action Code value is ";
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationActionCodeIsValuedAsAdd);
      id.setImplementationDescription(baseMessage.concat(MqeVaccination.ACTION_CODE_ADD));
      id.setHowToFix("If this vaccination was given to the patient or was known to have been given to the patient then there is nothing to fix. This issue is used for reporting purposes and does not necessarily indicate a problem situation. ");
      id.setWhyToFix("Sending the right action code will ensure that the patient's vaccinations are properly recorded.");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationActionCodeIsValuedAsAddOrUpdate);
      id.setImplementationDescription(baseMessage.concat(MqeVaccination.ACTION_CODE_ADD)
          .concat(" or ").concat(MqeVaccination.ACTION_CODE_UPDATE));
      id.setHowToFix("If this vaccination was given to the patient or was known to have been given to the patient then there is nothing to fix. This issue is used for reporting purposes and does not necessarily indicate a problem situation. ");
      id.setWhyToFix("Sending the right action code will ensure that the patient's vaccinations are properly recorded.");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationActionCodeIsValuedAsDelete);
      id.setImplementationDescription(baseMessage.concat(MqeVaccination.ACTION_CODE_DELETE));
      id.setHowToFix("If this vaccination was NOT given to the patient or was recorded in error and needs to be removed from the record then there is nothing to fix. This issue is used for reporting purposes and does not necessarily indicate a problem situation. ");
      id.setWhyToFix("Sending the right action code will ensure that the patient's vaccinations are properly recorded.");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationActionCodeIsValuedAsUpdate);
      id.setImplementationDescription(baseMessage.concat(MqeVaccination.ACTION_CODE_UPDATE));
      id.setHowToFix("If this vaccination was given to the patient or was known to have been given to the patient then there is nothing to fix. This issue is used for reporting purposes and does not necessarily indicate a problem situation. ");
      id.setWhyToFix("Sending the right action code will ensure that the patient's vaccinations are properly recorded.");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    issues.addAll(this.codr.handleCodeOrMissing(target.getAction(),
        VxuField.VACCINATION_ACTION_CODE, target));

    if (issues.size() > 0) {
      passed = false;
    } else {
      String actionCode = target.getActionCode();

      if (target.isActionAdd()) {
        issues.add(Detection.VaccinationActionCodeIsValuedAsAdd.build((actionCode), target));
        issues
            .add(Detection.VaccinationActionCodeIsValuedAsAddOrUpdate.build((actionCode), target));
      } else if (target.isActionUpdate()) {
        issues.add(Detection.VaccinationActionCodeIsValuedAsUpdate.build((actionCode), target));
        issues
            .add(Detection.VaccinationActionCodeIsValuedAsAddOrUpdate.build((actionCode), target));
      } else if (target.isActionDelete()) {
        issues.add(Detection.VaccinationActionCodeIsValuedAsDelete.build((actionCode), target));
      } else {
        issues.add(Detection.VaccinationActionCodeIsInvalid.build(target));
        passed = false;
      }

    }

    return buildResults(issues, passed);

  }
}
