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

public class VaccinationBodyRouteAndSiteAreValid extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationSourceIsAdministered.class};
  }

  public VaccinationBodyRouteAndSiteAreValid() {
    this.addRuleDocumentation(codr.getDetectionsForField(VxuField.VACCINATION_BODY_ROUTE));
    this.addRuleDocumentation(codr.getDetectionsForField(VxuField.VACCINATION_BODY_SITE));
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    if (target != null) {// No null pointers!
      String bodySite = target.getBodySite();
      String bodyRoute = target.getBodyRoute();
      
      if (this.common.isEmpty(bodyRoute) || this.common.isEmpty(bodySite)) {
          if (this.common.isEmpty(bodyRoute)) {
        	  issues.add(Detection.VaccinationBodyRouteIsMissing.build(target));
          }
          
          if (this.common.isEmpty(bodySite)) {
        	  issues.add(Detection.VaccinationBodySiteIsMissing.build(target));
          }
      } else {
          issues.addAll(codr.handleCode(bodyRoute, VxuField.VACCINATION_BODY_ROUTE, target));
          issues.addAll(codr.handleCode(bodySite, VxuField.VACCINATION_BODY_SITE, target));
      }
    }

    // These were not implemented in MQE 1.0
    // TODO VaccinationBodyRouteIsInvalidForVaccineIndicated
    // TODO VaccinationBodySiteIsInvalidForVaccineIndicated

    passed = (issues.size() == 0);

    return buildResults(issues, passed);
  }

}
