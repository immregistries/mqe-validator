package com.openimmunizationsoftware.dqa.validator.codes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.CodeReceived;
import com.openimmunizationsoftware.dqa.model.CodeTable;
import com.openimmunizationsoftware.dqa.model.codes.VaccineCpt;
import com.openimmunizationsoftware.dqa.model.codes.VaccineCvx;
import com.openimmunizationsoftware.dqa.model.codes.VaccineCvxGroup;
import com.openimmunizationsoftware.dqa.model.codes.VaccineMvx;
import com.openimmunizationsoftware.dqa.model.codes.VaccineProduct;
import com.openimmunizationsoftware.dqa.model.types.CodedEntity;

public enum CodeRepository {
	INSTANCE;
	
	//TODO:  get the data for these items.
	
	//TODO: make a way to map values before looking them up in the database. 
	// 		Check the old code on how this was done. 
	//		get a value out of the message.  Check the DQACM.  This is called a "Use Value"  
	//		
	
	//TODO: 
	
	public VaccineCpt getCptForCode(String cptcode) {
		VaccineCpt cpt = new VaccineCpt();
		cpt.setCptCode(cptcode);
		//this could be out of the DQA database, or out of a resource embedded in the libarry...
		//Should fill in dates and code status...  
		return cpt;
	}
	
	public VaccineCvx getCvxForCode(String cvxcode) {
		VaccineCvx cvx = new VaccineCvx();
		cvx.setCvxCode(cvxcode);
		//call the new XML based code repository
		return cvx;
	}
	
	public CodedEntity getCodeForType(String code, CodeTable.Type codeType) {
		CodedEntity ce = new CodedEntity(codeType);
		ce.setCode(code);
		CodeReceived cr = new CodeReceived();
		cr.setCodeValue(code);
		//call the new XML based code repository
		return ce;
	}

	public VaccineMvx getMfrForCode(String manufacturerCode) {
		VaccineMvx mvx = new VaccineMvx();
		mvx.setMvxCode(manufacturerCode);
		//call the new XML based code repository
		return null;
	}

	public VaccineProduct getVaccineProduct(VaccineCvx vaccineCvx,
			VaccineMvx vaccineMvx, Date adminDate) {
		VaccineProduct vp = new VaccineProduct();
		vp.setCvx(vaccineCvx);
		vp.setMvx(vaccineMvx);
		//call the new XML based code repository
		//This is where you'd pick the right product based on the date of the admin...
		
		return vp;
	}

	public List<VaccineCvxGroup> getVaccineCvxGroups(String cvxCode) {
		List<VaccineCvxGroup> groups = new ArrayList<VaccineCvxGroup>();
		//call the new XML based code repository
		return groups;
	}
}
