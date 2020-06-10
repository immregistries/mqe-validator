package org.immregistries.mqe.validator.engine.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.DetectionType;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.vxu.MetaFieldInfoData;
import org.immregistries.mqe.vxu.MqePhoneNumber;
import org.immregistries.mqe.vxu.VxuField;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;

/**
 * This is to evaluate the basic expectations for an address in the system this does not guarantee
 * that the address is real or that the street address is properly formatted. this evaluates the
 * very minimum required for the address to be evaluated.
 *
 * @author Josh
 */
public enum PhoneValidator {
  INSTANCE;

  private CodeHandler codr = CodeHandler.INSTANCE;

  public List<ValidationReport> validatePhone(MqePhoneNumber phone, VxuField piPhone,
      MetaFieldInfoData meta) {
    return validatePhone(phone, piPhone, null, null, meta);
  }

  public List<ValidationReport> validatePhone(MqePhoneNumber phone, VxuField piPhone,
      VxuField piPhoneTelUse, VxuField piPhoneTelEquip, MetaFieldInfoData meta) {

    List<ValidationReport> issues = new ArrayList<>();

    if (phone != null && StringUtils.isNotBlank(phone.getFormattedNumber())) {
      if (StringUtils.isBlank(phone.getAreaCode()) || StringUtils.isBlank(phone.getLocalNumber())) {
        Detection attr = Detection.get(piPhone, DetectionType.INCOMPLETE);
        if (attr != null) {
          issues.add(attr.build(phone.getFormattedNumber(), meta));
        }
      }

      // If there's a use code, make sure it's proper.
      if (piPhoneTelUse != null) {
        issues.addAll(codr.handleCodeOrMissing(phone.getTelUse(), piPhoneTelUse, meta));
      }

      // If it's got a code, make sure it's legit.
      if (piPhoneTelEquip != null) {
        issues.addAll(codr.handleCodeOrMissing(phone.getTelEquip(), piPhoneTelEquip, meta));
      }

      // Invalid phone number format.
      if (!isValidPhone(phone)) {
        Detection attr = Detection.get(piPhone, DetectionType.INVALID);
        if (attr != null) {
          issues.add(attr.build(phone.getFormattedNumber(), meta));
        }
      }

    } else {
      issues.add(Detection.get(piPhone, DetectionType.MISSING).build(meta));
    }
    return issues;
  }

  private PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

  protected boolean isValidPhone(MqePhoneNumber phone) {
    if (StringUtils.isBlank(phone.getCountryCode()) || phone.getCountryCode().equals("1")
        || phone.getCountryCode().equals("+1")) {
      // Validating all phone numbers using the North American Numbering
      // Plan
      // (NANP)

      if (StringUtils.isNotBlank(phone.getAreaCode())) {
        if (invalidPhone3Digit(phone.getAreaCode())) {
          return false;
        }
        
        // Check if Area Code contains 11
        if (phone.getAreaCode().substring(1, 3).equals("11")) {
        	return false;
        }
      }

      if (!StringUtils.isBlank(phone.getLocalNumber())) {
        String num = phone.getLocalNumber();
        StringBuilder numOnly = new StringBuilder();
        for (int i = 0; i < num.length(); i++) {
          if (num.charAt(i) >= '0' && num.charAt(i) <= '9') {
            numOnly.append(num.charAt(i));
          }
        }

        num = numOnly.toString();
        if (num.length() != 7) {
          return false;
        }

        if (invalidPhone3Digit(num.substring(0, 3))) {
          return false;
        }

        //Numbers 2 and 3 can't be 11...
        if (num.substring(1, 3).equals("11")) {
          return false;
        }
        
        // Check if first three numbers in the local number are equal to 555
        if (num.substring(0, 3).equals("555")) {
        	return false;
        }

      } else {
        return false;
      }
    }

    return true;
  }

  protected boolean isValidPhoneAccordingToGoogle(MqePhoneNumber phone) {
    if (phone == null
        || (StringUtils.isBlank(phone.getFormattedNumber()) && StringUtils.isBlank(phone.getAreaCode()) && StringUtils
            .isBlank(phone.getLocalNumber()))) {
      return false;
    }

    String numberToCheck = phone.getFormattedNumber();
    if (StringUtils.isBlank(numberToCheck)) {
      numberToCheck = phone.getAreaCode() + phone.getLocalNumber();
    }

    try {
      phoneUtil.parse(numberToCheck, Locale.US.getCountry());
      return true;
    } catch (NumberParseException e) {
      return false;
    }
  }


  private static boolean invalidPhone3Digit(String s) {
    if (s == null || s.length() != 3) {
      return true;
    }

    //First character is 1, 0, or non numeric
    if (s.charAt(0) < '2' || s.charAt(0) > '9') {
      return true;
    }
    //second character is non-numeric
    if (s.charAt(1) < '0' || s.charAt(1) > '9') {
      return true;
    }
    //third char is non-numeric
    if (s.charAt(2) < '0' || s.charAt(2) > '9') {
      return true;
    }

    return false;
  }

}
