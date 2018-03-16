package org.immregistries.dqa.validator.engine.common;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import org.apache.commons.lang3.StringUtils;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.IssueType;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaPhoneNumber;
import org.immregistries.dqa.vxu.MetaFieldInfoData;
import org.immregistries.dqa.vxu.VxuField;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * This is to evaluate the basic expectations for an address in the system this
 * does not guarantee that the address is real or that the street address is
 * properly formatted. this evaluates the very minimum required for the address
 * to be evaluated.
 *
 * @author Josh
 */
public enum PhoneValidator {
    INSTANCE;

    private CodeHandler codr = CodeHandler.INSTANCE;

    public List<ValidationIssue> validatePhone(DqaPhoneNumber phone, VxuField piPhone, MetaFieldInfoData meta) {
        return validatePhone(phone, piPhone, null, null, meta);
    }

    public List<ValidationIssue> validatePhone(DqaPhoneNumber phone,
                                               VxuField piPhone,
                                               VxuField piPhoneTelUse,
                                               VxuField piPhoneTelEquip, MetaFieldInfoData meta) {

        List<ValidationIssue> issues = new ArrayList<>();

        if (phone != null && StringUtils.isNotEmpty(phone.getNumber())) {
            if (StringUtils.isEmpty(phone.getAreaCode()) || StringUtils.isEmpty(phone.getLocalNumber())) {
                Detection attr = Detection.get(piPhone, IssueType.INCOMPLETE);
                if (attr != null) {
                    issues.add(attr.build(phone.getNumber(), meta));
                }
            }

            //If there's a use code, make sure it's proper.
            if (piPhoneTelUse != null) {
                issues.addAll(codr.handleCode(phone.getTelUse(), piPhoneTelUse, meta));
            }

            //If it's got a code, make sure it's legit.
            if (piPhoneTelEquip != null) {
                issues.addAll(codr.handleCode(phone.getTelEquip(), piPhoneTelEquip, meta));
            }

            //Invalid phone number format.
            if (!isValidPhone(phone)) {
                Detection attr = Detection.get(piPhone, IssueType.INVALID);
                if (attr != null) {
                    issues.add(attr.build(phone.getNumber(), meta));
                }
            }

        } else {
            issues.add(Detection.get(piPhone, IssueType.MISSING).build(meta));
        }
        return issues;
    }

    private PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

    protected boolean isValidPhone(DqaPhoneNumber phone) {
        if (StringUtils.isEmpty(phone.getCountryCode())
                || phone.getCountryCode().equals("1")
                || phone.getCountryCode().equals("+1")) {
            // Validating all phone numbers using the North American Numbering
            // Plan
            // (NANP)

            if (StringUtils.isNotEmpty(phone.getAreaCode())) {
                if (!validPhone3Digit(phone.getAreaCode())) {
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

                if (!validPhone3Digit(num.substring(0, 3))) {
                    return false;
                }

                if (num.substring(1, 3).equals("11")) {
                    return false;
                }
            } else {
                return false;
            }
        }

        return true;
    }

    protected boolean isValidPhoneAccordingToGoogle(DqaPhoneNumber phone) {
        if (phone == null || (StringUtils.isBlank(phone.getNumber()) && StringUtils.isBlank(phone.getAreaCode()) && StringUtils.isBlank(phone.getLocalNumber()))) {
            return false;
        }

        String numberToCheck = phone.getNumber();
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


    private static boolean validPhone3Digit(String s) {
        if (s == null || s.length() != 3) {
            return false;
        }


        if (s.charAt(0) < '2' || s.charAt(0) > '9') {
            return false;
        }
        if (s.charAt(1) < '0' || s.charAt(1) > '9') {
            return false;
        }
        if (s.charAt(2) < '0' || s.charAt(2) > '9') {
            return false;
        }
        return true;
    }

}
