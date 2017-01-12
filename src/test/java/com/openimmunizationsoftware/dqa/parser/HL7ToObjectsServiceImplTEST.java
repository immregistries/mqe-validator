package com.openimmunizationsoftware.dqa.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.openimmunizationsoftware.dqa.TestMessageGenerator;
import com.openimmunizationsoftware.dqa.model.MessageHeader;
import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Patient;

public class HL7ToObjectsServiceImplTEST {

	TestMessageGenerator genr = new TestMessageGenerator();
	HL7ToObjectsServiceImpl toObj = new HL7ToObjectsServiceImpl();
	MessageReceived mr;
	
	@Before
	public void setUp() {
		String msg = genr.getExampleVXU_1();
		this.mr = toObj.extractFromMessage(msg);
	}
	
	@Test
	public void itDoesnFail() {
		assertTrue(this.mr != null);
	}
	
	@Test
	public void mshHasExpectedParts() {
		MessageHeader msh = this.mr.getMessageHeader();
		
		assertFalse(msh==null);
		assertEquals("date", "20140619191115", msh.getMessageDateString());
		assertEquals("message identifier", "61731", msh.getMessageControl());
		assertEquals("pin", "1337-44-01", msh.getSendingFacility());
		
	}
	
	@Test
	public void pidHasExpectedParts() {
		Patient pid = this.mr.getPatient();
		
		assertFalse(pid==null);
		assertEquals("birthDate", "20120604", pid.getBirthDateString());
		assertEquals("race", "RACECD", pid.getRaceCode());
		assertEquals("address street", "100 Main", pid.getAddressStreet());
		assertEquals("MR", "23456", pid.getIdSubmitterNumber());
	}

}
