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
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationBodyRouteIsDeprecated);
      id.setHowToFix("The vaccination body route (into what tissue the vaccination was administered) is being reported with an old concept code. Please ask your vendor to update the values being sent for body route. ");
      id.setWhyToFix("While the route used for vaccination is normally set for a type of vaccination and so can often be correctly assumed, it is important for creating a complete immunization history to correctly indicate where the vaccination was actually administered. There are cases where it is appropriate for the body route to be different than indicated, and even if the vaccination was administered in a non-standard location it should be correctly reported where the vaccination was actually given. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationBodyRouteIsInvalid);
      id.setHowToFix("The vaccination body route (into what tissue the vaccination was administered) is being reported with an old concept code. Please ask your vendor to update the values being sent for body route. ");
      id.setWhyToFix("While the route used for vaccination is normally set for a type of vaccination and so can often be correctly assumed, it is important for creating a complete immunization history to correctly indicate where the vaccination was actually administered. There are cases where it is appropriate for the body route to be different than indicated, and even if the vaccination was administered in a non-standard location it should be correctly reported where the vaccination was actually given. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationBodyRouteIsMissing);
      id.setHowToFix("The vaccination body route (into what tissue the vaccination was administered) is not being reported. Please ensure you are recording this. ");
      id.setWhyToFix("While the route used for vaccination is normally set for a type of vaccination and so can often be correctly assumed, it is important for creating a complete immunization history to correctly indicate where the vaccination was actually administered. There are cases where it is appropriate for the body route to be different than indicated, and even if the vaccination was administered in a non-standard location it should be correctly reported where the vaccination was actually given. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationBodyRouteIsUnrecognized);
      id.setImplementationDescription("Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
      id.setHowToFix("The vaccination body route (into what tissue the vaccination was administered) is not recognized. Please ask your vendor to ensure that only valid body route codes are being reported. ");
      id.setWhyToFix("While the route used for vaccination is normally set for a type of vaccination and so can often be correctly assumed, it is important for creating a complete immunization history to correctly indicate where the vaccination was actually administered. There are cases where it is appropriate for the body route to be different than indicated, and even if the vaccination was administered in a non-standard location it should be correctly reported where the vaccination was actually given. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationBodySiteIsDeprecated);
      id.setHowToFix("The vaccination body site (the location on the human body where the vaccination was injected or given) is being reported with an old concept code. Please ask your vendor to update the values being sent for body site. ");
      id.setWhyToFix("The body site helps to provide a full vaccination history of where a vaccination was given and may be used to provide context when there are local reactions to this vaccination. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationBodySiteIsInvalid);
      id.setHowToFix("The vaccination body site (the location on the human body where the vaccination was injected or given) is being reported with an old concept code. Please ask your vendor to update the values being sent for body site. ");
      id.setWhyToFix("The body site helps to provide a full vaccination history of where a vaccination was given and may be used to provide context when there are local reactions to this vaccination. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationBodySiteIsMissing);
      id.setHowToFix("The vaccination body site (the location on the human body where the vaccination was injected or given) is not being reported. Please ensure you have indicated it and if so please ask your vendor to ensure that it is reported. ");
      id.setWhyToFix("The body site helps to provide a full vaccination history of where a vaccination was given and may be used to provide context when there are local reactions to this vaccination. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationBodySiteIsUnrecognized);
      id.setImplementationDescription("Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
      id.setHowToFix("The vaccination body site (the location on the human body where the vaccination was injected or given) is being reported with an unrecognized concept code. Please ask your vendor to update the values being sent for body site. ");
      id.setWhyToFix("The body site helps to provide a full vaccination history of where a vaccination was given and may be used to provide context when there are local reactions to this vaccination. ");
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
