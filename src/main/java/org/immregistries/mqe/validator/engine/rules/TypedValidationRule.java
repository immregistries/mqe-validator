package org.immregistries.mqe.validator.engine.rules;

import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.vxu.TargetType;

public class TypedValidationRule<T extends ValidationRule> {
    private T rule;
    private TargetType targetType;

    public TypedValidationRule(T rule, TargetType targetType) {
        this.rule = rule;
        this.targetType = targetType;
    }

    public T getRule() {
        return rule;
    }

    public void setRule(T rule) {
        this.rule = rule;
    }

    public TargetType getTargetType() {
        return targetType;
    }

    public void setTargetType(TargetType targetType) {
        this.targetType = targetType;
    }
}
