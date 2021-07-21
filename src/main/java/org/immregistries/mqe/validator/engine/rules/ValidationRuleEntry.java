package org.immregistries.mqe.validator.engine.rules;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.immregistries.mqe.vxu.TargetType;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidationRuleEntry {
    TargetType value();
    // If active is false the rule will not be read
    boolean    active() default true;
}
