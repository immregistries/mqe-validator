package org.immregistries.dqa.validator.engine;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.dqa.validator.engine.rules.ValidationRuleEntityLists;
import org.immregistries.dqa.vxu.DqaMessageHeader;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaNextOfKin;
import org.immregistries.dqa.vxu.DqaPatient;
import org.immregistries.dqa.vxu.DqaVaccination;

public enum RulePairBuilder {
  INSTANCE;
  private static final ValidationUtility util = ValidationUtility.INSTANCE;

  /*
   * The Rule List Builders!!!
   */

  protected List<ValidationRulePair> buildPatientRulePairs(DqaPatient p, DqaMessageReceived mr) {
    return util.buildRulePairs(ValidationRuleEntityLists.PATIENT_RULES.getRules(), p, mr);
  }

  protected List<ValidationRulePair> buildMessageHeaderRulePairs(DqaMessageHeader mh,
      DqaMessageReceived mr) {
    return util.buildRulePairs(ValidationRuleEntityLists.MESSAGE_HEADER_RULES.getRules(), mh, mr);
  }

  protected List<ValidationRulePair> buildVaccinationRulePairs(DqaVaccination v,
      DqaMessageReceived mr) {
    return util.buildRulePairs(ValidationRuleEntityLists.VACCINATION_RULES.getRules(), v, mr);
  }

  protected List<ValidationRulePair> buildNextOfKinRulePairs(DqaNextOfKin n, DqaMessageReceived mr) {
    return util.buildRulePairs(ValidationRuleEntityLists.NEXT_OF_KIN_RULES.getRules(), n, mr);
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
  public List<List<ValidationRulePair>> buildListItemRuleLists(DqaMessageReceived m) {
    List<List<ValidationRulePair>> ruleSets = new ArrayList<List<ValidationRulePair>>();

    // The Vaccination rules.
    for (DqaVaccination v : m.getVaccinations()) {
      ruleSets.add(this.buildVaccinationRulePairs(v, m));
    }
    // And NOK rules.
    for (DqaNextOfKin n : m.getNextOfKins()) {
      ruleSets.add(this.buildNextOfKinRulePairs(n, m));
    }

    return ruleSets;
  }
}
