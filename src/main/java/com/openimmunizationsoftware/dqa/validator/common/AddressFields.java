package com.openimmunizationsoftware.dqa.validator.common;

import com.openimmunizationsoftware.dqa.validator.issues.IssueField;

public class AddressFields {

	private IssueField address;
	private IssueField street2Field;
	private IssueField cityField;
	private IssueField streetField;
	private IssueField countryField;
	private IssueField countyField;
	private IssueField stateField;
	private IssueField zipField;
	private IssueField addressTypeField;
	
	public AddressFields(
			  IssueField addressRef
			, IssueField street
			, IssueField street2
			, IssueField city
			, IssueField state
			, IssueField county
			, IssueField country
			, IssueField zip
			, IssueField type
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
	
	public IssueField getAddress() {
		return address;
	}
	public IssueField getStreet2Field() {
		return street2Field;
	}
	public IssueField getCityField() {
		return cityField;
	}
	public IssueField getStreetField() {
		return streetField;
	}
	public IssueField getCountryField() {
		return countryField;
	}
	public IssueField getCountyField() {
		return countyField;
	}
	public IssueField getStateField() {
		return stateField;
	}
	public IssueField getZipField() {
		return zipField;
	}
	public IssueField getAddressTypeField() {
		return addressTypeField;
	}
	
	
}
