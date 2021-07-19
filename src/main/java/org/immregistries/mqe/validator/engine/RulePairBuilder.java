package org.immregistries.mqe.validator.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.MqeCode;
import org.immregistries.mqe.validator.engine.rules.ValidationRuleEntityLists;
import org.immregistries.mqe.validator.engine.rules.ValidationRuleEntry;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeNextOfKin;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.reflections.Reflections;

@SuppressWarnings({"unchecked", "rawtypes"})
public enum RulePairBuilder {
  INSTANCE;
  private static final ValidationUtility util = ValidationUtility.INSTANCE;
  private Set<ValidationRule> PATIENT_RULES        = ValidationRuleEntityLists.PATIENT_RULES.getRules();
  private Set<ValidationRule> MESSAGE_HEADER_RULES = ValidationRuleEntityLists.MESSAGE_HEADER_RULES.getRules();
  private Set<ValidationRule> VACCINATION_RULES    = ValidationRuleEntityLists.VACCINATION_RULES.getRules();
  private Set<ValidationRule> NEXT_OF_KIN_RULES    = ValidationRuleEntityLists.NEXT_OF_KIN_RULES.getRules();

  public void resetRules() {
    PATIENT_RULES        = ValidationRuleEntityLists.PATIENT_RULES.getRules();
    MESSAGE_HEADER_RULES = ValidationRuleEntityLists.MESSAGE_HEADER_RULES.getRules();
    VACCINATION_RULES    = ValidationRuleEntityLists.VACCINATION_RULES.getRules();
    NEXT_OF_KIN_RULES    = ValidationRuleEntityLists.NEXT_OF_KIN_RULES.getRules();
  }

  public Set<Class<?>> getRuleClasses() {
    Reflections reflections = new Reflections("org.immregistries.mqe.validator");
    Set<Class<?>> ruleClasses = reflections.getTypesAnnotatedWith(ValidationRuleEntry.class);
    return ruleClasses;
  }

  public void setActiveDetections(List<MqeCode> codes) {
    this.PATIENT_RULES        = this.filterRules(ValidationRuleEntityLists.PATIENT_RULES.getRules(), codes);
    this.MESSAGE_HEADER_RULES = this.filterRules(ValidationRuleEntityLists.MESSAGE_HEADER_RULES.getRules(), codes);
    this.VACCINATION_RULES    = this.filterRules(ValidationRuleEntityLists.VACCINATION_RULES.getRules(), codes);
    this.NEXT_OF_KIN_RULES    = this.filterRules(ValidationRuleEntityLists.NEXT_OF_KIN_RULES.getRules(), codes);

    Set allTheseRules = getAllCurrentRules();

    Set<ValidationRule> dependencies = this.collectDependencies(allTheseRules);
    this.addDependenciesToCorrectLists(dependencies, ValidationRuleEntityLists.PATIENT_RULES         .getRules(), PATIENT_RULES       );
    this.addDependenciesToCorrectLists(dependencies, ValidationRuleEntityLists.MESSAGE_HEADER_RULES  .getRules(), MESSAGE_HEADER_RULES);
    this.addDependenciesToCorrectLists(dependencies, ValidationRuleEntityLists.VACCINATION_RULES     .getRules(), VACCINATION_RULES   );
    this.addDependenciesToCorrectLists(dependencies, ValidationRuleEntityLists.NEXT_OF_KIN_RULES     .getRules(), NEXT_OF_KIN_RULES   );

    //At this point, it looks like we've finished. this needs to be tested.
  }
//
//  Set<Class> collectDependencies(List<VR> rules)
//
//  addRules(Set<Class> allDepsAccrossCategories,  List<VR> allCategoryRules,  List<VR> categoryActiveRulesList)

  protected Set<ValidationRule> getAllCurrentRules() {
    Set allTheseRules = new HashSet<>();
    allTheseRules.addAll(this.PATIENT_RULES);
    allTheseRules.addAll(this.MESSAGE_HEADER_RULES);
    allTheseRules.addAll(this.VACCINATION_RULES);
    allTheseRules.addAll(this.NEXT_OF_KIN_RULES);
    return allTheseRules;
  }

  protected void addDependenciesToCorrectLists(
      Set<ValidationRule>  rulesToAdd,
      Set<ValidationRule> allCategoryRules,
      Set<ValidationRule> categoryActiveRulesList) {
    for (ValidationRule rule : rulesToAdd) {
      if (allCategoryRules.contains(rule)) {
        categoryActiveRulesList.add(rule);
      }
    }
  }

  protected Set<ValidationRule> getRulesFromClasses(List<Class> classes) {
    Set<ValidationRule> dependenciesFound = new HashSet<>();

    for (ValidationRule ruleD : ValidationRuleEntityLists.allRules()) {
      if (classes.contains(ruleD.getClass())) {
        dependenciesFound.add(ruleD);
      }
    }

    return dependenciesFound;
  }


  protected Set<ValidationRule> collectDependencies(Set<ValidationRule> checkList) {
    Set<ValidationRule> dependenciesFound = new HashSet<>();

    for (ValidationRule rule : checkList) {
      List<Class> dependencies = Arrays.asList(rule.getDependencies());
      dependenciesFound.addAll(this.getRulesFromClasses(dependencies));
    }

    if (dependenciesFound.size() > 0) {
      dependenciesFound.addAll(this.collectDependencies(dependenciesFound));
    }

    return dependenciesFound;
  }

  protected Set<ValidationRule> filterRules(Set<ValidationRule> rulesToFilter, List<MqeCode> codesTokeep) {
    return rulesToFilter.stream().filter((rule) ->
        rule.getRuleDetections().stream().anyMatch((detection) ->
            codesTokeep.contains(((Detection) detection).getMqeCodeObject())))
        .collect(Collectors.toSet());
  }

  /*
   * The Rule List Builders!!!
   */

  protected List<ValidationRulePair> buildPatientRulePairs(MqePatient p, MqeMessageReceived mr) {
    return util.buildRulePairs(PATIENT_RULES, p, mr);
  }

  protected List<ValidationRulePair> buildMessageHeaderRulePairs(MqeMessageHeader mh,
      MqeMessageReceived mr) {
    return util.buildRulePairs(MESSAGE_HEADER_RULES, mh, mr);
  }

  protected List<ValidationRulePair> buildVaccinationRulePairs(MqeVaccination v,
      MqeMessageReceived mr) {
    return util.buildRulePairs(VACCINATION_RULES, v, mr);
  }

  protected List<ValidationRulePair> buildNextOfKinRulePairs(MqeNextOfKin n, MqeMessageReceived mr) {
    return util.buildRulePairs(NEXT_OF_KIN_RULES, n, mr);
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
