package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.immregistries.lonestar.core.ImmunizationForecastDataBean;
import org.immregistries.lonestar.core.api.impl.CvxCode;
import org.immregistries.lonestar.core.api.impl.CvxCodes;
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
import org.openimmunizationsoftware.cdsi.core.data.DataModel;
import org.openimmunizationsoftware.cdsi.core.data.DataModelLoader;
import org.openimmunizationsoftware.cdsi.core.data.ForecastInput;
import org.openimmunizationsoftware.cdsi.core.domain.*;
import org.openimmunizationsoftware.cdsi.core.domain.datatypes.TargetDoseStatus;
import org.openimmunizationsoftware.cdsi.core.logic.LogicStep;
import org.openimmunizationsoftware.cdsi.core.logic.LogicStepFactory;
import org.openimmunizationsoftware.cdsi.core.logic.LogicStepType;

@ValidationRuleEntry(TargetType.Patient)
public class VaccineEvaluation extends ValidationRule<MqePatient> {

  private static final int MONTHS_0 = 0;
  private static final int MONTHS_24 = 25;
  private static final int MONTHS_18 = 19;
  private static final int MONTHS_15 = 16;
  public static final String SUPPORTING_DATA_SET = "supporting-data-4.65-508";

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
    this.addRuleDetection(Detection.VaccineEvaluationHasInvalidDoses1orMore);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccineEvaluationHasInvalidDoses1orMore);
      id.setImplementationDescription(
          "Vaccination evaluation has 1 or more invalid doses.");
    }
    this.addRuleDetection(Detection.VaccineEvaluationHasInvalidDoses2orMore);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccineEvaluationHasInvalidDoses2orMore);
      id.setImplementationDescription(
          "Vaccination evaluation has 2 or more invalid doses.");
    }
    this.addRuleDetection(Detection.VaccineEvaluationHasInvalidDoses3orMore);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccineEvaluationHasInvalidDoses3orMore);
      id.setImplementationDescription(
          "Vaccination evaluation has 3 or more invalid doses.");
    }
    this.addRuleDetection(Detection.VaccineEvaluationHasInvalidDoses4orMore);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccineEvaluationHasInvalidDoses4orMore);
      id.setImplementationDescription(
          "Vaccination evaluation has 4 or more invalid doses.");
    }
    this.addRuleDetection(Detection.VaccineEvaluationHasInvalidDoses5orMore);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccineEvaluationHasInvalidDoses5orMore);
      id.setImplementationDescription(
          "Vaccination evaluation has 5 or more invalid doses.");
    }
    this.addRuleDetection(Detection.VaccineEvaluationHasInvalidDoses10orMore);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccineEvaluationHasInvalidDoses10orMore);
      id.setImplementationDescription(
          "Vaccination evaluation has 10 or more invalid doses.");
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
          this.addRuleDetection(Detection.VaccineCoverageAt36MonthsSeries4_3_1_3_3_1_4);
      id.setImplementationDescription(
          "Vaccination coverage at 36 months of age for 4:3:1:3:3:1:4 series.");
    }
    
    
    this.addRuleDetection(Detection.VaccineEvaluationHepb1Only);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccineEvaluationHepb1Only);
      id.setImplementationDescription(
          "Vaccination evaluation patient has only one Hep B");
    }
    this.addRuleDetection(Detection.VaccineEvaluationAt24MonthsHepa2);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccineEvaluationAt24MonthsHepa2);
      id.setImplementationDescription(
          "Vaccination evaluation patient at 24 months of age for Help A series, has at least 2 doses");
    }
    this.addRuleDetection(Detection.VaccineForecastAt24MonthsHibComplete);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccineForecastAt24MonthsHibComplete);
      id.setImplementationDescription(
          "Vaccination evaluation patient at 24 months of age for Hib series is complete");
    }
    this.addRuleDetection(Detection.VaccineForecastAt24MonthsPcvComplete);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccineForecastAt24MonthsPcvComplete);
      id.setImplementationDescription(
          "Vaccination evaluation patient at 24 months of age for PVC series is complete");
    }
    this.addRuleDetection(Detection.VaccineForecastAt24MonthsRotaComplete);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccineForecastAt24MonthsRotaComplete);
      id.setImplementationDescription(
          "Vaccination evaluation patient at 24 months of age for Rotavirus series is complete");
    }
    this.addRuleDetection(Detection.VaccineForecastAt24MonthsHepbComplete);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccineForecastAt24MonthsHepbComplete);
      id.setImplementationDescription(
          "Vaccination evaluation at 24 months of age for Hep B series complete.");
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

    {
      ForecastInput forecastInput = new ForecastInput();

      forecastInput.setPatientDateOfBirth(target.getBirthDate());
      forecastInput.setPatientSex(target.getSex());

      for (MqeVaccination v : m.getVaccinations()) {
        if (this.common.isValidDate(v.getAdminDateString())) {
          DateTime adminDate = this.datr.parseDateTime(v.getAdminDateString());
          String vaccineCvx = v.getAdminCvxCode();
          if (vaccineCvx != null) {
            CvxCode cvxCode = null;
            if (cvxToVaccineIdMap.containsKey(vaccineCvx)) {
              cvxCode = cvxToVaccineIdMap.get(vaccineCvx);
            } else {
              cvxCode = cvxToVaccineIdMap.get("0" + vaccineCvx);
            }
            if (cvxCode != null) {
              ForecastInput.VaccinationInput vaccinationInput  = forecastInput.addVaccination();
              vaccinationInput.setVaccineCvx(vaccineCvx);
              vaccinationInput.setDateAdministered(adminDate.toDate());
              vaccinationInput.setVaccineMvx(v.getManufacturerCode());
//              vaccinationInput.setDoseCondition();
            }
          }
        }

      }

      List<ImmunizationForecastDataBean> resultList = new ArrayList<ImmunizationForecastDataBean>();

      int invalidDoseCount = 0;
      // Run with stepintocdsi
      DataModel dataModel = executeForecasting(forecastInput);

      try {
        HashMap<Integer, Map<String, Integer>> countEvalMapList = new HashMap<>();
        for (int i = 0; i < MONTHS.length; i++) {
          Map<String, Integer> countEvalMap = new HashMap<>();
          countEvalMapList.put(MONTHS[i], countEvalMap);
        }
        // Filtering invalid doses from count and from detections
        PatientSeries patientSeries = dataModel.getForecastingForPatientSeries();
        if (patientSeries != null && patientSeries.getTargetDoseList() != null) {
          for (TargetDose dose : patientSeries.getTargetDoseList()) {
            if (dose.getTargetDoseStatus().equals(TargetDoseStatus.SATISFIED)) {
              DateTime adminDate = new DateTime(dose.getEvaluation().getVaccineDoseAdministered().getDateAdministered());
              for (int i = 0; i < MONTHS.length; i++) {
                Integer month = MONTHS[i];
                DateTime age = birthDate.plusMonths(month);
                Map<String, Integer> countEvalMap = countEvalMapList.get(month);
                if (month == 0 || adminDate.isBefore(age)) {
                  String forecastCode = dose.getTrackedSeriesDose().getAntigenSeries().getSeriesName();
                  Integer count = countEvalMap.getOrDefault(forecastCode, 0);
                  countEvalMap.put(forecastCode, count + 1);
                }
              }
            } else if (dose.getTargetDoseStatus().equals(TargetDoseStatus.NOT_SATISFIED)) {
              invalidDoseCount++;
            }

          }
        }

        if (invalidDoseCount >= 1) {
          detectionSet.add(Detection.VaccineEvaluationHasInvalidDoses1orMore);
          if (invalidDoseCount >= 2) {
            detectionSet.add(Detection.VaccineEvaluationHasInvalidDoses2orMore);
            if (invalidDoseCount >= 3) {
              detectionSet.add(Detection.VaccineEvaluationHasInvalidDoses3orMore);
              if (invalidDoseCount >= 4) {
                detectionSet.add(Detection.VaccineEvaluationHasInvalidDoses4orMore);
                if (invalidDoseCount >= 5) {
                  detectionSet.add(Detection.VaccineEvaluationHasInvalidDoses5orMore);
                  if (invalidDoseCount >= 10) {
                    detectionSet.add(Detection.VaccineEvaluationHasInvalidDoses10orMore);
                  }
                }
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

        forecastInput.setPatientDateOfBirth(target.getBirthDate());
        forecastInput.setPatientSex(target.getSexCode());

        for (MqeVaccination v : m.getVaccinations()) {
          if (this.common.isValidDate(v.getAdminDateString())) {
            DateTime adminDate = this.datr.parseDateTime(v.getAdminDateString());
            String vaccineCvx = v.getAdminCvxCode();
            if (vaccineCvx != null) {
              CvxCode cvxCode = null;
              if (cvxToVaccineIdMap.containsKey(vaccineCvx)) {
                cvxCode = cvxToVaccineIdMap.get(vaccineCvx);
              } else {
                cvxCode = cvxToVaccineIdMap.get("0" + vaccineCvx);
              }
              if (cvxCode != null && adminDate.isBefore(age)) {
                ForecastInput.VaccinationInput vaccinationInput  = forecastInput.addVaccination();
                vaccinationInput.setVaccineCvx(vaccineCvx);
                vaccinationInput.setDateAdministered(adminDate.toDate());
                vaccinationInput.setVaccineMvx(v.getManufacturerCode());
//              vaccinationInput.setDoseCondition();
              }
            }
          }
        }

        try {
          forecastInput.setAssessmentDate(age.toDate());
//          Map<String, List<Trace>> traceMap = new HashMap<String, List<Trace>>();

          DataModel dataModel = executeForecasting(forecastInput);

          if (dataModel.getVaccineGroupForecastList() != null) {
            for (VaccineGroupForecast forecast : dataModel.getVaccineGroupForecastList()) {
              if (forecast.getVaccineGroupStatus().equals(VaccineGroupStatus.COMPLETE)) {
                if (forecast.getVaccineGroup().getName().equals("Hib")) {
                  issues.add(Detection.VaccineForecastAt24MonthsHibComplete.build(target));
                } else if (forecast.getVaccineGroup().getName().equals("PCV13")) {
                  issues.add(Detection.VaccineForecastAt24MonthsPcvComplete.build(target));
                } else if (forecast.getVaccineGroup().getName().equals("Rota")) {
                  issues.add(Detection.VaccineForecastAt24MonthsRotaComplete.build(target));
                } else if (forecast.getVaccineGroup().getName().equals("HepB")) {
                  issues.add(Detection.VaccineForecastAt24MonthsHepbComplete.build(target));
                }
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

  private static @NonNull DataModel executeForecasting(ForecastInput forecastInput) {
    DataModel dataModel = null;
    try {
      List<String> names = DataModelLoader.listBundledSupportingDataZipNames();
      dataModel = DataModelLoader.createDataModel(names.get(0));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    dataModel.setForecastInput(forecastInput);
    LogicStepFactory.createLogicStep(LogicStepType.GATHER_NECESSARY_DATA, dataModel);
    dataModel.setNextLogicStep(
            LogicStepFactory.createLogicStep(LogicStepType.GATHER_NECESSARY_DATA, dataModel));
    int count = 0;
    while (dataModel.getLogicStep().getLogicStepType() != LogicStepType.END) {
        LogicStep nextLogicStep = null;
        try {
            nextLogicStep = dataModel.getLogicStep().process();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        dataModel.setNextLogicStep(nextLogicStep);
      count++;
      if (count > 100000) {
        System.err.println(
                "Appear to be caught in a loop at this step: " + dataModel.getLogicStep().getTitle());
        // too many steps!
        if (count > 100100) {
          throw new RuntimeException(
                  "Logic steps seem to be caught in a loop, " + dataModel.getLogicStep().getTitle()
                          + ", unable to get results");
        }
      }
    }
    return dataModel;
  }

}
