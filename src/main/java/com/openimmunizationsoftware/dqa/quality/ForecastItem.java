package com.openimmunizationsoftware.dqa.quality;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForecastItem implements Serializable {

  public ForecastItem() {
    // default;
  }

  public ForecastItem(int forecastItemId, String label, String vaccineCvx) {
    this.forecastItemId = forecastItemId;
    this.label = label;
    this.vaccineCvx = vaccineCvx;
  }

  private static final long serialVersionUID = 1L;

  private int forecastItemId = 0;
  private String label = "";
  private String vaccineCvx = "";

  public String getVaccineCvx() {
    return vaccineCvx;
  }

  public void setVaccineCvx(String vaccineCvx) {
    this.vaccineCvx = vaccineCvx;
  }

  public static final int ID_DTAP = 2;
  public static final int ID_INFLUENZA = 3;
  public static final int ID_HEPA = 4;
  public static final int ID_HEPB = 5;
  public static final int ID_HIB = 6;
  public static final int ID_HPV = 7;
  public static final int ID_MENING = 8;
  public static final int ID_MMR = 9;
  public static final int ID_PNEUMO = 10;
  public static final int ID_POLIO = 11;
  public static final int ID_ROTA = 12;
  public static final int ID_VAR = 13;
  public static final int ID_ZOSTER = 14;
  public static final int ID_TDAP_TD = 15;
  public static final int ID_PPSV = 16;
  public static final int ID_PCV = 17;
  public static final int ID_TD_ONLY = 18;
  public static final int ID_DTAP_TDAP_TD = 19;
  public static final int ID_HEPB_2_ONLY = 20;
  public static final int ID_HEPB_3_ONLY = 21;
  public static final int ID_MEASLES_ONLY = 22;
  public static final int ID_MUMPS_ONLY = 23;
  public static final int ID_RUBELLA_ONLY = 24;
  public static final int ID_TDAP_ONLY = 25;
  public static final int ID_ANTHRAX = 26;
  public static final int ID_SMALLPOX_SHOT_OR_READING = 27;
  public static final int ID_NOVEL_H1N1 = 28;
  public static final int ID_TYPHOID = 29;
  public static final int ID_JAPENESE_ENCEPHALITIS = 30;
  public static final int ID_RABIES = 31;
  public static final int ID_YELLOW_FEVER = 32;

  private static List<ForecastItem> forecastItemList = null;
  private static Map<Integer, ForecastItem> forecastItemMap = null;
  
  public static final ForecastItem getForecastItem(int id)
  {
    getForecastItemList();
    return forecastItemMap.get(id);
  }

  public static final List<ForecastItem> getForecastItemList() {
    if (forecastItemList == null) {
      forecastItemList = new ArrayList<ForecastItem>();
      forecastItemList.add(new ForecastItem(ID_DTAP, "DTaP", "20"));
      forecastItemList.add(new ForecastItem(ID_INFLUENZA, "Influenza", "88"));
      forecastItemList.add(new ForecastItem(ID_HEPA, "HepA", "85"));
      forecastItemList.add(new ForecastItem(ID_HEPB, "HepB", "45"));
      forecastItemList.add(new ForecastItem(ID_HIB, "Hib", "17"));
      forecastItemList.add(new ForecastItem(ID_HPV, "HPV", "137"));
      forecastItemList.add(new ForecastItem(ID_MENING, "Meningococcal", "108"));
      forecastItemList.add(new ForecastItem(ID_MMR, "MMR", "03"));
      forecastItemList.add(new ForecastItem(ID_PNEUMO, "Pneumococcal", "152"));
      forecastItemList.add(new ForecastItem(ID_POLIO, "Polio", "89"));
      forecastItemList.add(new ForecastItem(ID_ROTA, "Rotavirus", "122"));
      forecastItemList.add(new ForecastItem(ID_VAR, "Varicella", "21"));
      forecastItemList.add(new ForecastItem(ID_ZOSTER, "HerpesZoster", "121"));
      forecastItemList.add(new ForecastItem(ID_TDAP_TD, "Td or Tdap", "139"));
      forecastItemList.add(new ForecastItem(ID_PPSV, "PPSV", "33"));
      forecastItemList.add(new ForecastItem(ID_PCV, "PCV", "152"));
      forecastItemList.add(new ForecastItem(ID_TD_ONLY, "Td Only", "139"));
      forecastItemList.add(new ForecastItem(ID_DTAP_TDAP_TD, "DTaP, Tdap or Td", "20"));
      forecastItemList.add(new ForecastItem(ID_HEPB_2_ONLY, "Hep B 2 Dose Only", "43"));
      forecastItemList.add(new ForecastItem(ID_HEPB_3_ONLY, "Hep B 3 Dose Only", "08"));
      forecastItemList.add(new ForecastItem(ID_MEASLES_ONLY, "Measles Only", "05"));
      forecastItemList.add(new ForecastItem(ID_MUMPS_ONLY, "Mumps Only", "07"));
      forecastItemList.add(new ForecastItem(ID_RUBELLA_ONLY, "Rubella Only", "06"));
      forecastItemList.add(new ForecastItem(ID_TDAP_ONLY, "Tdap Only", "115"));
      forecastItemMap = new HashMap<Integer, ForecastItem>();
      for (ForecastItem forecastItem : forecastItemList)
      {
        forecastItemMap.put(forecastItem.getForecastItemId(), forecastItem);
      }
    }
    return forecastItemList;
  }

  private int[] typicalGivenYear;

  private int[] getTypicalGivenYear() {
    if (typicalGivenYear == null) {
      typicalGivenYear = initTypicalGivenYear();
    }
    return typicalGivenYear;
  }

  private int[] initTypicalGivenYear() {
    switch (forecastItemId) {
    case ID_DTAP:
      return new int[] { 0, 6 };
    case ID_INFLUENZA:
      return new int[] { 0, 120 };
    case ID_HEPA:
      return new int[] { 1, 18 };
    case ID_HEPB:
      return new int[] { 0, 18 };
    case ID_HIB:
      return new int[] { 0, 4 };
    case ID_HPV:
      return new int[] { 9, 26 };
    case ID_MENING:
      return new int[] { 11, 26 };
    case ID_MMR:
      return new int[] { 0, 18 };
    case ID_PNEUMO:
      return new int[] { 0, 6 };
    case ID_POLIO:
      return new int[] { 0, 18 };
    case ID_ROTA:
      return new int[] { 0, 1 };
    case ID_VAR:
      return new int[] { 0, 120 };
    case ID_ZOSTER:
      return new int[] { 60, 120 };
    case ID_TDAP_TD:
      return new int[] { 7, 120 };
    case ID_PCV:
      return new int[] { 65, 120 };
    case ID_TD_ONLY:
      return new int[] { 0, 0 };
    case ID_DTAP_TDAP_TD:
      return new int[] { 0, 0 };
    case ID_HEPB_2_ONLY:
      return new int[] { 0, 0 };
    case ID_HEPB_3_ONLY:
      return new int[] { 0, 0 };
    case ID_MEASLES_ONLY:
      return new int[] { 0, 0 };
    case ID_MUMPS_ONLY:
      return new int[] { 0, 0 };
    case ID_RUBELLA_ONLY:
      return new int[] { 0, 0 };
    case ID_TDAP_ONLY:
      return new int[] { 0, 0 };
    }
    return new int[] { 0, 120 };
  }

  public int getTypicallyGivenYearStart() {
    return getTypicalGivenYear()[0];
  }

  public int getTypicallyGivenYearEnd() {
    return getTypicalGivenYear()[1];
  }

  public int getForecastItemId() {
    return forecastItemId;
  }

  public void setForecastItemId(int forecastItemId) {
    this.forecastItemId = forecastItemId;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  @Override
  public boolean equals(Object obj) {

    if (obj instanceof ForecastItem) {
      return ((ForecastItem) obj).getForecastItemId() == this.getForecastItemId();
    }
    return false;
  }

  @Override
  public int hashCode() {
    return forecastItemId == 0 ? super.hashCode() : forecastItemId;
  }

  @Override
  public String toString() {
    return label;
  }

}
