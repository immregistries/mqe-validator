package org.immregistries.dqa.validator.transform;

import org.immregistries.dqa.validator.model.hl7types.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
