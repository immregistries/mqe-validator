package org.immregistries.mqe.validator.address;

public class AddressCleansingRequest {

  private String primaryStreet;
  private String secondaryStreet;
  private String city;
  private String state;
  private String postalCode;
  private String countyCode;
  private String countryCode;

  public AddressCleansingRequest() {
    // empty constructor
  }

  /**
   * Builds an address cleansing request object that accepts state/country abbreviations and uses the default value
   * to determine whether or not user mappings should be used.
   *
   * @param primaryStreet   Primary street range (e.g. house number and street)
   * @param secondaryStreet Secondary street range (e.g. apartment number)
   * @param city            City
   * @param stateAbbr       State abbreviation. Expects the abbreviation itself (e.g. "MI"), not a State object
   * @param postalCode      Postal code. Can be 5- or 9-digit version
   * @param countyCode      County code
   * @param countryIsoCode2 Country abbreviation. Expects the 2-digit ISO code (e.g. "US"), not a Country object
   */
  public AddressCleansingRequest(String primaryStreet, String secondaryStreet, String city,
      String stateAbbr, String postalCode, String countyCode, String countryIsoCode2) {
    this.setPrimaryStreet(primaryStreet);
    this.setSecondaryStreet(secondaryStreet);
    this.setCity(city);
    this.setPostalCode(postalCode);
    this.setState(stateAbbr);
    this.setCountyCode(countyCode);
    this.setCountryCode(countryCode);
  }

  public String getCountyCode() {
    return countyCode;
  }

  public void setCountyCode(String countyCode) {
    this.countyCode = countyCode;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public String getPrimaryStreet() {
    return primaryStreet;
  }

  public String getSecondaryStreet() {
    return secondaryStreet;
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPrimaryStreet(String primaryStreet) {
    this.primaryStreet = primaryStreet;
  }

  public void setSecondaryStreet(String secondaryStreet) {
    this.secondaryStreet = secondaryStreet;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public void setState(String state) {
    this.state = state;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  @Override
  public String toString() {
    return "AddressCleansingRequest{" +
        "primaryStreet='" + primaryStreet + '\'' +
        ", secondaryStreet='" + secondaryStreet + '\'' +
        ", city='" + city + '\'' +
        ", state='" + state + '\'' +
        ", postalCode='" + postalCode + '\'' +
        ", countryCode='" + countryCode + '\'' +
        '}';
  }


}
