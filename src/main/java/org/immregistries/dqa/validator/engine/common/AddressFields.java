package org.immregistries.dqa.validator.engine.common;

import org.immregistries.dqa.vxu.VxuField;

public class AddressFields {

	private VxuField address;
	private VxuField street2Field;
	private VxuField cityField;
	private VxuField streetField;
	private VxuField countryField;
	private VxuField countyField;
	private VxuField stateField;
	private VxuField zipField;
	private VxuField addressTypeField;
	
	public AddressFields(
			  VxuField addressRef
			, VxuField street
			, VxuField street2
			, VxuField city
			, VxuField state
			, VxuField county
			, VxuField country
			, VxuField zip
			, VxuField type
			) {
		
		this.address = addressRef;
		this.streetField = street;
		this.street2Field = street2;
		this.cityField = city;
		this.stateField = state;
		this.countyField = county; 
		this.countryField = country;
		this.zipField = zip;
		this.addressTypeField = type;
	}
	
	public VxuField getAddress() {
		return address;
	}
	public VxuField getStreet2Field() {
		return street2Field;
	}
	public VxuField getCityField() {
		return cityField;
	}
	public VxuField getStreetField() {
		return streetField;
	}
	public VxuField getCountryField() {
		return countryField;
	}
	public VxuField getCountyField() {
		return countyField;
	}
	public VxuField getStateField() {
		return stateField;
	}
	public VxuField getZipField() {
		return zipField;
	}
	public VxuField getAddressTypeField() {
		return addressTypeField;
	}
	
	
}
