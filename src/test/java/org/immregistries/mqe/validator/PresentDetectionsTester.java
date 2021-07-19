package org.immregistries.mqe.validator;

import static org.junit.Assert.assertTrue;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import org.immregistries.mqe.hl7util.SeverityLevel;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.MessageValidator;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.parse.HL7MessageParser;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PresentDetectionsTester {

  private static final Logger logger = LoggerFactory.getLogger(PresentDetectionsTester.class);
  private HL7MessageParser parser = HL7MessageParser.INSTANCE;
  private MessageValidator validator = MessageValidator.INSTANCE;
  private MqeMessageService service = MqeMessageService.INSTANCE;


  private static final String AIRA_TEST_MSG =
      "MSH|^~\\&|||||20160518151704-0400||VXU^V04^VXU_V04|5B6-B.10.05.1fa|P|2.5.1|\r"
          + "PID|||5B6-B.10.05^^^AIRA-TEST^MR||Latimer^Rocco^Janus^^^^L|Monona^Ushma|20120524|M||2106-3^White^HL70005|266 Lynch St^^Caspian^MI^49915^USA^P||^PRN^PH^^^906^3997846~^NET^Internet^bjones7656@isp.com|||||||||2186-5^not Hispanic or Latino^HL70005|\r"
          + "PD1|||||||||||02^Reminder/Recall - any method^HL70215|||||A|20160518|20160518|\r"
          + "NK1|1|Monona^Ushma^^^^^L|FTH^Father^HL70063|266 Lynch St^^Caspian^MI^49915^USA^P|^PRN^PH^^^906^3997846|\r"
          + "ORC|RE||E47Q115.3^AIRA|||||||I-23432^Burden^Donna^A^^^^^NIST-AA-1||57422^RADON^NICHOLAS^^^^^^NIST-AA-1^L|\r"
          + "RXA|0|1|20160518||03^MMR^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||U1747GW|20161001|MSD^Merck and Co^MVX||||A|\r"
          + "RXR|SC^^HL70162|LA^^HL70163|\r"
          + "OBX|1|CE|64994-7^Vaccine funding program eligibility category^LN|1|V05^VFC eligible - Federally Qualified Health Center Patient (under-insured)^HL70064||||||F|||20160518|||VXC40^Eligibility captured at the immunization level^CDCPHINVS|\r"
          + "OBX|2|CE|30963-3^Vaccine funding source^LN|1|PHC70^Private Funds^CDCPHINVS||||||F|||20160518||||\r"
          + "OBX|3|CE|30956-7^Vaccine Type^LN|2|03^MMR^CVX||||||F|\r"
          + "OBX|4|TS|29768-9^Date vaccine information statement published^LN|2|20120420||||||F|\r"
          + "OBX|5|TS|29769-7^Date vaccine information statement presented^LN|2|20160518||||||F|\r";;


  @Test
  public void presentDetectionTest() {
    String message = AIRA_TEST_MSG;
    System.out.println("MESSAGE: ***********************************************");
    String[] lines = message.split("\\r");
    for (String line : lines) {
      System.out.println("         " + line);
    }
    System.out.println("********************************************************");
    MqeMessageReceived mr = parser.extractMessageFromText(message);

    long start = Calendar.getInstance().getTimeInMillis();
    List<ValidationRuleResult> list = validator.validateMessage(mr);
    long finish = Calendar.getInstance().getTimeInMillis();

    System.out.println("IT TOOK: " + (finish - start) + " ms to validate");

    System.out.println("PRESENT: ***********************************************");
    HashSet<String> presentDetectionsSet = new HashSet<String>();
    HashSet<String> missingDetectionsSet = new HashSet<String>();
    for (ValidationRuleResult vrr : list) {
      for (ValidationReport validationReport : vrr.getValidationDetections()) {
        if (validationReport.getDetection().getDetectionType().getWording().equals("is present")) {
          //System.out.println(validationReport.getDetection() );
          presentDetectionsSet.add(validationReport.getDetection().toString());
        } else if (validationReport.getDetection().getDetectionType().getWording()
            .equals("is missing")) {
          missingDetectionsSet.add(validationReport.getDetection().toString());
        }
      }
    }
    assertTrue(presentDetectionsSet.contains("PatientNameFirstIsPresent"));
    assertTrue(presentDetectionsSet.contains("PatientNameMiddleIsPresent"));
    assertTrue(presentDetectionsSet.contains("PatientNameLastIsPresent"));
    assertTrue(presentDetectionsSet.contains("PatientBirthDateIsPresent"));
    assertTrue(presentDetectionsSet.contains("PatientGenderIsPresent"));
    assertTrue(presentDetectionsSet.contains("PatientAddressStreetIsPresent"));
    assertTrue(presentDetectionsSet.contains("PatientAddressCityIsPresent"));
    assertTrue(presentDetectionsSet.contains("PatientAddressStateIsPresent"));
    assertTrue(presentDetectionsSet.contains("PatientAddressZipIsPresent"));
    assertTrue(presentDetectionsSet.contains("PatientAddressIsPresent"));
    assertTrue(presentDetectionsSet.contains("NextOfKinNameFirstIsPresent"));
    assertTrue(presentDetectionsSet.contains("PatientGuardianNameFirstIsPresent"));
    assertTrue(presentDetectionsSet.contains("NextOfKinNameLastIsPresent"));
    assertTrue(presentDetectionsSet.contains("PatientGuardianNameLastIsPresent"));
    assertTrue(presentDetectionsSet.contains("PatientMotherSMaidenNameIsPresent"));
    assertTrue(presentDetectionsSet.contains("PatientRaceIsPresent"));
    assertTrue(presentDetectionsSet.contains("PatientEthnicityIsPresent"));
    assertTrue(presentDetectionsSet.contains("PatientPhoneIsPresent"));
    assertTrue(presentDetectionsSet.contains("PatientEmailIsPresent"));
    assertTrue(presentDetectionsSet.contains("VaccinationAdminCodeIsPresent"));
    assertTrue(presentDetectionsSet.contains("VaccinationAdminDateIsPresent"));
    assertTrue(presentDetectionsSet.contains("VaccinationInformationSourceIsPresent"));
    assertTrue(presentDetectionsSet.contains("VaccinationLotNumberIsPresent"));
    assertTrue(!missingDetectionsSet.contains("VaccinationLotExpirationDateIsMissing"));
    assertTrue(presentDetectionsSet.contains("VaccinationLotExpirationDateIsPresent"));
    assertTrue(presentDetectionsSet.contains("VaccinationFinancialEligibilityCodeIsPresent"));
    assertTrue(!missingDetectionsSet.contains("VaccinationFundingSourceCodeIsMissing"));
    assertTrue(presentDetectionsSet.contains("VaccinationFundingSourceCodeIsPresent"));
  }


  private void reportErrorResults(List<ValidationRuleResult> list) {
    reportResults(list, SeverityLevel.ERROR);
  }

  private void reportAcceptResults(List<ValidationRuleResult> list) {
    reportResults(list, SeverityLevel.ACCEPT);
  }

  private void reportWarnResults(List<ValidationRuleResult> list) {
    reportResults(list, SeverityLevel.WARN);
  }

  private void reportResults(List<ValidationRuleResult> list, SeverityLevel a) {
    for (ValidationRuleResult vrr : list) {
      for (ValidationReport i : vrr.getValidationDetections()) {
        if (a == i.getSeverity()) {
          String s = "  - ";
          if (i.getHl7LocationList() != null && i.getHl7LocationList().size() > 0) {
            s += i.getHl7LocationList().get(0);
          }
          s += "                   ";
          if (s.length() > 10) {
            s = s.substring(0, 18);
          }
          System.out.println(s + ": " + i.getDetection() + "[" + i.getValueReceived() + "]");
        }
      }
    }
  }

}
