package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.immregistries.lonestar.ForecastManagerSingleton;
import org.immregistries.lonestar.core.ImmunizationForecastDataBean;
import org.immregistries.lonestar.core.ImmunizationInterface;
import org.immregistries.lonestar.core.Trace;
import org.immregistries.lonestar.core.VaccinationDoseDataBean;
import org.immregistries.lonestar.core.api.impl.CvxCode;
import org.immregistries.lonestar.core.api.impl.CvxCodes;
import org.immregistries.lonestar.core.api.impl.ForecastHandlerCore;
import org.immregistries.lonestar.core.api.impl.ForecastOptions;
import org.immregistries.lonestar.core.model.Immunization;
import org.immregistries.lonestar.core.model.PatientRecordDataBean;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.validator.engine.rules.ValidationRuleEntry;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.TargetType;
import org.joda.time.DateTime;

@ValidationRuleEntry(TargetType.Patient)
public class VaccineEvaluation extends ValidationRule<MqePatient> {

  private static final int MONTHS_0 = 0;
  private static final int MONTHS_24 = 25;
  private static final int MONTHS_18 = 19;
  private static final int MONTHS_15 = 16;

  public VaccineEvaluation() {

    this.addRuleDetection(Detection.VaccineEvaluationAt18MonthsHepb3);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccineEvaluationAt18MonthsHepb3);
      id.setImplementationDescription(
          "Vaccination evaluation at 18 months of age for 3 valid doses of HepB.");
    }
    this.addRuleDetection(Detection.VaccineEvaluationAt18MonthsDtap4);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccineEvaluationAt18MonthsDtap4);
      id.setImplementationDescription(
          "Vaccination evaluation at 18 months of age for 4 valid doses of DTaP.");
    }
    this.addRuleDetection(Detection.VaccineEvaluationAt15MonthsPcv4);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccineEvaluationAt15MonthsPcv4);
      id.setImplementationDescription(
          "Vaccination evaluation at 15 months of age for 4 valid doses of PCV.");
    }
    this.addRuleDetection(Detection.VaccineEvaluationAt15MonthsPolio3);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccineEvaluationAt15MonthsPolio3);
      id.setImplementationDescription(
          "Vaccination evaluation at 15 months of age for 3 valid doses of Polio.");
    }
    this.addRuleDetection(Detection.VaccineEvaluationAt15MonthsMmr1);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccineEvaluationAt15MonthsMmr1);
      id.setImplementationDescription(
          "Vaccination evaluation at 15 months of age for 1 valid dose of MMR.");
    }
    this.addRuleDetection(Detection.VaccineEvaluationAt15MonthsVar1);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccineEvaluationAt15MonthsVar1);
      id.setImplementationDescription(
          "Vaccination evaluation at 15 months of age for 1 valid dose of Varicella.");
    }
    this.addRuleDetection(Detection.VaccineEvaluationAt18MonthsHepa2);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccineEvaluationAt18MonthsHepa2);
      id.setImplementationDescription(
          "Vaccination evaluation at 18 months of age for 1 valid dose of HepA.");
    }
    this.addRuleDetection(Detection.VaccineEvaluationAt15MonthsHib2);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccineEvaluationAt15MonthsHib2);
      id.setImplementationDescription(
          "Vaccination evaluation at 15 months of age for 2 valid doses of Hib.");
    }
    this.addRuleDetection(Detection.VaccineEvaluationAt24MonthsDtap4);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccineEvaluationAt24MonthsDtap4);
      id.setImplementationDescription(
          "Vaccination evaluation at 24 months of age for 4 valid doses of DTaP.");
    }
    this.addRuleDetection(Detection.VaccineEvaluationAt24MonthsPolio3);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccineEvaluationAt24MonthsPolio3);
      id.setImplementationDescription(
          "Vaccination evaluation at 24 months of age for 3 valid doses of Polio.");
    }
    this.addRuleDetection(Detection.VaccineEvaluationAt24MonthsMmr1);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccineEvaluationAt24MonthsMmr1);
      id.setImplementationDescription(
          "Vaccination evaluation at 24 months of age for 1 valid dose of MMR.");
    }
    this.addRuleDetection(Detection.VaccineEvaluationAt24MonthsHib3);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccineEvaluationAt24MonthsHib3);
      id.setImplementationDescription(
          "Vaccination evaluation at 24 months of age for 3 valid doses of Hib.");
    }
    this.addRuleDetection(Detection.VaccineEvaluationAt24MonthsHepb3);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccineEvaluationAt24MonthsHepb3);
      id.setImplementationDescription(
          "Vaccination evaluation at 24 months of age for 3 valid doses of HepB.");
    }
    this.addRuleDetection(Detection.VaccineEvaluationAt24MonthsVar1);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccineEvaluationAt24MonthsVar1);
      id.setImplementationDescription(
          "Vaccination evaluation at 24 months of age for 1 valid dose of Varicella.");
    }
    this.addRuleDetection(Detection.VaccineEvaluationAt24MonthsPcv4);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccineEvaluationAt24MonthsPcv4);
      id.setImplementationDescription(
          "Vaccination evaluation at 24 months of age for 4 valid doses of PCV.");
    }
    this.addRuleDetection(Detection.VaccineCoverageAt24MonthsSeries4_3_1_3_3_1_4);
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccineCoverageAt24MonthsSeries4_3_1_3_3_1_4);
      id.setImplementationDescription(
          "Vaccination coverage at 24 months of age for 4:3:1:3:3:1:4 series.");
    }
    this.addRuleDetection(Detection.VaccineCoverageAt36MonthsSeries4_3_1_3_3_1_4);
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccineCoverageAt24MonthsSeries4_3_1_3_3_1_4);
      id.setImplementationDescription(
          "Vaccination coverage at 36 months of age for 4:3:1:3:3:1:4 series.");
    }
  }

  private static Map<String, CvxCode> cvxToVaccineIdMap = null;

  public static Map<String, CvxCode> getCvxToVaccineIdMap() throws Exception {
    if (cvxToVaccineIdMap == null) {
      cvxToVaccineIdMap = CvxCodes.getCvxToCvxCodeMap();
    }
    return cvxToVaccineIdMap;
  }

  private static int MONTHS[] = {MONTHS_0, MONTHS_15, MONTHS_18, MONTHS_24};

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    DateTime birthDate = new DateTime(target.getBirthDate());

    Map<String, CvxCode> cvxToVaccineIdMap = null;
    try {
      cvxToVaccineIdMap = getCvxToVaccineIdMap();
    } catch (Exception e) {
      e.printStackTrace();
      return buildResults(issues, false);
    }
    Set<Detection> detectionSet = new HashSet<>();
    DateTime today = new DateTime();
    ForecastHandlerCore forecastHandlerCore =
        ForecastManagerSingleton.getForecastManagerSingleton().getForecastHandlerCore();

    {
      ForecastInput forecastInput = new ForecastInput();

      forecastInput.patient
          .setDob(new org.immregistries.lonestar.core.DateTime(target.getBirthDate()));
      forecastInput.patient.setSex(target.getSexCode());

      for (MqeVaccination v : m.getVaccinations()) {
        if (this.common.isValidDate(v.getAdminDateString())) {
          DateTime adminDate = this.datr.parseDateTime(v.getAdminDateString());
          Immunization imm = new Immunization();
          String vaccineCvx = v.getAdminCvxCode();
          if (vaccineCvx != null) {
            CvxCode cvxCode = null;
            if (cvxToVaccineIdMap.containsKey(vaccineCvx)) {
              cvxCode = cvxToVaccineIdMap.get(vaccineCvx);
            } else {
              cvxCode = cvxToVaccineIdMap.get("0" + vaccineCvx);
            }
            if (cvxCode != null) {
              imm.setVaccineId(cvxCode.getVaccineId());
              imm.setCvx(vaccineCvx);
              imm.setMvx(v.getManufacturerCode());
              imm.setDateOfShot(adminDate.toDate());
              forecastInput.imms.add(imm);
            }
          }
        }

      }

      List<ImmunizationForecastDataBean> resultList = new ArrayList<ImmunizationForecastDataBean>();

      try {
        Map<String, List<Trace>> traceMap = new HashMap<String, List<Trace>>();
        forecastHandlerCore.forecast(forecastInput.doseList, forecastInput.patient,
            forecastInput.imms, forecastInput.forecastDate, traceMap, resultList,
            forecastInput.forecastOptions);
        HashMap<Integer, Map<String, Integer>> countEvalMapList = new HashMap<>();
        for (int i = 0; i < MONTHS.length; i++) {
          Map<String, Integer> countEvalMap = new HashMap<>();
          countEvalMapList.put(MONTHS[i], countEvalMap);
        }
        for (VaccinationDoseDataBean dose : forecastInput.doseList) {
          if (dose.getStatusCode().equals(VaccinationDoseDataBean.STATUS_VALID)) {
            DateTime adminDate = new DateTime(dose.getAdminDate());
            for (int i = 0; i < MONTHS.length; i++) {
              Integer month = MONTHS[i];
              DateTime age = birthDate.plusMonths(month);
              Map<String, Integer> countEvalMap = countEvalMapList.get(month);
              if (month == 0 || adminDate.isBefore(age)) {
                Integer count = countEvalMap.getOrDefault(dose.getForecastCode(), 0);
                countEvalMap.put(dose.getForecastCode(), count + 1);
              }
            }
          }
        }

        for (int months = MONTHS.length - 1; months >= 0; months--) {
          Integer month = MONTHS[months];
          DateTime age = birthDate.plusMonths(month);
          if (!age.isBefore(today)) {
            break;
          }

          Map<String, Integer> countEvalMap = countEvalMapList.get(month);
          if (month == MONTHS_0) {
            if (countEvalMap.getOrDefault("HepB", 0) == 1) {
              detectionSet.add(Detection.VaccineEvaluationHepb1Only);
            }
          } else if (month == MONTHS_15) {
            if (countEvalMap.getOrDefault("Pneumo", 0) >= 4) {
              detectionSet.add(Detection.VaccineEvaluationAt15MonthsPcv4);
            }
            if (countEvalMap.getOrDefault("Polio", 0) >= 3) {
              detectionSet.add(Detection.VaccineEvaluationAt15MonthsPolio3);
            }
            if (countEvalMap.getOrDefault("Measles", 0) >= 1) {
              detectionSet.add(Detection.VaccineEvaluationAt15MonthsMmr1);
            }
            if (countEvalMap.getOrDefault("Varicella", 0) >= 1) {
              detectionSet.add(Detection.VaccineEvaluationAt15MonthsVar1);
            }
            if (countEvalMap.getOrDefault("Hib", 0) >= 2) {
              detectionSet.add(Detection.VaccineEvaluationAt15MonthsHib2);
            }
          } else if (month == MONTHS_18) {
            if (countEvalMap.getOrDefault("HepB", 0) >= 3) {
              detectionSet.add(Detection.VaccineEvaluationAt18MonthsHepb3);
            }
            if (countEvalMap.getOrDefault("Diphtheria", 0) >= 4) {
              detectionSet.add(Detection.VaccineEvaluationAt18MonthsDtap4);
            }
            if (countEvalMap.getOrDefault("HepA", 0) >= 2) {
              detectionSet.add(Detection.VaccineEvaluationAt18MonthsHepa2);
            }
          } else if (month == MONTHS_24) {
            if (countEvalMap.getOrDefault("Diphtheria", 0) >= 4) {
              detectionSet.add(Detection.VaccineEvaluationAt24MonthsDtap4);
            }
            if (countEvalMap.getOrDefault("Polio", 0) >= 3) {
              detectionSet.add(Detection.VaccineEvaluationAt24MonthsPolio3);
            }
            if (countEvalMap.getOrDefault("Measles", 0) >= 1) {
              detectionSet.add(Detection.VaccineEvaluationAt24MonthsMmr1);
            }
            if (countEvalMap.getOrDefault("Hib", 0) >= 3) {
              detectionSet.add(Detection.VaccineEvaluationAt24MonthsHib3);
            }
            if (countEvalMap.getOrDefault("HepB", 0) >= 3) {
              detectionSet.add(Detection.VaccineEvaluationAt24MonthsHepb3);
            }
            if (countEvalMap.getOrDefault("HepA", 0) >= 2) {
              detectionSet.add(Detection.VaccineEvaluationAt24MonthsHepa2);
            }
            if (countEvalMap.getOrDefault("Varicella", 0) >= 1) {
              detectionSet.add(Detection.VaccineEvaluationAt24MonthsVar1);
            }
            if (countEvalMap.getOrDefault("Pneumo", 0) >= 4) {
              detectionSet.add(Detection.VaccineEvaluationAt24MonthsPcv4);
            }
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }


      for (Detection detection : detectionSet) {
        issues.add(detection.build(target));
      }

      if (detectionSet.contains(Detection.VaccineEvaluationAt24MonthsDtap4)
          && detectionSet.contains(Detection.VaccineEvaluationAt24MonthsPolio3)
          && detectionSet.contains(Detection.VaccineEvaluationAt24MonthsMmr1)
          && detectionSet.contains(Detection.VaccineEvaluationAt24MonthsHib3)
          && detectionSet.contains(Detection.VaccineEvaluationAt24MonthsHepb3)
          && detectionSet.contains(Detection.VaccineEvaluationAt24MonthsVar1)
          && detectionSet.contains(Detection.VaccineEvaluationAt24MonthsPcv4)) {
        issues.add(Detection.VaccineCoverageAt24MonthsSeries4_3_1_3_3_1_4.build(target));
      }

    }
    {

      DateTime age = birthDate.plusMonths(MONTHS_24);
      if (!today.isBefore(age)) {
        ForecastInput forecastInput = new ForecastInput();

        forecastInput.patient
            .setDob(new org.immregistries.lonestar.core.DateTime(target.getBirthDate()));
        forecastInput.patient.setSex(target.getSexCode());

        for (MqeVaccination v : m.getVaccinations()) {
          if (this.common.isValidDate(v.getAdminDateString())) {
            DateTime adminDate = this.datr.parseDateTime(v.getAdminDateString());
            Immunization imm = new Immunization();
            String vaccineCvx = v.getAdminCvxCode();
            if (vaccineCvx != null) {
              CvxCode cvxCode = null;
              if (cvxToVaccineIdMap.containsKey(vaccineCvx)) {
                cvxCode = cvxToVaccineIdMap.get(vaccineCvx);
              } else {
                cvxCode = cvxToVaccineIdMap.get("0" + vaccineCvx);
              }
              if (cvxCode != null && adminDate.isBefore(age)) {
                imm.setVaccineId(cvxCode.getVaccineId());
                imm.setCvx(vaccineCvx);
                imm.setMvx(v.getManufacturerCode());
                imm.setDateOfShot(adminDate.toDate());
                forecastInput.imms.add(imm);
              }
            }
          }
        }

        List<ImmunizationForecastDataBean> resultList =
            new ArrayList<ImmunizationForecastDataBean>();
        try {
          forecastInput.forecastDate = new org.immregistries.lonestar.core.DateTime(age.toDate());
          Map<String, List<Trace>> traceMap = new HashMap<String, List<Trace>>();
          forecastHandlerCore.forecast(forecastInput.doseList, forecastInput.patient,
              forecastInput.imms, forecastInput.forecastDate, traceMap, resultList,
              forecastInput.forecastOptions);

          for (ImmunizationForecastDataBean forecast : resultList) {
            if (forecast.getStatusDescriptionExternal().equals("complete")) {
              if (forecast.getForecastLabel().equals("Hib")) {
                issues.add(Detection.VaccineForecastAt24MonthsHibComplete.build(target));
              } else if (forecast.getForecastLabel().equals("PCV13")) {
                issues.add(Detection.VaccineForecastAt24MonthsPcvComplete.build(target));
              } else if (forecast.getForecastLabel().equals("Rota")) {
                issues.add(Detection.VaccineForecastAt24MonthsRotaComplete.build(target));
              } else if (forecast.getForecastLabel().equals("HepB")) {
                issues.add(Detection.VaccineForecastAt24MonthsHepbComplete.build(target));
              }
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }

    return buildResults(issues, passed);
  }

  protected static class ForecastInput {
    protected List<VaccinationDoseDataBean> doseList = new ArrayList<VaccinationDoseDataBean>();
    protected List<ImmunizationInterface> imms = new ArrayList<ImmunizationInterface>();
    protected PatientRecordDataBean patient = new PatientRecordDataBean();
    protected org.immregistries.lonestar.core.DateTime forecastDate =
        new org.immregistries.lonestar.core.DateTime("today");
    protected ForecastOptions forecastOptions = new ForecastOptions();
    protected boolean dueUseEarly = false;

  }

}
