package org.immregistries.mqe.validator.engine.rules;

import java.util.*;
import java.util.stream.Collectors;

import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleService;
import org.immregistries.mqe.vxu.TargetType;

@SuppressWarnings({"unchecked", "rawtypes"})
public enum ValidationRuleEntityLists {
  PATIENT_RULES(TargetType.Patient),
  VACCINATION_RULES(TargetType.Vaccination),
  MESSAGE_HEADER_RULES(TargetType.MessageHeader),
  NEXT_OF_KIN_RULES(TargetType.NextOfKin);

  private List<ValidationRule> rules;
  private final TargetType targetType;

  static {
    // Get all defined rules (@ValidationRuleEntity)
    Map<TargetType, List<ValidationRuleDescriptor<?>>> allRuleDefinitions = ValidationRuleService.getAllRuleDefinitions();
    // Add rules to the appropriate enum value (by target type)
    for(ValidationRuleEntityLists list: ValidationRuleEntityLists.values()) {
      if(allRuleDefinitions.containsKey(list.targetType)) {
        list.setRules(
                allRuleDefinitions
                        .get(list.targetType)
                        .stream()
                        .map((descriptor) -> (ValidationRule) descriptor.getInstance())
                        .collect(Collectors.toList()));
        allRuleDefinitions.remove(list.targetType);
      }
    }

    // If any defined rule was not mapped fail
    // We need to fail because some rules may be required as dependencies of others but were not added to ValidationRuleEntityLists
    // This can happen if an enum value for a target type was not defined
    if(!allRuleDefinitions.isEmpty()) {
      throw new RuntimeException("All defined rules have not been mapped to a list in ValidationRuleEntityLists, the following target types were not defined : " + allRuleDefinitions.keySet());
    }
  }

  ValidationRuleEntityLists(TargetType targetType, ValidationRule... rulesIn) {
    this.targetType = targetType;
    this.setRules(Arrays.asList(rulesIn));
  }

  // Private, can only set rule list for enum from this class
  private void setRules(List<ValidationRule> rulesIn) {
    // Unmodifiable List
    this.rules = Collections.unmodifiableList(rulesIn);
  }

  public static Set<Detection> activeDetections() {
           // Create a stream of all lists (PATIENT, MESSAGE_HEADER, ...)
    return Arrays.stream(ValidationRuleEntityLists.values())
           // Flatten the stream to a stream of Rules
            .flatMap((validationRuleList) -> validationRuleList.getRules().stream()
                    // Cast validation rule to ValidationRule<?> to fix type erasure
                    .map((r) -> (ValidationRule<?>) r))
           // Flatten the stream to a stream of detections
            .flatMap((rule) -> rule.getRuleDetections().stream())
           // Collect as a Set
            .collect(Collectors.toSet());
  }

  public static Set<Detection> activeDetectionsForTargets(Set<TargetType> targetTypes) {
    // Create a stream of all lists (PATIENT, MESSAGE_HEADER, ...)
    return Arrays.stream(ValidationRuleEntityLists.values())
            // Filter lists by target type
            .filter((list) -> targetTypes.contains(list.targetType))
            // Flatten the stream to a stream of Rules
            .flatMap((validationRuleList) -> validationRuleList.getRules().stream()
                    // Cast validation rule to ValidationRule<?> to fix type erasure
                    .map((r) -> (ValidationRule<?>) r))
            // Flatten the stream to a stream of detections
            .flatMap((rule) -> rule.getRuleDetections().stream())
            // Collect as a Set
            .collect(Collectors.toSet());
  }

  public static Set<ImplementationDetail> getImplementationDocumentations() {
    // Create a stream of all lists (PATIENT, MESSAGE_HEADER, ...)
    return Arrays.stream(ValidationRuleEntityLists.values())
            // Flatten the stream to a stream of Rules
            .flatMap((validationRuleList) -> validationRuleList.getRules().stream()
                    // Cast validation rule to ValidationRule<?> to fix type erasure
                    .map((r) -> (ValidationRule<?>) r))
            // Flatten the stream to a stream of Implementation Details
            .flatMap((rule) -> rule.getImplementationDocumentation().stream())
            // Collect as a Set
            .collect(Collectors.toSet());
  }

  public static Set<ImplementationDetail> getImplementationDocumentationsForTargets(Set<TargetType> targetTypes) {
    // Create a stream of all lists (PATIENT, MESSAGE_HEADER, ...)
    return Arrays.stream(ValidationRuleEntityLists.values())
            // Filter lists by target type
            .filter((list) -> targetTypes.contains(list.targetType))
            // Flatten the stream to a stream of Rules
            .flatMap((validationRuleList) -> validationRuleList.getRules().stream()
                    // Cast validation rule to ValidationRule<?> to fix type erasure
                    .map((r) -> (ValidationRule<?>) r))
            // Flatten the stream to a stream of Implementation Details
            .flatMap((rule) -> rule.getImplementationDocumentation().stream())
            // Collect as a Set
            .collect(Collectors.toSet());
  }

  public TargetType getTargetType() {
    return targetType;
  }

  public Set<ValidationRule> getRules() {
    return new HashSet<>(this.rules);
  }

}
