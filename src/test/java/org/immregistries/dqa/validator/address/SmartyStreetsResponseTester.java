package org.immregistries.dqa.validator.address;

import static org.junit.Assert.assertEquals;

import java.util.List;
import org.junit.Test;

public class SmartyStreetsResponseTester {

  @Test
  public void testResponseCodesCRAZYDPV() {
    String ssExample = "A#L#P#";
    String dpvExample = "AAM3";
    List<SmartyStreetResponse> rList = SmartyStreetResponse.codesFromDpv(ssExample);
    assertEquals("Should have zero codes", 0, rList.size());

    rList = SmartyStreetResponse.codesFromDpv(dpvExample+ssExample);
    assertEquals("Should have zero codes", 2, rList.size());

    rList = SmartyStreetResponse.codesFromDpv(dpvExample+"-"+ssExample);
    assertEquals("Should have zero codes", 2, rList.size());

    rList = SmartyStreetResponse.codesFromDpv(ssExample+"-"+dpvExample);
    assertEquals("Should have zero codes", 2, rList.size());
  }

  @Test
  public void testResponseCodesCRAZYSS() {
    String ssExample = "A#L#P#";
    String dpvExample = "AAM3";
    List<SmartyStreetResponse> rList = SmartyStreetResponse.codesFromSS(dpvExample);
    assertEquals("Should have zero codes", 0, rList.size());

    rList = SmartyStreetResponse.codesFromSS(dpvExample+ssExample);
    assertEquals("Should have zero codes", 3, rList.size());

    rList = SmartyStreetResponse.codesFromSS(dpvExample+"-"+ssExample);
    assertEquals("Should have zero codes", 3, rList.size());

    rList = SmartyStreetResponse.codesFromSS(ssExample+"-"+dpvExample);
    assertEquals("Should have zero codes", 3, rList.size());
  }
  @Test
  public void testResponseCodes() {
    String ssExample = "A#L#P#";
    String dpvExample = "AAM3";

    List<SmartyStreetResponse> rList = SmartyStreetResponse.codesFromDpv(dpvExample);
    assertEquals("Should have two codes", 2, rList.size());

    rList = SmartyStreetResponse.codesFromDpv(ssExample);
    assertEquals("Should have zero codes", 0, rList.size());

    rList = SmartyStreetResponse.codesFromSS(ssExample);
    assertEquals("Should have three codes", 3, rList.size());

    String combined = SmartyStreetResponse.combineCodes("", dpvExample);
    rList = SmartyStreetResponse.codesFromCombined(combined);
    assertEquals("Should have two codes", 2, rList.size());

    combined = SmartyStreetResponse.combineCodes(ssExample, "");
    rList = SmartyStreetResponse.codesFromCombined(combined);
    assertEquals("Should have three codes", 3, rList.size());

    combined = SmartyStreetResponse.combineCodes(ssExample, dpvExample);
    rList = SmartyStreetResponse.codesFromCombined(combined);
    assertEquals("Should have five codes", 5, rList.size());

    /* just for fun */
    for (SmartyStreetResponse r : rList) {
      System.out.println(r.title);
    }
  }
}
