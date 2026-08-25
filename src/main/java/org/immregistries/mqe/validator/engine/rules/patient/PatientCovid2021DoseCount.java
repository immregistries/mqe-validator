package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.validator.engine.common.DoseCounting;
import org.immregistries.mqe.validator.engine.rules.ValidationRuleEntry;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.TargetType;

/**
 * Experimental calibration ladder (issue #102) flagging patients with an unusually high count of
 * COVID-19 doses administered in calendar year 2021, as an indirect signal of a possible patient
 * mismerge. Intentionally hard-coded - not a general config-driven mechanism (see
 * {@link org.immregistries.mqe.validator.engine.common.DoseCounting}). The three thresholds are
 * independent cumulative signals (not mutually exclusive buckets): a patient with 7 doses fires
 * all three, by design - see docs/changes-needed-2028-08.md's design discussion under #3312/#3313
 * for why (lets the calibration ladder reconstruct the per-count distribution from DAR's existing
 * per-MqeCode counts).
 */
@ValidationRuleEntry(TargetType.Patient)
public class PatientCovid2021DoseCount extends ValidationRule<MqePatient> {

  private static final Set<String> COVID_CVX_CODES = new HashSet<>(
      Arrays.asList("208", "207", "218", "212", "217"));
  private static final int CALENDAR_YEAR = 2021;

  public PatientCovid2021DoseCount() {
    super();
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientCovid2021DoseCountIs4OrMore);
      id.setImplementationDescription(
          "Patient has 4 or more doses of CVX " + COVID_CVX_CODES + " administered in calendar year 2021.");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientCovid2021DoseCountIs5OrMore);
      id.setImplementationDescription(
          "Patient has 5 or more doses of CVX " + COVID_CVX_CODES + " administered in calendar year 2021.");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientCovid2021DoseCountIs6OrMore);
      id.setImplementationDescription(
          "Patient has 6 or more doses of CVX " + COVID_CVX_CODES + " administered in calendar year 2021.");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<>();

    int count = DoseCounting.countMatchingDoses(m.getVaccinations(), COVID_CVX_CODES,
        adminDate -> adminDate.getYear() == CALENDAR_YEAR);

    if (count >= 4) {
      issues.add(Detection.PatientCovid2021DoseCountIs4OrMore.build(target));
    }
    if (count >= 5) {
      issues.add(Detection.PatientCovid2021DoseCountIs5OrMore.build(target));
    }
    if (count >= 6) {
      issues.add(Detection.PatientCovid2021DoseCountIs6OrMore.build(target));
    }

    return buildResults(issues, true);
  }

}
