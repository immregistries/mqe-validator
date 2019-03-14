package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
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
	  this.addRuleDocumentation(Arrays.asList(Detection.VaccinationInformationSourceIsDeprecated,
		        Detection.VaccinationInformationSourceIsIgnored,
		    	Detection.VaccinationInformationSourceIsInvalid,
		    	Detection.VaccinationInformationSourceIsMissing,
		    	Detection.VaccinationInformationSourceIsUnrecognized,
		    	Detection.VaccinationInformationSourceIsValuedAsAdministered,
		    	Detection.VaccinationInformationSourceIsValuedAsHistorical));
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
