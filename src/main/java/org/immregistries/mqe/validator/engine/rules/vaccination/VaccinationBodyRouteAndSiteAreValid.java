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

public class VaccinationBodyRouteAndSiteAreValid extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationSourceIsAdministered.class};
  }

  public VaccinationBodyRouteAndSiteAreValid() {
    this.addRuleDetection(Detection.VaccinationBodyRouteIsDeprecated);
    this.addRuleDetection(Detection.VaccinationBodyRouteIsInvalid);
    this.addRuleDetection(Detection.VaccinationBodyRouteIsMissing);
    this.addRuleDetection(Detection.VaccinationBodyRouteIsPresent);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationBodyRouteIsUnrecognized);
      id.setImplementationDescription(
          "Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
    }
    this.addRuleDetection(Detection.VaccinationBodySiteIsDeprecated);
    this.addRuleDetection(Detection.VaccinationBodySiteIsInvalid);
    this.addRuleDetection(Detection.VaccinationBodySiteIsMissing);
    this.addRuleDetection(Detection.VaccinationBodySiteIsPresent);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationBodySiteIsUnrecognized);
      id.setImplementationDescription(
          "Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    if (target != null) {// No null pointers!
      String bodySite = target.getBodySite();
      String bodyRoute = target.getBodyRoute();

      issues.addAll(codr.handleCodeOrMissing(bodyRoute, VxuField.VACCINATION_BODY_ROUTE, target));
      issues.addAll(codr.handleCodeOrMissing(bodySite, VxuField.VACCINATION_BODY_SITE, target));

    }

    // These were not implemented in MQE 1.0
    // TODO VaccinationBodyRouteIsInvalidForVaccineIndicated
    // TODO VaccinationBodySiteIsInvalidForVaccineIndicated

    passed = (issues.size() == 0);

    return buildResults(issues, passed);
  }

}
