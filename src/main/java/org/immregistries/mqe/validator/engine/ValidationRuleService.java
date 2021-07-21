package org.immregistries.mqe.validator.engine;

import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.MqeCode;
import org.immregistries.mqe.validator.engine.rules.*;
import org.immregistries.mqe.vxu.TargetType;
import org.reflections.Reflections;

import java.util.*;
import java.util.stream.Collectors;

public class ValidationRuleService {

    public static final String RULE_PACKAGE = "org.immregistries.mqe.validator";

    /*
        Create ValidationRuleHolder from All MQE Rules
     */
    public static ValidationRuleHolder getAllMqeRules() {
        return new ValidationRuleHolder(
                ValidationRuleEntityLists.PATIENT_RULES.getRules(),
                ValidationRuleEntityLists.VACCINATION_RULES.getRules(),
                ValidationRuleEntityLists.MESSAGE_HEADER_RULES.getRules(),
                ValidationRuleEntityLists.NEXT_OF_KIN_RULES.getRules()
        );
    }

    /*
        Create ValidationRuleHolder from rules that return a detections in input detections set (+ their dependencies)
     */
    public static ValidationRuleHolder getRulesForMqeCodes(Set<MqeCode> detections) {
        // Filter all rules and keep only the ones that return detections of interest
        Set<ValidationRule> patient = filterRules(ValidationRuleEntityLists.PATIENT_RULES.getRules(), detections);
        Set<ValidationRule> vaccination = filterRules(ValidationRuleEntityLists.VACCINATION_RULES.getRules(), detections);
        Set<ValidationRule> messageHeader = filterRules(ValidationRuleEntityLists.MESSAGE_HEADER_RULES.getRules(), detections);
        Set<ValidationRule> nextOfKin = filterRules(ValidationRuleEntityLists.NEXT_OF_KIN_RULES.getRules(), detections);

        // Create ValidationRuleHolder
        ValidationRuleHolder validationRuleHolder = new ValidationRuleHolder(
                patient,
                vaccination,
                messageHeader,
                nextOfKin
        );

        // Get all necessary dependencies for rules of interest
        Set<TypedValidationRule> dependencies = getRuleListDependencies(validationRuleHolder.getRules());

        // Add dependencies to lists
        dependencies.stream()
                .filter(tvr -> tvr.getTargetType().equals(TargetType.Patient))
                .forEach(tvr -> patient.add(tvr.getRule()));
        dependencies.stream()
                .filter(tvr -> tvr.getTargetType().equals(TargetType.Vaccination))
                .forEach(tvr -> vaccination.add(tvr.getRule()));
        dependencies.stream()
                .filter(tvr -> tvr.getTargetType().equals(TargetType.MessageHeader))
                .forEach(tvr -> messageHeader.add(tvr.getRule()));
        dependencies.stream()
                .filter(tvr -> tvr.getTargetType().equals(TargetType.NextOfKin))
                .forEach(tvr -> nextOfKin.add(tvr.getRule()));

        return validationRuleHolder;
    }

    /*
        Use reflection to get all the classes annotated by @ValidationRuleEntity and create Map (Rule Type) -> (Annotation Info + Class)
     */
    public static Map<TargetType, List<ValidationRuleDescriptor<?>>> getAllRuleDefinitions() {
        Map<TargetType, List<ValidationRuleDescriptor<?>>> ruleDefinitions = new HashMap<>();
        Reflections reflections = new Reflections(RULE_PACKAGE);
        Set<Class<?>> ruleClasses = reflections.getTypesAnnotatedWith(ValidationRuleEntry.class);
        for(Class<?> ruleClass : ruleClasses) {
            ValidationRuleEntry annotation =  ruleClass.getAnnotation(ValidationRuleEntry.class);
            if(annotation.active()) {
                ValidationRuleDescriptor<?> ruleDescriptor = new ValidationRuleDescriptor(annotation.value(), ruleClass);
                List<ValidationRuleDescriptor<?>> ruleList = ruleDefinitions.computeIfAbsent(annotation.value(), k -> new ArrayList<>());
                ruleList.add(ruleDescriptor);
            }
        }

        List<ValidationRule<?>> rulesWithUnmetDependencies = getRulesWithUnmetDependencies(ruleDefinitions);
        if(rulesWithUnmetDependencies != null && rulesWithUnmetDependencies.size() > 0) {
            throw new RuntimeException("One or many Validation Rules have unmet dependencies Rules = " +
                    rulesWithUnmetDependencies
                            .stream()
                            .map(Object::getClass)
                            .map(Objects::toString)
                            .collect(Collectors.joining(",")));
        }

        return ruleDefinitions;
    }

    public static List<ValidationRule<?>> getRulesWithUnmetDependencies(Map<TargetType, List<ValidationRuleDescriptor<?>>> allRuleDefinitions) {
               // Stream all ValidationRuleDescriptors
        return allRuleDefinitions.values().stream().flatMap(Collection::stream)
                // Filter Stream to keep only ValidationRuleDescriptor with Unmet Depedencies
                .filter(validationRuleDescriptor -> {
                    List<Class> ruleDependencyClasses = Arrays.asList(validationRuleDescriptor.getInstance().getDependencies());
                    // Keep if not all ruleDependencyClasses are in the list of allRuleDefinitions
                    return !ruleDependencyClasses.stream()
                            .allMatch(
                                        (ruleDependencyClass) -> allRuleDefinitions.values()
                                                                                    .stream()
                                                                                    .flatMap(Collection::stream)
                                                                                    .anyMatch(vrd -> vrd.getRuleClass().equals(ruleDependencyClass))
                            );
                })
                .map(ValidationRuleDescriptor::getInstance)
                .collect(Collectors.toList());
    }

    public static <T extends ValidationRule> TypedValidationRule<T> getRuleInstanceByClass(Class<T> ruleClass) {
        for(ValidationRuleEntityLists validationRuleEntityLists: ValidationRuleEntityLists.values()) {
            for(ValidationRule rule: validationRuleEntityLists.getRules()) {
                if(ruleClass.isInstance(rule)) {
                    return new TypedValidationRule<T>((T) rule, validationRuleEntityLists.getTargetType());
                }
            }
        }
        return null;
    }

    protected static Set<TypedValidationRule> getRuleListDependencies(Set<ValidationRule> ruleList) {
        Set<TypedValidationRule> dependencies = new HashSet<>();

        // For each rule in rule set get the dependencies as Rule + Rule Type
        for (ValidationRule rule : ruleList) {
            List<Class> ruleDependencyClasses = Arrays.asList(rule.getDependencies());
            Set<TypedValidationRule> ruleDependencyTypedInstances = new HashSet<>();
            for(Class ruleDependencyClass: ruleDependencyClasses) {
                TypedValidationRule typedValidationRule = getRuleInstanceByClass(ruleDependencyClass);
                if(typedValidationRule == null) {
                    throw new RuntimeException("Could not find rule instance for class " + ruleDependencyClass + " which is a required dependency for " + rule.getClass());
                } else {
                    ruleDependencyTypedInstances.add(typedValidationRule);
                }
            }
            dependencies.addAll(ruleDependencyTypedInstances);
        }

        // If we found dependencies, we try to get their own dependencies (recursively)
        if (dependencies.size() > 0) {
            dependencies.addAll(
                    // Get dependencies of dependencies
                    getRuleListDependencies(
                            // Map TypedValidationRule to ValidationRule
                            dependencies.stream()
                            .map(TypedValidationRule::getRule)
                            .collect(Collectors.toSet())
                    )
            );
        }

        return dependencies;
    }

    protected static Set<ValidationRule> filterRules(Set<ValidationRule> rulesToFilter, Set<MqeCode> codesTokeep) {
        return rulesToFilter.stream().filter((rule) ->
                rule.getRuleDetections().stream().anyMatch((detection) ->
                        codesTokeep.contains(((Detection) detection).getMqeCodeObject())))
                .collect(Collectors.toSet());
    }
}
