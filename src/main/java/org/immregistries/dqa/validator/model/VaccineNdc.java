package org.immregistries.dqa.validator.model;

import java.util.Date;

import org.immregistries.dqa.validator.model.codes.VaccineCvx;

public class VaccineNdc {

	public static final String NO_VACCINE_ADMINISTERED = "998";
	public static final String UNKNOWN = "999";

	public static final String CONCEPT_TYPE_NON_VACCINE = "non vaccine";
	public static final String CONCEPT_TYPE_VACCINE = "vaccine";
	public static final String CONCEPT_TYPE_UNSPECIFIED = "unspecified";
	public static final String CONCEPT_TYPE_NEVER_ACTIVE = "never active";
	public static final String CONCEPT_TYPE_FOREIGN_VACCINE = "foreign vaccine";

	private String ndcCode = "";
	private String ndcLabel = "";
	private Date validStartDate = null;
	private Date useStartDate = null;
	private Date useEndDate = null;
	private Date validEndDate = null;
	private int useMonthStart = 0;
	private int useMonthEnd = 0;
	private String conceptType = "";

	public Date getValidStartDate() {
		return validStartDate;
	}

	public void setValidStartDate(Date validStartDate) {
		this.validStartDate = validStartDate;
	}

	public Date getUseStartDate() {
		return useStartDate;
	}

	public void setUseStartDate(Date useStartDate) {
		this.useStartDate = useStartDate;
	}

	public Date getUseEndDate() {
		return useEndDate;
	}

	public void setUseEndDate(Date useEndDate) {
		this.useEndDate = useEndDate;
	}

	public Date getValidEndDate() {
		return validEndDate;
	}

	public void setValidEndDate(Date validEndDate) {
		this.validEndDate = validEndDate;
	}

	public int getUseMonthStart() {
		return useMonthStart;
	}

	public void setUseMonthStart(int useMonthStart) {
		this.useMonthStart = useMonthStart;
	}

	public int getUseMonthEnd() {
		return useMonthEnd;
	}

	public void setUseMonthEnd(int useMonthEnd) {
		this.useMonthEnd = useMonthEnd;
	}

	public String getConceptType() {
		return conceptType;
	}

	public void setConceptType(String conceptType) {
		this.conceptType = conceptType;
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof VaccineCvx) {
			return ((VaccineNdc) arg0).getNdcCode().equals(this.ndcCode);
		}
		return super.equals(arg0);
	}

	@Override
	public int hashCode() {
		return getNdcCode().hashCode();
	}

	public String getNdcLabel() {
		return ndcLabel;
	}

	public void setNdcLabel(String ndcLabel) {
		this.ndcLabel = ndcLabel;
	}

	public String getNdcCode() {
		return ndcCode;
	}

	public void setNdcCode(String ndcCode) {
		this.ndcCode = ndcCode;
	}

}
