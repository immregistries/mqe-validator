package org.immregistries.mqe.validator.address;

//import com.fasterxml.jackson.annotation.JsonCreator;
//import com.fasterxml.jackson.annotation.JsonValue;

import org.apache.commons.lang3.StringUtils;

/**
 * Enum representation of COUNTRY.
 * tocode/fromcode used by jaxb/hibernate annotations.
 */
public enum Country {
  //@formatter:off
    NONE(0, ".*", ".**",    0, "Invalid postal code"),
    AF(1, "AF", "AFG",      4, "Afghanistan"),
    AX(2, "AX", "ALA",    248, "Aland Islands"),
    AL(3, "AL", "ALB",      8, "Albania"),
    DZ(4, "DZ", "DZA",     12, "Algeria"),
    AS(5, "AS", "ASM",     16, "American Samoa "),
    AD(6, "AD", "AND",     20, "Andorra    "),
    AO(7, "AO", "AGO",     24, "Angola "),
    AI(8, "AI", "AIA",    660, "Anguilla   "),
    AQ(9, "AQ", "ATA",     10, "Antarctica "),
    AG(10, "AG", "ATG",    28, "Antigua and Barbuda"),
    AR(11, "AR", "ARG",    32, "Argentina"),
    AM(12, "AM", "ARM",    51, "Armenia"),
    AW(13, "AW", "ABW",   533, "Aruba"),
    AU(14, "AU", "AUS",    36, "Australia"),
    AT(15, "AT", "AUT",    40, "Austria"),
    AZ(16, "AZ", "AZE",    31, "Azerbaijan"),
    BS(17, "BS", "BHS",    44, "Bahamas"),
    BH(18, "BH", "BHR",    48, "Bahrain"),
    BD(19, "BD", "BGD",    50, "Bangladesh"),
    BB(20, "BB", "BRB",    52, "Barbados"),
    BY(21, "BY", "BLR",   112, "Belarus"),
    BE(22, "BE", "BEL",    56, "Belgium"),
    BZ(23, "BZ", "BLZ",    84, "Belize"),
    BJ(24, "BJ", "BEN",   204, "Benin"),
    BM(25, "BM", "BMU",    60, "Bermuda"),
    BT(26, "BT", "BTN",    64, "Bhutan"),
    BO(27, "BO", "BOL",    68, "Bolivia"),
    BA(28, "BA", "BIH",    70, "Bosnia and Herzegowina"),
    BW(29, "BW", "BWA",    72, "Botswana"),
    BV(30, "BV", "BVT",    74, "Bouvet Island"),
    BR(31, "BR", "BRA",    76, "Brazil"),
    IO(32, "IO", "IOT",    86, "British Indian Ocean Territory"),
    BN(33, "BN", "BRN",    96, "Brunei Darussalam"),
    BG(34, "BG", "BGR",   100, "Bulgaria"),
    BF(35, "BF", "BFA",   854, "Burkina Faso"),
    BI(36, "BI", "BDI",   108, "Burundi"),
    KH(37, "KH", "KHM",   116, "Cambodia"),
    CM(38, "CM", "CMR",   120, "Cameroon"),
    CA(39, "CA", "CAN",   124, "Canada"),
    CV(40, "CV", "CPV",   132, "Cape Verde"),
    KY(41, "KY", "CYM",   136, "Cayman Islands"),
    CF(42, "CF", "CAF",   140, "Central African Republic"),
    TD(43, "TD", "TCD",   148, "Chad"),
    CL(44, "CL", "CHL",   152, "Chile"),
    CN(45, "CN", "CHN",   156, "China"),
    CX(46, "CX", "CXR",   162, "Christmas Island"),
    CC(47, "CC", "CCK",   166, "Cocos (Keeling) Islands"),
    CO(48, "CO", "COL",   170, "Colombia"),
    KM(49, "KM", "COM",   174, "Comoros"),
    CG(50, "CG", "COG",   178, "Congo"),
    CD(51, "CD", "COD",   180, "Congo, Democratic Republic"),
    CK(52, "CK", "COK",   184, "Cook Islands"),
    CR(53, "CR", "CRI",   188, "Costa Rica"),
    HR(54, "HR", "HRV",   191, "Croatia"),
    CU(55, "CU", "CUB",   192, "Cuba"),
    CY(56, "CY", "CYP",   196, "Cyprus"),
    CZ(57, "CZ", "CZE",   203, "Czech Republic"),
    DK(58, "DK", "DNK",   208, "Denmark"),
    DJ(59, "DJ", "DJI",   262, "Djibouti"),
    DM(60, "DM", "DMA",   212, "Dominica"),
    DO(61, "DO", "DOM",   214, "Dominican Republic"),
    EC(62, "EC", "ECU",   218, "Ecuador"),
    EG(63, "EG", "EGY",   818, "Egypt"),
    SV(64, "SV", "SLV",   222, "El Salvador"),
    GQ(65, "GQ", "GNQ",   226, "Equatorial Guinea"),
    ER(66, "ER", "ERI",   232, "Eritrea"),
    EE(67, "EE", "EST",   233, "Estonia"),
    ET(68, "ET", "ETH",   231, "Ethiopia"),
    FK(69, "FK", "FLK",   238, "Falkland Islands"),
    FO(70, "FO", "FRO",   234, "Faroe Islands"),
    FJ(71, "FJ", "FJI",   242, "Fiji"),
    FI(72, "FI", "FIN",   246, "Finland"),
    FR(73, "FR", "FRA",   250, "France"),
    GF(74, "GF", "GUF",   254, "French Guiana"),
    PF(75, "PF", "PYF",   258, "French Polynesia"),
    TF(76, "TF", "ATF",   260, "French Southern Territories"),
    GA(77, "GA", "GAB",   266, "Gabon"),
    GM(78, "GM", "GMB",   270, "Gambia"),
    GE(79, "GE", "GEO",   268, "Georgia"),
    DE(80, "DE", "DEU",   276, "Germany"),
    GH(81, "GH", "GHA",   288, "Ghana"),
    GI(82, "GI", "GIB",   292, "Gibraltar"),
    GR(83, "GR", "GRC",   300, "Greece"),
    GL(84, "GL", "GRL",   304, "Greenland"),
    GD(85, "GD", "GrD",   308, "Grenada"),
    GP(86, "GP", "GlP",   312, "Guadeloupe"),
    GU(87, "GU", "GUM",   316, "Guam"),
    GT(88, "GT", "GTM",   320, "Guatemala"),
    GG(89, "GG", "GGY",   831, "Guernsey"),
    GN(90, "GN", "GiN",   324, "Guinea"),
    GW(91, "GW", "GNB",   624, "Guinea-Bissau"),
    GY(92, "GY", "GUY",   328, "Guyana"),
    HT(93, "HT", "HTI",   332, "Haiti"),
    HM(94, "HM", "HMD",   334, "Heard and McDonald Islands"),
    VA(95, "VA", "VAT",   336, "Holy See (Vatican City State)  "),
    HN(96, "HN", "HND",   340, "Honduras   "),
    HK(97, "HK", "HKG",   344, "Hong Kong  "),
    HU(98, "HU", "HUN",   348, "Hungary    "),
    IS(99, "IS", "ISL",   352, "Iceland    "),
    IN(100, "IN", "IND",  356, "India"),
    ID(101, "ID", "IDN",  360, "Indonesia"),
    IR(102, "IR", "IRN",  364, "Iran"),
    IQ(103, "IQ", "IRQ",  368, "Iraq"),
    IE(104, "IE", "IRL",  372, "Ireland"),
    IM(105, "IM", "IMN",  833, "Isle of Man"),
    IL(106, "IL", "ISR",  376, "Israel"),
    IT(107, "IT", "ITA",  380, "Italy"),
    JM(108, "JM", "JM ",  388, "Jamaica"),
    JP(109, "JP", "JPN",  392, "Japan"),
    JE(110, "JE", "JEY",  832, "Jersey"),
    JO(111, "JO", "JOR",  400, "Jordan"),
    KZ(112, "KZ", "KAZ",  398, "Kazakhstan"),
    KE(113, "KE", "KEN",  404, "Kenya"),
    KI(114, "KI", "KIR",  296, "Kiribati"),
    KR(115, "KR", "KOR",  410, "Korea, Republic of"),
    KP(116, "KP", "PRK",  408, "Korea, Democratic People's Republic of"),
    KW(117, "KW", "KWT",  414, "Kuwait"),
    KG(118, "KG", "KGZ",  417, "Kyrgyzstan"),
    LA(119, "LA", "LAO",  418, "Laos, People's Democratic Republic"),
    LV(120, "LV", "LVA",  428, "Latvia"),
    LB(121, "LB", "LBN",  422, "Lebanon"),
    LS(122, "LS", "LSO",  426, "Lesotho"),
    LR(123, "LR", "LBR",  430, "Liberia"),
    LY(124, "LY", "LBY",  434, "Libya"),
    LI(125, "LI", "LIE",  438, "Liechtenstein"),
    LT(126, "LT", "LTU",  440, "Lithuania"),
    LU(127, "LU", "LUX",  442, "Luxembourg"),
    MO(128, "MO", "MAC",  446, "Macau"),
    MK(129, "MK", "MKD",  807, "Macedonia, The Former Yugoslav Republic of"),
    MG(130, "MG", "MDG",  450, "Madagascar"),
    MW(131, "MW", "MWI",  454, "Malawi"),
    MY(132, "MY", "MYS",  458, "Malaysia"),
    MV(133, "MV", "MDV",  462, "Maldives"),
    ML(134, "ML", "MLI",  466, "Mali"),
    MT(135, "MT", "MLT",  470, "Malta"),
    MH(136, "MH", "MHL",  584, "Marshall Islands"),
    MQ(137, "MQ", "MTQ",  474, "Martinique"),
    MR(138, "MR", "MRT",  478, "Mauritania"),
    MU(139, "MU", "MUS",  480, "Mauritius"),
    YT(140, "YT", "MYT",  175, "Mayotte"),
    MX(141, "MX", "MEX",  484, "Mexico"),
    FM(142, "FM", "FSM",  583, "Micronesia, Federated States of"),
    MD(143, "MD", "MDA",  498, "Moldova"),
    MC(144, "MC", "MCO",  492, "Monaco"),
    MN(145, "MN", "MNF",  496, "Mongolia"),
    ME(146, "ME", "ME0",  499, "Montenegro"),
    MS(147, "MS", "MSR",  500, "Montserrat"),
    MA(148, "MA", "MAR",  504, "Morocco"),
    MZ(149, "MZ", "MOZ",  508, "Mozambique"),
    MM(150, "MM", "MMR",  104, "Myanmar"),
    NA(151, "NA", "NAM",  516, "Namibia"),
    NR(152, "NR", "NRU",  520, "Nauru"),
    NP(153, "NP", "NPL",  524, "Nepal"),
    NL(154, "NL", "NLD",  528, "Netherlands"),
    AN(155, "AN", "ANT",  530, "Netherlands Antilles"),
    NC(156, "NC", "NCL",  540, "New Caledonia"),
    NZ(157, "NZ", "NZL",  554, "New Zealand"),
    NI(158, "NI", "NIC",  558, "Nicaragua"),
    NE(159, "NE", "NER",  562, "Niger"),
    NG(160, "NG", "NGA",  566, "Nigeria"),
    NU(161, "NU", "NIU",  570, "Niue"),
    NF(162, "NF", "NFK",  574, "Norfolk Island"),
    MP(163, "MP", "MNP",  580, "Northern Mariana Islands"),
    NO(164, "NO", "NOR",  578, "Norway"),
    OM(165, "OM", "OMN",  512, "Oman"),
    PK(166, "PK", "PAK",  586, "Pakistan"),
    PW(167, "PW", "PLW",  585, "Palau"),
    PS(168, "PS", "PSE",  275, "Palestinian Territory"),
    PA(169, "PA", "PAN",  591, "Panama"),
    PG(170, "PG", "PNG",  598, "Papau New Guinea"),
    PY(171, "PY", "PRY",  600, "Paraguay"),
    PE(172, "PE", "PER",  604, "Peru"),
    PH(173, "PH", "PHL",  608, "Philippines"),
    PN(174, "PN", "PCN",  612, "Pitcairn"),
    PL(175, "PL", "POL",  616, "Poland"),
    PT(176, "PT", "PRT",  620, "Portugal"),
    PR(177, "PR", "PRI",  630, "Puerto Rico"),
    QA(178, "QA", "QAT",  634, "Qatar"),
    RE(179, "RE", "REU",  638, "Reunion"),
    RO(180, "RO", "ROM",  642, "Romania"),
    RU(181, "RU", "RUS",  643, "Russia"),
    RW(182, "RW", "RWA",  646, "Rwanda"),
    BL(183, "BL", "BLM",  652, "Saint Barhelemy"),
    SH(184, "SH", "SHN",  654, "Saint Helena"),
    KN(185, "KN", "KNA",  659, "Saint Kitts and Nevis"),
    LC(186, "LC", "LCA",  662, "Saint Lucia"),
    MF(187, "MF", "MAF",  663, "Saint Martin"),
    PM(188, "PM", "SPM",  666, "Saint Pierre and Miquelon"),
    VC(189, "VC", "VCT",  670, "Saint Vincent and the Grenadines"),
    WS(190, "WS", "WSM",  882, "Samoa"),
    SM(191, "SM", "SMR",  674, "San Marino"),
    ST(192, "ST", "STP",  678, "Sao Tome and Principe"),
    SA(193, "SA", "SAU",  682, "Saudi Arabia"),
    SN(194, "SN", "SEN",  686, "Senegal"),
    RS(195, "RS", "RS0",  688, "Serbia"),
    SC(196, "SC", "SYC",  690, "Seychelles"),
    SL(197, "SL", "SLE",  694, "Sierra Leone"),
    SG(198, "SG", "SGP",  702, "Singapore"),
    SK(199, "SK", "SVK",  703, "Slovakia"),
    SI(200, "SI", "SVN",  705, "Slovenia"),
    SB(201, "SB", "SLB",   90, "Solomon Islands"),
    SO(202, "SO", "SOM",  706, "Somalia"),
    ZA(203, "ZA", "ZAF",  710, "South Africa"),
    GS(204, "GS", "SGS",  239, "South Georgia and the South Sandwich Islands"),
    ES(205, "ES", "ESP",  724, "Spain"),
    LK(206, "LK", "LKA",  144, "Sri Lanka"),
    SD(207, "SD", "SDN",  736, "Sudan"),
    SR(208, "SR", "SUR",  740, "Suriname"),
    SJ(209, "SJ", "SJM",  744, "Svalbard and Jan Mayen"),
    SZ(210, "SZ", "SWZ",  748, "Swaziland"),
    SE(211, "SE", "SWE",  752, "Sweden"),
    CH(212, "CH", "CHE",  756, "Switzerland"),
    SY(213, "SY", "SYR",  760, "Syrian Arab Republic"),
    TW(214, "TW", "TWN",  158, "Taiwan, Province of China"),
    TJ(215, "TJ", "TJK",  762, "Tajikistan"),
    TZ(216, "TZ", "TZA",  834, "Tanzania, United Republic of"),
    TH(217, "TH", "THA",  764, "Thailand"),
    TL(218, "TL", "TLS",  626, "Timor-Leste"),
    TG(219, "TG", "TGO",  768, "Togo"),
    TK(220, "TK", "TKL",  772, "Tokelau"),
    TO(221, "TO", "TON",  776, "Tonga"),
    TT(222, "TT", "TTO",  780, "Trinidad and Tobago"),
    TN(223, "TN", "TUN",  788, "Tunisia"),
    TR(224, "TR", "TUR",  792, "Turkey"),
    TM(225, "TM", "TKM",  795, "Turkmenistan"),
    TC(226, "TC", "TCA",  796, "Turks and Caicos Islands"),
    TV(227, "TV", "TUV",  798, "Tuvalu"),
    UG(228, "UG", "UGA",  800, "Uganda"),
    UA(229, "UA", "UKR",  804, "Ukraine"),
    AE(230, "AE", "ARE",  784, "United Arab Emirates"),
    GB(231, "GB", "GBR",  826, "United Kingdom"),
    US(232, "US", "USA",  840, "United States", 1),
    UM(233, "UM", "UMI",  581, "United States Minor Outlying Islands"),
    UY(234, "UY", "URY",  858, "Uruguay"),
    UZ(235, "UZ", "UZB",  860, "Uzbekistan"),
    VU(236, "VU", "VUT",  548, "Vanuatu"),
    VE(237, "VE", "VEN",  862, "Venezuela, Bolivarian Republic of"),
    VN(238, "VN", "VNM",  704, "Viet Nam"),
    VG(239, "VG", "VGB",   92, "Virgin Islands, British"),
    VI(240, "VI", "VIR",  850, "Virgin Islands, U.S."),
    WF(241, "WF", "WLF",  876, "Wallis and Futuna"),
    EH(242, "EH", "ESH",  732, "Western Sahara"),
    YE(243, "YE", "ESH",  887, "Yemen"),
    ZM(244, "ZM", "ZMB",  894, "Zambia"),
    ZW(245, "ZW", "ZWE",  716, "Zimbabwe", 250);
    //@formatter:on

  private Long countryId;
  private String displayName;
  private String isoCharCode2;
  private String isoCharCode3;
  private String isoNumericCode;
  private Long sortOrder;

  /**
   * Contructor
   *
   * @param id internal identifier number.
   * @param isoCharCd2
   * @param isoCharCd3
   * @param isoNumericCd
   * @param displayName
   * @param sortOrder
   */
  Country(int id, String isoCharCd2, String isoCharCd3, int isoNumericCd,
      String displayName, int sortOrder) {
    this(id, isoCharCd2, isoCharCd3, isoNumericCd, displayName);
    this.sortOrder = (long) sortOrder;
  }

  /**
   * Constructor
   *
   * @param id internal identifier number.
   * @param isoCharCd2
   * @param isoCharCd3
   * @param isoNumericCd
   * @param displayName
   */
  Country(int id, String isoCharCd2, String isoCharCd3, int isoNumericCd,
      String displayName) {
    this.countryId = (long) id;
    this.displayName = displayName;
    this.isoCharCode2 = isoCharCd2;
    this.isoCharCode3 = isoCharCd3;
    this.isoNumericCode = Integer.toString(isoNumericCd);
    this.sortOrder = 0L;
  }

  /**
   * @return matching enum, or Null.
   */
  public static Country fromString(String countryString) {

    if (US.isoCharCode2.equalsIgnoreCase(countryString)
        || US.isoCharCode3.equalsIgnoreCase(countryString)
        || US.displayName.equalsIgnoreCase(countryString)) {
      return US;
    }

    for (Country option : values()) {
      if (option.isoCharCode2.equalsIgnoreCase(countryString)
          || option.isoCharCode3.equalsIgnoreCase(countryString)
          || option.displayName.equalsIgnoreCase(countryString)
          ) {
        return option;
      }
    }

    return null;
  }

  /**
   * Used by hibernate to resolve the value to put into tables.
   *
   * @return
   */
  public Long toId() {
    return this.countryId;
  }

  /**
   * Used by hibernate to resolve the proper enum from the database value. Can
   * also be used in code to find the matching enum.
   *
   * @param id database identifier.
   * @return matching enum, or Null.
   */
  public static Country fromId(Long id) {

    for (Country option : values()) {
      if (option.getCountryId().equals(id)) {
        return option;
      }
    }
    return null;
  }

  public static Country fromIsoCharCode3(String isCharCode3In) {
    for (Country option : values()) {
      if (option.getIsoCharCode3().equalsIgnoreCase(isCharCode3In)) {
        return option;
      }
    }
    return null;
  }

  public static Country fromIsoCharCode2(String isoCharCode2) {
    for (Country option : values()) {
      if (isoCharCode2.equals(option.getIsoCharCode2())) {
        return option;
      }
    }
    return null;
  }

  //    @JsonCreator
  public static Country fromDisplayName(String name) {
    for (Country option : values()) {
      if (option.getDisplayName().equals(name)) {
        return option;
      }
    }
    return null;
  }

  public Long getCountryId() {
    return countryId;
  }

  //    @JsonValue
  public String getDisplayName() {
    return displayName;
  }

  public String getIsoCharCode2() {
    return isoCharCode2;
  }

  public String getIsoCharCode3() {
    return isoCharCode3;
  }

  public String getIsoNumericCode() {
    return isoNumericCode;
  }

  public Long getSortOrder() {
    return sortOrder;
  }
}
