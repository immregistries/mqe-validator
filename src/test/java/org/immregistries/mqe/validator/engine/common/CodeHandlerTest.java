package org.immregistries.mqe.validator.engine.common;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.immregistries.codebase.client.generated.Code;
import org.immregistries.codebase.client.generated.UseDate;
import org.immregistries.codebase.client.reference.CodesetType;
import org.immregistries.mqe.core.util.DateUtility;
import org.immregistries.mqe.hl7util.model.MetaFieldInfo;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.codes.CodeRepository;
import org.immregistries.mqe.vxu.DetectionInfo;
import org.immregistries.mqe.vxu.MetaFieldInfoData;
import org.immregistries.mqe.vxu.VxuField;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;


public class CodeHandlerTest {

  private CodeRepository repo = CodeRepository.INSTANCE;
  private Code codeAnthrax;
  private Code codeAdenovirus;
  private CodeHandler codr = CodeHandler.INSTANCE;
  private DateUtility datr = DateUtility.INSTANCE;

  @Before
  public void buildNewCode() {
    this.codeAnthrax = repo.getCodeFromValue("24", CodesetType.VACCINATION_CVX_CODE);
    this.codeAdenovirus = repo.getCodeFromValue("82", CodesetType.VACCINATION_CVX_CODE);
  }

  MetaFieldInfoData meta = new MetaFieldInfoData() {
    @Override
    public MetaFieldInfo getMetaFieldInfo(VxuField vxuField) {
      MetaFieldInfo mfi = new MetaFieldInfo();
      mfi.setVxuField(vxuField);
      return null;
    }

    @Override
    public List<DetectionInfo> getDetectionList() {
      return new ArrayList<>();
    }
  };

  @Test
  public void testUseDates() {
    UseDate ud = this.codeAnthrax.getUseDate();
    String beforeDateString = ud.getNotBefore();
    DateTime dt = datr.parseDateTime(beforeDateString);
    DateTime valid = dt.plusDays(30);
    String adminDate = datr.toString(valid);
    List<ValidationReport> list = codr
        .handleUseDate(codeAnthrax, adminDate, VxuField.VACCINATION_ADMIN_DATE, meta);
    assertEquals(
        "Shouldn't have an issue with a current date, even though it doesn't have an end date", 0,
        list.size());

    adminDate = "19650101";
    list = codr.handleUseDate(codeAnthrax, adminDate, VxuField.VACCINATION_ADMIN_DATE, meta);
    assertEquals("Should have an issue with adminDate of " + adminDate, 1, list.size());
    assertEquals(Detection.VaccinationAdminDateIsBeforeOrAfterLicensedVaccineRange,
        list.get(0).getDetection());
  }

  @Test
  public void testAgeDates() {
    //adenovirus has "not before month 1 and not after month 1440"
    Date birthDate = datr.parseDate("20160101");
    Date adminDate = datr.parseDate("20160115");
    //This should violate the age date, because its before month 1.
    List<ValidationReport> list = codr
        .handleAgeDate(this.codeAdenovirus, birthDate, adminDate, VxuField.VACCINATION_ADMIN_DATE,
            meta);
    assertEquals("Should have an issue", 1, list.size());

    //This should violate the age date because its after month 1440
    adminDate = datr.parseDate("21500115");
    list = codr
        .handleAgeDate(this.codeAdenovirus, birthDate, adminDate, VxuField.VACCINATION_ADMIN_DATE,
            meta);
    assertEquals("Should have an issue", 1, list.size());

    //This should NOT violate the age date because its null
    adminDate = null;
    list = codr
        .handleAgeDate(this.codeAdenovirus, birthDate, adminDate, VxuField.VACCINATION_ADMIN_DATE,
            meta);
    assertEquals("Should not have an issue because its null", 0, list.size());

    //This should NOT violate the age date because its within the range
    adminDate = datr.parseDate("20160515");
    list = codr
        .handleAgeDate(this.codeAdenovirus, birthDate, adminDate, VxuField.VACCINATION_ADMIN_DATE,
            meta);
    assertEquals("Should not have an issue because its within the range", 0, list.size());
  }

  @Test
  public void testAgeDatesNull() {
    //adenovirus has "not before month 1 and not after month 1440"
    Date birthDate = datr.parseDate("20160101");
    Date adminDate = datr.parseDate("20160115");

    List<ValidationReport> list = codr
        .handleAgeDate(this.codeAdenovirus, birthDate, null, VxuField.VACCINATION_ADMIN_DATE, meta);
    assertEquals("Should not have an issue because admin date is null", 0, list.size());

    //This should NOT violate the age date because birth date is null
    list = codr
        .handleAgeDate(this.codeAdenovirus, null, adminDate, VxuField.VACCINATION_ADMIN_DATE, meta);
    assertEquals("Should not have an issue because reference date is null", 0, list.size());

    //This should NOT violate the age date because both are null
    list = codr
        .handleAgeDate(this.codeAdenovirus, null, null, VxuField.VACCINATION_ADMIN_DATE, meta);
    assertEquals("Should not have an issue because both dates are null", 0, list.size());

    //This should NOT violate the age date because the code is null
    list = codr.handleAgeDate(null, null, null, VxuField.VACCINATION_ADMIN_DATE, meta);
    assertEquals("Should not have an issue because all is null", 0, list.size());

    //This should NOT violate the age date because the code is null
    list = codr.handleAgeDate(null, birthDate, adminDate, VxuField.VACCINATION_ADMIN_DATE, meta);
    assertEquals("Should not have an issue because the code is null", 0, list.size());

  }
}
