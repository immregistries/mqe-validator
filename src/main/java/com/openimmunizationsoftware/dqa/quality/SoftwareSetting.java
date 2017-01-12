package com.openimmunizationsoftware.dqa.quality;

import java.io.Serializable;

public class SoftwareSetting implements Serializable {
  private int settingId = 0;
  private Software software = null;
  private ServiceOption serviceOption = null;
  private String optionValue = "";
  public int getSettingId() {
    return settingId;
  }
  public void setSettingId(int settingId) {
    this.settingId = settingId;
  }
  public Software getSoftware() {
    return software;
  }
  public void setSoftware(Software software) {
    this.software = software;
  }
  public ServiceOption getServiceOption() {
    return serviceOption;
  }
  public void setServiceOption(ServiceOption serviceOption) {
    this.serviceOption = serviceOption;
  }
  public String getOptionValue() {
    return optionValue;
  }
  public void setOptionValue(String optionValue) {
    this.optionValue = optionValue;
  }
}
