package org.immregistries.mqe.validator.engine.rules.vaccination;

import org.immregistries.mqe.validator.engine.rules.ValidationRuleEntry;
import org.immregistries.mqe.vxu.TargetType;

@ValidationRuleEntry(TargetType.Vaccination)
public class VaccinationMfrIsValidForAdministered extends VaccinationMfrIsValid {

    @Override
    protected final Class[] getDependencies() {
        return new Class[] { VaccinationSourceIsAdministered.class };
    }
}
