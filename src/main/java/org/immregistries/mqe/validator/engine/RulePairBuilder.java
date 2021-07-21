package org.immregistries.mqe.validator.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.immregistries.mqe.validator.detection.MqeCode;
import org.immregistries.mqe.validator.engine.rules.ValidationRuleHolder;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeNextOfKin;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.MqeVaccination;

@SuppressWarnings({"unchecked", "rawtypes"})
public enum RulePairBuilder {
  INSTANCE;
  private static final ValidationUtility util = ValidationUtility.INSTANCE;
  private static ValidationRuleHolder VALIDATION_RULES = ValidationRuleService.getAllMqeRules();

  public void resetRules() {
    VALIDATION_RULES = ValidationRuleService.getAllMqeRules();
  }

  public void setActiveDetections(Set<MqeCode> codes) {
    VALIDATION_RULES = ValidationRuleService.getRulesForMqeCodes(codes);
  }

  // Return copy with immutable list to avoid changing active rule any other way than through setActiveDetections
  public ValidationRuleHolder getActiveValidationRules() {
    return new ValidationRuleHolder(
            Collections.unmodifiableSet(VALIDATION_RULES.getPatientRules()),
            Collections.unmodifiableSet(VALIDATION_RULES.getVaccinationRules()),
            Collections.unmodifiableSet(VALIDATION_RULES.getMessageHeaderRules()),
            Collections.unmodifiableSet(VALIDATION_RULES.getNextOfKinRules())
    );
  }

  /*
   * The Rule List Builders!!!
   */

  protected List<ValidationRulePair> buildPatientRulePairs(MqePatient p, MqeMessageReceived mr) {
    return util.buildRulePairs(VALIDATION_RULES.getPatientRules(), p, mr);
  }

  protected List<ValidationRulePair> buildMessageHeaderRulePairs(MqeMessageHeader mh, MqeMessageReceived mr) {
    return util.buildRulePairs(VALIDATION_RULES.getMessageHeaderRules(), mh, mr);
  }

  protected List<ValidationRulePair> buildVaccinationRulePairs(MqeVaccination v, MqeMessageReceived mr) {
    return util.buildRulePairs(VALIDATION_RULES.getVaccinationRules(), v, mr);
  }

  protected List<ValidationRulePair> buildNextOfKinRulePairs(MqeNextOfKin n, MqeMessageReceived mr) {
    return util.buildRulePairs(VALIDATION_RULES.getNextOfKinRules(), n, mr);
  }

  /*
   * The Rule Categories!!!
   */

  /**
   * This builds list of lists of rules for the list-item (secondary order) entities. These are
   * things like a Vaccination, for which we do not want cross-dependency fulfillment. If a
   * vaccination's rules are fulfilled, we intend for that pass to fulfill only dependencies for the
   * vaccination for which it passed. Hence, these rules are treated slightly differently in that
   * their passed rules do not propagate on to further dependency checks - only their own.
   */
  public List<List<ValidationRulePair>> buildListItemRuleLists(MqeMessageReceived m) {
    List<List<ValidationRulePair>> ruleSets = new ArrayList<List<ValidationRulePair>>();

    // The Vaccination rules.
    for (MqeVaccination v : m.getVaccinations()) {
      ruleSets.add(this.buildVaccinationRulePairs(v, m));
    }
    // And NOK rules.
    for (MqeNextOfKin n : m.getNextOfKins()) {
      ruleSets.add(this.buildNextOfKinRulePairs(n, m));
    }

    return ruleSets;
  }
}
