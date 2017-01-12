/*
 * Copyright 2013 by Dandelion Software & Research, Inc (DSR)
 * 
 * This application was written for immunization information system (IIS) community and has
 * been released by DSR under an Apache 2 License with the hope that this software will be used
 * to improve Public Health.  
 */
package com.openimmunizationsoftware.dqa.model.types;

import com.openimmunizationsoftware.dqa.model.CodeTable;

public class Address
{
  @Override
	public String toString() {
		return "Address [street=" + street + ", street2=" + street2 + ", city="
				+ city + ", state=" + state + ", zip=" + zip + ", country="
				+ country + ", countyParish=" + countyParish + ", type=" + type
				+ "]";
	}
  private String street = "";
  private String street2 = "";
  private String city = "";
  private CodedEntity state = new CodedEntity(CodeTable.Type.ADDRESS_STATE);
  private String zip = "";
  private CodedEntity country = new CodedEntity(CodeTable.Type.ADDRESS_COUNTRY);
  private CodedEntity countyParish = new CodedEntity(CodeTable.Type.ADDRESS_COUNTY);
  private CodedEntity type = new CodedEntity(CodeTable.Type.ADDRESS_TYPE);
  
  
  

public String getStreet()
  {
    return street;
  }
  public void setStreet(String street)
  {
    this.street = street;
  }
  public String getStreet2()
  {
    return street2;
  }
  public void setStreet2(String street2)
  {
    this.street2 = street2;
  }
  public String getCity()
  {
    return city;
  }
  public void setCity(String city)
  {
    this.city = city;
  }
  public CodedEntity getState()
  {
    return state;
  }
  public String getStateCode()
  {
    return state.getCode();
  }
  public void setStateCode(String stateCode)
  {
    this.state.setCode(stateCode);
  }
  public String getZip()
  {
    return zip;
  }
  public void setZip(String zip)
  {
    this.zip = zip;
  }
  public CodedEntity getCountry()
  {
    return country;
  }
  public String getCountryCode()
  {
    return country.getCode();
  }
  public void setCountryCode(String countryCode)
  {
    this.country.setCode(countryCode);
  }
  public CodedEntity getCountyParish()
  {
    return countyParish;
  }
  public void setCountyParishCode(String countyParishCode)
  {
    this.countyParish.setCode(countyParishCode);
  }
  public String getCountyParishCode()
  {
    return countyParish.getCode();
  }
  public CodedEntity getType()
  {
    return type;
  }
  public String getTypeCode()
  {
    return type.getCode();
  }
  public void setTypeCode(String typeCode)
  {
    this.type.setCode(typeCode);
  }
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((city == null) ? 0 : city.hashCode());
//	result = prime * result + ((country == null) ? 0 : country.hashCode());
//	result = prime * result + ((getCountyParishCode() == null) ? 0 : getCountyParishCode().hashCode());
	result = prime * result + ((getStateCode() == null) ? 0 : getStateCode().hashCode());
	result = prime * result + ((street == null) ? 0 : street.hashCode());
	result = prime * result + ((street2 == null) ? 0 : street2.hashCode());
//	result = prime * result + ((zip == null) ? 0 : zip.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Address other = (Address) obj;

	//City/State/Street/Street2
	if (city == null) {
		if (other.city != null)
			return false;
	} else if (!city.equals(other.city))
		return false;
	if (getStateCode() == null) {
		if (other.getStateCode() != null)
			return false;
	} else if (!getStateCode().equals(other.getStateCode()))
		return false;
	if (street == null) {
		if (other.street != null)
			return false;
	} else if (!street.equals(other.street))
		return false;
	if (street2 == null) {
		if (other.street2 != null)
			return false;
	} else if (!street2.equals(other.street2))
		return false;

	return true;
}
}
