/*
 * Copyright 2013 by Dandelion Software & Research, Inc (DSR)
 * 
 * This application was written for immunization information system (IIS) community and has been
 * released by DSR under an Apache 2 License with the hope that this software will be used to
 * improve Public Health.
 */
package org.immregistries.mqe.validator.engine.codes;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.immregistries.mqe.vxu.hl7.Name;

public class KnownName {

  public enum NameType {
    INVALID_NAME("Invalid Name"),
    UNNAMED_NEWBORN("Unnamed Newborn"),
    TEST_PATIENT("Test Patient"),
    INVALID_PREFIXES("Invalid Prefixes"),
    UNNAMED_PATIENT("Unnamed Patient"),
    JUNK_NAME("Junk Name"),
    TEST_PREFIXES("Test Prefixes");

    private String desc;

    private NameType(String d) {
      this.desc = d;
    }

    public String getDesc() {
      return desc;
    }

  }

  private int knownNameId = 0;
  private String nameLast = "";
  private String nameFirst = "";
  private String nameMiddle = "";
  private Date birthDate = null;
  private NameType nameType;

  public KnownName() {

  }

  public KnownName(int knownNameId, String nameLast, String nameFirst, String nameMiddle,
      Date birthDate, NameType nameType) {
    super();
    this.knownNameId = knownNameId;
    this.nameLast = nameLast;
    this.nameFirst = nameFirst;
    this.nameMiddle = nameMiddle;
    this.birthDate = birthDate;
    this.nameType = nameType;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (nameLast != null) {
      sb.append(nameLast);
      sb.append(" ");
    }
    if (nameFirst != null) {
      sb.append(nameFirst);
      sb.append(" ");
    }
    if (nameMiddle != null) {
      sb.append(nameMiddle);
      sb.append(" ");
    }
    if (birthDate != null) {
      SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
      sb.append(sdf.format(birthDate));
      sb.append(" ");
    }
    return sb.toString();
  }

  public boolean match(Name name, Date personBirthDate) {
    if (nameLast != null && name.getLast() != null && !nameLast.equalsIgnoreCase(name.getLast())) {
      return false;
    }
    if (nameFirst != null && name.getFirst() != null
        && !nameFirst.equalsIgnoreCase(name.getFirst())) {
      return false;
    }
    if (nameMiddle != null && name.getMiddle() != null
        && !nameMiddle.equalsIgnoreCase(name.getMiddle())) {
      return false;
    }
    if (birthDate != null && personBirthDate != null && birthDate.equals(personBirthDate)) {
      return false;
    }
    return true;
  }

  public int getKnownNameId() {
    return knownNameId;
  }

  public boolean onlyNameLast() {
    return StringUtils.isNotEmpty(nameFirst) && StringUtils.isNotEmpty(nameMiddle) && birthDate == null;
  }

  public boolean onlyNameFirst() {
    return StringUtils.isNotEmpty(nameLast) && StringUtils.isNotEmpty(nameMiddle) && birthDate == null;
  }

  public boolean onlyNameMiddle() {
    return StringUtils.isNotEmpty(nameFirst) && StringUtils.isNotEmpty(nameLast) && birthDate == null;
  }

  public void setKnownNameId(int knownNameId) {
    this.knownNameId = knownNameId;
  }

  public String getNameLast() {
    return nameLast;
  }

  public void setNameLast(String nameLast) {
    this.nameLast = nameLast;
  }

  public String getNameFirst() {
    return nameFirst;
  }

  public void setNameFirst(String nameFirst) {
    this.nameFirst = nameFirst;
  }

  public String getNameMiddle() {
    return nameMiddle;
  }

  public void setNameMiddle(String nameMiddle) {
    this.nameMiddle = nameMiddle;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public NameType getNameType() {
    return nameType;
  }

  public String getNameTypeDesc() {
    return nameType.getDesc();
  }

}
