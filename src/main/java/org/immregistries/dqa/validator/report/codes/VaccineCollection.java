package org.immregistries.dqa.validator.report.codes;

import org.immregistries.dqa.core.util.DateUtility;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class VaccineCollection {
    private static final Logger logger = LoggerFactory.getLogger(VaccineCollection.class);
    private DateUtility datr = DateUtility.INSTANCE;
    @Override public String toString() {
        return "VaccineCollection{ counts=" + codeCountList + '}';
    }

    /*
        For each message, we'll collect vaccines sent through.
        we're not detecting anything about the codes.  Just counting.
    */
    public List<VaccineBucket> getCodeCountList() {
        return codeCountList;
    }

    public List<VaccineBucket> getByCvx(String cvx) {
        List<VaccineBucket> vbList = new ArrayList<>();
        for (VaccineBucket vb : this.codeCountList) {
            if (cvx.equals(vb.getCode())) {
                vbList.add(vb);
            }
        }
        return vbList;
    }

    private final List<VaccineBucket> codeCountList = new ArrayList<>();


    public void addAll(List<VaccineBucket> vbList) {
        for (VaccineBucket vb : vbList) {
            this.add(vb);
        }
    }

    public void add(VaccineBucket cbIn) {
        if (cbIn == null) {
            return;
        }

        if (this.codeCountList.contains(cbIn)) {
            VaccineBucket existing = this.codeCountList.get(this.codeCountList.indexOf(cbIn));
            existing.incrementBy(cbIn.getCount());
        } else {
            this.codeCountList.add(cbIn);
        }
    }

    public VaccineCollection() {

    }

    public VaccineCollection(DqaMessageReceived message) {
        DateTime birthDate = datr.parseDateTime(message.getPatient().getBirthDateString());
        this.codeCountList.addAll(this.getVaccineBuckets(message.getVaccinations(), birthDate));
    }

    private List<VaccineBucket> getVaccineBuckets(List<DqaVaccination> vaccineList, DateTime birthDate) {
        List<VaccineBucket> bucketList = new ArrayList<>();
        for (DqaVaccination v : vaccineList) {
            //calculate the vaccine admin date - birth date to get a year.
            DateTime adminDate = datr.parseDateTime(v.getAdminDateString());
            Integer adminAge = datr.getYearsBetween(birthDate, adminDate);
            VaccineBucket vb = new VaccineBucket(v.getCvxDerived(), adminAge, v.isAdministered());
            if (bucketList.contains(vb)) {
                bucketList.get(bucketList.indexOf(vb)).increment();
            } else {
                bucketList.add(vb);
            }
        }
        return bucketList;
    }

    /**
     * Adds duplicates together, and combines the entry
     * @return
     */
    public VaccineCollection reduce() {
        VaccineCollection crNew = new VaccineCollection();
        for (VaccineBucket vb : this.codeCountList) {
            crNew.add(vb);
        }
        return crNew;
    }

    public VaccineCollection combine(VaccineCollection that) {
        //Intentionally making a new one so we can return it without modifying the others.

        VaccineCollection crThis = this.reduce();
        VaccineCollection crThat = that.reduce();

        //first add all the ones from "this" instance.
        for (VaccineBucket vb : crThat.codeCountList) {
            crThis.add(new VaccineBucket(vb));
        }

        return crThis;
    }

}
