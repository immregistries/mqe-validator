package org.immregistries.dqa.validator.transform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import org.immregistries.dqa.validator.TestMessageGenerator;
import org.immregistries.dqa.validator.ValidatorProperties;
import org.immregistries.dqa.vxu.DqaAddress;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaNextOfKin;
import org.immregistries.dqa.vxu.DqaVaccination;
import org.immregistries.dqa.vxu.PatientImmunity;
import org.immregistries.dqa.vxu.hl7.Observation;
import org.immregistries.dqa.vxu.parse.HL7MessageParser;
import org.junit.Test;

public class MessageTransformerTest {

  private TestMessageGenerator genr = new TestMessageGenerator();
  private HL7MessageParser toObj = HL7MessageParser.INSTANCE;
  private MessageTransformer mt = MessageTransformer.INSTANCE;
  private ValidatorProperties props = ValidatorProperties.INSTANCE;

  @Test
  public void testAddressCleansing() {
    if (props.isAddressCleanserEnabled()) {
      DqaMessageReceived mr = toObj.extractMessageFromText(genr.getImmunityMessage());
      DqaAddress a = mr.getPatient().getPatientAddress();
      mt.transformAddesses(mr);
      DqaAddress b = mr.getPatient().getPatientAddress();
      assertEquals("should be the same, it's a bogus address", a, b);
      assertFalse("should say not clean, it's a bogus address", b.isClean());
      assertFalse("first address should not have cleansing attempted.", a.isCleansingAttempted());
      assertTrue("Second address should have cleansing attempted.  not the first",
          b.isCleansingAttempted());
      for (DqaNextOfKin nk : mr.getNextOfKins()) {
        DqaAddress na = nk.getAddress();
        assertTrue("should have attempted cleansing", na.isCleansingAttempted());
      }
    }
  }

  @Test
  public final void testObxList() {

    DqaMessageReceived mr = toObj.extractMessageFromText(genr.getImmunityMessage());
    mt.transform(mr);

    List<PatientImmunity> immunities = mr.getPatient().getPatientImmunityList();
    assertNotNull(immunities);
    assertEquals("Should have two immunity", 1, immunities.size());
    assertEquals("should be an immunity of type 23511006", "23511006",
        immunities.get(0).getImmunityCode());

    List<DqaVaccination> vaccList = mr.getVaccinations();
    assertNotNull(vaccList);
    //it should have one vaccine for the 998 vacc.
    //and then one for the 83 vaccination.
    assertEquals("should have two vaccines", 2, vaccList.size());

    DqaVaccination immVacc = vaccList.get(0);
    DqaVaccination hepaVacc = vaccList.get(1);

    assertEquals("First one should be 998", "998", immVacc.getAdminCvxCode());
    assertEquals("Second one should be 83", "83", hepaVacc.getAdminCvxCode());

    List<Observation> immObsList = immVacc.getObservations();
    List<Observation> hepaObsList = immVacc.getObservations();

    assertNotNull(immObsList);
    assertEquals("imm obs list should have one entry", 1, immObsList.size());

    int x = 1;

//		for (Observation o : immObsList) {
//			System.out.println("IMMUNITY OBSERVATION: " + x++);
//			System.out.println(o.getIdentifierCode());
//			System.out.println(o.getObservationDateString());
//			System.out.println(o.getObservationMethodCode());
//			System.out.println(o.getValue());
//			System.out.println(o.getValueTypeCode());
//			System.out.println(o.getSubId());
//		}

    assertNotNull(hepaObsList);
    assertEquals("imm obs list should have one entry", 1, hepaObsList.size());

    x = 1;

//		for (Observation o : hepaObsList) {
//			System.out.println("IMMUNITY OBSERVATION: " + x++);
//			System.out.println(o.getIdentifierCode());
//			System.out.println(o.getObservationDateString());
//			System.out.println(o.getObservationMethodCode());
//			System.out.println(o.getValue());
//			System.out.println(o.getValueTypeCode());
//			System.out.println(o.getSubId());
//		}

    assertEquals("imm obs entry should have identifier of ", "59784-9",
        immObsList.get(0).getIdentifierCode());

  }


}
