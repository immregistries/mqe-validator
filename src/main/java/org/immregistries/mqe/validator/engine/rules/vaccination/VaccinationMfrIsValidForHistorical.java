package org.immregistries.mqe.validator.engine.rules.vaccination;

public class VaccinationMfrIsValidForHistorical extends VaccinationMfrIsValid {

    @Override
    protected final Class[] getDependencies() {
        return new Class[] { VaccinationSourceIsHistorical.class };
    }
}
