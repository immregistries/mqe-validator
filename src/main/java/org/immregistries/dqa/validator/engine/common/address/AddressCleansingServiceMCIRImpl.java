package org.immregistries.dqa.validator.engine.common.address;

import gov.mi.agcts.acd.address.AddressCleansingRequest;
import gov.mi.agcts.acd.address.request.CleansingRequester;
import gov.mi.agcts.acd.address.wrapper.CleanseAddressResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressCleansingServiceMCIRImpl {

	private static final Logger logger = LoggerFactory
			.getLogger(AddressCleansingServiceMCIRImpl.class);
	
	@Autowired 
	private CleansingRequester clean;
	
	public CleanseAddressResult cleanseAddress(String street1, String street2, String city, String state, String zip, String countyIsoCd, String countryIso2) {
		
		AddressCleansingRequest req = new AddressCleansingRequest(street1, street2, city, state, zip, countyIsoCd, countryIso2);
		
		try {
			CleanseAddressResult result = clean.postAddressCleansingRequest(req, "http://localhost:8082/acd-service/ws/1.0/cleanseAddress");
			return result;
		} catch (Exception e) {
			logger.error("Error in cleanseAddress");
		}		
		
		return null;
	}
}
