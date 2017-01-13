/*
 * Copyright 2013 by Dandelion Software & Research, Inc (DSR)
 * 
 * This application was written for immunization information system (IIS) community and has
 * been released by DSR under an Apache 2 License with the hope that this software will be used
 * to improve Public Health.  
 */
package org.immregistries.dqa.validator.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;

import org.immregistries.dqa.validator.engine.issues.PotentialIssue;
import org.immregistries.dqa.validator.engine.issues.PotentialIssueStatus;

public class SubmitterProfile implements Serializable
{

  private static final long serialVersionUID = 1l;

  public static final int MASTER_HL7 = 1;
  public static final int TEST_HL7 = 101;

  public static final String DATA_FORMAT_HL7V2 = "HL7v2";

  public static final String PROFILE_STATUS_CLOSED = "Closed";
  public static final String PROFILE_STATUS_HOLD = "Hold";
  public static final String PROFILE_STATUS_PROD = "Prod";
  public static final String PROFILE_STATUS_SETUP = "Setup";
  public static final String PROFILE_STATUS_TEMPLATE = "Template";
  public static final String PROFILE_STATUS_VALIDATE = "Validate";
  public static final String PROFILE_STATUS_TEST = "Test";

  public static final String[] PROFILE_STATUS = { PROFILE_STATUS_CLOSED, PROFILE_STATUS_HOLD, PROFILE_STATUS_PROD, PROFILE_STATUS_SETUP,
      PROFILE_STATUS_TEMPLATE, PROFILE_STATUS_VALIDATE, PROFILE_STATUS_TEST };

  public static final String TRANSFER_PRIORITY_HIGH = "High";
  public static final String TRANSFER_PRIORITY_HIGHEST = "Highest";
  public static final String TRANSFER_PRIORITY_LOW = "Low";
  public static final String TRANSFER_PRIORITY_LOWEST = "Lowest";
  public static final String TRANSFER_PRIORITY_NORMAL = "Normal";

  public static final String[] TRANSFER_PRIORITY = { TRANSFER_PRIORITY_HIGH, TRANSFER_PRIORITY_HIGHEST, TRANSFER_PRIORITY_LOW,
      TRANSFER_PRIORITY_LOWEST, TRANSFER_PRIORITY_NORMAL };

  private String accessKey = "";
  private String dataFormat = "";
  private Organization organization = null;
  private HashMap<PotentialIssue, PotentialIssueStatus> potentialIssueStatusMap = null;
  private String profileCode = "";
  private int profileId = 0;
  private String profileLabel = "";
  private String profileStatus = "";
  private String transferPriority = "";


  @Override
  public boolean equals(Object obj)
  {
    if (obj instanceof SubmitterProfile)
    {
      return ((SubmitterProfile) obj).getProfileId() == profileId;
    }
    return super.equals(obj);
  }

  public String getAccessKey()
  {
    return accessKey;
  }


  public String getDataFormat()
  {
    return dataFormat;
  }

  public Organization getOrganization()
  {
    return organization;
  }

  public PotentialIssueStatus getPotentialIssueStatus(PotentialIssue potentialIssue)
  {
    return potentialIssueStatusMap.get(potentialIssue);
  }

  public HashMap<PotentialIssue, PotentialIssueStatus> getPotentialIssueStatusMap()
  {
    return potentialIssueStatusMap;
  }

  public String getProfileCode()
  {
    return profileCode;
  }

  public int getProfileId()
  {
    return profileId;
  }

  public String getProfileLabel()
  {
    return profileLabel;
  }

  public String getProfileStatus()
  {
    return profileStatus;
  }

  public String getTransferPriority()
  {
    return transferPriority;
  }

  @Override
  public int hashCode()
  {
    return getProfileId();
  }

  public boolean isProfileStatusClosed()
  {
    return PROFILE_STATUS_CLOSED.equals(profileStatus);
  }

  public boolean isProfileStatusHold()
  {
    return PROFILE_STATUS_HOLD.equals(profileStatus);
  }

  public boolean isProfileStatusProd()
  {
    return PROFILE_STATUS_PROD.equals(profileStatus);
  }

  public boolean isProfileStatusSetup()
  {
    return PROFILE_STATUS_SETUP.equals(profileStatus);
  }

  public boolean isProfileStatusTemplate()
  {
    return PROFILE_STATUS_TEMPLATE.equals(profileStatus);
  }

  public boolean isProfileStatusTest()
  {
    return PROFILE_STATUS_TEST.equals(profileStatus);
  }

  public void setAccessKey(String accessKey)
  {
    this.accessKey = accessKey;
  }

  public void setDataFormat(String dataFormat)
  {
    this.dataFormat = dataFormat;
  }

  public void setOrganization(Organization organization)
  {
    this.organization = organization;
  }

  public void setPotentialIssueStatusMap(HashMap<PotentialIssue, PotentialIssueStatus> potentialIssueStatusMap)
  {
    this.potentialIssueStatusMap = potentialIssueStatusMap;
  }

  public void setProfileCode(String profileCode)
  {
    this.profileCode = profileCode;
  }

  public void setProfileId(int profileId)
  {
    this.profileId = profileId;
  }

  public void setProfileLabel(String profileLabel)
  {
    this.profileLabel = profileLabel;
  }

  public void setProfileStatus(String profileStatus)
  {
    this.profileStatus = profileStatus;
  }

  public void setTransferPriority(String transferPriority)
  {
    this.transferPriority = transferPriority;
  }

  private static Random random;

  private static char[] randomCharacters = "ABCDEFGHJKMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz23456789".toCharArray();

  public void generateAccessKey()
  {
    if (random == null)
    {
      random = new Random();
    }
    accessKey = "";
    for (int i = 0; i < 16; i++)
    {
      int pos = random.nextInt(randomCharacters.length);
      accessKey += randomCharacters[pos];
    }
  }
}
