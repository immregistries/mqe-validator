package org.immregistries.dqa.validator.engine.rules.patient;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class PatientDeathDateIsValid extends ValidationRule<DqaPatient> {

//	if (notEmpty(patient.getDeathIndicator(), pi.PatientDeathIndicatorIsMissing))
//    {
//      if (patient.getDeathIndicator().equals("Y"))
//      {
//        if (notEmpty(patient.getDeathDate(), pi.PatientDeathDateIsMissing))
//        {
//          if (patient.getBirthDate() != null && patient.getDeathDate().before(trunc(patient.getBirthDate())))
//          {
//            registerIssue(pi.PatientDeathDateIsBeforeBirth);
//          }
//          if (message.getReceivedDate().before(trunc(patient.getDeathDate())))
//          {
//            registerIssue(pi.PatientDeathDateIsInFuture);
//          }
//        }
//      } else
//      {
//        if (patient.getDeathDate() != null)
//        {
//          registerIssue(pi.PatientDeathIndicatorIsInconsistent);
//        }
//      }
//    }

    @Override
    protected final Class[] getDependencies() {
        return new Class[]{PatientExists.class, PatientDeathIndicatorIsValid.class, PatientBirthDateIsValid.class};
    }

    @Override
    protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived message) {
        List<ValidationIssue> issues = new ArrayList<>();
        boolean passed = true;

        String deathDateString = target.getDeathDateString();

        // if the death date is empty
        if ("Y".equals(target.getDeathIndicator()) && this.common.isEmpty(deathDateString)) {
            issues.add(Detection.PatientDeathDateIsMissing.build(deathDateString));
            passed = false;
        } else {
            // if the death date isn't parsable
            if (!this.common.isValidDate(deathDateString)) {
                issues.add(Detection.PatientDeathDateIsInvalid.build(deathDateString));
                passed = false;
            } else {
                DateTime deathDate = this.common.parseDateTimeFrom(deathDateString);
                DateTime receivedDate = new DateTime(message.getReceivedDate());

                // if the death date is after the date the message was received
                if (receivedDate.isBefore(deathDate)) {
                    issues.add(Detection.PatientDeathDateIsInFuture.build(deathDateString));
                    passed = false;
                }

                DateTime birthDate = this.common.parseDateTimeFrom(target.getBirthDateString());

                // if the death date is before the birth date
                if (deathDate.isBefore(birthDate)) {
                    issues.add(Detection.PatientDeathDateIsBeforeBirth.build(deathDateString));
                    passed = false;
                }
            }
        }

        if (target.getDeathDate() != null) {
            if (target.getBirthDate() != null && target.getDeathDate().before(target.getBirthDate())) {
                issues.add(Detection.PatientDeathDateIsBeforeBirth.build(target.getDeathDate().toString()));
                passed = false;
            }

            if (message.getReceivedDate().before(target.getDeathDate())) {
                issues.add(Detection.PatientDeathDateIsInFuture.build(target.getDeathDate().toString()));
                passed = false;
            }
        }

        return buildResults(issues, passed);
    }

}
