package org.immregistries.dqa.validator.report;

import java.util.List;
import org.immregistries.dqa.vxu.VxuField;
import org.immregistries.dqa.vxu.VxuObject;
import org.junit.Before;
import org.junit.Test;

public class ReportDefinitionTest {

  ReportDefinition tester;

  @Before
  public void setup() {
    ReportDefinition rd = new ReportDefinition();

    List<ReportCompletenessSectionDefinition> sections = rd.getQualitySections();

    ReportCompletenessSectionDefinition s = new ReportCompletenessSectionDefinition();
    s.setLabel("Patient");
    s.setSectionObject(VxuObject.PATIENT);
    sections.add(s);

    List<DqaReportFieldDefinition> scores = s.getReportFields();
    DqaReportFieldDefinition sd = new DqaReportFieldDefinition();
    sd.setField(VxuField.PATIENT_NAME_FIRST);
    sd.setWeight(5);
    scores.add(sd);

    sd = new DqaReportFieldDefinition();
    sd.setField(VxuField.PATIENT_NAME_LAST);
    sd.setWeight(5);
    scores.add(sd);

    sd = new DqaReportFieldDefinition();
    sd.setField(VxuField.PATIENT_BIRTH_DATE);
    sd.setWeight(10);
    scores.add(sd);

    //so at this point, we have one section, one measure, which
    //matches the XML File.
    tester = rd;
  }

  @Test
  public void testGenerateReport() {
    //So now, I have something...  that defines what I want to measure.
    //and I want to take that, and make a report that measures message metrics.
    //Message metrics are going to be:
    // List<MessageAttribute>
    // List<CodeReceived>

    //For now, let's focus on MessageAttributes...  that's what says "First name is missing!" and stuff.
    /*		So I need to take the reporter...
     * 		send in a set of measures... and this definition.
     * 		get out of it, a report.
     *
     * 		a set of measures =
     * 				message count
     * 				Map<IssueObject, Integer> counts of the parts we're measuring...
     * 				Map<MessageAttribute, Integer> counts of issues
     * 				Map<CodeReceived, Integer> counts of codes
     *
     * 				to start with, we need the count of parts, and the count of issues.
     *
     */

  }

}
