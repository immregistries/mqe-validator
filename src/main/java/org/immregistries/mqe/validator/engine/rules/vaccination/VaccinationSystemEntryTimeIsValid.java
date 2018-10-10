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
import org.joda.time.DateTime;

public class VaccinationSystemEntryTimeIsValid extends ValidationRule<MqeVaccination> {

  // dependency: VaccinationIsAdministered

  public VaccinationSystemEntryTimeIsValid() {
    ruleDetections.addAll(Arrays.asList(Detection.VaccinationSystemEntryTimeIsMissing,
        Detection.VaccinationSystemEntryTimeIsInFuture));
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    if (target.getSystemEntryDateString() == null) {
      issues.add(Detection.VaccinationSystemEntryTimeIsMissing.build(target));
    } else  {
    	if (this.common.isValidDate(target.getSystemEntryDateString())) {
        	DateTime systemEntryDate = this.common.parseDateTimeFrom(target.getSystemEntryDateString());
            
            if (datr.isBeforeDate(m.getReceivedDate(), systemEntryDate.toDate())) {
                issues.add(Detection.VaccinationSystemEntryTimeIsInFuture.build(target));
            }
    	} else {
    		issues.add(Detection.VaccinationSystemEntryTimeIsInvalid.build(target));
    	}
    }

    passed = (issues.size() == 0);

    return buildResults(issues, passed);
  }
}
