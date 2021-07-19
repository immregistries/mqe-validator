package org.immregistries.mqe.validator.engine.rules;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.vxu.TargetType;
import org.reflections.Reflections;
@SuppressWarnings({"unchecked", "rawtypes"})
public enum ValidationRuleEntityLists {
//  //@formatter:off
  PATIENT_RULES(TargetType.Patient),
  VACCINATION_RULES(TargetType.Vaccination),
  MESSAGE_HEADER_RULES(TargetType.MessageHeader),
  NEXT_OF_KIN_RULES(TargetType.NextOfKin);
//  //@formatter:on

  public static Map<TargetType, Set<ValidationRule>> rulesMap = new HashMap<>();

  static {
    Reflections reflections = new Reflections("org.immregistries.mqe.validator");
    Set<Class<?>> ruleClasses = reflections.getTypesAnnotatedWith(ValidationRuleEntry.class);
    for (Class c : ruleClasses) {
      try {
        if (!c.isAssignableFrom(ValidationRule.class)) {
          throw new RuntimeException("You have a class annotated with ValidateRuleEntry that is not a Validation Rule: " + c);
        }
        ValidationRule rule = (ValidationRule)c.newInstance();
        ValidationRuleEntry annotation = (ValidationRuleEntry) c.getAnnotation(ValidationRuleEntry.class);
        TargetType type = annotation.value();
        Set<ValidationRule> ruleList = rulesMap.computeIfAbsent(type, k -> new HashSet<>());
        ruleList.add(rule);
      } catch (InstantiationException | IllegalAccessException e) {
        throw new RuntimeException("Something really bad happened while instantiating a class annotated with ValidationRuleEntry. Class["+ c+"]", e);
      }
    }
  }

  public final Set<ValidationRule> rules = new HashSet<>();//<--  how do we fill these buckets.
  public final TargetType targetType;
  ValidationRuleEntityLists(TargetType type) {
    this.targetType = type;
  }


  public static Set<ValidationRule> allRules() {
    //This collects all of the values from the lists in each of the type entries for the map.
    //This could have been a lot longer, but this is pretty, and cool, right?
    return rulesMap.values().stream().flatMap(Collection::stream).collect(Collectors.toSet());
  }

  public static Set<Detection> activeDetections() {
    return null;
//    return this.allRules().stream().flatMap((r) -> r.getRuleDetections().stream().map((d) -> (Detection) d))
//        .collect(Collectors.toSet())
//        ;
  }

  public static Set<ImplementationDetail> getImplementationDocumentations() {
    Set<ImplementationDetail> dets = new HashSet<ImplementationDetail>();
    for (ValidationRule rule : allRules()) {
      dets.addAll(rule.getImplementationDocumentation());
    }
    return dets;
  }


  public Set<ValidationRule> getRules() {
    return rulesMap.get(this.targetType);
    // It needs to be a new list. then if it's modified, the enum's version doesn't change.
  }


}
