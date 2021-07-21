package org.immregistries.mqe.validator.engine.rules;

import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.vxu.TargetType;

public class ValidationRuleDescriptor<T extends ValidationRule<?>> {
    private TargetType targetType;
    private Class<T> ruleClass;
    private final T instance;

    public ValidationRuleDescriptor(TargetType targetType, Class<T> ruleClass) {
        this.targetType = targetType;
        this.ruleClass = ruleClass;
        this.instance = this.createInstance();
    }

    public TargetType getTargetType() {
        return targetType;
    }

    public void setTargetType(TargetType targetType) {
        this.targetType = targetType;
    }

    public Class<T> getRuleClass() {
        return ruleClass;
    }

    public void setRuleClass(Class<T> ruleClass) {
        this.ruleClass = ruleClass;
    }

    public T getInstance() {
        return instance;
    }

    public T createInstance() throws RuntimeException {
        if (!ValidationRule.class.isAssignableFrom(this.ruleClass)) {
            throw new RuntimeException("You have a class annotated with ValidateRuleEntry that is not a Validation Rule: " + this.ruleClass);
        }
        try {
            return this.ruleClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Something really bad happened while instantiating a class annotated with ValidationRuleEntry. Class["+this.ruleClass+"]", e);
        }
    }
}
