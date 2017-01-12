package com.openimmunizationsoftware.dqa.quality;

import java.io.Serializable;

public class ServiceOption implements Serializable{

  private int optionId = 0;
  private Service service = null;
  private String optionName = "";
  private String optionLabel = "";
  private String description = "";
  private String validValues = "";
  private ServiceOption baseServiceOption = null;
  
  public int getOptionId() {
    return optionId;
  }
  public void setOptionId(int optionId) {
    this.optionId = optionId;
  }
  public Service getService() {
    return service;
  }

  public void setService(Service service) {
    this.service = service;
  }

  public String getServiceType() {
    return service == null ? null : service.getServiceType();
  }

  public void setServiceType(String serviceType) {
    this.service = Service.getService(serviceType);
  }
  public String getOptionName() {
    return optionName;
  }
  public void setOptionName(String optionName) {
    this.optionName = optionName;
  }
  public String getOptionLabel() {
    return optionLabel;
  }
  public void setOptionLabel(String optionLabel) {
    this.optionLabel = optionLabel;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public String getValidValues() {
    return validValues;
  }
  public void setValidValues(String validValues) {
    this.validValues = validValues;
  }
  public ServiceOption getBaseServiceOption() {
    return baseServiceOption;
  }
  public void setBaseServiceOption(ServiceOption baseOption) {
    this.baseServiceOption = baseOption;
  }
  
  @Override
  public String toString() {
    return getOptionLabel();
  }
  
  @Override
  public int hashCode() {
    return optionId;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof ServiceOption)
    {
      return ((ServiceOption) obj).getOptionId() == this.getOptionId();
    }
    return super.equals(obj);
  }
  
}
