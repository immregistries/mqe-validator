package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.codebase.client.generated.Code;
import org.immregistries.codebase.client.reference.CodesetType;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.VxuField;

public class VaccinationCptIsValid extends ValidationRule<MqeVaccination> {


  public VaccinationCptIsValid() {
    this.addRuleDetections(codr.getDetectionsForField(VxuField.VACCINATION_CPT_CODE));
	ImplementationDetail id = this.addRuleDetection(Detection.VaccinationCptCodeIsInvalidForDateAdministered);id.setImplementationDescription("Vaccination CPT Code is outside of expected vaccine date range for the cvx it maps to.");
	ImplementationDetail id = this.addRuleDetection(Detection.VaccinationCptCodeIsUnexpectedForDateAdministered);id.setImplementationDescription("Vaccination CPT Code is outside of licensed vaccine date range for the cvx it maps to.");
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    String cptCode = target.getAdminCptCode();
    Code cpt = repo.getCodeFromValue(cptCode, CodesetType.VACCINATION_CPT_CODE);

    // so what I need to do... is check the CPT code stuff...
    if (!this.common.isEmpty(cptCode)) {

      issues.addAll(this.codr.handleCode(cpt, VxuField.VACCINATION_CPT_CODE, cptCode, target));

      if (issues.size() > 0) {
        passed = false;
      }

      // TODO: figure out if this is the right place for use dates and start dates.
      if (cpt != null && target.getAdminDate() != null) {
        codr.handleUseDate(cpt, target.getAdminDateString(), VxuField.VACCINATION_CPT_CODE, target);
        issues.add(Detection.VaccinationCptCodeIsInvalidForDateAdministered.build(
            target.getAdminCptCode(), target));
        passed = false;
      }
    }

    return buildResults(issues, passed);
  }
}
