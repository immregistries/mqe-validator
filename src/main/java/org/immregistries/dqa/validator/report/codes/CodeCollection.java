package org.immregistries.dqa.validator.report.codes;

import org.apache.commons.lang3.StringUtils;
import org.immregistries.dqa.codebase.client.reference.CodesetType;
import org.immregistries.dqa.core.util.DateUtility;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CodeCollection {
    private static final Logger logger = LoggerFactory.getLogger(CodeCollection.class);
    @Override public String toString() {
        return "CodeCollection{ counts=" + codeCountList + '}';
    }
	/*
	For each message, we'll collect codes sent through.
	we're not detecting anything about the codes.  Just counting.
	The codes need to be categorized by type... maybe.
	except each code should have a type.
	and maybe the Code type should be from CodeBase
	*/

    public List<CollectionBucket> getCodeCountList() {
        return codeCountList;
    }

    public void setCodeCountList(List<CollectionBucket> list) {
        this.codeCountList = list;
    }
    /**
     * A map of Types.
     */
//    Map<CollectionBucket, Map<String, Integer>> vaccineCodeCounts = new HashMap<>();
    List<CollectionBucket> codeCountList = new ArrayList<>();

    public CodeCollection combine(CodeCollection other) {
        CodeCollection crNew = new CodeCollection();
//        crNew.vaccineCodeCounts.putAll(mergeTwo(this.vaccineCodeCounts, other.vaccineCodeCounts));
        crNew.codeCountList.addAll(mergeTwo(this.codeCountList, other.codeCountList));
        return crNew;
    }

    public void add(CollectionBucket cbIn) {
        this.codeCountList.add(cbIn);
    }
    List<CollectionBucket> mergeTwo(List<CollectionBucket> one, List<CollectionBucket> two) {
        List<CollectionBucket> cbNew = new ArrayList<>();
        for (CollectionBucket cbOne : one) {
            int x = two.indexOf(cbOne);
            if (x > -1) {
                CollectionBucket cbTwo = two.get(x);
                int countNew = cbTwo.getCount() + cbOne.getCount();
                CollectionBucket cbClone = new CollectionBucket(cbOne.getType(), cbOne.getAttribute(), cbOne.getValue());
                cbClone.setCount(countNew);
                cbNew.add(cbClone);
            }
        }

        return cbNew;
    }

    public CodeCollection() {
        //default.
    }

    public CodeCollection(DqaMessageReceived message) {
        //from the message, count the types of codes.
        DateTime birthDate = DateUtility.INSTANCE.parseDateTime(message.getPatient().getBirthDateString());
        this.codeCountList = this.collectVaccineCodesNew(message.getVaccinations(), birthDate);
    }

    List<CollectionBucket> getByType(CodesetType desiredType) {
        List<CollectionBucket> cbNew = new ArrayList<>();
        for (CollectionBucket bucket : this.codeCountList) {
            if (desiredType.getType().equals(bucket.getType())) {
                cbNew.add(bucket);
            }
        }
        return cbNew;
    }

    List<CollectionBucket> collectVaccineCodesNew(List<DqaVaccination> vaccineList, DateTime birthDate) {
        List<CollectionBucket> bucketList = new ArrayList<>();
        for (DqaVaccination v : vaccineList) {
            //calculate the vaccine admin date - birth date to get a year.
            DateTime adminDate = DateUtility.INSTANCE.parseDateTime(v.getAdminDateString());
            Integer adminAge = DateUtility.INSTANCE.getYearsBetween(birthDate, adminDate);
            String adminAgeString = String.valueOf(adminAge);
            //find the admin type.
            String adminType = "Historical";

            if (v.isAdministered()) {
                adminType = "Administered";
                for (String group : v.getVaccineGroupsDerived()) {
                    addCounts(CodesetType.VACCINE_GROUP, adminAgeString, group, bucketList);
                }
            }
            addCounts(CodesetType.VACCINATION_CPT_CODE,  adminType, v.getAdminCptCode(), bucketList);
            addCounts(CodesetType.VACCINATION_INFORMATION_SOURCE, "RXA-9",  v.getInformationSource(), bucketList);
            addCounts(CodesetType.VACCINATION_CVX_CODE,  v.getInformationSource(), v.getAdminCvxCode(), bucketList);
            addCounts(CodesetType.VACCINATION_NDC_CODE,  adminType, v.getAdminNdc(), bucketList);
            addCounts(CodesetType.ADMINISTRATION_UNIT,   adminType, v.getAmountUnit(), bucketList);
        }
        return bucketList;
    }

    void addCounts(CodesetType ct, String attribute, String value, List<CollectionBucket> existing) {
        if (StringUtils.isBlank(value)) {
            return;//don't add anything to the count.
        }

        CollectionBucket cbLookup = new CollectionBucket(ct,  attribute, value);
        int idx = existing.indexOf(cbLookup);
        if (idx > -1) {
            CollectionBucket cb = existing.get(idx);
            cb.setCount(cb.getCount()+1);
        } else {
            cbLookup.setCount(1);
            existing.add(cbLookup);
        }
    }
}
