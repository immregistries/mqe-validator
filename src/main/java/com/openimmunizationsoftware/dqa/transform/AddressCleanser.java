package com.openimmunizationsoftware.dqa.transform;

import com.openimmunizationsoftware.dqa.model.types.Address;

/**
 * The purpose of this is to provide a point to inject an 
 * address cleansing system into the DQA process.
 * @author Josh Hull
 *
 */
public interface AddressCleanser {
	public void cleanThisAddress(Address a);
}
