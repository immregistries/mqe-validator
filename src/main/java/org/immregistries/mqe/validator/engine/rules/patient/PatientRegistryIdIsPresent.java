package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;

public class PatientRegistryIdIsPresent extends ValidationRule<MqePatient> {

  public PatientRegistryIdIsPresent() {
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientRegistryIdIsMissing);
      id.setHowToFix("The IIS registry id was not recognized. Please contact your software vendor to verify the need for sending "
          + "the IIS registry id. ");
      id.setWhyToFix("The IIS id is used for identifying the specific patient that is to be updated or queried in the IIS. "
          + "This is normally used in queries and is not expected on updates. ");
    }

  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<>();
    String regNum = target.getIdRegistryNumber();
    if (StringUtils.isEmpty(regNum)) {
      issues.add(Detection.PatientRegistryIdIsMissing.build(target));
    }
    boolean passed = issues.size() == 0;
    return buildResults(issues, passed);
  }

}
