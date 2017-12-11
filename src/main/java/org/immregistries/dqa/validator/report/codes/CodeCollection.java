package org.immregistries.dqa.validator.report.codes;

import org.apache.commons.lang3.StringUtils;
import org.immregistries.dqa.codebase.client.reference.CodesetType;
import org.immregistries.dqa.core.util.DateUtility;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CodeCollection {

    @Override public String toString() {
        return "CodeCollection{ vaccineCodeCounts=" + vaccineCodeCounts + '}';
    }

	/*
	For each message, we'll collect codes sent through.
	we're not detecting anything about the codes.  Just counting.
	The codes need to be categorized by type... maybe.
	except each code should have a type.
	and maybe the Code type should be from CodeBase
	*/

    /**
     * A map of Types.
     */
    Map<CollectionBucket, Map<String, Integer>> vaccineCodeCounts = new HashMap<>();

    public CodeCollection combine(CodeCollection other) {
        CodeCollection crNew = new CodeCollection();
        crNew.vaccineCodeCounts.putAll(mergeTwo(this.vaccineCodeCounts, other.vaccineCodeCounts));
        return crNew;
    }

    Map<CollectionBucket, Map<String, Integer>> mergeTwo(Map<CollectionBucket, Map<String, Integer>> one, Map<CollectionBucket, Map<String, Integer>> two) {
        Map<CollectionBucket, Map<String, Integer>> combinedMap = new HashMap<>();
        combinedMap.putAll(two);
        for (CollectionBucket obj : one.keySet()) {
            Map<String, Integer> listNew = combinedMap.get(obj);
            Map<String, Integer> listOld = one.get(obj);
            if (listNew == null) {
                combinedMap.put(obj, listOld);
            } else {
                for (String value : listOld.keySet()) {
                    Integer count = listNew.get(value);
                    if (count == null) {
                        listNew.put(value, listOld.get(value));
                    } else {
                        Integer count2 = listOld.get(value);
                        if (count2 != null) {
                            listNew.put(value, count + count2);
                        }
                    }
                }
            }
        }

        return combinedMap;
    }


    public Map<CollectionBucket, Map<String, Integer>> getVaccineCodeCounts() {
        return vaccineCodeCounts;
    }

    public CodeCollection() {
        //default.
    }

    public CodeCollection(DqaMessageReceived message) {
        //from the message, count the types of codes.
        DateTime birthDate = DateUtility.INSTANCE.parseDateTime(message.getPatient().getBirthDateString());
        this.vaccineCodeCounts = this.collectVaccineCodes(message.getVaccinations(), birthDate);
    }

    Map<CollectionBucket, Map<String, Integer>> getByType(CodesetType desiredType) {
        Map<CollectionBucket, Map<String, Integer>> out = new HashMap<>();
        for (CollectionBucket bucket : this.vaccineCodeCounts.keySet()) {
            if (desiredType == bucket.getType()) {
                out.put(bucket, this.vaccineCodeCounts.get(bucket));
            }
        }
        return out;
    }


    Map<CollectionBucket, Map<String, Integer>> collectVaccineCodes(List<DqaVaccination> vaccineList, DateTime birthDate) {
        Map<CollectionBucket, Map<String, Integer>> vcounts = new HashMap<>();

        for (DqaVaccination v : vaccineList) {
            //calculate the vaccine admin date - birth date to get a year.
            DateTime adminDate = DateUtility.INSTANCE.parseDateTime(v.getAdminDateString());
            Integer adminAge = DateUtility.INSTANCE.getYearsBetween(birthDate, adminDate);
            String adminAgeString = String.valueOf(adminAge);
            //find the admin type.
            String adminType = "Historical";

            if (v.isAdministered()) {
                adminType = "Administered";
                countUp(new CollectionBucket(CodesetType.VACCINE_GROUP, adminAgeString, "Age"), v.getVaccineGroupsDerived(), vcounts);
            }

            countUp(new CollectionBucket(CodesetType.VACCINATION_CPT_CODE,  adminType), v.getAdminCptCode(), vcounts);
            countUp(new CollectionBucket(CodesetType.VACCINATION_INFORMATION_SOURCE), v.getInformationSource(), vcounts);
            countUp(new CollectionBucket(CodesetType.VACCINATION_CVX_CODE,  v.getInformationSource(), "Type"), v.getAdminCvxCode(), vcounts);
            countUp(new CollectionBucket(CodesetType.VACCINATION_NDC_CODE,  adminType), v.getAdminNdc(), vcounts);
            countUp(new CollectionBucket(CodesetType.ADMINISTRATION_UNIT,   adminType), v.getAmountUnit(), vcounts);
        }

        return vcounts;
    }

    private void countUp(CollectionBucket codeType, List<String> vaccineGroupsDerived, Map<CollectionBucket, Map<String, Integer>> map) {
        for (String value : vaccineGroupsDerived) {
            countUp(codeType, value, map);
        }
    }

    private void countUp(CollectionBucket codeType, String value, Map<CollectionBucket, Map<String, Integer>> map) {
        Map<String, Integer> theseCounts = map.get(codeType);
        if (theseCounts == null) {
            theseCounts = new HashMap<>();
        }

        if (StringUtils.isNotBlank(value)) {
            map.put(codeType, theseCounts);
            Integer count = theseCounts.get(value);
            if (count == null) {
                theseCounts.put(value, 1);
            } else {
                theseCounts.put(value, count + 1);
            }
        }
    }
}
