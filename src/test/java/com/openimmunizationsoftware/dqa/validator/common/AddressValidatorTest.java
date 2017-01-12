package com.openimmunizationsoftware.dqa.validator.common;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.openimmunizationsoftware.dqa.model.types.Address;

public class AddressValidatorTest {

	@Before
	public void buildNewAddress() {
		Address a = new Address();
		a.setCity("Lansing");
		a.setStateCode("MI");
		a.setStreet("1221 Mechanic");
		a.setZip("48910");
		a.setCountryCode("USA");
		a.setCountyParishCode("43");
		this.address = a;
	}
	
	private Address address;
	private AddressValidator av = AddressValidator.INSTANCE;
	
	@Test
	public void testBasic() {
		System.out.println(address);
		assertEquals("it's valid!", true, av.isValid(address));
		
		address.setCity("");
		
		assertEquals("it's crappy!", false, av.isValid(address));
	}

	@Test
	public void testValidCity() {
		boolean validCity = av.validCity(address.getCity());
		assertTrue("Should be a valid city", validCity);
		
		boolean validcity2 = av.validCity("X");
		assertFalse("Should not be valid", validcity2);
	}
}
