/*
 * Copyright 2013 by Dandelion Software & Research, Inc (DSR)
 * 
 * This application was written for immunization information system (IIS) community and has
 * been released by DSR under an Apache 2 License with the hope that this software will be used
 * to improve Public Health.  
 */
package com.openimmunizationsoftware.dqa.parser;

import java.util.Date;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.SubmitterProfile;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;

public abstract class VaccinationParser extends ValidateMessage
{
  public VaccinationParser(SubmitterProfile profile) {
    super(profile);
  }

  public abstract void createVaccinationUpdateMessage(MessageReceived messageReceived);

//  public abstract void createQueryMessage(QueryReceived queryReceived);

//  public abstract String makeAckMessage(MessageReceived messageReceived);
//  
  protected Date createDate(PotentialIssue piInvalid, PotentialIssue piNoTimeZone, String fieldValue)
  {
    HL7DateAnalyzer dateAnalyzer = new HL7DateAnalyzer(fieldValue);

    if (dateAnalyzer.hasErrors())
    {
      registerIssue(piInvalid);
    }
    if (piNoTimeZone != null && !dateAnalyzer.isHasTimezone())
    {
      registerIssue(piNoTimeZone);
    }
    return dateAnalyzer.getDate();

  }

}
