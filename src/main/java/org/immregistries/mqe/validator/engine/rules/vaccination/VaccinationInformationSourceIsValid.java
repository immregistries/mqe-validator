package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.VxuField;

public class VaccinationInformationSourceIsValid extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationIsAdministeredOrHistorical.class};
  }

  public VaccinationInformationSourceIsValid() {
      {
        ImplementationDetail id =
            this.addRuleDetection(Detection.VaccinationInformationSourceIsDeprecated);
        id.setHowToFix("The information source, which indicates if the vaccination is administered or historical, is not indicated with a good value. Please contact your software vendor and request that the information source be indicated properly on all historical and administered vaccinations.");
        id.setWhyToFix("The information source indicates if this information is first-hand, recorded by the organization that gave the vaccination, or is second-hand. It is important that all accurate vaccination history be reported including historical immunizations that may be on paper records. A full history allows for proper decision on which vaccinations are due to be given. However, administered vaccinations are held to higher standards for completeness and given higher priority when merging data from multiple sources. Correctly indicating the information source helps create a consolidated vaccination history that is the most accurate. ");
      }
      {
        ImplementationDetail id =
            this.addRuleDetection(Detection.VaccinationInformationSourceIsInvalid);
        id.setHowToFix("The information source, which indicates if the vaccination is administered or historical, is not indicated with a good value. Please contact your software vendor and request that the information source be indicated properly on all historical and administered vaccinations.");
        id.setWhyToFix("The information source indicates if this information is first-hand, recorded by the organization that gave the vaccination, or is second-hand. It is important that all accurate vaccination history be reported including historical immunizations that may be on paper records. A full history allows for proper decision on which vaccinations are due to be given. However, administered vaccinations are held to higher standards for completeness and given higher priority when merging data from multiple sources. Correctly indicating the information source helps create a consolidated vaccination history that is the most accurate. ");
      }
      {
        ImplementationDetail id =
            this.addRuleDetection(Detection.VaccinationInformationSourceIsMissing);
        id.setHowToFix("The information source, which indicates if the vaccination is administered or historical, is not indicated. Please contact your software vendor and request that the information source be indicated on all historical and administered vaccinations.");
        id.setWhyToFix("The information source indicates if this information is first-hand, recorded by the organization that gave the vaccination, or is second-hand. It is important that all accurate vaccination history be reported including historical immunizations that may be on paper records. A full history allows for proper decision on which vaccinations are due to be given. However, administered vaccinations are held to higher standards for completeness and given higher priority when merging data from multiple sources. Correctly indicating the information source helps create a consolidated vaccination history that is the most accurate. ");
      }
      {
        ImplementationDetail id =
            this.addRuleDetection(Detection.VaccinationInformationSourceIsUnrecognized);
        id.setHowToFix("The information source, which indicates if the vaccination is administered or historical, is not indicated with a good value. Please contact your software vendor and request that the information source be indicated properly on all historical and administered vaccinations.");
        id.setWhyToFix("The information source indicates if this information is first-hand, recorded by the organization that gave the vaccination, or is second-hand. It is important that all accurate vaccination history be reported including historical immunizations that may be on paper records. A full history allows for proper decision on which vaccinations are due to be given. However, administered vaccinations are held to higher standards for completeness and given higher priority when merging data from multiple sources. Correctly indicating the information source helps create a consolidated vaccination history that is the most accurate. ");
      }
      
      {
        ImplementationDetail id =
            this.addRuleDetection(Detection.VaccinationInformationSourceIsValuedAsAdministered);
        id.setHowToFix("The vaccination is indicated as being administered at your location. Reports of administered vaccinations is not expected for this interface and should not be submitted. Ensure you are entering the immunizations in the right place in the application or please contact your vendor and request that administered vaccinations must not be submitted to the IIS. ");
        id.setWhyToFix("The information source indicates if this information is first-hand, recorded by the organization that gave the vaccination, or is second-hand. It is important that all accurate vaccination history be reported including historical immunizations that may be on paper records. A full history allows for proper decision on which vaccinations are due to be given. However, administered vaccinations are held to higher standards for completeness and given higher priority when merging data from multiple sources. Correctly indicating the information source helps create a consolidated vaccination history that is the most accurate. ");
      }

      {
        ImplementationDetail id =
            this.addRuleDetection(Detection.VaccinationInformationSourceIsValuedAsHistorical);
        id.setHowToFix("The vaccination information is indicated as coming from a historical source. Reports of non-administered vaccinations is not expected for this interface and should not be submitted. Ensure you are entering the immunizations in the right place in the application or please contact your vendor and request that historical vaccinations must not be submitted to the IIS. ");
        id.setWhyToFix("The information source indicates if this information is first-hand, recorded by the organization that gave the vaccination, or is second-hand. It is important that all accurate vaccination history be reported including historical immunizations that may be on paper records. A full history allows for proper decision on which vaccinations are due to be given. However, administered vaccinations are held to higher standards for completeness and given higher priority when merging data from multiple sources. Correctly indicating the information source helps create a consolidated vaccination history that is the most accurate. ");
      }
  }

  /*
   * This is the money:
   */

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    String sourceCd = target.getInformationSourceCode();

	  issues.addAll(this.codr.handleCodeOrMissing(target.getInformationSource(),
	      VxuField.VACCINATION_INFORMATION_SOURCE, target));

	  passed = (issues.size() == 0);
	
	  switch (sourceCd) {
	    case MqeVaccination.INFO_SOURCE_ADMIN:
	      issues.add(Detection.VaccinationInformationSourceIsValuedAsAdministered.build((sourceCd),
	          target));
	      break;
	    default:
	      issues.add(Detection.VaccinationInformationSourceIsValuedAsHistorical.build((sourceCd),
	          target));
	      break;
	  }
    

    return buildResults(issues, passed);
  }
}
