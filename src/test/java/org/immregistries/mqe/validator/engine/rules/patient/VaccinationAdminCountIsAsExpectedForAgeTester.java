package org.immregistries.mqe.validator.engine.rules.patient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VaccinationAdminCountIsAsExpectedForAgeTester {

	private VaccinationAdminCountIsAsExpectedForAge rule = new VaccinationAdminCountIsAsExpectedForAge();

	private MqeMessageReceived mr = new MqeMessageReceived();
	private MqePatient p = new MqePatient();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

	private static final Logger logger = LoggerFactory.getLogger(PatientMultipleBirthsValidTester.class);

	List<MqeVaccination> createVaccination(int nb, Date at){
		  List<MqeVaccination> vaccinations = new ArrayList<>();
		  
		  for(int i = 0; i < nb; i++){
			  MqeVaccination tmp = new MqeVaccination();
			  tmp.setAdminDateString(dateFormat.format(at));
			  vaccinations.add(tmp);
		  }
		  
		  return vaccinations;
	  }

	
	@Before
	public void setUpTheObjects() {
		mr.setPatient(p);
	}

	@Test
	public void moreThan20forBaby() {
		Calendar cal = Calendar.getInstance();
		p.setBirthDate(cal.getTime());	
		cal.add(Calendar.MONTH, 5);
		Date _5months = cal.getTime();
		
		List<MqeVaccination> vaccinations = createVaccination(21, _5months);
		this.mr.setVaccinations(vaccinations);
		
		ValidationRuleResult r = rule.executeRule(p, mr);
		logger.info(r.getValidationDetections().toString());
		assertTrue(!r.isRulePassed());
		assertEquals(1, r.getValidationDetections().size());
		assertEquals(Detection.AdministeredVaccinationsCountIsLargerThanExpected, r.getValidationDetections().get(0).getDetection());
	}
	
	@Test
	public void moreThan30forToddler() {
		Calendar cal = Calendar.getInstance();
		p.setBirthDate(cal.getTime());	
		cal.add(Calendar.YEAR, 1);
		Date _1year = cal.getTime();
		
		List<MqeVaccination> vaccinations = createVaccination(31, _1year);
		this.mr.setVaccinations(vaccinations);
		
		ValidationRuleResult r = rule.executeRule(p, mr);
		logger.info(r.getValidationDetections().toString());
		assertTrue(!r.isRulePassed());
		assertEquals(1, r.getValidationDetections().size());
		assertEquals(Detection.AdministeredVaccinationsCountIsLargerThanExpected, r.getValidationDetections().get(0).getDetection());
	}
	
	@Test
	public void shouldPass() {
		Calendar cal = Calendar.getInstance();
		p.setBirthDate(cal.getTime());	
		cal.add(Calendar.YEAR, 1);
		Date _5months = cal.getTime();
		cal.add(Calendar.YEAR, -1);
		cal.add(Calendar.MONTH, 5);
		Date _1year = cal.getTime();
		
		List<MqeVaccination> vaccinations = createVaccination(19, _5months);
		vaccinations.addAll(createVaccination(10, _1year));
		
		this.mr.setVaccinations(vaccinations);
		
		ValidationRuleResult r = rule.executeRule(p, mr);
		logger.info(r.getValidationDetections().toString());
		assertTrue(r.isRulePassed());
		assertEquals(0, r.getValidationDetections().size());
	}

}
