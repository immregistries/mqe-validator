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
import org.immregistries.mqe.vxu.hl7.Id;

public class VaccinationOrdererIsValid extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationSourceIsAdministered.class};
  }

  public VaccinationOrdererIsValid() {
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationOrderedByIsDeprecated);
      id.setHowToFix("The orderer of the vaccination is not recognized. ");
      id.setWhyToFix("The orderer of the vaccination may be contacted if there are questions about the administration of a vaccination.");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationOrderedByIsInvalid);
      id.setHowToFix("The orderer of the vaccination is not recognized. ");
      id.setWhyToFix("The orderer of the vaccination may be contacted if there are questions about the administration of a vaccination.");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationOrderedByIsMissing);
      id.setHowToFix("The orderer of the vaccination is not indicated. ");
      id.setWhyToFix("The orderer of the vaccination may be contacted if there are questions about the administration of a vaccination.");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationOrderedByIsUnrecognized);
      id.setImplementationDescription("Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
      id.setHowToFix("The orderer of the vaccination is not recognized. ");
      id.setWhyToFix("The orderer of the vaccination may be contacted if there are questions about the administration of a vaccination.");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationRecordedByIsDeprecated);
      id.setHowToFix("The recorder of the vaccination is not recognized. ");
      id.setWhyToFix("The recorder of the vaccination may be contacted if there are questions about the administration of a vaccination.");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationRecordedByIsInvalid);
      id.setHowToFix("The recorder of the vaccination is not recognized. ");
      id.setWhyToFix("The recorder of the vaccination may be contacted if there are questions about the administration of a vaccination.");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationRecordedByIsMissing);
      id.setHowToFix("The recorder of the vaccination is not indicated. ");
      id.setWhyToFix("The recorder of the vaccination may be contacted if there are questions about the administration of a vaccination.");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationRecordedByIsUnrecognized);
      id.setImplementationDescription("Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
      id.setHowToFix("The recorder of the vaccination is not recognized. ");
      id.setWhyToFix("The recorder of the vaccination may be contacted if there are questions about the administration of a vaccination.");
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
