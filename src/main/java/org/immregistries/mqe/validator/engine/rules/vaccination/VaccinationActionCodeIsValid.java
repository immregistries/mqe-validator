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
    this.addRuleDetection(Detection.VaccinationActionCodeIsDeprecated);
    this.addRuleDetection(Detection.VaccinationActionCodeIsInvalid);
    this.addRuleDetection(Detection.VaccinationActionCodeIsMissing);
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationActionCodeIsUnrecognized);
      id.setImplementationDescription(
          "Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
    }
    String baseMessage = "Vaccination Action Code value is ";
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationActionCodeIsValuedAsAdd);
      id.setImplementationDescription(baseMessage.concat(MqeVaccination.ACTION_CODE_ADD));
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationActionCodeIsValuedAsAddOrUpdate);
      id.setImplementationDescription(baseMessage.concat(MqeVaccination.ACTION_CODE_ADD)
          .concat(" or ").concat(MqeVaccination.ACTION_CODE_UPDATE));
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationActionCodeIsValuedAsDelete);
      id.setImplementationDescription(baseMessage.concat(MqeVaccination.ACTION_CODE_DELETE));
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationActionCodeIsValuedAsUpdate);
      id.setImplementationDescription(baseMessage.concat(MqeVaccination.ACTION_CODE_UPDATE));
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
