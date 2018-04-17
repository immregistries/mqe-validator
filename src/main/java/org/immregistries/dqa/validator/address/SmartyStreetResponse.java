package org.immregistries.dqa.validator.address;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SmartyStreetResponse {
  /*SS Footnotes https://smartystreets.com/docs/cloud/us-street-api#footnotes */
  A ("SS", "A#", "CORRECTED ZIP CODE", "THE ADDRESS WAS FOUND TO HAVE A DIFFERENT 5-DIGIT ZIP CODE THAN GIVEN IN THE SUBMITTED LIST. THE CORRECT ZIP CODE IS SHOWN IN THE ZIP CODE FIELD.", "(E.G., 4800 FAIRMOUNT AVE KANSAS CITY MO 64111)"),
  B ("SS", "B#", "FIXED CITY/STATE SPELLING", "THE SPELLING OF THE CITY NAME AND/OR STATE ABBREVIATION IN THE SUBMITTED ADDRESS WAS FOUND TO BE DIFFERENT THAN THE STANDARD SPELLING. THE STANDARD SPELLING OF THE CITY NAME AND STATE ABBREVIATION IS SHOWN IN THE CITY AND STATE FIELDS.", "(E.G., 34 MAIN ST ROEBLONG NJ 08554)"),
  C ("SS", "C#", "INVALID CITY/STATE/ZIP", "THE ZIP CODE IN THE SUBMITTED ADDRESS COULD NOT BE FOUND BECAUSE NEITHER A VALID CITY AND STATE, NOR VALID 5-DIGIT ZIP CODE WAS PRESENT. SMARTYSTREETS RECOMMENDS THAT THE CUSTOMER CHECK THE ACCURACY OF THE SUBMITTED ADDRESS.", "(E.G., 200 CAMP DRIVE 25816)"),
  D ("SS", "D#", "NO ZIP+4 ASSIGNED", "THIS IS A RECORD LISTED BY THE UNITED STATES POSTAL SERVICE AS A NON-DELIVERABLE LOCATION. SMARTYSTREETS RECOMMENDS THAT THE CUSTOMER CHECK THE ACCURACY OF THE SUBMITTED ADDRESS.", "(E.G., 39 MAIN STREET ROEBLONG NJ 08554)"),
  E ("SS", "E#", "SAME ZIP FOR MULTIPLE", "MULTIPLE RECORDS WERE RETURNED, BUT EACH SHARES THE SAME 5-DIGIT ZIP CODE.", "(E.G., 1 ROSEDALE BALTIMORE MD)"),
  F ("SS", "F#", "ADDRESS NOT FOUND", "THE ADDRESS, EXACTLY AS SUBMITTED, COULD NOT BE FOUND IN THE CITY, STATE, OR ZIP CODE PROVIDED. MANY FACTORS CONTRIBUTE TO THIS; EITHER THE PRIMARY NUMBER IS MISSING, THE STREET IS MISSING, OR THE STREET IS TOO HORRIBLY MISSPELLED TO UNDERSTAND.", "(E.G., 2600 RAFE LANE JACKSON MS 39201)"),
  G ("SS", "G#", "USED FIRM DATA", "INFORMATION IN THE FIRM LINE WAS DETERMINED TO BE A PART OF THE ADDRESS. IT WAS MOVED OUT OF THE FIRM LINE AND INCORPORATED INTO THE ADDRESS LINE.", "(E.G., 97 TAYLOR ST APT 3F TAYLOR MANCHESTER NH)"),
  H ("SS", "H#", "MISSING SECONDARY NUMBER", "ZIP+4 INFORMATION INDICATES THAT THIS ADDRESS IS A BUILDING. THE ADDRESS AS SUBMITTED DOES NOT CONTAIN A SECONDARY (APARTMENT, SUITE, ETC.) NUMBER. SMARTYSTREETS RECOMMENDS THAT THE CUSTOMER CHECK THE ACCURACY OF THE SUBMITTED ADDRESS AND ADD THE MISSING SECONDARY NUMBER TO ENSURE THE CORRECT DELIVERY POINT BARCODE (DPBC).", "(E.G., 109 WIMBLEDON SQ CHESAPEAKE VA 23320)"),
  I ("SS", "I#", "INSUFFICIENT/ INCORRECT ADDRESS DATA", "MORE THAN ONE ZIP+4 CODE WAS FOUND TO SATISFY THE ADDRESS AS SUBMITTED. THE SUBMITTED ADDRESS DID NOT CONTAIN SUFFICIENTLY COMPLETE OR CORRECT DATA TO DETERMINE A SINGLE ZIP+4 CODE. SMARTYSTREETS RECOMMENDS THAT THE CUSTOMER CHECK THE ACCURACY AND COMPLETENESS OF THE SUBMITTED ADDRESS. FOR EXAMPLE, A STREET MAY HAVE A SIMILAR ADDRESS AT BOTH THE NORTH AND SOUTH ENDS OF THE STREET.", "(E.G., 1 ROSEDALE BALTIMORE MD 21229)"),
  J ("SS", "J#", "DUAL ADDRESS", "THE INPUT CONTAINED TWO ADDRESSES. FOR EXAMPLE: 123 MAIN ST PO BOX 99.", "(E.G., PO BOX 38606 30TH STREET TRAIN STATION PHILADELPHIA PA 19104)"),
  K ("SS", "K#", "CARDINAL RULE MATCH", "ALTHOUGH THE ADDRESS AS SUBMITTED IS NOT VALID, WE WERE ABLE TO FIND A MATCH BY CHANGING THE CARDINAL DIRECTION (NORTH, SOUTH, EAST, WEST). THE CARDINAL DIRECTION WE USED TO FIND A MATCH IS FOUND IN THE COMPONENTS.", "(E.G., 315 W CESAR CHAVEZ ST AUSTIN TX)"),
  L ("SS", "L#", "CHANGED ADDRESS COMPONENT", "AN ADDRESS COMPONENT (I.E., DIRECTIONAL OR SUFFIX ONLY) WAS ADDED, CHANGED, OR DELETED IN ORDER TO ACHIEVE A MATCH.", "(E.G., 173 BROADWAY SALT LAKE UT 84101)"),
  LL ("SS", "LL#", "FLAGGED ADDRESS FOR LACSLINK", "THE INPUT ADDRESS MATCHED A RECORD THAT WAS LACS-INDICATED, THAT WAS SUBMITTED TO LACSLINK FOR PROCESSING. THIS DOES NOT MEAN THAT THE ADDRESS WAS CONVERTED; IT ONLY MEANS THAT THE ADDRESS WAS SUBMITTED TO LACSLINK BECAUSE THE INPUT ADDRESS HAD THE LACS INDICATOR SET.", ""),
  LI ("SS", "LI#", "FLAGGED ADDRESS FOR LACSLINK", "THE INPUT ADDRESS MATCHED A RECORD THAT WAS LACS-INDICATED, THAT WAS SUBMITTED TO LACSLINK FOR PROCESSING. THIS DOES NOT MEAN THAT THE ADDRESS WAS CONVERTED; IT ONLY MEANS THAT THE ADDRESS WAS SUBMITTED TO LACSLINK BECAUSE THE INPUT ADDRESS HAD THE LACS INDICATOR SET.", ""),
  M ("SS", "M#", "FIXED STREET SPELLING", "THE SPELLING OF THE STREET NAME WAS CHANGED IN ORDER TO ACHIEVE A MATCH.", "(E.G., 3308 FOUNTAINVIUW MONSEY NY)"),
  N ("SS", "N#", "FIXED ABBREVIATIONS", "THE DELIVERY ADDRESS WAS STANDARDIZED. FOR EXAMPLE, IF STREET WAS IN THE DELIVERY ADDRESS, SMARTYSTREETS WILL RETURN ST AS ITS STANDARD SPELLING.", "(E.G., 2438 BROWN AVENUE KNOXVILLE TN 37917)"),
  O ("SS", "O#", "MULTIPLE ZIP+4; LOWEST USED", "MORE THAN ONE ZIP+4 CODE WAS FOUND TO SATISFY THE ADDRESS AS SUBMITTED. THE LOWEST ZIP+4 ADD-ON MAY BE USED TO BREAK THE TIE BETWEEN THE RECORDS.", "(E.G., RR 2 BOX 132 WOLF SUMMIT WV 26426)"),
  P ("SS", "P#", "BETTER ADDRESS EXISTS", "THE DELIVERY ADDRESS IS MATCHABLE, BUT IT IS KNOWN BY ANOTHER (PREFERRED) NAME. FOR EXAMPLE, IN NEW YORK, NY, AVENUE OF THE AMERICAS IS ALSO KNOWN AS 6TH AVE. AN INQUIRY USING A DELIVERY ADDRESS OF 39 6TH AVENUE WOULD BE FLAGGED WITH FOOTNOTE P.", "(E.G., 131 STONE FARM LEBANON NH 03766)"),
  Q ("SS", "Q#", "UNIQUE ZIP MATCH", "MATCH TO AN ADDRESS WITH A UNIQUE ZIP CODE", "(E.G., 645 SWICK HILL STREET CHARLOTTE NC 28263)"),
  R ("SS", "R#", "NO MATCH; EWS: MATCH SOON", "THE DELIVERY ADDRESS IS NOT YET MATCHABLE, BUT THE EARLY WARNING SYSTEM FILE INDICATES THAT AN EXACT MATCH WILL BE AVAILABLE SOON.", "(E.G., 1644 CR 1800E PMB 17420 ARTHUR IL 61911)"),
  S ("SS", "S#", "BAD SECONDARY ADDRESS", "THE SECONDARY INFORMATION (APARTMENT, SUITE, ETC.) DOES NOT MATCH THAT ON THE NATIONAL ZIP+4 FILE. THE SECONDARY INFORMATION, ALTHOUGH PRESENT ON THE INPUT ADDRESS, WAS NOT VALID IN THE RANGE FOUND ON THE NATIONAL ZIP+4 FILE.", "(E.G., 1409 HUEYTOWN RD APT 1781 BESSEMER AL 35023)"),
  T ("SS", "T#", "MULTIPLE RESPONSE DUE TO MAGNET STREET SYNDROME", "THE SEARCH RESULTED IN A SINGLE RESPONSE; HOWEVER, THE RECORD MATCHED WAS FLAGGED AS HAVING MAGNET STREET SYNDROME, AND THE INPUT STREET NAME COMPONENTS (PRE-DIRECTIONAL, PRIMARY STREET NAME, POST-DIRECTIONAL, AND SUFFIX) DID NOT EXACTLY MATCH THOSE OF THE RECORD. A 'MAGNET STREET' IS ONE HAVING A PRIMARY STREET NAME THAT IS ALSO A SUFFIX OR DIRECTIONAL WORD, HAVING EITHER A POST-DIRECTIONAL OR A SUFFIX (I.E., 2220 PARK MEMPHIS TN LOGICALLY MATCHES TO A ZIP+4 RECORD 2200-2258 PARK AVE MEMPHIS TN 38114-6610), BUT THE INPUT ADDRESS LACKS THE SUFFIX 'AVE' WHICH IS PRESENT ON THE ZIP+4 RECORD. THE PRIMARY STREET NAME 'PARK' IS A SUFFIX WORD. THE RECORD HAS EITHER A SUFFIX OR A POST-DIRECTIONAL PRESENT. THEREFORE, IN ACCORDANCE WITH CASS REQUIREMENTS, A ZIP+4 CODE MUST NOT BE RETURNED. THE MULTIPLE RESPONSE RETURN CODE IS GIVEN SINCE A 'NO MATCH' WOULD PREVENT THE BEST CANDIDATE.", "(E.G., 84 GREEN ST NORTHAMPTON MA)"),
  U ("SS", "U#", "UNOFFICIAL POST OFFICE NAME", "THE CITY OR POST OFFICE NAME IN THE SUBMITTED ADDRESS IS NOT RECOGNIZED BY THE UNITED STATES POSTAL SERVICE AS AN OFFICIAL LAST LINE NAME (PREFERRED CITY NAME), AND IS NOT ACCEPTABLE AS AN ALTERNATE NAME. THE PREFERRED CITY NAME IS INCLUDED IN THE CITY FIELD.", "(E.G., 9894 BISSONNET ST #723 SHARPSTOWN TX 77036)"),
  V ("SS", "V#", "UNVERIFIABLE CITY / STATE", "THE CITY AND STATE IN THE SUBMITTED ADDRESS COULD NOT BE VERIFIED AS CORRESPONDING TO THE GIVEN 5-DIGIT ZIP CODE. THIS COMMENT DOES NOT NECESSARILY DENOTE AN ERROR; HOWEVER, SMARTYSTREETS RECOMMENDS THAT THE CUSTOMER CHECK THE ACCURACY OF THE CITY AND STATE IN THE SUBMITTED ADDRESS.", "(E.G., 107 KERWOOD ST KILDEER IL 60067)"),
  W ("SS", "W#", "INVALID DELIVERY ADDRESS", "THE INPUT ADDRESS RECORD CONTAINS A DELIVERY ADDRESS OTHER THAN A PO BOX, GENERAL DELIVERY, OR POSTMASTER 5-DIGIT ZIP CODE THAT IS IDENTIFIED AS A 'SMALL TOWN DEFAULT.' THE USPS DOES NOT PROVIDE STREET DELIVERY SERVICE FOR THIS ZIP CODE. THE USPS REQUIRES THE USE OF A PO BOX, GENERAL DELIVERY, OR POSTMASTER FOR DELIVERY WITHIN THIS ZIP CODE.", ""),
  X ("SS", "X#", "UNIQUE ZIP CODE", "DEFAULT MATCH INSIDE A UNIQUE ZIP CODE", "(E.G., 609 PHEASANT RIDGE ROAD WAYNE PA 19088)"),
  Y ("SS", "Y#", "MILITARY MATCH", "MATCH MADE TO A RECORD WITH A MILITARY OR DIPLOMATIC ZIP CODE.", "(E.G., PSC 10 BOX 1324 APO AE 09142)"),
  Z ("SS", "Z#", "MATCHED WITH ZIPMOVE", "THE ZIPMOVE PRODUCT SHOWS WHICH ZIP+4 RECORDS HAVE MOVED FROM ONE ZIP CODE TO ANOTHER. IF AN INPUT ADDRESS MATCHES A ZIP+4 RECORD WHICH THE ZIPMOVE PRODUCT INDICATES HAS MOVED, THE SEARCH IS PERFORMED AGAIN IN THE NEW ZIP CODE.", "(E.G., 100 BRANNEN ST PULASKI GA 30451)"),

  /*DPV Footnotes https://smartystreets.com/docs/cloud/us-street-api#dpvfootnotes*/
  AA ("DPV", "AA", "City/state/ZIP + street are all valid.", "", "(e.g., 2335 S State St Ste 300 Provo UT)"),
  A1 ("DPV", "A1", "ZIP+4 not matched; address is invalid. (City/state/ZIP + street don't match.)", "", "(e.g., 3214 N University Ave New York NY)"),
  BB ("DPV", "BB", "ZIP+4 matched; confirmed entire address; address is valid.", "", "(e.g., 2335 S State St Ste 300 Provo UT)"),
  CC ("DPV", "CC", "Confirmed address by dropping secondary (apartment, suite, etc.) information.", "", "(e.g., 2335 S State St Ste 500 Provo UT)"),
  F1 ("DPV", "F1", "Matched to military or diplomatic address.", "", "(e.g., Unit 2050 Box 4190 APO AP 96278)"),
  G1 ("DPV", "G1", "Matched to general delivery address.", "", "(e.g., General Delivery Provo UT 84601)"),
  M1 ("DPV", "M1", "Primary number (e.g., house number) is missing.", "", "(e.g., N University Ave Provo UT)"),
  M3 ("DPV", "M3", "Primary number (e.g., house number) is invalid.", "", "(e.g., 16 N University Ave Provo UT)"),
  N1 ("DPV", "N1", "Confirmed with missing secondary information; address is valid but it also needs a secondary number (apartment, suite, etc.).", "", "(e.g., 2335 S State St Provo UT)"),
  PB ("DPV", "PB", "Confirmed as a PO BOX street style address.", "", "(e.g., 555 S B B King Blvd Unit 1 Memphis TN 38103)"),
  P1 ("DPV", "P1", "PO, RR, or HC box number is missing.", "", "(e.g., RR 7 Broken Arrow OK)"),
  P3 ("DPV", "P3", "PO, RR, or HC box number is invalid.", "", "(e.g., HC 2 Box 4155 Luquillo PR 40615)"),
  RR ("DPV", "RR", "Confirmed address with private mailbox (PMB) info.", "", "(e.g., 3214 N University Ave #409 Provo UT)"),
  R1 ("DPV", "R1", "Confirmed address without private mailbox (PMB) info.", "", "(e.g., 3214 N University Ave Provo UT)"),
  R7 ("DPV", "R7", "Confirmed as a valid address that doesn't currently receive US Postal Service street delivery.", "", "(e.g., 6D Cruz Bay St John VI 00830)"),
  U1 ("DPV", "U1", "Matched a unique ZIP Code.", "", "(e.g., 100 North Happy Street 12345)"),
  ;
  public final String type;
  public final String code;
  public final String title;
  public final String desc;
  public final String example;

  private static final Map<String, SmartyStreetResponse> map = new HashMap<>();

  static {
    for (SmartyStreetResponse f : SmartyStreetResponse.values()) {
      map.put(f.code, f);
    }
  }

  SmartyStreetResponse(String type, String code, String title, String desc, String example) {
    this.type = type;
    this.code = code;
    this.title = title;
    this.desc = desc;
    this.example = example;
  }

  public static List<SmartyStreetResponse> codesFromSS(String responseCode) {
    List<SmartyStreetResponse> list = new ArrayList<>();
    if (responseCode == null) {
      return list;
    }

    //the code string may  have either type...  They're all two digit codes except LL# and LI#
    //so...  for the most part we can just take it two chars at a time.
    String[] parts = responseCode.split("#");
    for (String part : parts) {
      if (part != null && part.length() > 1) {
        if (part.endsWith("LI") || part.endsWith("LL")){
          part = part.substring(part.length() - 2);
        } else {
          part = part.substring(part.length() - 1);
        }
      }
      if (part != null && part.length() > 0) {
        SmartyStreetResponse f = map.get(part + "#");
        if (f!=null) list.add(f);
      }
    }
    return list;
  }

  private static Pattern p = Pattern.compile("\\w\\w");

  /**
   * This assumes the two code types are separated by a dash.
   * @param combined
   * @return
   */
  public static List<SmartyStreetResponse> codesFromCombined(String combined) {
    if (combined == null) {
      return new ArrayList<>();
    }

    List<SmartyStreetResponse> list = codesFromSS(combined);
    list.addAll(codesFromDpv(combined));

//    String[] separatedCodes = combined.split("-");
//    if (separatedCodes.length == 2) {
//      List<SmartyStreetResponse> list = codesFromSS(separatedCodes[0]);
//      list.addAll(codesFromDpv(separatedCodes[1]));
//      return list;
//    } else if (separatedCodes.length == 1) {
//      return codesFromSS(separatedCodes[0]);
//    }

    return list;
  }

  /**
   * This ensures the separator is used how we expect.
   * @param ssFootnotes
   * @param dpvFootnotes
   * @return
   */
  public static String combineCodes(String ssFootnotes, String dpvFootnotes) {
    return ssFootnotes + "-" + dpvFootnotes;
  }

  public static List<SmartyStreetResponse> codesFromDpv(String responseCode) {
    List<SmartyStreetResponse> list = new ArrayList<>();
    if (responseCode == null) {
      return list;
    }

    Matcher matcher = p.matcher(responseCode);
    while (matcher.find()) {
      SmartyStreetResponse f = map.get(matcher.group(0));
      if (f!=null) list.add(f);
    }
    return list;
  }
}
