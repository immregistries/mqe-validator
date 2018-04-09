package org.immregistries.dqa.validator;

public enum IISProperties {
  INSTANCE;
  // This should be formatted with the DateTimeFormatter notation.
  // private final String expectedDateFormat = "yyyyMMddHHmmss";//MCIR
  private final String expectedDateFormat = "yyyyMMddHHmmssZ";

  public String getExpectedDateFormat() {
    return this.expectedDateFormat;
  }
}
