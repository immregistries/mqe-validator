package com.openimmunizationsoftware.dqa.transform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openimmunizationsoftware.dqa.model.types.Address;

/**
 * This does nothing except provide a default implementation. 
 * @author Josh Hull
 *
 */
public enum AddressCleanserDefault implements AddressCleanser {
	INSTANCE;
	private static final Logger LOGGER = LoggerFactory.getLogger(AddressCleanserDefault.class);
	
	public void cleanThisAddress(Address a) {
		LOGGER.info("AddressCleansing not implemented. ");
	}
}
