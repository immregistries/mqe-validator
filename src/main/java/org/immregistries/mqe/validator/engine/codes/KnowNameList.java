package org.immregistries.mqe.validator.engine.codes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.immregistries.mqe.validator.engine.codes.KnownName.NameType;

public enum KnowNameList {
  INSTANCE;
  private final List<KnownName> names;
  private final Map<NameType, List<KnownName>> typeMap;

  private KnowNameList() {
    this.names = getNameList();
    this.typeMap = mapNamesByType(this.names);
  }


  public List<KnownName> getNames() {
    List<KnownName> nms = new ArrayList<KnownName>();
    Collections.copy(nms, names);;
    return nms;
  }

  public List<KnownName> getKnownNames(NameType type) {
    List<KnownName> nms = typeMap.get(type);
    if (nms == null) {
      nms = new ArrayList<KnownName>();
    }

    return nms;
  }

  public boolean firstNameOnlyMatch(NameType type, String first) {
    List<KnownName> typeNames = getKnownNames(type);
    for (KnownName name : typeNames) {
      if (name.onlyNameFirst()) {
        if (name.getNameFirst().equalsIgnoreCase(first)) {
          return true;
        }
      }
    }

    return false;
  }

  public boolean lastNameOnlyMatch(NameType type, String last) {
    List<KnownName> typeNames = getKnownNames(type);
    for (KnownName name : typeNames) {
      if (name.onlyNameLast()) {
        if (name.getNameLast().equalsIgnoreCase(last)) {
          return true;
        }
      }
    }

    return false;
  }

  public boolean middleNameOnlyMatch(NameType type, String middle) {
    List<KnownName> typeNames = getKnownNames(type);
    for (KnownName name : typeNames) {
      if (name.onlyNameMiddle()) {
        if (name.getNameMiddle().equalsIgnoreCase(middle)) {
          return true;
        }
      }
    }

    return false;
  }

  public boolean matches(NameType type, String first, String last, String middle) {
    List<KnownName> typeNames = getKnownNames(type);
    if (typeNames == null) {
      return false;
    }
    for (KnownName name : typeNames) {

      if (!StringUtils.isBlank(name.getNameLast()) && !StringUtils.isBlank(last)
          && name.getNameLast().equalsIgnoreCase(last)) {
        return true;
      }

      if (!StringUtils.isBlank(name.getNameFirst()) && !StringUtils.isBlank(first)
          && name.getNameFirst().equalsIgnoreCase(first)) {
        return true;
      }

      if (!StringUtils.isBlank(name.getNameMiddle()) && !StringUtils.isBlank(middle)
          && name.getNameMiddle().equalsIgnoreCase(middle)) {
        return true;
      }
    }
    return false;
  }

  private Map<NameType, List<KnownName>> mapNamesByType(List<KnownName> names) {
    Map<NameType, List<KnownName>> map = new HashMap<NameType, List<KnownName>>();
    for (KnownName name : names) {
      List<KnownName> nameList = map.get(name.getNameType());
      if (nameList == null) {
        nameList = new ArrayList<KnownName>();
        map.put(name.getNameType(), nameList);
      }
      nameList.add(name);
    }

    return map;
  }

  private List<KnownName> getNameList() {
    List<KnownName> names = new ArrayList<KnownName>();
    names.add(new KnownName(1, "", "X", "", null, NameType.INVALID_NAME));
    names.add(new KnownName(2, "", "U", "", null, NameType.INVALID_NAME));
    names.add(new KnownName(3, "", "UN", "", null, NameType.INVALID_NAME));
    names.add(new KnownName(4, "", "UK", "", null, NameType.INVALID_NAME));
    names.add(new KnownName(5, "", "UNK", "", null, NameType.INVALID_NAME));
    names.add(new KnownName(6, "", "UNKN", "", null, NameType.INVALID_NAME));
    names.add(new KnownName(7, "", "NONE", "", null, NameType.INVALID_NAME));
    names.add(new KnownName(8, "X", "", "", null, NameType.INVALID_NAME));
    names.add(new KnownName(9, "U", "", "", null, NameType.INVALID_NAME));
    names.add(new KnownName(10, "UN", "", "", null, NameType.INVALID_NAME));
    names.add(new KnownName(11, "UK", "", "", null, NameType.INVALID_NAME));
    names.add(new KnownName(12, "UNK", "", "", null, NameType.INVALID_NAME));
    names.add(new KnownName(13, "UNKN", "", "", null, NameType.INVALID_NAME));
    names.add(new KnownName(14, "NONE", "", "", null, NameType.INVALID_NAME));
    names.add(new KnownName(15, "", "", "UN", null, NameType.INVALID_NAME));
    names.add(new KnownName(16, "", "", "UK", null, NameType.INVALID_NAME));
    names.add(new KnownName(17, "", "", "UNK", null, NameType.INVALID_NAME));
    names.add(new KnownName(18, "", "", "UNKN", null, NameType.INVALID_NAME));
    names.add(new KnownName(19, "", "", "NONE", null, NameType.INVALID_NAME));
    names.add(new KnownName(20, "", "BABY BOY", "", null, NameType.UNNAMED_NEWBORN));
    names.add(new KnownName(21, "", "BABY GIRL", "", null, NameType.UNNAMED_NEWBORN));
    names.add(new KnownName(22, "", "BABY", "", null, NameType.UNNAMED_NEWBORN));
    names.add(new KnownName(23, "", "NEWBORN", "", null, NameType.UNNAMED_NEWBORN));
    names.add(new KnownName(24, "BOY", "BABY", "", null, NameType.UNNAMED_NEWBORN));
    names.add(new KnownName(25, "GIRL", "BABY", "", null, NameType.UNNAMED_NEWBORN));
    names.add(new KnownName(26, "MOUSE", "MICKY", "", null, NameType.TEST_PATIENT));
    names.add(new KnownName(27, "DUCK", "DONALD", "", null, NameType.TEST_PATIENT));
    names.add(new KnownName(28, "PATIENT", "TEST", "", null, NameType.TEST_PATIENT));
    names.add(new KnownName(29, "", "TEST", "", null, NameType.TEST_PATIENT));
    names.add(new KnownName(30, "TEST", "", "", null, NameType.TEST_PATIENT));
    names.add(new KnownName(31, "", "PATIENT", "", null, NameType.TEST_PATIENT));
    names.add(new KnownName(32, "PATIENT", "", "", null, NameType.TEST_PATIENT));
    names.add(new KnownName(34, "PETERSON", "BENJAMIN", "S", null, NameType.TEST_PATIENT));
    names.add(new KnownName(35, "", "ZZ", "", null, NameType.INVALID_PREFIXES));
    names.add(new KnownName(36, "", "XX", "", null, NameType.INVALID_PREFIXES));
    names.add(new KnownName(37, "ZZ", "", "", null, NameType.INVALID_PREFIXES));
    names.add(new KnownName(38, "XX", "", "", null, NameType.INVALID_PREFIXES));
    names.add(new KnownName(39, "", "", "ZZ", null, NameType.INVALID_PREFIXES));
    names.add(new KnownName(40, "", "", "XX", null, NameType.INVALID_PREFIXES));
    names.add(new KnownName(41, "TEST", "TEST", "", null, NameType.TEST_PREFIXES));
    names.add(new KnownName(42, "LNU", "FNU", "", null, NameType.UNNAMED_PATIENT));
    names.add(new KnownName(43, "", "B1", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(44, "", "G1", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(45, "", "G2", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(46, "", "UNNAMED", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(47, "", "UNKNOWN", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(48, "", "", "UNKNOWN", null, NameType.JUNK_NAME));
    names.add(new KnownName(49, "UNKNOWN", "", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(50, "", "NONE", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(51, "", "", "NONE", null, NameType.JUNK_NAME));
    names.add(new KnownName(52, "NONE", "", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(53, "", "NOFIRSTNAME", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(54, "", "NO FIRST NAME", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(55, "", "NO FIRSTNAME", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(56, "", "", "NOMIDDLENAME", null, NameType.JUNK_NAME));
    names.add(new KnownName(57, "", "", "NO MIDDLE NAME", null, NameType.JUNK_NAME));
    names.add(new KnownName(58, "", "", "NO MIDDLENAME", null, NameType.JUNK_NAME));
    names.add(new KnownName(59, "", "", "NOLASTNAME", null, NameType.JUNK_NAME));
    names.add(new KnownName(60, "NO LAST NAM", "", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(61, "NO LASTNAME", "", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(62, "", "NONAME", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(63, "", "NO NAME", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(64, "", "", "NONAME", null, NameType.JUNK_NAME));
    names.add(new KnownName(65, "", "", "NO NAME", null, NameType.JUNK_NAME));
    names.add(new KnownName(66, "NONAME", "", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(67, "NO NAME", "", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(68, "", "EMPTY", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(69, "", "MISSING", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(70, "", "", "EMPTY", null, NameType.JUNK_NAME));
    names.add(new KnownName(71, "", "", "MISSING", null, NameType.JUNK_NAME));
    names.add(new KnownName(72, "EMPTY", "", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(73, "MISSING", "", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(74, "", "BABY", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(75, "", "BABY BOY", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(76, "", "BABY GIRL", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(77, "", "GIRL", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(78, "", "BOY", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(79, "", "A BOY", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(80, "", "A GIRL", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(81, "", "ABABYGIRL", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(82, "", "B BOY", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(83, "", "B GIRL", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(84, "", "BABY OY", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(85, "", "BABY BAY", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(86, "", "BABY BO", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(87, "", "BABY BOY", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(88, "", "BABY BOY 2", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(89, "", "BABY BOY A", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(90, "", "BABY BOY B", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(91, "", "BABY BOY #1", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(92, "", "BABY BOY 2", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(93, "", "BABY BOY1", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(94, "", "BABY GIRL 1", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(95, "", "BABY GIRL B", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(96, "", "BABY GIRL #1", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(97, "", "BABY GIRL 1", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(98, "", "BABY GIRL A", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(99, "", "BABY GIRL B", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(100, "", "BABY GIRL ONE", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(101, "", "BABY GIRL1", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(102, "", "BABY GRIL", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(103, "", "BABY M", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(104, "", "BABY SISTER", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(105, "", "BABY-GIRL", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(106, "", "BABYBOY", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(107, "", "BABYBOY-1", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(108, "", "BABYBOY-2", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(109, "", "BABYBOYA", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(110, "", "BABYGIR", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(111, "", "BABYGIRL", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(112, "", "BABYGIRL-A", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(113, "", "BABYGIRL-B", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(114, "", "BB", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(115, "", "BBABYGIRL", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(116, "", "BG", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(117, "", "BOY #1", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(118, "", "BOY #2", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(119, "", "BOY 1", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(120, "", "BOY 2", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(121, "", "BOY 3", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(122, "", "BOY A", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(123, "", "BOY B", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(124, "", "BOY ONE", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(125, "", "BOY TWO", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(126, "", "BOY+", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(127, "", "C BOY", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(128, "", "GIRL # 2", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(129, "", "GIRL #2", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(130, "", "GIRL (L)", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(131, "", "GIRL A", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(132, "", "GIRL B", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(133, "", "GIRL TWIN 2", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(134, "", "GIRL#1", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(135, "", "GIRL#2", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(136, "", "TEST GIRL", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(137, "", "TWIN BOY", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(138, "", "TWIN GIRL A", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(139, "", "B2", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(140, "", "NEWBORN", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(141, "", "TWIN GIRL", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(142, "", "BABU GIRL TWIN", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(143, "", "BABY BOY TWIN", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(144, "", "BABY BOY 1", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(145, "", "BBOY", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(146, "", "BABY GIRL", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(147, "", "BABY GIRL TWO", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(148, "", "BABY 1", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(149, "", "BABYGIRL A", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(150, "", "BABYBOY 2", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(151, "", "BBTWO", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(152, "", "BBONE", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(153, "", "BGONE", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(154, "", "BGTWO", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(155, "", "B-G", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(156, "", "BG2", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(157, "", "BG1", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(158, "", "MALE", "", null, NameType.JUNK_NAME));
    names.add(new KnownName(159, "", "FEMALE", "", null, NameType.JUNK_NAME));

    return names;
  }
}
