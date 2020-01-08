package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

public class VaccinationAdminDateIsValid extends ValidationRule<MqeVaccination> {

  public VaccinationAdminDateIsValid() {
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationAdminDateIsMissing);
      id.setHowToFix("The vaccination administration date was not valued. Please contact your vendor and request that they ensure that all vaccinations are submitted with an administration date. Vaccinations that have no administration date should not be reported until they have a specific administration date indicated. ");
      id.setWhyToFix("The administration date is used both to identify a unique vaccination event and to establish the event in the correct place on the vaccination history. The primary function of an immunization history is to provide specific information about when vaccinations were given so that clinical decisions can be made as to what vaccinations are due next and if the patient is properly protected from disease. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationAdminDateIsInvalid);
      id.setImplementationDescription(
          "Vaccination Administered Date annot be translated to a date.");
      id.setHowToFix("The vaccination administration date was not understood because it is not formatted correctly in the proper date format. Please contact your vendor and request that they ensure that all vaccinations are submitted with a properly formatted administration date. ");
      id.setWhyToFix("The administration date is used both to identify a unique vaccination event and to establish the event in the correct place on the vaccination history. The primary function of an immunization history is to provide specific information about when vaccinations were given so that clinical decisions can be made as to what vaccinations are due next and if the patient is properly protected from disease. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationAdminDateIsAfterMessageSubmitted);
      id.setImplementationDescription(
          "Vaccination Administered Date is after the message header date.");
      id.setHowToFix("The vaccination administration date is recorded as having occurred after the message was constructed. This could mean that vaccination administrations are being recorded and reported before they have actually occurred, or HL7 messaging system's clock is set incorrectly, or the recording system has it's clock set incorrectly, or the vaccination administration date was set incorrectly in the future and this problem was not corrected before resubmission. Please contact you system administrator or vendor to verify if any of these situations are occurring. ");
      id.setWhyToFix("The administration date is used both to identify a unique vaccination event and to establish the event in the correct place on the vaccination history. The primary function of an immunization history is to provide specific information about when vaccinations were given so that clinical decisions can be made as to what vaccinations are due next and if the patient is properly protected from disease. Also, it is important to only report immunization events that have already occurred. Immunization events that are scheduled or expected to occur should not be reported for inclusion on a patient's vaccination history until they have been properly completed. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationAdminDateIsAfterPatientDeathDate);
      id.setImplementationDescription(
          "Vaccination Administered Date is after patient's death date");
      id.setHowToFix("The patient has been marked as having been deceased as a certain date, and after that date a vaccination was administered. Vaccinations are not given to patients after they die, so either the patient is not deceased or the vaccination event was recorded improperly. Please review the patient's death status and vaccination event to ensure both are recorded properly. ");
      id.setWhyToFix("The administration date is used both to identify a unique vaccination event and to establish the event in the correct place on the vaccination history. The primary function of an immunization history is to provide specific information about when vaccinations were given so that clinical decisions can be made as to what vaccinations are due next and if the patient is properly protected from disease. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationAdminDateIsBeforeBirth);
      id.setImplementationDescription(
          "Vaccination Administered Date is before patient's birth date.");
      id.setHowToFix("The vaccination administered date is recorded as having occurred before the patient was born. Vaccinations are not given until after birth so the problem is assumed to happen because either the birth date or the vaccination date has been incorrectly recorded. Please review both of these values and determine which is incorrect. ");
      id.setWhyToFix("The administration date is used both to identify a unique vaccination event and to establish the event in the correct place on the vaccination history. The primary function of an immunization history is to provide specific information about when vaccinations were given so that clinical decisions can be made as to what vaccinations are due next and if the patient is properly protected from disease. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationAdminDateIsAfterSystemEntryDate);
      id.setImplementationDescription("Vaccination Administered Date is after System Entry date.");
      id.setHowToFix("The vaccination administration date is recorded as having occurred after the data was entered in the recording system. This could mean that vaccination administrations are being recorded and reported before they have actually occurred, the recording system has it's clock set incorrectly, or the vaccination administration date was set incorrectly in the future. Please contact you system administrator or vendor to verify if any of these situations are occurring. ");
      id.setWhyToFix("The administration date is used both to identify a unique vaccination event and to establish the event in the correct place on the vaccination history. The primary function of an immunization history is to provide specific information about when vaccinations were given so that clinical decisions can be made as to what vaccinations are due next and if the patient is properly protected from disease. Also, it is important to only report immunization events that have already occurred. Immunization events that are scheduled or expected to occur should not be reported for inclusion on a patient's vaccination history until they have been properly completed. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationAdminDateIsOnFirstDayOfMonth);
      id.setImplementationDescription(
          "Vaccination Administered Date is the first day of the month.");
      id.setHowToFix("Most systems and all registries require that the full vaccination administration date be entered, but in some cases only the month and year are known. (Especially when transcribing data from old paper shot records.) In these cases, some operators may decide to enter the information but select a day of the month such as the first, fifteenth, or last day. Vaccinations can be given on any of these dates, but high levels of reporting administration on these dates of the month may indicate that this practice is happening. Please consult your registry on best practices for recording and reporting vaccinations that are known to be given but where you do not have the full date. ");
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationAdminDateIsOn15ThDayOfMonth);
      id.setImplementationDescription("Vaccination Administered Date is on the 15th of the month.");
      id.setHowToFix("Most systems and all registries require that the full vaccination administration date be entered, but in some cases only the month and year are known. (Especially when transcribing data from old paper shot records.) In these cases, some operators may decide to enter the information but select a day of the month such as the first, fifteenth, or last day. Vaccinations can be given on any of these dates, but high levels of reporting administration on these dates of the month may indicate that this practice is happening. Please consult your registry on best practices for recording and reporting vaccinations that are known to be given but where you do not have the full date. ");
      id.setWhyToFix("The administration date is used both to identify a unique vaccination event and to establish the event in the correct place on the vaccination history. The primary function of an immunization history is to provide specific information about when vaccinations were given so that clinical decisions can be made as to what vaccinations are due next and if the patient is properly protected from disease. Registries that combine data from multiple sources can struggle to properly match vaccinations that have different dates, and incorrect dates might lead to incorrect recommendations for next vaccinations due. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationAdminDateIsOnLastDayOfMonth);
      id.setImplementationDescription(
          "Vaccination Administered Date is on the last day of the month.");
      id.setHowToFix("Most systems and all registries require that the full vaccination administration date be entered, but in some cases only the month and year are known. (Especially when transcribing data from old paper shot records.) In these cases, some operators may decide to enter the information but select a day of the month such as the first, fifteenth, or last day. Vaccinations can be given on any of these dates, but high levels of reporting administration on these dates of the month may indicate that this practice is happening. Please consult your registry on best practices for recording and reporting vaccinations that are known to be given but where you do not have the full date. ");
      id.setWhyToFix("The administration date is used both to identify a unique vaccination event and to establish the event in the correct place on the vaccination history. The primary function of an immunization history is to provide specific information about when vaccinations were given so that clinical decisions can be made as to what vaccinations are due next and if the patient is properly protected from disease. Registries that combine data from multiple sources can struggle to properly match vaccinations that have different dates, and incorrect dates might lead to incorrect recommendations for next vaccinations due. ");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    String dateString = target.getAdminDateString();

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    if (this.common.isEmpty(dateString)) {
      issues.add(Detection.VaccinationAdminDateIsMissing.build(target));
      passed = false;

      return buildResults(issues, passed);
    }

    if (!this.common.isValidDate(dateString)) {
      LOGGER.info("Date is not valid: " + dateString);
      issues.add(Detection.VaccinationAdminDateIsInvalid.build((dateString), target));
      passed = false;
      return buildResults(issues, passed);
    } ;

    DateTime adminDate = this.datr.parseDateTime(dateString);

    if (this.datr.isAfterDate(adminDate.toDate(), m.getMessageHeader().getMessageDate())) {
      LOGGER.info("Date is not valid: " + dateString);
      issues.add(Detection.VaccinationAdminDateIsAfterMessageSubmitted.build((dateString), target));
      passed = false;
    }

    if (m.getPatient().getDeathDate() != null) {
      if (datr.isAfterDate(target.getAdminDate(), m.getPatient().getDeathDate())) {
        issues.add(Detection.VaccinationAdminDateIsAfterPatientDeathDate.build(target));
        passed = false;
      }
    }

    if (m.getPatient().getBirthDate() != null) {
      if (datr.isBeforeDate(target.getAdminDate(), m.getPatient().getBirthDate())) {
        issues.add(Detection.VaccinationAdminDateIsBeforeBirth.build(target));
        passed = false;
      }
    }

    if (target.getSystemEntryDate() != null) {
      if (datr.isAfterDate(target.getAdminDate(), target.getSystemEntryDate())) {
        issues.add(Detection.VaccinationAdminDateIsAfterSystemEntryDate.build(target));
        passed = false;
      }
    }

    // After this, we have a date.
    int dayOfMonth = adminDate.getDayOfMonth();

    LocalDate lastDayOfMonth = adminDate.toLocalDate().dayOfMonth().withMaximumValue();

    int lastDay = lastDayOfMonth.getDayOfMonth();

    if (dayOfMonth == 1) {
      issues.add(Detection.VaccinationAdminDateIsOnFirstDayOfMonth.build((dateString), target));
    } else if (dayOfMonth == 15) {
      issues.add(Detection.VaccinationAdminDateIsOn15ThDayOfMonth.build((dateString), target));
    } else if (dayOfMonth == lastDay) {
      issues.add(Detection.VaccinationAdminDateIsOnLastDayOfMonth.build((dateString), target));
    }

    return buildResults(issues, passed);

  }
}
