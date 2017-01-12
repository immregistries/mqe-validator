package com.openimmunizationsoftware.dqa.parser;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.publichealthsoftware.parser.message.HL7MessageMap;
import org.publichealthsoftware.parser.message.MessageParserHL7;
import org.springframework.stereotype.Service;

import com.openimmunizationsoftware.dqa.model.CodeTable;
import com.openimmunizationsoftware.dqa.model.CodeTable.Type;
import com.openimmunizationsoftware.dqa.model.MessageHeader;
import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.NextOfKin;
import com.openimmunizationsoftware.dqa.model.Observation;
import com.openimmunizationsoftware.dqa.model.Patient;
import com.openimmunizationsoftware.dqa.model.Vaccination;
import com.openimmunizationsoftware.dqa.model.types.Address;
import com.openimmunizationsoftware.dqa.model.types.CodedEntity;
import com.openimmunizationsoftware.dqa.model.types.Id;
import com.openimmunizationsoftware.dqa.model.types.OrganizationName;
import com.openimmunizationsoftware.dqa.model.types.PatientAddress;
import com.openimmunizationsoftware.dqa.model.types.PatientIdNumber;

/**
 * <ul>
 * <li>This class will put data into the transfer message objects, given a message.
 * <li>Declaration:  This class will not get data from any external sources.  Any data transformations will happen in other classes, as augmentation
 * after this class is invoked.  This will give you ONLY what's in the message, and will not look up anything, or add it it. 
 * it WILL interpret the information that exists in the message so that the values are put into the appropriate places in the object model. Keep in mind
 * this strategic difference between transforming the data V.S. Interpreting the data.  
 * <br /><br />
 *  
 * Those objects can then be used to manipulate the MCIR data for the patient... 
 * so this was step 1.  Get the data out of the message into a holding place. 
 * Theoretically, the holding place could have been dropped right into the mcir objects
 * like CHILD, VACCINE, etc.'
 * This code was largely taken from  VaccinationUpdateToExtLoader.java and another class HL7Converter.java
 * 
 * THe portion that is basic data gathering is also pulled from SQL/PL packages as noted in the methods. 
 * 
 * TODO: still need to do the processing that gest done to populate the 
 * transfer data in the SQL/PL packages.  for these three in particular
 * p_populate_transfer_shot
 * p_populate_transfer_child
 * p_populate_transfer_resp_party
 * That should be accomplished in the next classes. 
 * 
 * 
 * I think the next step is to take the object, and then make a class to decide what to do with the information. 
 * 
 * SO - MAKE A NEW CLASS TO TAKE THIS INFO AND DO STUFF WITH IT. 
 * STUFF += update, add, delete from MCIR
 * STUFF += update VFC by calling the VFC stored PROC. 
 * 
 * Random thought:  this could be made more open-source friendly by using a value map...  just a map of <String,String>
 * you'd store it like this: <"patient.name.first", "George">...  but there'd have to be a standard reference model.  Like an ENUM. 
 * then you could just use the enum to map that junk: <PATIENT_FIRST_NAME, "George">
 *  
 * Then in the processor, you'd figure out what to do, and populate the right objects to do that, and execute. 
 * so you'd get the action code.  Then you'd pick all the pieces you need to do the person lookup, and find the MCIR person. 
 * Then you'd have the objects, and update that, and persist those changes.  Boom.  Awesome. 
 * Updates - just get the object from the DBB, and update it, and persist changes. 
 * Adds - create a new MCIR object, and persist it.  
 * With deletes, you'd do even less.  just find the vaccine, and delete it.
 * 
 * Right now it's all in TRANSFER_ objects, which may or may not be useful...  they're a part of the previous system. 
 * changing to the Map<MessagePartsEnum, String> would loosen it up a bit. 
 * 
 *  Could even validate off that in DQA. The enum could be used to map the HL7 parts to the business parts. 
 *  and then you could have references to put into your dbb of rules.  Yes.  this is good. 
 *  
 *  And the Enum could be generated from a database table of locations.  a location map so to speak.
 *    
 *  I guess the problem is the repeating entities.  HL7 attributes can repeat...  So you'd either have to have a nested model. Or just... go with objects. 
 *  And while i'm thinking about this...  why even do it?  what's wrong with an object model?  I guess it can get bloated. there's nothing documented about it. 
 *  Anyway...  I'll think on that.  There's nothing particularly hard about one or the other. 
 *  
 *   that part's really all about the message pre-processor. 
 *  
 *  
 * 
 * @author Josh Hull
 *
 */
@Service
public class HL7ToObjectsServiceImpl  {
	
	protected static final Log LOGGER = LogFactory.getLog(HL7ToObjectsServiceImpl.class);
	protected static final String OBX3_DISEASE_WITH_PRESUMED_IMMUNITY = "59784-9";
	protected static final String OBX3_DISEASE_WITH_SEROLOGICAL_EVIDENCE_OF_IMMUNITY = "75505-8";
	private static final String VACCINATION_ACTION_ADD = "A";
	
	MessageParserHL7 parser = new MessageParserHL7();
	/**
	 * Extracts all the values from the message into objects that can be used to 
	 * process the message. 
	 * @param map
	 * @return filled out MessageParts
	 */
	public MessageReceived extractFromMessage(String message) {
		HL7MessageMap map = parser.getMessagePartMap(message);
		MessageReceived mr = extractFromMessage(map);
		return mr;
	}
	
	protected MessageReceived extractFromMessage(HL7MessageMap map) {
		MessageReceived container = new MessageReceived();

		//Message Header
		container.setMessageHeader(getTransferHeader(map));
		
		//Child from message
		Patient child = getPatient(map);
		container.setPatient(child);
		
		//Shots from message
		List<Vaccination> shots = getVaccinationList(map);
		container.setVaccinations(shots);
		
		//Resp Parties from message.
		//This currenty only gets the first one.  we need to get all of them!
		List<NextOfKin> respParties = getNk1List(map);
		
		container.setNextOfKins(respParties);
		
		return container;
	}
	
	/**
	 * Reads the MSH fields and builds a Message Header object from them. 
	 * @param map
	 * @return
	 */
	protected MessageHeader getTransferHeader(HL7MessageMap map) {
		
	    String sendingApp 		= map.get("MSH-3");//getMSH3SendingApplication(map);
	    String sendingFacility 	= map.get("MSH-4");//getMSH4SendingFacility(map);
	    String receivingApp 	= map.get("MSH-5");//getMSH5ReceivingApplication(map);
	    String recevingFacility = map.get("MSH-6");//getMSH6ReceivingFacility(map);
	    String sentDate 		= map.get("MSH-7");//getMSH7DateTime(map);
	    String messageType 		= map.get("MSH-9-1");//getMSH9_1MessageType(map);
	    String messageTrigger 	= map.get("MSH-9-2");//getMSH9_2MessageTrigger(map);
	    String messageStructure = map.get("MSH-9-3");//getMSH9_3MessageStructure(map);
	    String messageControlId = map.get("MSH-10");//getMSH10MessageControlId(map);
	    String processingId 	= map.get("MSH-11");//getMSH11ProcessingId(map);
	    String versionId 		= map.get("MSH-12");//getMSH12VersionId(map);
	    String ackTypeAcceptCode= map.get("MSH-15");//getMSH15AcceptCode(map);
	    String messageAckType 	= map.get("MSH-16");//getMSH16ApplicationAcknowledgementType(map);
	    String countryCode 		= map.get("MSH-17");//getMSH17CountryCode(map);
	    String charSetCode 		= map.get("MSH-18");//getMSH18CharacterSet(map);
	    String charSetAltCode 	= map.get("MSH-20");//getMSH20CharacterSetAltCode(map);
	    String messageProfile 	= map.get("MSH-21");//getMSH21MessageProfileIdentifier(map);
	    
	    MessageHeader h = new MessageHeader();
	    h.setSendingApplication(sendingApp);
	    h.setSendingFacility(sendingFacility);
	    h.setReceivingApplication(receivingApp);
	    h.setReceivingFacility(recevingFacility);
	    h.setMessageDateString(sentDate);
	    h.setMessageType(messageType);
	    h.setMessageTrigger(messageTrigger);
	    h.setMessageStructure(messageStructure);
	    h.setMessageControl(messageControlId);
	    h.setProcessingIdCode(processingId);
	    h.setMessageVersion(versionId);
	    h.setAckTypeAcceptCode(ackTypeAcceptCode);
	    h.setAckTypeApplicationCode(messageAckType);
	    h.setCountryCode(countryCode);
	    h.setCharacterSetCode(charSetCode);
	    h.setCharacterSetAltCode(charSetAltCode);
	    h.setMessageProfile(messageProfile);
	    
	    return h;
	}

	/* (non-Javadoc)
	 * @see gov.mi.mdch.xfer.rewrite.TransferMessageService#getTransferChild(gov.mi.mdch.immunization.message.HL7MessageMap)
	 */
	public Patient getPatient(HL7MessageMap map) {
		Patient patient = new Patient();
		
		
		//pull all the single value and coded fields. 
		//Get the ID's out of PID-3-5
		PatientIdNumber childId 	= getSRPatId(map);//PID-3
		PatientIdNumber medicaidId 	= getMAPatId(map);//PID-3
		PatientIdNumber patId 		= getMRPatId(map);//PID-3
		PatientIdNumber ssnTx 		= getSSPatId(map);//PID-3
		PatientIdNumber wicId		= getWCPatId(map);//PID-3
		
		String legalLastNm 		= map.get("PID-5-1");//getLegalLastNm(map);
		String legalFirstNm 	= map.get("PID-5-2");//getLegalFirstNm(map);
		String legalMiddleNm 	= map.get("PID-5-3");//getLegalMiddleNm(map);
		String legalSuffixNm 	= map.get("PID-5-4");//getLegalSuffixNm(map);
		String momsMaidenNm 	= map.get("PID-6");//getMomsMaidenNm(map);
		String birthDt 			= map.get("PID-7");//getPID7BirthDt(map);
		String sexCd 			= map.get("PID-8");//getSexCd(map);
		
		CodedEntity raceCd 		= getCodedEntity(map, "PID-10", Type.PATIENT_RACE);//getPID10Race(map);
		
		//Get the address list.  PID-11 
		List<PatientAddress> patientAddresses = getPID_11PatientAddressList(map);
		patient.getPatientAddressList().addAll(patientAddresses);
		
		CodedEntity language 	= getCodedEntity(map, "PID-15", Type.PERSON_LANGUAGE);//getPID15PrimaryLanguage(map);
		CodedEntity ethnicCd 	= getCodedEntity(map, "PID-22", CodeTable.Type.PATIENT_ETHNICITY);//getPID22EthnicCd(map);
		
		String birthHospNm 		= map.get("PID-23");//getPID23BirthHostNm(map);
		String birthMultCode 	= map.get("PID-24");//getPID24BirthMultiple(map);
		String birthOrderCode 	= map.get("PID-25");//getPID25BirthOrder(map);
		String deathDt 			= map.get("PID-29");
 		String deathInd 		= map.get("PID-30");
		
		String mogeStatusCode 	= map.get("PD1-16");//getPD1_16MogeCode(map);
		String publicityCode 	= map.get("PD1-11");
		String protectionCode 	= map.get("PD1-12");
		
		String pv1ClassCode 	= map.get("PV1-2");//getPV1Class(map);
		String pv1VFCCode 		= map.get("PV1-20");//getPatientVaccineFundEligCd(map);
		String pv1VFCEligDate 	= map.get("PV1-20-2");//getPatientVaccineFundEligEffDate(map);
		
		
		patient.setIdRegistry(childId);//PID-3-1 when PID-3-5 = "SR"
		patient.setIdMedicaid(medicaidId);//PID-3-1 when PID-3-5 = 'MA'
		patient.setIdSubmitter(patId);//PID-3-1 when PID-3-5 = 'MR'
		patient.setIdSsn(ssnTx);//PID-3-1 when PID-3-5 = 'SS'
		patient.setIdWic(wicId);//PID-3-1 when PID-3-5 =’WC'
		//TODO: Decide if I need birth county code here...  I think I should just get all the address repetitions...
		patient.setBirthDateString(birthDt);
		patient.setBirthPlace(birthHospNm);//PID-23
		patient.setBirthMultiple(birthMultCode);//PID-24
		patient.setBirthOrderCode(birthOrderCode);
		patient.setDeathDateString(deathDt);//PID-29
		patient.setDeathIndicator(deathInd);
		
		patient.setEthnicity(ethnicCd);//PID-22 first repetition...  CE not being used?
		patient.setNameFirst(legalFirstNm);//PID-5-2, first repetition 
		patient.setNameLast(legalLastNm);//PID-5-1, first repetition
		patient.setNameMiddle(legalMiddleNm);//PID-5-3, first repetition
		patient.setNameSuffix(legalSuffixNm);//PID-5-4, first repetition
		patient.setMotherMaidenName(momsMaidenNm);//PID-6
		patient.setRace(raceCd);//PID-10
		patient.setSexCode(sexCd);//PID-8
		patient.setPrimaryLanguage(language);//PID-15
		patient.setRegistryStatusCode(mogeStatusCode);;//PD1-16 Immunization Registry Status

		//PV1 values:
		patient.setFinancialEligibilityCode(pv1VFCCode);
		patient.setFinancialEligibilityDateString(pv1VFCEligDate);
		patient.setPatientClassCode(pv1ClassCode);
		
		//PD1 values: 
		patient.setProtectionCode(protectionCode);
		patient.setPublicityCode(publicityCode);
		
		return patient;
		
		//Transformations:
//		String messageAction = getVaccineAction(map);
//		String birthCntyCd = getPID11BirthCntyCd(map);
//		patient.setBirthCounty(birthCntyCd);//PID-11-9 when PID-11-7 = "BDL"
//		patient.setCntyCd(cntyCd);//PID-11-9 in first repetition of PID-11
	}
	
	
	/**
	 * Values and Descriptions from Table 0441 - Immunization registry status (9/2014): 
	 * <ul>
	 * <li>A - Accepted
	 * <li>I - Inactive
	 * <li>L - Lost to follow up
	 * <li>M - Inactive-moved or gone elsewhere
	 * <li>P - Inactive-Permanently Inactive (do not reactivate or add new entries to this record)
	 * <li>U - Unknown
	 * 
	 * <br />
	 * Note: P generally indicates that the patient is deceased. 
	 * @param map
	 * @return
	 */
	private String getPD1_16MogeCode(HL7MessageMap map) {
		String mogeCode = map.get("PD1-16");
		return mogeCode;
	}

	/* (non-Javadoc)
	 * @see gov.mi.mdch.xfer.rewrite.TransferMessageService#getTransferResponsibleParty(gov.mi.mdch.immunization.message.HL7MessageMap)
	 */
	
	public List<NextOfKin> getNk1List(HL7MessageMap map) {
		List<NextOfKin> nk1s = new ArrayList<NextOfKin>();
		
		Integer segCount = map.getSegmentCount("NK1");
		if (segCount == null || segCount.intValue() == 0) {
			return nk1s; 
		}
		
		for (int x = 1 ; x <= segCount.intValue() ; x++) {
			NextOfKin nextOfKin = getNextOfKin(map, x);
			nextOfKin.setPositionId(x);
			nk1s.add(nextOfKin);
		}
	
		return nk1s;
	}
	
	public NextOfKin getNextOfKin(HL7MessageMap map, int ordinal) {
		
		NextOfKin nextOfKin = new NextOfKin();
		
		int nk1Idx = map.getAbsoluteIndexForSegment("NK1",  ordinal);
		
		String nk1Id = map.getAtIndex("NK1-1", nk1Idx);
		
		
		
//		String actionCd = getVaccineAction(map);
		
		//Get the Mother's NK1 segment, if there is one: 
//		int motherIdx = map.findSegmentRepWithValue("MTH", "NK1-3", 1);
		
//		if (motherIdx > 0) {
//			//do all the mother stuff... using this index...
//			String momFirstNm = getNK1FirstNm(map, motherIdx);
//			String momLastNm = getNK1LastNm(map, motherIdx);
////			String momSsnTx = getNK1SSn(map, motherIdx);
//			
//			nextOfKin.setNameFirst(momFirstNm);
//			nextOfKin.setNameLast(momLastNm);
////			trp.setMomSsnTx(momSsnTx);
//		}
		
//		trp.setClientSupportId(clientSupportId);//not used
//		trp.setCreatedBy(createdBy);???
//		trp.setDateCreated(dateCreated);//generated
//		trp.setDateModified(dateModified);//NA
//		trp.setModifiedBy(modifiedBy);//NA
		
//		trp.setRecordId(recordId);//not used???
		
		//This is a transform step. 
//		int respPartIdx = getRespPartyNK1Index(map);
		
		//Interestingly, the RespParty address comes first from the patient, and if it's not there, it comes from the NK1. 
		//I think I'll put that here.  I'll first try to get it from the patient.  And if it comes up blank, I'll get it from the NK1. 
		
		//Changing the patient phone to the NK1 phone is a transform step...
//		String phone = getPatientPhone(map);
		
//		if (phone == null) {
			String phone = getNK1Phone(map, nk1Idx);
//		}
		
		//Then break it apart...  this is strange since we just put it together.
		//This is to be done in the transoform layer. 
//		String[] parts = splitPhoneParts(phone);
//		
//		String respPhoneTx = phone;
//		String respAreaCd = null;
//		if (parts != null && parts.length == 2) {
//			respAreaCd = parts[0];
//			respPhoneTx = parts[1];
//		}
		
		/*
		 * HL7Converter.java
		  protected void readPID11Address(String[] fields, VaccinationUpdateMessage vum, Patient patient, Map<Separator, Character> separators) {
			    if (fields.length <= 11) {
			      return;
			    }
			    String[] repeats = splitRepeats(separators, fields[11]);
			    for (int j = 0; j < repeats.length; j++) {
			      String[] comps = splitComponents(separators, repeats[j]);
			      clean(comps, separators);
			      if (j == 0) {
			        patient.getAddress().setStreet(read(1, comps));
			        patient.getAddress().setStreet2(read(2, comps));
			        patient.getAddress().setCity(read(3, comps));
			        patient.getAddress().setState(read(4, comps));
			        patient.getAddress().setZip(read(5, comps));
			        patient.getAddress().setCountry(read(6, comps));
			        patient.getAddress().setCountyProvince(read(9, comps));
			        vum.addDebugOutput(" + PID-11 address               " + patient.getAddress());
			      } else if (read(7, comps).equals("BDL")) {
			        patient.setBirthCountry(read(6, comps));
			        patient.setBirthCountyProvince(read(9, comps));
			        vum.addDebugOutput(" + PID-11 birth country         " + patient.getBirthCountry());
			        vum.addDebugOutput(" + PID-11 birth county          " + patient.getBirthCountyProvince());
			      }
			    }
			  }
		  
		  */
		
		//Next, let's figure out which address to use. Looks like the rule is: If the patient zip is empty, use the next of kin address. 
		
		//This is a transform step.  This will need to be done in that layer. 
//		respZipTx = getPatientAddressZip(map);
//		if (!StringUtils.isEmpty(respZipTx)) {
//			respCityTx = getPatientAddressCity(map);
//			respCntryCd = getPatientAddressCountryCd(map);
//			respStateCd = getPatientAddressStateCd(map);
//			respStreetTx = getPatientAddressStreet(map);
//		} else {
//			respCityTx = getNk1AddressCity(map, respPartIdx);
//			respCntryCd = getNk1AddressCountryCd(map, respPartIdx);
//			respStateCd = getNk1AddressStateCd(map, respPartIdx);
//			respStreetTx = getNk1AddressStreet(map, respPartIdx);
//			respZipTx = getNk1AddressZip(map, respPartIdx);
		
		//NOTE:  We don't actually use the resp party address. 
		
		/* VaccinationUpdateToExtLoader.java
		 *   protected static Address findAddress(VaccinationUpdateMessage message, Patient patient) {
			    Address address = patient.getAddress();
			    if (address.getZip().equals("")) {
			      for (NextOfKin nok : message.getNextOfKins()) {
			        if (!nok.getAddress().getZip().equals("")) {
			          if (nok.getRelationship().equals("") || nok.isRelationshipFather() || nok.isRelationshipGuardian() || nok.isRelationshipParent() || nok.isRelationshipMother()
			              || (nok.isRelationshipSelf() && patient.isChild())) {
			            address = nok.getAddress();
			            break;
			
			          }
			        }
			      }
			    }
			    return address;
			  }
		 */
		
		
		String respFirstNm = getRespPartyFirstName(map, nk1Idx);
		nextOfKin.setNameFirst(respFirstNm);

		String respLastNm = getRespPartyLastName(map, nk1Idx);
		nextOfKin.setNameLast(respLastNm);
		
		String respMiddleNm = getRespPartyMiddleName(map, nk1Idx);
		nextOfKin.setNameMiddle(respMiddleNm);
		
		String respSuffixNm = getRespPartySuffixName(map, nk1Idx);
		nextOfKin.setNameSuffix(respSuffixNm);
		
		//	OR SELF&&patient is child
		//And get that if there isn't a suitable responsible party. 
		
		//Area code - three digits. 
		nextOfKin.setPhoneNumber(phone);
//		mother.setRespAreaCd(respAreaCd);
		
		//Seven digits number.
//		mother.setRespPhoneTx(respPhoneTx);
		
		Address a = this.getNk1Address(map, nk1Idx);
		nextOfKin.setAddress(a);
		
		String relCode = getNk1_3RelationshipCode(map, nk1Idx);
		nextOfKin.setRelationshipCode(relCode);

		String primaryLang = getNk1_20PrimaryLangCode(map, nk1Idx);
		nextOfKin.setPrimaryLanguageCode(primaryLang); 
		
		return nextOfKin;
		
	}
	
	protected Address getNk1Address(HL7MessageMap map, int index) {
		return this.getAddressFromIndex(map, "NK1-4", index, 1);
	}
	
	//For example, the second RXA has an associated OBX, and RXR. 
	//So I should be able to fairly simply find the indexes which are greater 
	//than the current, and less than the next. 

	//The goal is to find all the segments between this and the next RXA segment. 
	//The idea is to get an arraylist of the segments...  in order... 
	//then I could just look for the index of the next RXA, and loop through 
	//the stuff between here and there. 
	
	//So the core problem is getting the segment names between here and there. 
	//once I know the segment name, and segment index, 
	//I can grab the relevant data from it.  
	
	//The rules according to the MCIR guide are that the ORC comes first
	//Then the RXA, and RXR (recommended, but apparently optional), 
	//And the OBX, which is required if RXA-9 = '00'.  The NTE segment is ingnored. 
	
	//So, The ORC should come before each RXA. 
	
//	So Maybe I could figure out a profile, and then grab it...  
//	the profile would be: 
//		check for something that preferably starts with ORC, and if not that, then RXA. 
//			Then it contains everything until the next match of ORC or RXA
//			Get that from the map object... it'd have to be a list of segment and index...
//			Maybe it can be a list of segments...
//	
			
	//So the algorithm goes thusly: 
		//Loop the list.  find the first ORC or RXA.  At the next RXA or ORC, 
		//send the last indexes into the method to build a TRANSFER_SHOT record. 
	/* (non-Javadoc)
	 * @see gov.mi.mdch.xfer.rewrite.TransferMessageService#getAllTransferShots(gov.mi.mdch.immunization.message.HL7MessageMap)
	 */
	public List<Vaccination> getVaccinationList(HL7MessageMap map) {
		//Start a list of shots
		List<Vaccination> shotList = new ArrayList<Vaccination>();
		
		//Get the list of segments so you know the total length. 
		int segmentListSize = map.getMessageSegments().size();
		
		int startingPoint = map.getNextImmunizationStartingIndex(0);

		while (startingPoint < segmentListSize) {
			int nextStartingPoint = map.getNextImmunizationStartingIndex(startingPoint);
			int finishPoint = nextStartingPoint - 1;
			Vaccination ts = getVaccination(map, startingPoint, finishPoint);
			shotList.add(ts);
			startingPoint = nextStartingPoint;
			//Theory: This will naturally finish the process to the end of the segments.
		}
		
		return shotList;
	}
	
	/**
	 * this is going to the be the most complicated process.  There are dependent segments
	 * and you need to read those into the Trasnfer_Shot object to create a whole shot record.
	 * 
	 * <p>Notice, a lot of the HL7 locations were represented in HL7Converter.java in this method: 
	 * 
	 * <code>
	<br />private void readRXA(String[] fields, VaccinationUpdateMessage vum, Vaccination vaccination, Map<Separator, Character> separators) {
	<br />vum.addDebugOutput("[RXA] Perscription Administration");
	<br />readRXA3AdminDate(fields, vum, vaccination, separators);
    <br />readRXA5Vaccination(fields, vum, vaccination, separators);
    <br />readRXA6AdministeredAmount(fields, vum, vaccination, separators);
    <br />readRXA9AdminNotes(fields, vum, vaccination, separators);
    <br />readRXA10Provider(fields, vum, vaccination, separators);
    <br />readRXA11AdministeredAtLocation(fields, vum, vaccination, separators);
    <br />readRXA15LotNumber(fields, vum, vaccination, separators);
    <br />readRXA16LotExpirationDate(fields, vum, vaccination, separators);
    <br />readRXA17Manufacturer(fields, vum, vaccination, separators);
    <br />readRXA18RefusalReason(fields, vum, vaccination, separators);
    <br />readRXA20CompletionStatus(fields, vum, vaccination, separators);
    <br />readRXA22EntryDateTime(fields, vum, vaccination, separators);
    <br />readRXA21ActionCode(fields, vum, vaccination, separators);
    <br />readRXA22EntryDateTime(fields, vum, vaccination, separators);
  <br />}
  </code>
	 * @param map
	 * @return
	 */
	public Vaccination getVaccination(HL7MessageMap map, int vaccinationStartSegment, int vaccinationFinishSegment) {
		
		Vaccination shot = new Vaccination();
		
		//So my task here is to find the index of the:
		//ORC
		//RXA
		//RXR
		//OBX's
		//Maybe NTE segments that we ignore. 
		List<String> segments = map.getMessageSegments();

		int orcIdx = -1;
		int rxaIdx = -1;
		int rxrIdx = -1;
		List<Integer> obxIdxList = new ArrayList<Integer>();
		
		//We're going to operate with relative indexes.  
		for (int i = vaccinationStartSegment; i <= vaccinationFinishSegment; i++) {
			String segName = segments.get(i);
			switch (segName) {
				case "ORC": orcIdx = map.getRelativeFromAbsoluteIndex(i); break;
				case "RXA": rxaIdx = map.getRelativeFromAbsoluteIndex(i); break;
				case "RXR": rxrIdx = map.getRelativeFromAbsoluteIndex(i); break;
				case "OBX": obxIdxList.add(map.getRelativeFromAbsoluteIndex(i)); break;
			}
		}
		
		LOGGER.info("Relative segment ID's being used for this shot: ORC[" + orcIdx + "] RXA[" + rxaIdx + "] RXR[" + rxrIdx + "] OBX{" + obxIdxList + "}");

		//Find the vaccine code. This is kind of a big deal.
		List<CodedEntity> vxList = getVaccineCodes(map, rxaIdx);
		
		boolean codeFound = false;
		for (CodedEntity code : vxList) {
			    if ("CVX"		.equals(code.getTable()) 
	    		||  "CVX"		.equals(code.getAltTable()) 
			    ||  "HL70292"	.equals(code.getTable())
			    ||  "HL70292"	.equals(code.getAltTable()))
			    {
			      shot.getAdminCvx().setCode(code.getCode());
			      shot.getAdminCvx().setText(code.getText());
			      shot.getAdminCvx().setTable(code.getTable());
			      codeFound = true;
			    }
			    else if ("CPT"		.equals(code.getTable()) 
	    		||  "CPT"		.equals(code.getAltTable()) 
	    		||  "C4"	.equals(code.getTable())
	    		||  "C4"	.equals(code.getAltTable()))
			    {
			      shot.getAdminCpt().setCode(code.getCode());
			      shot.getAdminCpt().setText(code.getText());
			      shot.getAdminCpt().setTable(code.getTable());
			      codeFound = true;
			    }
			    else if ("NDC"		.equals(code.getTable()) 
	    		||  "NDC"		.equals(code.getAltTable()) 
	    		||  "?"	.equals(code.getTable())
	    		||  "?"	.equals(code.getAltTable()))
			    {
			      shot.getAdminNdc().setCode(code.getCode());
			      shot.getAdminNdc().setText(code.getText());
			      shot.getAdminNdc().setTable(code.getTable());
			      codeFound = true;
			    }
		}
		
//		String facilityCd   = map.get("RXA-");//getMSH4SendingFacility(map); This is a transformation...
		
		String messageRxaCnt= map.getAtIndex("RXA-1", rxaIdx);
		String subMsgRxaCnt = map.getAtIndex("RXA-2", rxaIdx);
		String shotDt 		= map.getAtIndex("RXA-3", rxaIdx);//getShotDt(map, rxaIdx);
		String shotDtEnd 	= map.getAtIndex("RXA-4", rxaIdx);//getShotDt(map, rxaIdx);
		String doseVl 		= map.getAtIndex("RXA-6", rxaIdx);//getAdministeredAmount(map, rxaIdx);
		String doseVlUnit	= map.getAtIndex("RXA-7", rxaIdx);
		CodedEntity source  = getCodedEntity(map, "RXA-9",  rxaIdx, CodeTable.Type.VACCINATION_INFORMATION_SOURCE);
		String sourceId 	= map.getAtIndex("RXA-10-1", rxaIdx);//getProviderId(map, rxaIdx);
		OrganizationName org= readOrganizationName(map, "RXA-11", rxaIdx);
		String doseLotTx 	= map.getAtIndex("RXA-15", rxaIdx);//getVaccineDoseLot(map, rxaIdx);
		String doseExpDtStr = map.getAtIndex("RXA-16", rxaIdx);
		String mfrCd 		= map.getAtIndex("RXA-17", rxaIdx);//getVaccineMfrCode(map, rxaIdx);
		String nonAdmCd 	= map.getAtIndex("RXA-18", rxaIdx);//getNonAdminCd(map, rxaIdx);
		String completionCode=map.getAtIndex("RXA-20", rxaIdx);
		String actionCd 	= map.getAtIndex("RXA-21", rxaIdx);//getVaccineAction(map);
		String systemEntryDt= map.getAtIndex("RXA-22", rxaIdx);//getVaccineAction(map);
		
		String routeCd 		= map.getAtIndex("RXR-1", rxrIdx);//getRouteCd(map, rxrIdx);
		String bodySiteCd 	= map.getAtIndex("RXR-2", rxrIdx);//getBodySiteCd(map, rxrIdx);
		
		String orderId      = map.getAtIndex("ORC-1", orcIdx);//getOrderControlId(map, orcIdx);
		String idPlacer     = map.getAtIndex("ORC-2", orcIdx);//getPlacerOrderNumber(map, orcIdx);
		String shotMrn 		= map.getAtIndex("ORC-3", orcIdx);//getVaccineMRN(map, orcIdx);//This comes from ORC-3!
		
		//Hmm.... this is a lot of transformation here...
		String vfcCode 		= getShotVFCCode(map, rxaIdx, obxIdxList);
		
		
		if (StringUtils.isNumeric(messageRxaCnt)) {
			shot.setPositionId(Integer.parseInt(messageRxaCnt));
		}
		if (StringUtils.isNumeric(subMsgRxaCnt)) {
			shot.setPositionSubId(Integer.parseInt(subMsgRxaCnt));
		}
		
		shot.setAdminDateString(shotDt);
		shot.setAdminDateEndString(shotDtEnd);
		shot.setAmount(doseVl);
		shot.setAmountUnitCode(doseVlUnit);
		shot.setInformationSource(source);
		shot.setFacility(org);
		shot.setLotNumber(doseLotTx);
		shot.setExpirationDateString(doseExpDtStr);
		
		shot.setManufacturerCode(mfrCd);
		shot.setRefusalCode(nonAdmCd);
		
		shot.setCompletionCode(completionCode);
		shot.setActionCode(actionCd);
		shot.setSystemEntryDateString(systemEntryDt);

		List<Observation> observations = getObservations(map, obxIdxList);
		shot.setObservations(observations);
		
//		ts.setFacilityName(facilityName);
//		shot.setFacilityIdNumber(facilityCd);
//		ts.setFacilityTypeCode(facilityTypeCode);
//		ts.setHl7ImmunityCd(hl7ImmunityCd);
//		shot.setInitialsTx(provId);
		
		shot.setBodyRouteCode(routeCd);
		shot.setBodySiteCode(bodySiteCd);
		
		shot.setOrderControlCode(orderId);
		shot.setIdPlacer(idPlacer);
		shot.setIdSubmitter(shotMrn);
		
		shot.setFinancialEligibilityCode(vfcCode);
//		ts.setTreatmentMappingCode(treatmentMappingCode);

		return shot;
	}
	

	/**
	 * Question:  Is this a transformation???
	 * Should we be doing this in the transform layer? Picking the financial eligibility out of the OBX segments?
	 * @param map
	 * @param associatedRxaSegId
	 * @param obxIdxList
	 * @return
	 */
	protected String getShotVFCCode(HL7MessageMap map, int associatedRxaSegId, List<Integer> obxIdxList) {
		
		//Step 1: Look through the OBX list for a VFC code. 
		//Assumption:  the obxIdList is ordered, from smallest to largest. 
		//Also - need to verify that it's an administered shot!  

		/*
		 * “administered” is derived from RXA-9.  
		 * If the ID (first component) is ’00’, 
		 * that means its administered. 
		 */
		String vfcStatus = "V00";//"VFC eligibility not determined/unknown"
		
		//It stays unknown if it's a non-admin'd shot, or if it is not found in the OBX
		//segments of the message. 
		
		String rxa9Val = map.getAtIndex("RXA-9", associatedRxaSegId, 1);
		LOGGER.info("Administered value: " + rxa9Val + " for segment id: " + associatedRxaSegId);
		
		if ("00".equals(rxa9Val)) {
			//That means it's administered.  You can then look at the OBX's to find 
			//the VFC observation. 
			int start = obxIdxList.get(0);
			int finish = obxIdxList.get(obxIdxList.size() - 1);
			//It's a VFC code if the OBX-3 value is "64994-7".  SO look for that: 
			List<Integer> vfcObxList = map.findAllSegmentRepsWithValuesWithinRange(new String[] {"64994-7"},"OBX-3",start,finish,1);
			LOGGER.info("Observation segments: " + vfcObxList);
			
			if (vfcObxList != null && vfcObxList.size() > 0) { 
				vfcStatus = map.getAtIndex("OBX-5", vfcObxList.get(0), 1);
			} 
			
		}
		
		return vfcStatus;
		
	}
	

	  private OrganizationName readOrganizationName(HL7MessageMap map, String field, int segIdx) {
	    String id =  map.getAtIndex(field + "-1",  segIdx);
	    String name =  map.getAtIndex(field + "-4",  segIdx);
	    
	    OrganizationName on = new OrganizationName();
	    if (StringUtils.isEmpty(name)) {
	    	on.getId().setNumber(id);
	    } else {
	    	on.getId().setNumber(name);
	    	on.setName(name);
	    }
	    return on;
	  }
	  

	
	protected void HL7ConverterNotes() {
		/*
		 * the method "cleanAndReadField" essentially does the following: 
		 * Remove field repetitions
		 * Only take the first component, and sub-component. 
		 * Replace escape characters. 
		 */
	}
	
	protected String getNonAdminCd(HL7MessageMap map, int segIdx) {
		String nonAdminCd = map.getAtIndex("RXA-18", segIdx, 1);
		return nonAdminCd;
	}
	
	protected String getRefusalReason(HL7MessageMap map, int segIdx) {
		/*
		 * HL7Converter.java
		 *  protected void readRXA18RefusalReason(String[] fields, VaccinationUpdateMessage vum, Vaccination vaccination, Map<Separator, Character> separators) {
			    if (fields.length <= 18) {
			      return;
			    }
			    vaccination.setRefusalReason(cleanAndReadField(fields[18], separators));
			    vum.addDebugOutput(" + RXA-18 refusal reason        " + vaccination.getRefusalReason());
			  }
  
  			VaccinationUpdateToExtLoader.java
  			
  			ext.setNonAdmCode(chop(Table.SUBSTANCE_REFUSAL.mapToMcirValue(vaccination.getRefusalReason()), 2));
		 */
		
		String nonAdminCd = getNonAdminCd(map, segIdx);
		return nonAdminCd;
	}
	
	protected String getPatientVaccineFundEligCd(HL7MessageMap map) {
		/*
		 * From HL7Converter.java: 
		 * PV1-20

			 privatevoid readPV1(String[] fields, VaccinationUpdateMessage vum, Patient patient, Map<Separator, Character> separators) {
			    if (fields.length <= 20) {
			      return;
			    }
			    String field = cleanRepeatsOnly(fields[20], separators);
			    String[] comps = splitComponents(separators, field);
			    clean(comps, separators);
			    if (comps.length > 0) {
			      patient.getVfc().setStatus(comps[0]);
			      vum.addDebugOutput(" + PV1-20 vfc status            " + patient.getVfc().getStatus());
			      if (comps.length >= 2) {
			        patient.getVfc().setEffectiveDate(comps[1]);
			        vum.addDebugOutput(" + PV1-20 vfc effective date    " + patient.getVfc().getEffectiveDateString());
			      }
			    }
			
			  }
  
  From VaccineUpdateToExtLoader.java: 
			  //ext.setVaccineFundEligCode(chop(vaccination.getVfc().getStatus(), 1)); Bug# 8612
			ext.setVaccineFundEligCode(chop(Table.VFC_STATUS.mapToMcirValue(vaccination.getVfc().getStatus()), 1));
		 * 
		 * 
		 */
		
		String messageValue = map.get("PV1-20");
		return messageValue;
	}
	
	protected String getPatientVaccineFundEligEffDate(HL7MessageMap map) {
		String effDate = map.get("PV1-20-2");
		return effDate;
	}
	
	protected String getPV1Class(HL7MessageMap map) {
		String effDate = map.get("PV1-2");
		return effDate;
	}
	
	protected String getVaccineMRN(HL7MessageMap map, int orcIdx) {
		/*
		 * From HL7Converter.java: 
		 * ORC-3

			vaccination.setVaccinationIdSender(cleanAndReadField(fields[3], separators));
    		vum.addDebugOutput(" + ORC-3  vacc id sender      " + vaccination.getVaccinationIdSender());
    
    	From VaccineUpdateToExtLoader.java: 
    		ext.setVaccinationMrn(chop(vaccination.getVaccinationIdSender(),199));
		 */
		
		String messageVal = map.getAtIndex("ORC-3", orcIdx, 1);
		return chop(messageVal, 199);
		
	}
	
	protected String getOrderControlId(HL7MessageMap map, int orcIdx) {
		String controlId = map.getAtIndex("ORC-1", orcIdx, 1);
		return controlId;
	}
	
	
	protected String getPlacerOrderNumber(HL7MessageMap map, int orcIdx) {
		String controlId = map.getAtIndex("ORC-2", orcIdx, 1);
		return controlId;
	}
	
	
	
//	TODO: Make sure we really don't need this here. 
//	protected long getSiteId() {
/*
 * Site_id is not on the HL7 Message...  
 * It's looked up from the Transfer Interface PIN, 
 * which is gotten from the message_interface
 * 
 * Site_id is not set onto the EXT_TRANSFER_DATA record in VaccinationUpdateToExtLoader
 * 
 * In the stored procedure; 
 * 
 * 	NVL(ext_transfer_rec.site_id,l_site_id)

 	SELECT     Site_id
   	INTO       l_site_id 
   	FROM       Transfer_Job
   	WHERE      request_status_id = p_request_id;
   
    This will be added in a layer where we're augmenting/transforming data.  We can probably get the site_id from the PIN in the message. 
    	
 */
//		return ConvertSiteId("0");
//	}
	
	protected Long ConvertSiteId(String siteId) {

//		CREATE OR REPLACE FUNCTION CONVERT_SITE_ID( fi_site_id in VARCHAR2 )
//		  RETURN NUMBER
//		IS
//
//		l_site_id       VARCHAR2(12);
//		retval NUMBER;
//
//		BEGIN

		try {
	//		  l_site_id := NVL(fi_site_id,'X');
	//		  IF l_site_id = 'X' THEN
	//		    retval := NULL;
			if (siteId == null) {
				return null;
			}
	//		  ELSIF (SUBSTR(UPPER(l_site_id),1,2)='U0') THEN
	//		    l_site_id := 'U9' || SUBSTR(l_site_id,3);
	//		    retval := TO_NUMBER(SUBSTR(l_site_id,2));
			else if ("U0".equalsIgnoreCase(siteId.substring(0, 2))) {
				return Long.valueOf("9" + siteId.substring(3));//How do you cast this to a number???
			}
	//		  ELSIF l_site_id = 'MCIR' THEN
	//		    l_site_id := 'U90000000000';
	//		    retval := TO_NUMBER(SUBSTR(l_site_id,2));
			else if ("MCIR".equals(siteId)) {
				return new Long(90000000000L);
			}
	//		  ELSIF (SUBSTR(UPPER(l_site_id),1,1)='U') THEN
	//		    retval := TO_NUMBER(SUBSTR(l_site_id,2));
			else if ("U".equals(siteId.substring(0, 1))) {
				return new Long(siteId.substring(2));
			}
	//		  ELSE
	//		    retval := TO_NUMBER(l_site_id);
				return new Long(siteId);
//			  END IF;
			//
//					  return retval;
		} catch (NumberFormatException e) {
//			EXCEPTION
//			  WHEN OTHERS THEN
//			     DBMS_OUTPUT.PUT_LINE(l_site_id || ':' ||SQLERRM);
//			     RAISE;
			throw new RuntimeException("Error Converting Site Id",  e);
			//TODO: put some attention to this...  Need to come up with a more unified exception handling strategy...  This layer needs to have it's own exception probably...
		}

//
//		EXCEPTION
//		  WHEN OTHERS THEN
//		     DBMS_OUTPUT.PUT_LINE(l_site_id || ':' ||SQLERRM);
//		     RAISE;
//		END CONVERT_SITE_ID;

	}
	
	protected String getRouteCd(HL7MessageMap map, int segIdx) {
		/*HL7Converter.java
		 * RXR-1

			privatevoid readRXR(String[] fields, VaccinationUpdateMessage vum, Vaccination vaccination, Map<Separator, Character> separators) {
			    if (fields.length <= 1) {
			      return;
			    }
			    vaccination.setBodyRoute(cleanAndReadField(fields[1], separators));
			    if (fields.length <= 2) {
			      return;
			    }
			    vaccination.setBodySite(cleanAndReadField(fields[2], separators));
			  }
  
  		VaccinationUpdateToExtLoader.java
  			chop(Table.BODY_ROUTE.mapToMcirValue(vaccination.getBodyRoute()), 1)
		 */
		String routeCode = map.getAtIndex("RXR-1", segIdx, 1);
		
		return routeCode;
	}
	
	protected String getBodySiteCd(HL7MessageMap map, int segIdx) {
		
		/*
		 * HL7Converter.java
		 * RXR-2

				privatevoid readRXR(String[] fields, VaccinationUpdateMessage vum, Vaccination vaccination, Map<Separator, Character> separators) {
				    if (fields.length <= 1) {
				      return;
				    }
				    vaccination.setBodyRoute(cleanAndReadField(fields[1], separators));
				    if (fields.length <= 2) {
				      return;
				    }
				    vaccination.setBodySite(cleanAndReadField(fields[2], separators));
				  }
  
  		VaccinationUpdateToExtLoader.java
    		chop(Table.BODY_SITE.mapToMcirValue(vaccination.getBodySite()), 1)
		 */
		
		String siteCd = map.getAtIndex("RXR-2", segIdx, 1);
		
		return siteCd;
		
	}
	
	protected String getShotDt(HL7MessageMap map, int segIdx) {
		/*
		 * HL7Converter.java: 
		 * RXA-3

			 protected void readRXA3AdminDate(String[] fields, VaccinationUpdateMessage vum, Vaccination vaccination, Map<Separator, Character> separators) {
			    if (fields.length <= 3) {
			      return;
			    }
			    String date = cleanAndReadField(fields[3], separators);
			    vaccination.setAdministeredDateString(date.length() > 8 ? date.substring(0, 8) : date);
			    vum.addDebugOutput(" + RXA-3  admin date            " + vaccination.getAdministeredDateString());
			    if (fields.length <= 4) {
			      return;
			    }
			    vaccination.setAdministeredDateEndString(cleanAndReadField(fields[4], separators));
			    vum.addDebugOutput(" + RXA-4  admin date end        " + vaccination.getAdministeredDateEndString());
			  }
			  
		  VaccinationUpdateToExtLoader.java
		  dateNormalizer(vaccination.getAdministeredDateString())
		  
		  
		 * 
		 */
		
		String adminsteredDateString = map.getAtIndex("RXA-3", segIdx, 1);
		String normalizedDate = dateNormalizer(adminsteredDateString);
		return normalizedDate;
	}

	
/**
 * Taken directly from VaccinationUpdateToExtLoader.java in oder to preserve the treatment of dates.
 * <br /><br />This always returns YYYYMMDD.  There are two possible incoming formats (ignoring spaces): 
 * <ol><li>All numeric<li>MM/DD/YYYY
 * </ol>
 * <br />
 * What this does: 
 * <li>removes spaces
 * <li>removes non-numbers
 * <li>if there aren't 8 numbers, returns ""
 * <li>Only uses the first 8 digits
 * <li>If there are "/" in the non-numeric chars, It must be of the format: {1-2}/{1-2}/{4}. Meaning 1 to 2 digits in the first and second section. four in the third.  
 * @param date
 * @return
 */
	protected String dateNormalizer(String date) {
		
		if (date == null) {
			return "";
		}
		
	    String nonNumbers = "";//date.replaceAll("\\d+", "");
	    String numbers = "";//date.replaceAll("\\D+", "");;
	    char[] digits = date.toCharArray();
	    
	    //Loop through the characters in the date string. 
	    for (char c : digits) {
	      //Remove spaces by no adding them to the list. 
	      if (c == ' ') {
	        continue;
	      }
	      if (c >= '0' && c <= '9') {
	    	//Add any numbers. 
	        numbers += c;
	        continue;
	      }
	      nonNumbers += c;
	    }
	    
	    if (nonNumbers.length() == 0) {
	      // Assumed to be YYYYMMDD format
	      if (numbers.length() < 8) {
	        return "";
	      }
	      if (numbers.length() > 8) {
	        numbers = numbers.substring(0, 8);
	      }
	      return numbers;//return the first 8 numbers in the character string. 
	      
	    } else if (nonNumbers.startsWith("//")) {
	      //Figure out how it's formatted.  
	      int i = date.indexOf("/");
	      String month = date.substring(0, i).trim();
	      if (month.length() == 1) {
	        month = "0" + month;
	      } else if (month.length() != 2) {
	    	//assumes it's formatted wrong?
	        return "";
	      }
	      
	      //Get the next digits. 
	      i++;
	      int j = date.indexOf("/", i);
	      String day = date.substring(i, j).trim();
	      
	      //second chunk has to be 1 or 2 chars long. 
	      if (day.length() == 1) {
	        day = "0" + day;
	      } else if (day.length() != 2) {
	        return "";
	      }
	      
	      i = j + 1;
	      j = i + 4;
	      if (j > date.length()) {
	        j = date.length();
	      }
	      String year = date.substring(i, j);
	      //Year has to be 4 digits long. 
	      if (year.length() != 4) {
	        return "";
	      }
	      try {
	        Integer.parseInt(year);
	        Integer.parseInt(month);
	        Integer.parseInt(day);
	      } catch (NumberFormatException nfe) {
	        return "";
	      }
	      return year + month + day;
	    } else {
	      return "";
	    }
	  }	
	
	protected String getProviderId(HL7MessageMap map, int segIdx) {
/*HL7Converter.java
 *   protected void readRXA10Provider(String[] fields, VaccinationUpdateMessage vum, Vaccination vaccination, Map<Separator, Character> separators) {
    if (fields.length <= 10) {
	      return;
	    }
	    readXcn(fields[10], vaccination.getPersonThatVaccinated(), separators);
	    vum.addDebugOutput("RXA-10 person that vaccinated   " + vaccination.getPersonThatVaccinated());
	  }
	  
	    protected void readXcn(String field, Person person, Map<Separator, Character> separators) {
		    field = cleanRepeatsOnly(field, separators);
		    String[] comps = splitComponents(separators, field);
		    clean(comps, separators);
		    if (comps.length > 0)
		    {
		      person.setId(comps[0]);
		      if (comps.length > 1) {
		        person.setNameLast(comps[1]);
		        if (comps.length > 2) {
		          person.setNameFirst(comps[2]);
		        }
		      }
		    }
		  }
		  
		  And from VaccinationUpdateToExtLoader.java
		  ext.setInitials(chop(vaccination.getPersonThatVaccinated().getId(), 3));
		  
		  So the ID is getting set from RXA-10-1, trimmed to 3
		  
		  that doesn't make sense.  most of those are numbers that are around four to six digits long. 
 */
		String providerId = map.getAtIndex("RXA-10-1", segIdx, 1);
		return chop(providerId, 3);
	}
	
	protected List<CodedEntity> getVaccineCodes(HL7MessageMap map, int segIdx) {
		List<CodedEntity> vaxList = new ArrayList<CodedEntity>();
		
		int fieldCount = map.getFieldRepCountFor("RXA-5", segIdx);
		
		for (int x = 1; x <= fieldCount; x++) {
			CodedEntity vxuCode = getCodedEntity(map, "RXA-5", segIdx, CodeTable.Type.VACCINATION_CVX_CODE, x);
			vaxList.add(vxuCode);
		}
		
		return vaxList;
	}
//
//	/**
//	 * The CVX is expected in the RXA-5-1 (code type specified in RXA-5-3)
//	 * <p>However, it may be coded as the alternative coding system if the provider sends an NDC code, or if they mixed up the CVX/CPT alternate/primary.
//	 * <p>This was previously done in HL7Converter.java in the method readRXA5Vaccination
//	 * @param map
//	 * @param segIdx
//	 * @return
//	 */
//	protected String getCVX(HL7MessageMap map, int segIdx) {
//		//CVX should be the primary identifier.   
//		int cvxRep = map.findFieldRepWithValue("CVX", "RXA-5-3", segIdx);
//		String cvxVal = null;
//		if (cvxRep > 0) {
//			cvxVal = map.getAtIndex("RXA-5-1", segIdx, cvxRep);
//		} else {
//			//If it's in the Alternate identifier spot, get it from there...  
//			//This isn't a good thing though.  Hopefully they just mixed CVX/CPT in the primary/alternate spots.
//			cvxRep = map.findFieldRepWithValue("CVX", "RXA-5-6", segIdx);
//			if (cvxRep > 0) {
//				cvxVal = map.getAtIndex("RXA-5-4", segIdx, cvxRep);
//			}
//		}
//		return cvxVal;
//	}
//	
//	/**
//	 * The CVX is expected in the RXA-5-1 (code type specified in RXA-5-3)
//	 * <p>However, it may be coded as the alternative coding system if the provider sends an NDC code, or if they mixed up the CVX/CPT alternate/primary.
//	 * <p>This was previously done in HL7Converter.java in the method readRXA5Vaccination
//	 * @param map
//	 * @param segIdx
//	 * @return
//	 */
//	protected String getNDC(HL7MessageMap map, int segIdx) {
//		//CVX should be the primary identifier.   
//		int ndcRep = map.findFieldRepWithValue("NDC", "RXA-5-3", segIdx);
//		String ndcVal = null;
//		if (ndcRep > 0) {
//			ndcVal = map.getAtIndex("RXA-5-1", segIdx, ndcRep);
//		} else {
//			//If it's in the Alternate identifier spot, get it from there...  
//			//This isn't a good thing though.  Hopefully they just mixed CVX/CPT in the primary/alternate spots.
//			ndcRep = map.findFieldRepWithValue("NDC", "RXA-5-6", segIdx);
//			if (ndcRep > 0) {
//				ndcVal = map.getAtIndex("RXA-5-4", segIdx, ndcRep);
//			}
//		}
//		return ndcVal;
//	}
//	
//	
//	/**
//	 * The CPT may be provided as an alternate identifier in RXA-5. 
//	 * We allow for them to put it in the primary slot, but that is not a well formed message. 
//	 * @param map
//	 * @param segIdx
//	 * @return
//	 */
//	protected String getCPT(HL7MessageMap map, int segIdx) {
//		int cptRep = map.findFieldRepWithValue("CPT", "RXA-5-6", segIdx);
//		String cptVal = null;
//		
//		if (cptRep > 0) {
//			cptVal = map.getAtIndex("RXA-5-4", segIdx, cptRep);
//		} else {
//			//If it's in the Primary identifier spot, get it from there...  
//			//This isn't a good thing though.  Hopefully they just mixed CVX/CPT in the primary/alternate spots.
//			cptRep = map.findFieldRepWithValue("CPT", "RXA-5-3", segIdx);
//			if (cptRep > 0) {
//				cptVal = map.getAtIndex("RXA-5-1", segIdx, cptRep);
//			}
//		}
//		return cptVal;
//	}
	
//	/**
//	 * This is takedn from HL7Converter.java in the method "guessAndSetVaccinationCode". 
//	 * <p>It's basically checking the values in RXA-5-1 and RXA-5-4, and deciding if it's 
//	 * a CPT code by whether or not it has five digits and starts with 90. 
//	 * <p>If the value does look like a CPT, it should be taken as one. 
//	 * <p>This is slightly different than the HL7Converter.java code, because in that case, it only called this
//	 * if neither a CPT or a CVX was found. 
//	 * @param map
//	 * @param segIdx
//	 */
//	protected String guessCptFromRxa5(HL7MessageMap map, int segIdx) {
//		String rxa5primary 		= map.get("RXA-5-1", segIdx, 1);
//		
//		if (looksLikeACptCode(rxa5primary)) {
//			return rxa5primary;
//		} else {
//			String rxa5alternate 	= map.get("RXA-5-4", segIdx, 1);
//			if (looksLikeACptCode(rxa5alternate)) {
//				return rxa5alternate;
//			}
//		}
//		
//		return null;
//	}
	
	/**
	 * This is takedn from HL7Converter.java in the method "guessAndSetVaccinationCode". 
	 * <p>It's basically checking the values in RXA-5-1 and RXA-5-4, and deciding if it's 
	 * a CPT code by whether or not it has five digits and starts with 90. 
	 * <p>If the value does look like a CPT, it's ignored for this method. We want stuff that doesn't look like a CPT code. 
	 * <p>This is the opposite of the guessCpt method.  
	 * <p>This is slightly different than the HL7Converter.java code, because in that case, it only called this
	 * if neither a CPT or a CVX was found. 
	 * @param map
	 * @param segIdx
	 */
	//TODO:  Now we can just use the code type.  Don't guess anymore.  it should say if it's a CVX/NDC/CPT
//	protected String guessCvxFromRxa5(HL7MessageMap map, int segIdx) {
//		String rxa5primary 		= map.get("RXA-5-1", segIdx, 1);
//		
//		if (!looksLikeACptCode(rxa5primary)) {
//			return rxa5primary;
//		} else {
//			String rxa5alternate 	= map.get("RXA-5-4", segIdx, 1);
//			if (!looksLikeACptCode(rxa5alternate)) {
//				return rxa5alternate;
//			}
//		}
//		
//		return null;
//	}
//	
//	protected boolean looksLikeACptCode(String string) {
//		return string != null && string.length() == 5 && string.startsWith("90");
//	}
	

	/**
	 * This is very simple: 
	 * <p>vaccination.setManufacturerCode(cleanAndReadField(fields[17], separators));
	 * <p>This means we were getting it from RXA-17-1, and ignoring the rest. 
	 * @param map
	 * @param segIdx
	 * @return
	 */
	protected String getVaccineMfrCode(HL7MessageMap map, int segIdx) {
		String mfrCode = map.getAtIndex("RXA-17", segIdx, 1);
		//Should we check to make sure the coding system is specified as "MVX"?
		return mfrCode;
	}
	
	/**
	 * This method finds an OBX that is tagged as an observation of immunity.  
	 * <p>Then it gets the value from that OBX and returns it. 
	 * <p>if it doesn't find an OBX with one of the immunity codes, it returns null;
	 * @param map
	 * @param obxIdxList
	 * @return String
	 */
	private String getImmunityObservationCode(HL7MessageMap map,
			List<Integer> obxIdxList) {
		String immunityCd = null;
		
		int immunityObsSeg = getImmunityObxSegmentIndex(map, obxIdxList);
		
		if (immunityObsSeg > 0) {
			immunityCd = map.getAtIndex("OBX-5", immunityObsSeg, 1);
		} 
		
		return immunityCd;
	}
	
	/**
	 * Getting the observation segments for this rxa...
	 */
	private List<Observation> getObservations(HL7MessageMap map, List<Integer> obxIdxList) {
		List<Observation> list = new ArrayList<Observation>();
		for (Integer i : obxIdxList) {
			list.add(getObservation(map, i));
		}
		return list;
	}
	
	/**
	 *
	 *  The previous version of this: 
		<code>private void populateOBX(MessageReceived message)
			  {
			    Observation obs = new Observation();
			    vaccination.getObservations().add(obs);
			    readCodeEntity(2, obs.getValueType());
			    readCodeEntity(3, obs.getObservationIdentifier());
			    obs.setObservationSubId(getValue(4));
			    obs.setObservationValue(getValue(5));
			  }
		</code>
	 * @param map
	 * @param idx
	 * @return
	 */
	private Observation getObservation(HL7MessageMap map, Integer idx) {
		Observation o = new Observation();
		
		String valueTypeCode = map.getAtIndex("OBX-2", idx, 1);
		o.setValueTypeCode(valueTypeCode);
		
		String identifier = map.getAtIndex("OBX-3", idx, 1);
		o.setObservationIdentifierCode(identifier);
		
		String subId = map.getAtIndex("OBX-4", idx, 1);
		o.setObservationSubId(subId);
		
		String observationValue = map.getAtIndex("OBX-5", idx, 1);
		o.setObservationValue(observationValue);
		
		String obsrvtnDate = map.getAtIndex("OBX-14", idx, 1);
		o.setObservationValueDateString(obsrvtnDate);

		return o;
	}
	
	/**
	 * THe code in this method is derived from several other methods and classes: 
	 * <br /><br />
	 * <code>
	 * protected void readOBX(String[] fields, VaccinationUpdateMessage vum, Observation observation, Map<Separator, Character> separators) {
		    vum.addDebugOutput("[OBX] Observation Segment");
		    if (fields.length <= 3) {
		      return;
		    }
		    observation.setIdentifierCode(cleanAndReadField(fields[3], separators));
		    vum.addDebugOutput(" + OBX-3 observation identifier " + observation.getIdentifierCode());
		    if (fields.length <= 5) {
		      return;
		    }
		    observation.setValue(cleanAndReadField(fields[5], separators));
		    vum.addDebugOutput(" + OBX-5 observation value      " + observation.getValue());
		  }
  
		String obx3Code = vaccination.getObservation().getIdentifierCode();
	    if (OBX3_DISEASE_WITH_PRESUMED_IMMUNITY.equals(obx3Code) || OBX3_DISEASE_WITH_SEROLOGICAL_EVIDENCE_OF_IMMUNITY.equals(obx3Code) ) {
	    
		    String obx5Code = vaccination.getObservation().getValue();
		    ext.setHl7ImmunityCd(chop(obx5Code, 50));
		    //Assuming if an OBX-3 indicates documented immunity, the RXA-18 refusal reason won't be filled out, so we'll hard code it here.
		    ext.setNonAdmCode(chop(Table.SUBSTANCE_REFUSAL.mapToMcirValue("42"), 2));
		   
		    if ("998".equals(vaccination.getVaccinationCode().getCvx())) {
		    	//998^No vaccine administered^CVX <- that's the CE that gets coded in RXA-5 for an evidence of immunity message.
		    	String vaccine4code = getVaccineCdForImmunity(obx5Code);
		    	if (vaccine4code != null) {
		    		ext.setVaccine4Code(vaccine4code);
		    	}
		    }
	    } 
	    
	    	static String getVaccineCdForImmunity(String immunityCd) {
				ConverterHelper helper = ConverterHelper.getInstance();
				ImmunityAntigen ia;
				try {
					ia = helper.getImmunityAntigenByImmunityCd(immunityCd, false);
					if (ia != null) {
						return ia.getVaccineCd();
					}
				} catch (MessageException | DAOSysException e) {
		
				}
				return null;
			}
	   </code>
	 * @param map
	 * @param relativeObxList
	 * @return
	 */
	protected int getImmunityObxSegmentIndex(HL7MessageMap map, List<Integer> relativeObxList) {
		
		//TODO: check RXA-20 for "NA" non-administered. 
		
		
		//assumes the Obx list is ordered...  
		int first = relativeObxList.get(0);
		int last = relativeObxList.get(relativeObxList.size() - 1);
		
		List<Integer> immunitySegIdxs = map.findAllSegmentRepsWithValuesWithinRange(new String[]{OBX3_DISEASE_WITH_PRESUMED_IMMUNITY, OBX3_DISEASE_WITH_SEROLOGICAL_EVIDENCE_OF_IMMUNITY},
				"OBX-3", 
				first, last, 1);
		
		if (immunitySegIdxs.size() > 0) {
			//Should only be one...  and only the first one is meaningful. 
			return immunitySegIdxs.get(0);
		}
		return -1;
	}
	
	protected String getAdministeredAmount(HL7MessageMap map, int segIdx) {
		/*  HL7Converter.java
		 *  protected void readRXA6AdministeredAmount(String[] fields, VaccinationUpdateMessage vum, Vaccination vaccination, Map<Separator, Character> separators) {
			    if (fields.length <= 6) {
			      return;
			    }
			    String amount = "";
			    amount = cleanAndReadField(fields[6], separators);
			    vum.addDebugOutput("RXA-6  amount                   " + amount);
			    if (fields.length > 7) {
			      amount = amount + cleanAndReadField(fields[7], separators);
			      vum.addDebugOutput("RXA-7  amount + units           " + amount);
			    }
			    vaccination.setAdministeredAmount(amount);
			  }
			  
		  	VaccinationUpdateToExtLoader.java
			  ext.setDoseVl(fixAdministeredAmount(vaccination.getAdministeredAmount()));
			  
			static String fixAdministeredAmount(String amount) {
			    if (!amount.equals("")) {
			      amount = amount.trim().toLowerCase();
			      if (amount.endsWith("ml") || amount.endsWith("cc")) {
			        amount = amount.substring(0, amount.length() - 2).trim();
			      }
			      try {
			        if (!amount.equals("")) {
			          amount = String.valueOf(Double.parseDouble(amount));
			        }
			      } catch (NumberFormatException nfe) {
			        amount = "";
			      }
			    }
			    return amount;
			  }
  
			And then in the Stored Procs: 
			
			common_validations.f_masked_val('DOSE_VL',ext_transfer_rec.dose_vl)
			
			But this validation should probably be done in a separate process... I think so. yes. 
		 */
		
//		So what happens in the old process is
//		get the amount and units, 
//		put them together, 
//		then get them, 
//		take them apart, 
//		validate that the amount is a number, 
//		and then use that value. 
		
		String amount = map.getAtIndex("RXA-6", segIdx, 1);
//		String units = map.get("RXA-7", segIdx, 1);
		try {
	        if (!StringUtils.isEmpty(amount)) {
	          amount = String.valueOf(Double.parseDouble(amount));
	        }
	    } catch (NumberFormatException nfe) {
	        amount = "";
	    }
		return amount;
	}
	
	
	
	protected String getVaccineDoseLot(HL7MessageMap map, int segIdx) {
		
		/*From HL7Converter.java
		  
		      protected void readRXA15LotNumber(String[] fields, VaccinationUpdateMessage vum, Vaccination vaccination, Map<Separator, Character> separators) {
			    if (fields.length <= 15) {
			      return;
			    }
			    vaccination.setLotNumber(cleanAndReadField(fields[15], separators));
			    vum.addDebugOutput(" + RXA-15 lot number            " + vaccination.getFacility());
			  }
			  
		   From VaccinationToExtLoader.java
		   		ext.setDoseLot(chop(vaccination.getLotNumber(), 20));
		 */
		String lot = map.getAtIndex("RXA-15", segIdx, 1);
		return chop(lot, 40);
	}
	
//TODO: make sure to trim everything.  go back to the VaccinationToExtLoader and make sure to trim the same way.  
	protected static String chop(String value, int length) {
		if (value == null) {
			return "";
		}
		if (value.length() <= length) {
			return value;
		}
		return value.substring(0, length);
	}
	
	protected String getRespPartySSN(HL7MessageMap map, int segIdx) {
/*HL7Converter.java code to get the SSN: 
 *   
 *   readNK133Id(fields, vum, nok, separators);
 *   readNK137Ssn(fields, vum, nok, separators);
    
    
 *  protected void readNK137Ssn(String[] fields, VaccinationUpdateMessage vum, NextOfKin nok, Map<Separator, Character> separators) {
    if (fields.length <= 37) {
      return;
    }
    if (nok.getSsn().equals("")) {
      nok.setSsn(cleanAndReadField(fields[37], separators));
      if (nok.getSsn().equals("")) {
        vum.addDebugOutput(" + NK1-37 ssn                   " + nok.getSsn());
      }
    }
  }

  protected void readNK133Id(String[] fields, VaccinationUpdateMessage vum, NextOfKin nok, Map<Separator, Character> separators) {
    if (fields.length <= 33) {
      return;
    }
    String[] comps;
    String[] repeats = splitRepeats(separators, fields[33]);
    for (int j = 0; j < repeats.length; j++) {
      comps = splitComponents(separators, repeats[j]);
      clean(comps, separators);
      if (read(5, comps).equals("SS")) {
        nok.setSsn(comps[0]);
        if (nok.getSsn().equals("")) {
          vum.addDebugOutput(" + NK1-33 ssn                   " + nok.getSsn());
        }
        break;
      }
    }
  }
 */
		String ssn = null;
		int fieldRep = map.findFieldRepWithValue("SS", "NK1-33-5", segIdx);
		if (fieldRep > 0) {
			ssn = map.getAtIndex("NK1-33-1", segIdx, fieldRep);
		}
		
		if (StringUtils.isEmpty(ssn)) {
			ssn = map.getAtIndex("NK1-37", segIdx, 1);
		}
		
		return ssn;
	}
	
	protected String  getRespPartyFirstName(HL7MessageMap map, int segIdx) {
		String first = map.getAtIndex("NK1-2-2", segIdx, 1);
		return first;
	}
	protected String  getRespPartyLastName(HL7MessageMap map, int segIdx) {
		String last = map.getAtIndex("NK1-2-1", segIdx, 1);
		return last;		
	}
	protected String  getRespPartyMiddleName(HL7MessageMap map, int segIdx) {
		String middle = map.getAtIndex("NK1-2-3", segIdx, 1);
		return middle;
	}
	protected String  getRespPartySuffixName(HL7MessageMap map, int segIdx) {
		String suffix = map.getAtIndex("NK1-2-4", segIdx, 1);
		return suffix;
	}
	
	protected List<PatientAddress> getPID_11PatientAddressList(HL7MessageMap map) {
		List<PatientAddress> list = new ArrayList<PatientAddress>();
		
		int fieldReps = map.getFieldRepCountFor("PID-11");
		
		for (int x = 1 ; x <= fieldReps; x++) {
			list.add(getPatientAddress(map, x));
		}
		return list;
	}
	
	protected PatientAddress getPatientAddress(HL7MessageMap map, int fieldRep) {
		
		Address a = getAddressFromOrdinal(map, "PID-11", 1, fieldRep);
		PatientAddress address = new PatientAddress(a);
		
//		address.setStreet(a.getStreet());
//        address.setStreet2(a.getStreet2());
//        address.setCity(a.getCity());
//        address.setStateCode(a.getStateCode());
//        address.setZip(a.getZip());
//        address.setCountryCode(a.getCountryCode());
//        address.setTypeCode(a.getTypeCode());
//        address.setCountyParishCode(a.getCountyParishCode());
        
		return address;
	}
	
	protected Address getAddressFromIndex(HL7MessageMap map, String locator, int segmentIndex, int fieldRep) {
		Address address = new Address();
        address.setStreet(map.getAtIndex(locator+ "-1", segmentIndex, fieldRep));
        address.setStreet2(map.getAtIndex(locator+ "-2", segmentIndex, fieldRep));
        address.setCity(map.getAtIndex(locator+ "-3", segmentIndex, fieldRep));
        address.setStateCode(map.getAtIndex(locator+ "-4", segmentIndex, fieldRep));
        address.setZip(map.getAtIndex(locator+ "-5", segmentIndex, fieldRep));
        address.setCountryCode(map.getAtIndex(locator+ "-6", segmentIndex, fieldRep));
        address.setTypeCode(map.getAtIndex(locator+ "-7", segmentIndex, fieldRep));
        address.setCountyParishCode(map.getAtIndex(locator+ "-8", segmentIndex, fieldRep));
        return address;
      }
	
	protected Address getAddressFromOrdinal(HL7MessageMap map, String locator, int ordinal, int fieldrep) {
		int index = map.getAbsoluteIndexForLocation(locator, ordinal);
		return getAddressFromIndex(map, locator, index, fieldrep);
	}
	
//	protected String getPatientAddressStreet(HL7MessageMap map, int fieldRep) {
//		String street = map.get("PID-11-1-1" + "~" + String.valueOf(fieldRep));
//		return street;
//	}
//	
//	protected String getPatientAddressStreet2(HL7MessageMap map, int fieldRep) {
//		String street = map.get("PID-11-2-1" + "~" + String.valueOf(fieldRep));
//		return street;
//	}
//
//	
//	protected String getPatientAddressCity(HL7MessageMap map, int fieldRep) {
//		String city = map.get("PID-11-3" + "~" + String.valueOf(fieldRep));
//		return city;
//	}
//	
//	protected String getPatientAddressStateCd(HL7MessageMap map, int fieldRep) {
//		String state = map.get("PID-11-4" + "~" + String.valueOf(fieldRep));
//		return state;
//	}
//	
//	protected String getPatientAddressZip(HL7MessageMap map, int fieldRep) {
//		String zip = map.get("PID-11-5" + "~" + String.valueOf(fieldRep));
//		return zip;
//	}
//	
//
//	protected String getPatientAddressCountryCd(HL7MessageMap map, int fieldRep) {
//		String country = map.get("PID-11-6" + "~" + String.valueOf(fieldRep));
//		return country;
//	}
//	
//	protected String getPatientAddressType(HL7MessageMap map, int fieldRep) {
//		String city = map.get("PID-11-7" + "~" + String.valueOf(fieldRep));
//		return city;
//	}
//	
//	protected String getPatientCountyParish(HL7MessageMap map, int fieldRep) {
//		String city = map.get("PID-11-9" + "~" + String.valueOf(fieldRep));
//		return city;
//	}
//	
	

	
//	
//	
//	
//	protected String getNk1AddressCity(HL7MessageMap map, int segIdx) {
//		String city = map.getAtIndex("NK1-4-3", segIdx, 1);
//		return city;
//	}
//	
//	protected String getNk1AddressCountryCd(HL7MessageMap map, int segIdx) {
//		String country = map.getAtIndex("NK1-4-6", segIdx, 1);
//		return country;
//	}
//
//	protected String getNk1AddressStateCd(HL7MessageMap map, int segIdx) {
//		String state = map.getAtIndex("NK1-4-4", segIdx, 1);
//		return state;
//	}
//
//	protected String getNk1AddressStreet(HL7MessageMap map, int segIdx) {
//		String street = map.getAtIndex("NK1-4-1-1", segIdx, 1);
//		return street;
//	}
//
//	protected String getNk1AddressZip(HL7MessageMap map, int segIdx) {
//		String zip = map.getAtIndex("NK1-4-5", segIdx, 1);
//		return zip;
//	}

	/**
	 * <p>The MCIR implentation guide specifies that the NK1 phone can come from positions 1,6,7
	 * <p>It looks like position 1 is a complete phone number, and 6,7 would need to be used together to 
	 * form a complete phone number. 
	 *
	<li>NK1-5 - XTN_IZ - Phone Number (complex)
	<li>NK1-5-1 - ST - Phone Number:Telephone Number  
	<li>NK1-5-2 - ID - Phone Number:Telecommunication Use Code  
	<li>NK1-5-3 - ID - Phone Number:Telecommunication Equipment Type 
	<li>NK1-5-4 - ST - Phone Number:Email Address 
	<li>NK1-5-5 - NM - Phone Number:Country Code  
	<li>NK1-5-6 - NM - Phone Number:Area/City Code  
	<li>NK1-5-7 - NM - Phone Number:Local Number  
	<li>NK1-5-8 - NM - Phone Number:Extension 
	<li>NK1-5-9 - ST - Phone Number:Any Text  
	<li>NK1-5-10 - ST - Phone Number:Extension Prefix 
	<li>NK1-5-11 - ST - Phone Number:Speed Dial Code  
	<li>NK1-5-12 - ST - Phone Number:Unformatted Telephone number 

	 * @param map
	 * @param segIdx
	 * @return
	 */
	protected String getNK1Phone(HL7MessageMap map, int segIdx) {
		/*HL7Converter.java for getting NK1 phone. 
		 *  protected void readNK15Phone(String[] fields, VaccinationUpdateMessage vum, NextOfKin nok, Map<Separator, Character> separators) {
			    if (fields.length <= 5) {
			      return;
			    }
			    nok.setPhone(cleanAndReadField(fields[5], separators));
			    vum.addDebugOutput(" + NK1-5  phone                 " + nok.getPhone());
			  }
		 */
		
		//NOTE:  I upgraded this code so that we can take the NK extra phone fields. 
		//Hopefully this doesn't cause problems.  If it does, just cut it out. 
		
		String phone = null;
		String localNumber = map.getAtIndex("NK1-5-7", segIdx, 1);
		
		if (localNumber != null) {
			String areaCode = map.getAtIndex("NK1-5-6", segIdx, 1);
			phone = "(" + areaCode + ")" + localNumber;
		} else {
			//This is what was originally happening. 
			phone = map.getAtIndex("NK1-5", segIdx, 1);
		}
		
		return phone;
	}
	
	protected String getPatientPhone(HL7MessageMap map) {
		/*HL7Converter for interpreting the phone in the PID segment
		 *   protected String readPhone(String field, Map<Separator, Character> separators) {
			    String phone = "";
			    field = cleanRepeatsOnly(field, separators);
			    String[] comps = splitComponents(separators, field);
			    clean(comps, separators);
			    if (comps.length > 7 && comps[6].length() > 0) {
			      phone = "(" + comps[5] + ")" + comps[6];
			    } else {
			      phone = comps.length == 0 ? "" : comps[0];
			    }
			    return phone;
			  }
		 */
		//So.. if it has 6 and 7, use that.  otherwise, check the first ones.
		 
		String phone = null;
		String localNumber = map.get("PID-13-7");
		if (localNumber != null) {
			String areaCode = map.get("PID-13-6");
			phone = "(" + areaCode + ")" + localNumber;
		} else {
			phone = map.get("PID-13-1");
		}
		//Incidentally, most of the messages are sending the number in the first position.
		return phone;
	}
	
	protected int getRespPartyNK1Index(HL7MessageMap map) {
		/*VaccinationUpdatetoEXtLoader.java - check out how they're deciding which NK1 to use.  That's what we're doing here. 
		  protected static NextOfKin findResponsibleParty(VaccinationUpdateMessage message, Patient patient) {
			    NextOfKin responsibleParty = null;
			    for (NextOfKin nok : message.getNextOfKins()) {
			      if (nok.getRelationship().equals("") || nok.isRelationshipFather() || nok.isRelationshipGuardian() || nok.isRelationshipParent() || nok.isRelationshipMother()
			          || (nok.isRelationshipSelf() && patient.isChild())) {
			        responsibleParty = nok;
			        break;
			      }
			    }
			    return responsibleParty;
			  }
  */
		
		
		//Find the first responsible party that's of relationship type:
		// father, guardian, parent, mother
		// So step 1... find the first one of those things in the NK1 list...
		List<Integer> respParties = map.findAllSegmentRepsWithValues(new String[] {"MTH","FTH","PAR","GRD"},"NK1-3", 1);

		if (respParties == null || respParties.size() < 1) {
			return map.findSegmentRepWithValue("SEL", "NK1-3", 1); 
		}
		//Return the first match.  In the old code there was no order with which it was determined. 
		return respParties.get(0);
	}
	
	protected String getNk1_3RelationshipCode(HL7MessageMap map, int index) {
		return map.getAtIndex("NK1-3", index);
	}
	
	protected String getNk1_20PrimaryLangCode(HL7MessageMap map, int index) {
		return map.getAtIndex("NK1-20", index);
	}
	
	protected boolean isChild(HL7MessageMap map) {
		//this was hard coded to true...  with a note to change it.  check out VaccinationUpdateToExtLoader.findAddress and Patient.isChild
		//TODO: check the age.  Greater than 17 = not child...
		return true;
	}
	
	protected String getNK1SSn(HL7MessageMap map, int segIdx) {
		String ssn = map.getAtIndex("NK1-37", segIdx, 1);
		return ssn;
	}
	
	protected String getNK1LastNm(HL7MessageMap map, int segIdx) {
		String last = map.getAtIndex("NK1-2-1-1", segIdx, 1);
		return last;
	}
	
	protected String getNK1FirstNm(HL7MessageMap map, int segIdx) {
		String first = map.getAtIndex("NK1-2-2", segIdx, 1);
		return first;
	}
	
	protected PatientIdNumber getWCPatId(HL7MessageMap map) {
		return getPatientIdType("WC", map);
	}
	
	protected PatientIdNumber getMRPatId(HL7MessageMap map) {
		return getPatientIdType("MR", map);
	}
	
	protected PatientIdNumber getSSPatId(HL7MessageMap map) {
		return getPatientIdType("SS", map);
	}
	
	protected PatientIdNumber getMAPatId(HL7MessageMap map) {
		return getPatientIdType("MA", map);
	}
	
	protected PatientIdNumber getSRPatId(HL7MessageMap map) {
		return getPatientIdType("SR", map);
	}
	
	protected PatientIdNumber getPatientIdType(String type, HL7MessageMap map) {
		int i = map.findFieldRepWithValue(type, "PID-3-5", 1);
		
		if (i > 0) {
			Id id = getId(map, "PID-3-1", 1, Type.PATIENT_ID, i);
			
			PatientIdNumber num = new PatientIdNumber(id, i);
			
			return num;
		}
		
		return new PatientIdNumber();
	}
	
//	protected String getLegalLastNm(HL7MessageMap map) {
//		String name = map.get("PID-5-1");
//		return name;
//	}
//	
//	protected String getLegalFirstNm(HL7MessageMap map) {
//		String name = map.get("PID-5-2");
//		return name;
//	}
//	
//	protected String getLegalMiddleNm(HL7MessageMap map) {
//		String name = map.get("PID-5-3");
//		return name;
//	}
//	
//	protected String getLegalSuffixNm(HL7MessageMap map) {
//		String name = map.get("PID-5-4");
//		return name;
//	}
//	
//	protected String getMomsMaidenNm(HL7MessageMap map) {
//		return map.get("PID-6");
//	}
//	
//	protected String getSexCd(HL7MessageMap map) {
//		return map.get("PID-8");
//	}
//	
//	protected CodedEntity getPID10Race(HL7MessageMap map) {
//		return getCodedEntity(map, "PID-10", Type.PATIENT_RACE);
//	}
//	
//	protected String getPID11BirthCntyCd(HL7MessageMap map) {
//		int i = map.findFieldRepWithValue("BDL", "PID-11-7", 1);
//		LOGGER.info("Found BDL in PID-11-7 in field rep: " + i);
//		if (i > 0) {
//			return map.getAtIndex("PID-11-9", 1, i) ;
//		}
//		
//		return null;
//	}
//	
//	protected String getCntyCd(HL7MessageMap map) {
//		return map.get("PID-11-9");
//	}

	protected String trimStringToLength(String s, int length) {
		if (s != null && s.length() > length) {
			return s.substring(0, length);
		}
		return s;
	}
	
	protected String getPID7BirthDt(HL7MessageMap map) {
		String birthDt = map.get("PID-7");
		return trimStringToLength(birthDt, 8);
	}

	protected CodedEntity getPID15PrimaryLanguage(HL7MessageMap map) {
		return getCodedEntity(map, "PID-15", Type.PERSON_LANGUAGE);
	}
	
	protected CodedEntity getPID22EthnicCd(HL7MessageMap map) {
		String ethnicCdField ="PID-22";
		CodeTable.Type type = CodeTable.Type.PATIENT_ETHNICITY;
		return getCodedEntity(map, ethnicCdField, type);
	}
	
	protected String getPID24BirthMultiple(HL7MessageMap map) {
		String value = map.get("PID-24");
		return value;
	}
	
	protected String getPID23BirthHostNm(HL7MessageMap map) {
		String birthHospNm = map.get("PID-23");
		return birthHospNm;
	}

	protected String getPID25BirthOrder(HL7MessageMap map) {
		String value = map.get("PID-25");
		return value;
	}
	
	protected String getPID29DeathDt(HL7MessageMap map) {
		String deathDt =  map.get("PID-29");
		return trimStringToLength(deathDt, 8);
	}
	
	protected String getPID30DeathInd(HL7MessageMap map) {
		String deathDt =  map.get("PID-30");
		return trimStringToLength(deathDt, 8);
	}
	
	/**
	 * The second part of this is certainly a transform step...
	 * @param map
	 * @return
	 */
	protected String getVaccineAction(HL7MessageMap map) {
		String actionCode = map.get("RXA-21");
	    if (StringUtils.isEmpty(actionCode)) {
	    	actionCode = VACCINATION_ACTION_ADD;
		} 
		return actionCode;
	}

	 protected CodedEntity getCodedEntity(HL7MessageMap map, String field, CodeTable.Type type) {
		 //I'm not sure this works right...  need to check. With a unit test.   Please.  Now.  
		 return getCodedEntity(map, field, 1, type, 1);
	  }
	 
	  protected CodedEntity getCodedEntity(HL7MessageMap map, String field, int segIdx, CodeTable.Type type) {
		  return getCodedEntity(map, field, segIdx, type, 1);
	  }
	  
	  protected CodedEntity getCodedEntity(HL7MessageMap map, String field, int segIdx, CodeTable.Type type, int fieldRep)
	  {
		CodedEntity ce = new CodedEntity(type);
	    ce.setCode(map.getAtIndex(field + "-1", segIdx, fieldRep));
	    ce.setText(map.getAtIndex(field + "-2", segIdx, fieldRep));
	    ce.setTable(map.getAtIndex(field + "-3", segIdx, fieldRep));
	    ce.setAltCode(map.getAtIndex(field + "-4", segIdx, fieldRep));
	    ce.setAltText(map.getAtIndex(field + "-5", segIdx, fieldRep));
	    ce.setAltTable(map.getAtIndex(field + "-6", segIdx, fieldRep));
	    return ce;
	  }
	  
	  
	  protected Id getId(HL7MessageMap map, String field, int segIdx, CodeTable.Type type, int fieldRep)
	  {
		Id ce = new Id(type);
	    ce.setNumber(map.getAtIndex(field + "-1", segIdx, fieldRep));
	    ce.setAssigningAuthorityCode(map.getAtIndex(field + "-4", segIdx, fieldRep));
	    ce.setTypeCode(map.getAtIndex(field + "-5", segIdx, fieldRep));
	    return ce;
	  }
	  
	  
	protected String[] splitPhoneParts(String value) {
		//this is taken from VaccinationUpdateToExtLoader.java method called "breakPhoneApart"
			    String[] values = new String[2];
			    String phone = "";
			    char[] digits = value.toCharArray();
			    for (char c : digits) {
			      if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
			        break;
			      }
			      if (c >= '0' && c <= '9') {
			        phone += c;
			      }
			    }
			    if (phone.length() <= 7) {
			      values[0] = "";
			      values[1] = phone;
			    } else {
			      if (phone.length() == 11 && phone.startsWith("1")) {
			        // remove initial 1, it is not needed
			        phone = phone.substring(1);
			      }
			      values[0] = phone.substring(0, phone.length() - 7);
			      values[1] = phone.substring(phone.length() - 7);
			    }
			    return values;
	}
}
