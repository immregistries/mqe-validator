/*
 * Copyright 2013 by Dandelion Software & Research, Inc (DSR)
 * 
 * This application was written for immunization information system (IIS) community and has
 * been released by DSR under an Apache 2 License with the hope that this software will be used
 * to improve Public Health.  
 */
package com.openimmunizationsoftware.dqa.model;

import java.io.Serializable;

import com.openimmunizationsoftware.dqa.model.types.Address;
import com.openimmunizationsoftware.dqa.model.types.CodedEntity;
import com.openimmunizationsoftware.dqa.model.types.Name;
import com.openimmunizationsoftware.dqa.model.types.PhoneNumber;

public class NextOfKin implements Skippable, Serializable
{
  
  private static final long serialVersionUID = 1l;
  
  public static final String RELATIONSHIP_BROTHER = "BRO";
  public static final String RELATIONSHIP_CARE_GIVER = "CGV";
  public static final String RELATIONSHIP_CHILD = "CHD";
  public static final String RELATIONSHIP_FATHER = "FTH";
  public static final String RELATIONSHIP_FOSTER_CHILD = "FCH";
  public static final String RELATIONSHIP_GRANDPARENT = "GRP";
  public static final String RELATIONSHIP_GUARDIAN = "GRD";
  public static final String RELATIONSHIP_MOTHER = "MTH";
  public static final String RELATIONSHIP_OTHER = "OTH";
  public static final String RELATIONSHIP_PARENT = "PAR";
  public static final String RELATIONSHIP_SELF = "SEL";
  public static final String RELATIONSHIP_SIBLING = "SIB";
  public static final String RELATIONSHIP_SISTER = "SIS";
  public static final String RELATIONSHIP_SPOUSE = "SPO";
  public static final String RELATIONSHIP_STEPCHILD = "SCH";
  
  public boolean isResponsibleParty() {
	  switch (this.getRelationshipCode()) {
	  	case NextOfKin.RELATIONSHIP_CARE_GIVER:
    	case NextOfKin.RELATIONSHIP_FATHER:
    	case NextOfKin.RELATIONSHIP_GRANDPARENT:
    	case NextOfKin.RELATIONSHIP_MOTHER:
    	case NextOfKin.RELATIONSHIP_PARENT:
    	case NextOfKin.RELATIONSHIP_GUARDIAN:
    		return true;
	  }
	  
	  return false;
  }
  
  public boolean isChild() {
	  switch (this.getRelationshipCode()) {
	  	case NextOfKin.RELATIONSHIP_CHILD:
    	case NextOfKin.RELATIONSHIP_FOSTER_CHILD:
    	case NextOfKin.RELATIONSHIP_STEPCHILD:
    		return true;
	  }
	  return false;
  }
  
  public boolean isSelf() {
	  return RELATIONSHIP_SELF.equals(this.getRelationshipCode());
  }
  
  private Address address = new Address();
  private Name name = new Name();
  private long nextOfKinId;
  private PhoneNumber phone = new PhoneNumber();
  private int positionId = 0;
//  private long receivedId = 0l;
  private CodedEntity relationship = new CodedEntity(CodeTable.Type.PERSON_RELATIONSHIP);
  private boolean skipped = false;
  private String primaryLanguageCode;
  
  public Address getAddress()
  {
    return address;
  }


  public void setAddress(Address a) {
	  this.address = a;
  }
  
  public Name getName()
  {
    return name;
  }

  public String getNameFirst()
  {
    return name.getFirst();
  }

  public String getNameLast()
  {
    return name.getLast();
  }

  public String getNameMiddle()
  {
    return name.getMiddle();
  }

  public String getNamePrefix()
  {
    return name.getPrefix();
  }

  public String getNameSuffix()
  {
    return name.getSuffix();
  }

  public String getNameTypeCode()
  {
    return name.getTypeCode();
  }

  public long getNextOfKinId()
  {
    return nextOfKinId;
  }

  public PhoneNumber getPhone()
  {
    return phone;
  }

  public String getPhoneNumber()
  {
    return phone.getNumber();
  }

  public int getPositionId()
  {
    return positionId;
  }

  public CodedEntity getRelationship()
  {
    return relationship;
  }

  public String getRelationshipCode()
  {
    return relationship.getCode();
  }

  public boolean isSkipped()
  {
    return skipped;
  }

  public void setNameFirst(String nameFirst)
  {
    name.setFirst(nameFirst);
  }

  public void setNameLast(String nameLast)
  {
    name.setLast(nameLast);
  }

  public void setNameMiddle(String nameMiddle)
  {
    name.setMiddle(nameMiddle);
  }

  public void setNamePrefix(String namePrefix)
  {
    name.setPrefix(namePrefix);
  }

  public void setNameSuffix(String nameSuffix)
  {
    name.setSuffix(nameSuffix);
  }

  public void setNameTypeCode(String nameTypeCode)
  {
    name.setTypeCode(nameTypeCode);
  }

  public void setNextOfKinId(long nextOfKinId)
  {
    this.nextOfKinId = nextOfKinId;
  }

  public void setPhoneNumber(String phoneNumber)
  {
    phone.setNumber(phoneNumber);
  }

  public void setPositionId(int positionId)
  {
    this.positionId = positionId;
  }

  public void setRelationshipCode(String relationshipCode)
  {
    relationship.setCode(relationshipCode);
  }

  public void setSkipped(boolean skipped)
  {
    this.skipped = skipped;
  }

/**
 * @return the primaryLanguageCode
 */
public String getPrimaryLanguageCode() {
	return primaryLanguageCode;
}

/**
 * @param primaryLanguageCode the primaryLanguageCode to set
 */
public void setPrimaryLanguageCode(String primaryLanguageCode) {
	this.primaryLanguageCode = primaryLanguageCode;
}

}
