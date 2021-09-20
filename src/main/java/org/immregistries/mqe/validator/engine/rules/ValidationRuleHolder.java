package org.immregistries.mqe.validator.engine.rules;

import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.ValidationRule;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValidationRuleHolder {
    private Set<ValidationRule> patientRules;
    private Set<ValidationRule> vaccinationRules;
    private Set<ValidationRule> messageHeaderRules;
    private Set<ValidationRule> nextOfKinRules;

    public ValidationRuleHolder(Set<ValidationRule> patientRules,
                                Set<ValidationRule> vaccinationRules,
                                Set<ValidationRule> messageHeaderRules,
                                Set<ValidationRule> nextOfKinRules) {
        this.patientRules = patientRules;
        this.vaccinationRules = vaccinationRules;
        this.messageHeaderRules = messageHeaderRules;
        this.nextOfKinRules = nextOfKinRules;
    }

    public Set<ValidationRule> getPatientRules() {
        return patientRules;
    }

    public void setPatientRules(Set<ValidationRule> patientRules) {
        this.patientRules = patientRules;
    }

    public Set<ValidationRule> getVaccinationRules() {
        return vaccinationRules;
    }

    public void setVaccinationRules(Set<ValidationRule> vaccinationRules) {
        this.vaccinationRules = vaccinationRules;
    }

    public Set<ValidationRule> getMessageHeaderRules() {
        return messageHeaderRules;
    }

    public void setMessageHeaderRules(Set<ValidationRule> messageHeaderRules) {
        this.messageHeaderRules = messageHeaderRules;
    }

    public Set<ValidationRule> getNextOfKinRules() {
        return nextOfKinRules;
    }

    public void setNextOfKinRules(Set<ValidationRule> nextOfKinRules) {
        this.nextOfKinRules = nextOfKinRules;
    }

    public Set<Detection> getRuleDetections() {
        return this.getRules().stream()
                // Cast validation rule to ValidationRule<?> to fix type erasure
                .map((r) -> (ValidationRule<?>) r)
                .flatMap((rule) -> rule.getRuleDetections().stream())
                .collect(Collectors.toSet());
    }

    public Set<ValidationRule> getRules() {
        return Stream.concat(this.patientRules.stream(),
                Stream.concat(this.messageHeaderRules.stream(),
                        Stream.concat(
                              this.vaccinationRules.stream(),
                                this.nextOfKinRules.stream()
                )
        )).collect(Collectors.toSet());
    }
}
