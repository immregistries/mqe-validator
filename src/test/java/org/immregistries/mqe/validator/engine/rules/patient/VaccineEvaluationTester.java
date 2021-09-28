package org.immregistries.mqe.validator.engine.rules.patient;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VaccineEvaluationTester {

	private static final String HIB = "Hib";

	private static final String HEP_A = "HepA";

	private static final String VARICELLA = "Varicella";

	private static final String MMR = "MMR";

	private static final String PCV = "PCV";

	private static final String DTAP = "DTaP";

	private static final String HEP_B = "HepB";

	private static final String POLIO = "Polio";

	private VaccineEvaluation rule = new VaccineEvaluation();

	private MqeMessageReceived mr = new MqeMessageReceived();
	private MqePatient p = new MqePatient();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

	private static final Logger logger = LoggerFactory.getLogger(PatientMultipleBirthsValidTester.class);

	List<MqeVaccination> createVaccination(int nb, Date at) {
		List<MqeVaccination> vaccinations = new ArrayList<>();

		for (int i = 0; i < nb; i++) {
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
	public void uptodateThreeYearOld() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -10);

		// birth
		p.setBirthDate(cal.getTime());
		List<MqeVaccination> vaccinations = new ArrayList<>();
		addVaccination("08", vaccinations, cal); // Hep B at Birth

		// 2 months
		cal.add(Calendar.MONTH, 2);
		addVaccination("122", vaccinations, cal); // Rotavirus
		addVaccination("20", vaccinations, cal); // DTaP
		addVaccination("10", vaccinations, cal); // IPV
		addVaccination("17", vaccinations, cal); // Hib
		addVaccination("08", vaccinations, cal); // Hep B
		addVaccination("133", vaccinations, cal); // PCV13

		// 4 months
		cal.add(Calendar.MONTH, 2);
		addVaccination("122", vaccinations, cal); // Rotavirus
		addVaccination("20", vaccinations, cal); // DTaP
		addVaccination("10", vaccinations, cal); // IPV
		addVaccination("17", vaccinations, cal); // Hib
		addVaccination("08", vaccinations, cal); // Hep B
		addVaccination("133", vaccinations, cal); // PCV13

		// 6 months
		cal.add(Calendar.MONTH, 2);
		addVaccination("122", vaccinations, cal); // Rotavirus
		addVaccination("20", vaccinations, cal); // DTaP
		addVaccination("10", vaccinations, cal); // IPV
		addVaccination("17", vaccinations, cal); // Hib
		addVaccination("08", vaccinations, cal); // Hep B
		addVaccination("133", vaccinations, cal); // PCV13

		// 12 months
		cal.add(Calendar.MONTH, 6);
		addVaccination("133", vaccinations, cal); // PCV13
		addVaccination("94", vaccinations, cal); // MMRV
		addVaccination("83", vaccinations, cal); // Hep A

		// 15 months
		cal.add(Calendar.MONTH, 3);
		addVaccination("20", vaccinations, cal); // DTaP
		addVaccination("10", vaccinations, cal); // IPV
		addVaccination("17", vaccinations, cal); // Hib
		addVaccination("08", vaccinations, cal); // Hep B

		// 18 months
		cal.add(Calendar.MONTH, 3);
		addVaccination("83", vaccinations, cal); // Hep A

		// 23 months
		cal.add(Calendar.MONTH, 5);
		addVaccination("20", vaccinations, cal); // DTaP
		addVaccination("10", vaccinations, cal); // IPV

		// 47 months
		cal.add(Calendar.MONTH, 24);
		addVaccination("20", vaccinations, cal); // DTaP

		this.mr.setVaccinations(vaccinations);

		ValidationRuleResult r = rule.executeRule(p, mr);
		logger.info(r.getValidationDetections().toString());
		assertTrue(r.isRulePassed());
		Set<String> foundVaccineSet = new HashSet<>();
		System.out.println("--> ** " + r.getValidationDetections().size());
		for (ValidationReport vr : r.getValidationDetections()) {
			System.out.println("--> ** " + vr.getDetection());
			if (Detection.VaccineEvaluationAt15MonthsPolio3.equals(vr.getDetection())) {
				foundVaccineSet.add(POLIO);
			}
			if (Detection.VaccineEvaluationAt24MonthsPolio3.equals(vr.getDetection())) {
				foundVaccineSet.add(POLIO);
			}
			if (Detection.VaccineEvaluationAt18MonthsHepb3.equals(vr.getDetection())) {
				foundVaccineSet.add(HEP_B);
			}
			if (Detection.VaccineEvaluationAt24MonthsHepb3.equals(vr.getDetection())) {
				foundVaccineSet.add(HEP_B);
			}
			if (Detection.VaccineEvaluationAt18MonthsDtap4.equals(vr.getDetection())) {
				foundVaccineSet.add(DTAP);
			}
			if (Detection.VaccineEvaluationAt24MonthsDtap4.equals(vr.getDetection())) {
				foundVaccineSet.add(DTAP);
			}
			if (Detection.VaccineEvaluationAt15MonthsPcv4.equals(vr.getDetection())) {
				foundVaccineSet.add(PCV);
			}
			if (Detection.VaccineEvaluationAt24MonthsPcv4.equals(vr.getDetection())) {
				foundVaccineSet.add(PCV);
			}
			if (Detection.VaccineEvaluationAt15MonthsMmr1.equals(vr.getDetection())) {
				foundVaccineSet.add(MMR);
			}
			if (Detection.VaccineEvaluationAt24MonthsMmr1.equals(vr.getDetection())) {
				foundVaccineSet.add(MMR);
			}
			if (Detection.VaccineEvaluationAt15MonthsVar1.equals(vr.getDetection())) {
				foundVaccineSet.add(VARICELLA);
			}
			if (Detection.VaccineEvaluationAt24MonthsVar1.equals(vr.getDetection())) {
				foundVaccineSet.add(VARICELLA);
			}
			if (Detection.VaccineEvaluationAt18MonthsHepa2.equals(vr.getDetection())) {
				foundVaccineSet.add(HEP_A);
			}
			if (Detection.VaccineEvaluationAt15MonthsHib2.equals(vr.getDetection())) {
				foundVaccineSet.add(HIB);
			}
			if (Detection.VaccineEvaluationAt24MonthsHib3.equals(vr.getDetection())) {
				foundVaccineSet.add(HIB);
			}

		}
		for (String foundVaccine : foundVaccineSet)
		{
			System.out.println("--> " + foundVaccine);
		}
		assertTrue(foundVaccineSet.contains(POLIO));
		assertTrue(foundVaccineSet.contains(HEP_B));
		assertTrue(foundVaccineSet.contains(DTAP));
		assertTrue(foundVaccineSet.contains(PCV));
		assertTrue(foundVaccineSet.contains(MMR));
		assertTrue(foundVaccineSet.contains(VARICELLA));
		assertTrue(foundVaccineSet.contains(HEP_A));
		assertTrue(foundVaccineSet.contains(HIB));

	}

	private void addVaccination(String cvxCode, List<MqeVaccination> vaccinations, Calendar cal) {
		MqeVaccination tmp = new MqeVaccination();
		tmp.setAdminDateString(dateFormat.format(cal.getTime()));
		tmp.setAdminCvxCode(cvxCode);
		vaccinations.add(tmp);
	}

}
