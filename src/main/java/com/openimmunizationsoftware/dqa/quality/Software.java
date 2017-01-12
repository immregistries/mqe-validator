package com.openimmunizationsoftware.dqa.quality;

import java.io.Serializable;
import java.util.List;

public class Software implements Serializable {

  private static final long serialVersionUID = 1L;
  
  public static final String VISIBLE_STATUS_VISIBLE = "V";
  public static final String VISIBLE_STATUS_RESTRICTED = "R";
  public static final String VISIBLE_STATUS_CLOSED = "X";

  private int softwareId = 0;
  private String label = "";
  private String serviceUrl = "";
  private Service service = null;
  private String scheduleName = "";
  private String visibleStatus = "";
  
  private List<SoftwareSetting> softwareSettingList = null;

  public List<SoftwareSetting> getSoftwareSettingList() {
    return softwareSettingList;
  }

  public void setSoftwareSettingList(List<SoftwareSetting> softwareSettingList) {
    this.softwareSettingList = softwareSettingList;
  }

  public String getVisibleStatus() {
    return visibleStatus;
  }

  public void setVisibleStatus(String visibleStatus) {
    this.visibleStatus = visibleStatus;
  }
  
  public boolean isVisibleStatusVisible()
  {
    return VISIBLE_STATUS_VISIBLE.equals(visibleStatus);
  }

  public boolean isVisibleStatusRestricted()
  {
    return VISIBLE_STATUS_RESTRICTED.equals(visibleStatus);
  }

  public boolean isVisibleStatusClosed()
  {
    return VISIBLE_STATUS_CLOSED.equals(visibleStatus);
  }

  public String getScheduleName() {
    return scheduleName;
  }

  public void setScheduleName(String scheduleName) {
    this.scheduleName = scheduleName;
  }

  public String getServiceUrl() {
    return serviceUrl;
  }

  public void setServiceUrl(String serviceUrl) {
    this.serviceUrl = serviceUrl;
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

  public int getSoftwareId() {
    return softwareId;
  }

  public void setSoftwareId(int softwareId) {
    this.softwareId = softwareId;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    return label;
  }
}
