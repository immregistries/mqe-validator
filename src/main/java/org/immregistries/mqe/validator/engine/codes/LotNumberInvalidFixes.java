package org.immregistries.mqe.validator.engine.codes;

import java.util.ArrayList;
import java.util.List;

public enum LotNumberInvalidFixes {
                                   INSTANCE;
  private final List<String> invalidPrefixes;
  private final List<String> invalidSuffixes;
  private final List<String> invalidInfixes;
  private final List<String> separators;



  private List<String> createInvalidPrefixes() {
    List<String> l = new ArrayList<>();
    for (String s : new String[] {"LOT", "(P)", "MED", "SKB", "LOT", "PMC", "WSD", "WAL"}) {
      l.add(s);
    }
    return l;
  }

  private List<String> createInvalidSuffixes() {
    List<String> l = new ArrayList<>();
    for (String s : new String[] {"(P)", "-P", "-S", "-C", "-H", "-V", "*", "#", "(S)", "(P)",
        "MSD", "HSP", "SELECT", "CP", "VFC", "STATE", "CHIP", "ADULT", "ST-", "PRIVATE", "PED",
        "UNINSURED", "SPECIAL", "OVER19", "VMC", "-COUNT", "REAR", "PENT", "PENTACEL", "DTAP",
        "IPV", "ACTH", "HIB", "PFF", "FLU", "BOOST", "HAV", "GARDASIL", "ROTATEQ", "PEDVAX",
        "VARIVAX", "PNEU", "PNEUMOVAX", "MMR", "MENVEO", "MENACTRA", "FLU ZONE"}) {
      l.add(s);
    }
    return l;
  }

  private List<String> createInvalidInfixes() {
    List<String> l = new ArrayList<>();
    for (String s : new String[] {"ICE3"}) {
      l.add(s);
    }
    return l;
  }

  private List<String> createSeparators() {
    List<String> l = new ArrayList<>();
    for (String s : new String[] {",", "/", " - "}) {
      l.add(s);
    }
    return l;
  }

  private LotNumberInvalidFixes() {
    invalidPrefixes = createInvalidPrefixes();
    invalidSuffixes = createInvalidSuffixes();
    invalidInfixes = createInvalidInfixes();
    separators = createSeparators();
  }

  public List<String> getInvalidSuffixes() {
    return new ArrayList<String>(invalidSuffixes);
  }

  public List<String> getInvalidPrefixes() {
    return new ArrayList<String>(invalidPrefixes);
  }

  public List<String> getInvalidInfixes() {
    return new ArrayList<String>(invalidInfixes);
  }

  public List<String> getInvalidSeparators() {
    return new ArrayList<String>(separators);
  }


}
