///*
// * Copyright 2013 by Dandelion Software & Research, Inc (DSR)
// * 
// * This application was written for immunization information system (IIS) community and has
// * been released by DSR under an Apache 2 License with the hope that this software will be used
// * to improve Public Health.  
// */
//package com.openimmunizationsoftware.dqa.parser;
//
//import static com.openimmunizationsoftware.dqa.parser.HL7Util.ACK_ACCEPT;
//import static com.openimmunizationsoftware.dqa.parser.HL7Util.ACK_ERROR;
//import static com.openimmunizationsoftware.dqa.parser.HL7Util.ACK_REJECT;
//import static com.openimmunizationsoftware.dqa.parser.HL7Util.AMP;
//import static com.openimmunizationsoftware.dqa.parser.HL7Util.BAR;
//import static com.openimmunizationsoftware.dqa.parser.HL7Util.CAR;
//import static com.openimmunizationsoftware.dqa.parser.HL7Util.SLA;
//import static com.openimmunizationsoftware.dqa.parser.HL7Util.TIL;
//import static com.openimmunizationsoftware.dqa.parser.HL7Util.getNextAckCount;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import org.springframework.boot.autoconfigure.web.ServerProperties.Session;
//
//import com.openimmunizationsoftware.dqa.model.MessageHeader;
//import com.openimmunizationsoftware.dqa.model.MessageReceived;
//import com.openimmunizationsoftware.dqa.model.MessageReceivedGeneric;
//import com.openimmunizationsoftware.dqa.model.NextOfKin;
//import com.openimmunizationsoftware.dqa.model.Observation;
//import com.openimmunizationsoftware.dqa.model.Patient;
//import com.openimmunizationsoftware.dqa.model.SubmitterProfile;
//import com.openimmunizationsoftware.dqa.model.Vaccination;
//import com.openimmunizationsoftware.dqa.model.types.Address;
//import com.openimmunizationsoftware.dqa.model.types.CodedEntity;
//import com.openimmunizationsoftware.dqa.model.types.Id;
//import com.openimmunizationsoftware.dqa.model.types.Name;
//import com.openimmunizationsoftware.dqa.model.types.OrganizationName;
//import com.openimmunizationsoftware.dqa.model.types.PatientAddress;
//import com.openimmunizationsoftware.dqa.model.types.PatientIdNumber;
//import com.openimmunizationsoftware.dqa.model.types.PatientPhone;
//import com.openimmunizationsoftware.dqa.model.types.PhoneNumber;
//import com.openimmunizationsoftware.dqa.validator.issues.IssueAction;
//import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
//import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;
//
//public class VaccinationParserHL7UsingMyParser extends VaccinationParser
//{
//
//	  public static final String PROCESSING_ID_DEBUG = "D";
//
////	  private List<IssueFound> parsingIssues = new ArrayList<IssueFound>();
//	  private char[] separators = new char[5];
//
//	  public VaccinationParserHL7UsingMyParser(SubmitterProfile profile) {
//	    super(profile);
//	  }
//
//	  private int startField = 0;
//	  private int currentSegmentPos = -1;
//	  private String segmentName = "";
//	  private List<List<String>> segments;
//	  private List<String> currentSegment;
//	  private int vaccinationCount = 0;
//	  private int nextOfKinCount = 0;
//	  private Session session = null;
//
//	  private void setup()
//	  {
//	    startField = 0;
//	    currentSegmentPos = -1;
//	    segmentName = "";
//	    segments = new ArrayList<List<String>>();
//	    currentSegment = new ArrayList<String>();
//
//	    patient = null;
//	    vaccination = null;
//	    nextOfKin = null;
//
//	    vaccinationCount = 0;
//	    nextOfKinCount = 0;
//
//	  }
//
//	  private static final String[] RECOGNIZED_SEGMENTS = { "MSH", "SFT", "PID", "PD1", "NK1", "PV1", "PV2", "GT1", "IN1", "IN3", "IN4", "PD2", "ORC",
//	      "TQ1", "TQ2", "RXA", "RXR", "OBX", "NTE", "BTS", "FTS", "FHS", "BHS" };
//
//	  @Override
//	  public void createVaccinationUpdateMessage(MessageReceived messageReceived)
//	  {
//	    String messageText = messageReceived.getRequestText();
//	    message = messageReceived;
//	    issuesFound = message.getIssuesFound();
//	    setup();
//	    boolean okayToParse = readSeparators(messageText);
//	    if (!okayToParse)
//	    {
//	      return;
//	    }
//	    readFields(messageText);
//
//	    patient = message.getPatient();
//	    currentSegment = segments.get(0);
//	    boolean foundPID = false;
//	    boolean foundPV1 = false;
//	    boolean foundOBX = false;
//	    boolean foundRXA = false;
//	    boolean foundORC = false;
//	    boolean foundNK1 = false;
//	    boolean foundRXR = false;
//	    boolean previouslyStartedAdminSegments = false;
//	    populateMSH(message);
//	    while (moveNext())
//	    {
//	      if (segmentName.equals("PID"))
//	      {
//	        if (previouslyStartedAdminSegments)
//	        {
//	          registerIssue(PotentialIssue.Hl7SegmentsOutOfOrder);
//	        }
//	        if (foundPID)
//	        {
//	          registerIssue(PotentialIssue.Hl7PidSegmentIsRepeated);
//	        } else
//	        {
//	          foundPID = true;
//	          populatePID(message);
//	        }
//	      } else if (segmentName.equals("PV1"))
//	      {
//	        if (previouslyStartedAdminSegments)
//	        {
//	          registerIssue(PotentialIssue.Hl7SegmentsOutOfOrder);
//	        }
//	        if (foundPV1)
//	        {
//	          registerIssue(PotentialIssue.Hl7Pv1SegmentIsRepeated);
//	        } else
//	        {
//	          foundPID = assertPIDFound(foundPID);
//	          populatePV1(message);
//	          foundPV1 = true;
//	        }
//	      } else if (segmentName.equals("PD1"))
//	      {
//	        if (previouslyStartedAdminSegments)
//	        {
//	          registerIssue(PotentialIssue.Hl7SegmentsOutOfOrder);
//	        }
//	        foundPID = assertPIDFound(foundPID);
//	        populatePD1(message);
//	      } else if (segmentName.equals("NK1"))
//	      {
//
//	        foundNK1 = true;
//	        if (foundPV1 || previouslyStartedAdminSegments)
//	        {
//	          registerIssue(PotentialIssue.Hl7SegmentsOutOfOrder);
//	        }
//	        foundPID = assertPIDFound(foundPID);
//	        nextOfKinCount++;
//	        positionId = nextOfKinCount;
//	        nextOfKin = new NextOfKin();
//	        skippableItem = nextOfKin;
//	        nextOfKin.setPositionId(nextOfKinCount);
//	        message.getNextOfKins().add(nextOfKin);
//	        nextOfKin.setReceivedId(message.getNextOfKins().size());
//	        populateNK1(message);
//	      } else if (segmentName.equals("ORC") || segmentName.equals("RXA"))
//	      {
//	        if (previouslyStartedAdminSegments)
//	        {
//	          if (!foundOBX)
//	          {
//	            registerIssue(PotentialIssue.Hl7ObxSegmentIsMissing);
//	          }
//	          if (!foundRXR)
//	          {
//	            registerIssue(PotentialIssue.Hl7RxrSegmentIsMissing);
//	          }
//	        }
//	        previouslyStartedAdminSegments = true;
//	        foundORC = false;
//	        foundRXA = false;
//	        foundRXR = false;
//	        foundOBX = false;
//
//	        vaccinationCount++;
//	        positionId = vaccinationCount;
//	        vaccination = new Vaccination();
//	        skippableItem = vaccination;
//	        vaccination.setPositionId(vaccinationCount);
//	        message.getVaccinations().add(vaccination);
//	        if (segmentName.equals("ORC"))
//	        {
//	          foundORC = true;
//	          populateORC(messageReceived);
//	          boolean moved = false;
//	          if (!(moved = moveNext()) || !segmentName.equals("RXA"))
//	          {
//	            if (moved && segmentName.equals("ORC"))
//	            {
//	              registerIssue(PotentialIssue.Hl7OrcSegmentIsRepeated);
//	            }
//	            registerIssue(PotentialIssue.Hl7RxaSegmentIsMissing);
//	            moveBack();
//	            continue;
//	          }
//	        } else
//	        {
//	          foundRXA = true;
//	          if (!message.getMessageHeader().getMessageVersion().startsWith("2.3") && !message.getMessageHeader().getMessageVersion().startsWith("2.4"))
//	          {
//	            registerIssue(PotentialIssue.Hl7OrcSegmentIsMissing);
//	          }
//	        }
//	        populateRXA(message);
//	      } else if (segmentName.equals("RXR"))
//	      {
//	        if (vaccination == null)
//	        {
//	          registerIssue(PotentialIssue.Hl7RxaSegmentIsMissing);
//	          continue;
//	        }
//	        if (foundRXR)
//	        {
//	          registerIssue(PotentialIssue.Hl7RxrSegmentIsRepeated);
//	        }
//	        foundRXR = true;
//	        populateRXR(message);
//	      } else if (segmentName.equals("OBX"))
//	      {
//	        if (vaccination == null)
//	        {
//	          continue;
//	        }
//	        populateOBX(message);
//	        foundOBX = true;
//	      } else
//	      {
//	        if (segmentName.length() > 0 && segmentName.charAt(0) > ' ')
//	        {
//	          boolean recognized = false;
//	          for (String recognizedSegment : RECOGNIZED_SEGMENTS)
//	          {
//	            if (recognizedSegment.equals(segmentName))
//	            {
//	              recognized = true;
//	              continue;
//	            }
//	          }
//	          if (!recognized)
//	          {
//	            registerIssue(PotentialIssue.Hl7SegmentIsUnrecognized);
//	          }
//	        }
//	      }
//	    }
//	    positionId = 0;
//	    assertPIDFound(foundPID);
//	    if (!foundPV1)
//	    {
//	      registerIssue(PotentialIssue.Hl7Pv1SegmentIsMissing);
//	    }
//	    if (!foundNK1)
//	    {
//	      registerIssue(PotentialIssue.Hl7Nk1SegmentIsMissing);
//	    }
//	    if (!foundPV1)
//	    {
//	      registerIssue(PotentialIssue.Hl7Pv1SegmentIsMissing);
//	      foundPV1 = true;
//	    }
//	    if (previouslyStartedAdminSegments)
//	    {
//	      if (!foundRXR)
//	      {
//	        registerIssue(PotentialIssue.Hl7RxrSegmentIsMissing);
//	      }
//	      if (!foundOBX)
//	      {
//	        registerIssue(PotentialIssue.Hl7ObxSegmentIsMissing);
//	      }
//	    }
//
//	  }
//
//	  private boolean assertPIDFound(boolean foundPID)
//	  {
//	    if (!foundPID)
//	    {
//	      registerIssue(PotentialIssue.Hl7PidSegmentIsMissing);
//	      foundPID = true;
//	    }
//	    return foundPID;
//	  }
//
//	  private boolean moveNext()
//	  {
//	    currentSegmentPos++;
//	    while (currentSegmentPos < segments.size())
//	    {
//	      currentSegment = segments.get(currentSegmentPos);
//	      if (currentSegment.size() > 0)
//	      {
//	        segmentName = currentSegment.get(0);
//	        return true;
//	      }
//	      currentSegmentPos++;
//	    }
//	    return false;
//	  }
//
//	  private void moveBack()
//	  {
//	    currentSegmentPos--;
//	    while (currentSegmentPos >= 0)
//	    {
//	      currentSegment = segments.get(currentSegmentPos);
//	      if (currentSegment.size() > 0)
//	      {
//	        segmentName = currentSegment.get(0);
//	        return;
//	      }
//	      currentSegmentPos--;
//	    }
//
//	  }
//
////	  private MessageHeader getMessageHeader()
//	  
//	  private void populateMSH(MessageReceivedGeneric message)
//	  {
//	    positionId = 1;
//	    MessageHeader header = message.getMessageHeader();
//	    header.setSendingApplication(getValue(3));
//	    header.setSendingFacility(getValue(4));
//	    header.setReceivingApplication(getValue(5));
//	    header.setReceivingFacility(getValue(6));
//	    header.setMessageDate(getValueDate(7, PotentialIssue.Hl7MshMessageDateIsInvalid, PotentialIssue.Hl7MshMessageDateIsMissingTimezone));
//	    String[] field = getValues(9);
//	    header.setMessageType(field.length >= 1 ? field[0] : "");
//	    header.setMessageTrigger(field.length >= 2 ? field[1] : "");
//	    header.setMessageStructure(field.length >= 3 ? field[2] : "");
//	    header.setMessageControl(getValue(10));
//	    header.setProcessingIdCode(getValue(11));
//	    header.setMessageVersion(getValue(12));
//	    header.setAckTypeAcceptCode(getValue(15));
//	    header.setAckTypeApplicationCode(getValue(16));
//	    header.setCountryCode(getValue(17));
//	    header.setCharacterSetCode(getValue(18));
//	    header.setCharacterSetAltCode(getValue(20));
//	    header.setMessageProfile(getValue(21));
//	  }
//
//	  private void populatePID(MessageReceived message)
//	  {
//	    positionId = 1;
//	    readAddress(11, patient);
//	    // private Name alias = new Name();
//	    patient.setBirthDateString(getValue(7));
//	    patient.setBirthDate(getValueDate(7, PotentialIssue.PatientBirthDateIsInvalid, null));
//	    patient.setBirthMultiple(getValue(24));
//	    patient.setBirthOrderCode(getValue(25));
//	    patient.setBirthPlace(getValue(23));
//	    patient.setDeathDate(getValueDate(29, PotentialIssue.PatientDeathDateIsInvalid, null));
//	    patient.setDeathIndicator(getValue(30));
//	    readCodeEntity(22, patient.getEthnicity());
//	    // TODO private OrganizationName facility = new OrganizationName();
//	    readPatientId(3, patient);
//	    patient.setMotherMaidenName(getValue(6));
//	    readName(5, patient.getName(), patient.getAlias());
//	    readPhoneNumber(13, patient);
//	    readCodeEntity(15, patient.getPrimaryLanguage());
//	    readCodeEntity(10, patient.getRace());
//	    patient.setSexCode(getValue(8));
//	  }
//
//	  private void populatePD1(MessageReceived message)
//	  {
//	    patient.setRegistryStatusCode(getValue(16));
//	    patient.setPublicityCode(getValue(11));
//	    patient.setProtectionCode(getValue(12));
//	    // TODO private OrganizationName facility = new OrganizationName();
//	    // TODO private Id idSubmitter = new Id();
//	    // TODO private Id physician = new Id();
//	  }
//
//	  private void populateNK1(MessageReceived message)
//	  {
//	    String setId = getValue(1);
//	    if (setId.equals(""))
//	    {
//	      registerIssue(PotentialIssue.Hl7Nk1SetIdIsMissing);
//	    }
//	    readAddress(4, nextOfKin.getAddress());
//	    readPhoneNumber(5, nextOfKin.getPhone());
//	    readCodeEntity(3, nextOfKin.getRelationship());
//	    readName(2, nextOfKin.getName());
//	  }
//
//	  private void populateRXA(MessageReceived message)
//	  {
//	    registerIssueIfEmpty(1, PotentialIssue.Hl7RxaGiveSubIdIsMissing);
//	    registerIssueIfEmpty(2, PotentialIssue.Hl7RxaAdminSubIdCounterIsMissing);
//	    
//	    vaccination.setAdminDateString(getValue(3));
//	    vaccination.setAdminDate(getValueDate(3, PotentialIssue.VaccinationAdminDateIsInvalid, null));
//	    vaccination.setAdminDateEnd(getValueDate(4, null, null));
//	    
//	    readCodeEntity(5, vaccination.getAdmin());
//	    CodedEntity admin = vaccination.getAdmin();
//	    readCptCvxCodes(admin);
//	    vaccination.setAmount(getValue(6));
//	    vaccination.setAmountUnitCode(getValue(7));
//	    readCodeEntity(9, vaccination.getInformationSource());
//	    // TODO 10 XCN private Id givenBy = new Id();
//	    readLocationWithAddress(11, vaccination.getFacility());
//	    vaccination.setLotNumber(getValue(15));
//	    vaccination.setExpirationDate(getValueDate(16, PotentialIssue.VaccinationLotExpirationDateIsInvalid, null));
//	    readCodeEntity(17, vaccination.getManufacturer());
//	    readCodeEntity(18, vaccination.getRefusal());
//	    vaccination.setCompletionCode(getValue(20));
//	    vaccination.setActionCode(getValue(21));
//	    vaccination.setSystemEntryDate(getValueDate(22, PotentialIssue.VaccinationSystemEntryTimeIsInvalid, null));
//	  }
//
//	  private void populateRXR(MessageReceived message)
//	  {
//	    readCodeEntity(1, vaccination.getBodyRoute());
//	    readCodeEntity(2, vaccination.getBodySite());
//	  }
//
//	  private void populateOBX(MessageReceived message)
//	  {
//	    Observation obs = new Observation();
//	    vaccination.getObservations().add(obs);
//	    readCodeEntity(2, obs.getValueType());
//	    readCodeEntity(3, obs.getObservationIdentifier());
//	    obs.setObservationSubId(getValue(4));
//	    obs.setObservationValue(getValue(5));
//	  }
//
//	  private void readCptCvxCodes(CodedEntity admin)
//	  {
//	    boolean codeFound = false;
//	    if (admin.getTable().equals("CVX") || admin.getTable().equals("HL70292"))
//	    {
//	      vaccination.getAdminCvx().setCode(admin.getCode());
//	      vaccination.getAdminCvx().setText(admin.getText());
//	      vaccination.getAdminCvx().setTable(admin.getTable());
//	      codeFound = true;
//	    } else if (admin.getAltTable().equals("CVX") || admin.getAltTable().equals("HL70292"))
//	    {
//	      vaccination.getAdminCvx().setCode(admin.getAltCode());
//	      vaccination.getAdminCvx().setText(admin.getAltText());
//	      vaccination.getAdminCvx().setTable(admin.getAltTable());
//	      codeFound = true;
//	    }
//	    if (admin.getTable().equals("CPT") || admin.getTable().equals("C4"))
//	    {
//	      vaccination.getAdminCpt().setCode(admin.getCode());
//	      vaccination.getAdminCpt().setText(admin.getText());
//	      vaccination.getAdminCpt().setTable(admin.getTable());
//	      codeFound = true;
//	    } else if (admin.getAltTable().equals("CPT") || admin.getAltTable().equals("C4"))
//	    {
//	      vaccination.getAdminCpt().setCode(admin.getAltCode());
//	      vaccination.getAdminCpt().setText(admin.getAltText());
//	      vaccination.getAdminCpt().setTable(admin.getAltTable());
//	      codeFound = true;
//	    }
//	    if (!codeFound)
//	    {
//	      if (admin.getCode().equals(""))
//	      {
//	        registerIssue(PotentialIssue.VaccinationAdminCodeTableIsMissing);
//	      } else
//	      {
//	        registerIssue(PotentialIssue.VaccinationAdminCodeTableIsInvalid);
//	      }
//	      String possible = admin.getCode();
//	      if (possible != null)
//	      {
//	        try
//	        {
//	          Integer.parseInt(possible);
//	          if (possible.length() >= 2 && possible.length() <= 3)
//	          {
//	            vaccination.getAdminCvx().setCode(admin.getCode());
//	            vaccination.getAdminCvx().setText(admin.getText());
//	            vaccination.getAdminCvx().setTable(admin.getTable());
//	          } else if (possible.length() == 5 && possible.startsWith("90"))
//	          {
//	            vaccination.getAdminCpt().setCode(admin.getCode());
//	            vaccination.getAdminCpt().setText(admin.getText());
//	            vaccination.getAdminCpt().setTable(admin.getTable());
//	          }
//	        } catch (NumberFormatException nfe)
//	        {
//	          // not CVX
//	        }
//	      }
//	    }
//
//	  }
//
//	  private void populateORC(MessageReceived message)
//	  {
//	    readCodeEntity(1, vaccination.getOrderControl());
//	    vaccination.setIdPlacer(getValue(2));
//	    vaccination.setIdSubmitter(getValue(3));
//	    readCodeEntity(28, vaccination.getConfidentiality());
//	  }
//
//	  private void populatePV1(MessageReceived message)
//	  {
//	    positionId = 1;
//	    patient.setPatientClassCode(getValue(2));
//	    String[] field = getValues(20);
//	    if (field.length > 0)
//	    {
//	      patient.setFinancialEligibilityCode(field[0]);
//	      if (field.length > 1)
//	      {
//	        patient.setFinancialEligibilityDate(createDate(PotentialIssue.PatientVfcEffectiveDateIsInvalid, null, field[1]));
//	      }
//	    }
//	  }
//
//	  private void readPhoneNumber(int fieldNumber, Patient patient)
//	  {
//	    List<PatientPhone> patientPhoneList = patient.getPatientPhoneList();
//	    List<String[]> fieldList = getRepeatValues(fieldNumber);
//	    int position = 0;
//	    PatientPhone phoneNumber = null;
//	    for (String[] field : fieldList)
//	    {
//	      position++;
//	      if (phoneNumber == null)
//	      {
//	        phoneNumber = patient.getPhone();
//	      } else
//	      {
//	        phoneNumber = new PatientPhone();
//	      }
//	      phoneNumber.setPatient(patient);
//	      phoneNumber.setPositionId(position);
//	      patientPhoneList.add(phoneNumber);
//	      if (field.length == 0)
//	      {
//	        continue;
//	      }
//	      readPhoneNumber(phoneNumber, field);
//	    }
//	  }
//
//	  private void readPhoneNumber(int fieldNumber, PhoneNumber phoneNumber)
//	  {
//	    String[] field = getValues(fieldNumber);
//	    if (field.length == 0)
//	    {
//	      return;
//	    }
//	    readPhoneNumber(phoneNumber, field);
//	  }
//
//	  public void readPhoneNumber(PhoneNumber phoneNumber, String[] field)
//	  {
//	    phoneNumber.setNumber(field.length >= 1 ? field[0] : "");
//	    if (field.length >= 2 && field[1].length() > 0)
//	    {
//	      phoneNumber.setTelUseCode(field[1]);
//	    }
//	    if (field.length >= 3 && field[2].length() > 0)
//	    {
//	      phoneNumber.setTelEquipCode(field[2]);
//	    }
//	    if (field.length >= 4 && field[3].length() > 0)
//	    {
//	      phoneNumber.setEmail(field[3]);
//	    }
//	    if (field.length >= 5 && field[4].length() > 0)
//	    {
//	      phoneNumber.setCountryCode(field[4]);
//	    }
//	    if (field.length >= 6 && field[5].length() > 0)
//	    {
//	      phoneNumber.setAreaCode(field[5]);
//	    }
//	    if (field.length >= 7 && field[6].length() > 0)
//	    {
//	      phoneNumber.setLocalNumber(field[6]);
//	    }
//	    if (field.length >= 8 && field[7].length() > 0)
//	    {
//	      phoneNumber.setExtension(field[7]);
//	    }
//	  }
//
//	  private void readAddress(int fieldNumber, Patient patient)
//	  {
//	    List<String[]> fieldList = getRepeatValues(fieldNumber);
//	    int positionId = 0;
//	    for (String[] field : fieldList)
//	    {
//	      positionId++;
//	      PatientAddress address = new PatientAddress();
//	      address.setPatient(patient);
//	      address.setPositionId(positionId);
//	      patient.getPatientAddressList().add(address);
//	      readAddress(field, address);
//	    }
//	  }
//
//	  private void readAddress(int fieldNumber, Address address)
//	  {
//	    readAddress(getValues(fieldNumber), address);
//	  }
//
//	  private void readAddress(String[] field, Address address)
//	  {
//	    address.setStreet(field.length >= 1 ? field[0] : "");
//	    address.setStreet2(field.length >= 2 ? field[1] : "");
//	    address.setCity(field.length >= 3 ? field[2] : "");
//	    address.setStateCode(field.length >= 4 ? field[3] : "");
//	    address.setZip(field.length >= 5 ? field[4] : "");
//	    address.setCountryCode(field.length >= 6 ? field[5] : "");
//	    address.setTypeCode(field.length >= 7 ? field[6] : "");
//	    address.setCountyParishCode(field.length >= 9 ? field[8] : "");
//	  }
//
//	  private void readPatientId(int position, Patient patient)
//	  {
//	    List<String[]> values = getRepeatValues(position);
//	    boolean mrFound = false;
//	    for (int i = 0; i < values.size(); i++)
//	    {
//	      String[] fields = values.get(i);
//	      PatientIdNumber id = null;
//	      String typeCode = "";
//	      if (fields.length >= 5)
//	      {
//	        typeCode = fields[4];
//	        if ("SS".equals(typeCode))
//	        {
//	          id = patient.getIdSsn();
//	        } else if ("MA".equals(typeCode))
//	        {
//	          id = patient.getIdMedicaid();
//	        } else if ("SR".equals(typeCode))
//	        {
//	          id = patient.getIdRegistry();
//	        } else
//	        {
//	          if (mrFound)
//	          {
//	            id = new PatientIdNumber();
//	          } else if (i == 1 && values.size() == 1 && "".equals(typeCode))
//	          {
//	            id = patient.getIdSubmitter();
//	            mrFound = true;
//	          } else if ("MR".equals(typeCode))
//	          {
//	            id = patient.getIdSubmitter();
//	            mrFound = true;
//	          } else if ("PT".equals(typeCode))
//	          {
//	            id = patient.getIdSubmitter();
//	            mrFound = true;
//	          } else if ("PI".equals(typeCode))
//	          {
//	            id = patient.getIdSubmitter();
//	            mrFound = true;
//	          }
//	        }
//	      }
//	      if (id == null && !mrFound && values.size() == 1 && "".equals(typeCode))
//	      {
//	        id = patient.getIdSubmitter();
//	      }
//	      if (id != null)
//	      {
//	        id.setPatient(patient);
//	        id.setPositionId((i + 1));
//	        patient.getPatientIdNumberList().add(id);
//	        readId(fields, id);
//	      }
//	    }
//	  }
//
//	  private void readId(String[] fields, Id id)
//	  {
//	    id.setNumber(fields.length >= 1 ? fields[0] : "");
//	    id.setAssigningAuthorityCode(fields.length >= 4 ? fields[3] : "");
//	    id.setTypeCode(fields.length >= 5 ? fields[4] : "");
//	  }
//
//	  private void readCodeEntity(int fieldNumber, CodedEntity ce)
//	  {
//	    String[] field = getValues(fieldNumber);
//	    ce.setCode(field.length >= 1 ? field[0] : "");
//	    ce.setText(field.length >= 2 ? field[1] : "");
//	    ce.setTable(field.length >= 3 ? field[2] : "");
//	    ce.setAltCode(field.length >= 4 ? field[3] : "");
//	    ce.setAltText(field.length >= 5 ? field[4] : "");
//	    ce.setAltTable(field.length >= 6 ? field[5] : "");
//	  }
//
//	  private void readLocationWithAddress(int fieldNumber, OrganizationName orgName)
//	  {
//	    String[] field = getValues(fieldNumber);
//	    orgName.getId().setNumber(field.length >= 4 ? field[3] : "");
//	    if (orgName.getId().getNumber().isEmpty())
//	    {
//	      orgName.getId().setNumber(field.length >= 1 ? field[0] : "");
//	    }
//	    orgName.setName(field.length >= 4 ? field[3] : "");
//	  }
//
//	  private void readName(int fieldNumber, Name name)
//	  {
//	    String[] field = getValues(fieldNumber);
//	    name.setLast(field.length >= 1 ? field[0] : "");
//	    name.setFirst(field.length >= 2 ? field[1] : "");
//	    name.setMiddle(field.length >= 3 ? field[2] : "");
//	    name.setSuffix(field.length >= 4 ? field[3] : "");
//	    name.setPrefix(field.length >= 5 ? field[4] : "");
//	    name.setTypeCode(field.length >= 7 ? field[6] : "");
//	  }
//
//	  private void readName(int fieldNumber, Name name, Name alias)
//	  {
//	    List<String[]> fieldList = getRepeatValues(fieldNumber);
//	    int positionId = 0;
//	    for (String[] field : fieldList)
//	    {
//	      positionId++;
//	      if (positionId == 1)
//	      {
//	        name.setLast(field.length >= 1 ? field[0] : "");
//	        name.setFirst(field.length >= 2 ? field[1] : "");
//	        name.setMiddle(field.length >= 3 ? field[2] : "");
//	        name.setSuffix(field.length >= 4 ? field[3] : "");
//	        name.setPrefix(field.length >= 5 ? field[4] : "");
//	        name.setTypeCode(field.length >= 7 ? field[6] : "");
//	      } else if (field.length >= 7 && field[6] != null && field[6].equals("A"))
//	      {
//	        alias.setLast(field.length >= 1 ? field[0] : "");
//	        alias.setFirst(field.length >= 2 ? field[1] : "");
//	        alias.setMiddle(field.length >= 3 ? field[2] : "");
//	        alias.setSuffix(field.length >= 4 ? field[3] : "");
//	        alias.setPrefix(field.length >= 5 ? field[4] : "");
//	        alias.setTypeCode(field.length >= 7 ? field[6] : "");
//	      }
//
//	    }
//	  }
//
//	  private void readFields(String messageText)
//	  {
//	    int totalLength = messageText.length();
//	    startField = 0;
//	    for (int i = 0; i < totalLength; i++)
//	    {
//	      char c = messageText.charAt(i);
//	      if (c < ' ')
//	      {
//	        // end of segment, break
//	        currentSegment.add(messageText.substring(startField, i));
//	        segments.add(currentSegment);
//	        currentSegment = new ArrayList<String>();
//	        while (c < ' ' && i < (totalLength - 1))
//	        {
//	          i++;
//	          c = messageText.charAt(i);
//	        }
//	        startField = i;
//	      } else if (c == separators[BAR])
//	      {
//	        String fieldValue = messageText.substring(startField, i);
//	        currentSegment.add(fieldValue);
//	        if (currentSegment.size() == 1 && fieldValue.equals("MSH"))
//	        {
//	          // MSH is a special case where the first separator is the first
//	          // field.
//	          currentSegment.add(String.valueOf(separators[0]));
//	        }
//	        startField = i + 1;
//	      }
//	    }
//	    // The last segment should have ended with a \r so the startField would be
//	    // equal to the length of the message. If not, then there is a mistake, but
//	    // the last line should still be added on as is.
//	    if (startField < messageText.length())
//	    {
//	      currentSegment.add(messageText.substring(startField));
//	    }
//	    // This shouldn't happen, unless no \r is sent at the end of the last
//	    // segment.
//	    if (currentSegment.size() > 0)
//	    {
//	      segments.add(currentSegment);
//	    }
//	  }
//
//	  private Date getValueDate(int fieldNumber, PotentialIssue piInvalid, PotentialIssue piNoTimeZone)
//	  {
//	    String fieldValue = getValue(fieldNumber);
//	    return createDate(piInvalid, piNoTimeZone, fieldValue);
//	  }
//
//	  private void registerIssueIfEmpty(int fieldNumber, PotentialIssue pi)
//	  {
//	    if (isEmpty(fieldNumber))
//	    {
//	      registerIssue(pi);
//	    }
//	  }
//
//	  private boolean isEmpty(int fieldNumber)
//	  {
//	    return getValue(fieldNumber).equals("");
//	  }
//
//	  private String getValue(int fieldNumber)
//	  {
//	    String value = null;
//	    if (currentSegment.size() > fieldNumber)
//	    {
//	      value = currentSegment.get(fieldNumber);
//	    }
//	    if (value == null)
//	    {
//	      return "";
//	    }
//	    int valueLength = value.length();
//	    for (int i = 0; i < valueLength; i++)
//	    {
//	      char c = value.charAt(i);
//	      if (c == separators[TIL] || c == separators[CAR] || c == separators[AMP])
//	      {
//	        return value.substring(0, i);
//	      }
//	    }
//	    return value;
//	  }
//
//	  private String[] getValues(int fieldNumber)
//	  {
//	    String value = null;
//	    if (currentSegment.size() > fieldNumber)
//	    {
//	      value = currentSegment.get(fieldNumber);
//	    }
//	    if (value == null)
//	    {
//	      return new String[] { "" };
//	    }
//	    int valueLength = value.length();
//	    for (int i = 0; i < valueLength; i++)
//	    {
//	      char c = value.charAt(i);
//	      if (c == separators[TIL])
//	      {
//	        value = value.substring(0, i);
//	        break;
//	      }
//	    }
//	    return value.split("\\" + separators[CAR]);
//	  }
//
//	  private List<String[]> getRepeatValues(int fieldNumber)
//	  {
//	    List<String[]> values = new ArrayList<String[]>();
//	    String value = null;
//	    if (currentSegment.size() > fieldNumber)
//	    {
//	      value = currentSegment.get(fieldNumber);
//	    }
//	    if (value == null)
//	    {
//	      values.add(new String[] { "" });
//	      return values;
//	    }
//	    int valueLength = value.length();
//	    int startPos = 0;
//	    for (int i = 0; i < valueLength; i++)
//	    {
//	      char c = value.charAt(i);
//	      if (c == separators[TIL])
//	      {
//	        values.add(value.substring(startPos, i).split("\\" + separators[CAR]));
//	        startPos = i + 1;
//	      }
//	    }
//	    if (startPos < valueLength)
//	    {
//	      values.add(value.substring(startPos).split("\\" + separators[CAR]));
//	    }
//	    return values;
//	  }
//
//	  private boolean readSeparators(String messageText)
//	  {
//	    if (HL7Util.setupSeparators(messageText, separators))
//	    {
//	      if (separators[BAR] == separators[CAR])
//	      {
//	        registerError(PotentialIssue.Hl7MshEncodingCharacterIsMissing);
//	        HL7Util.setDefault(separators);
//	      }
//
//	      boolean unique = HL7Util.checkSeparatorsAreValid(separators);
//	      if (!unique)
//	      {
//	        registerError(PotentialIssue.Hl7MshEncodingCharacterIsInvalid);
//	        HL7Util.setDefault(separators);
//	      }
//	      if (separators[BAR] != '|' || separators[CAR] != '^' || separators[TIL] != '~' || separators[SLA] != '\\' || separators[AMP] != '&')
//	      {
//	        registerIssue(PotentialIssue.Hl7MshEncodingCharacterIsNonStandard);
//	      }
//	      // Bar should be used again after the 4 encoding characters
//	      if (separators[BAR] != messageText.charAt(8))
//	      {
//	        registerError(PotentialIssue.Hl7MshEncodingCharacterIsInvalid);
//	      }
//	    } else
//	    {
//	      if (!messageText.startsWith("MSH"))
//	      {
//	        registerError(PotentialIssue.Hl7MshSegmentIsMissing);
//	        return false;
//	      } else if (messageText.length() < 10)
//	      {
//	        registerError(PotentialIssue.Hl7MshEncodingCharacterIsMissing);
//	        return false;
//	      } else
//	      {
//	        registerError(PotentialIssue.GeneralParseException);
//	        return false;
//	      }
//	    }
//	    return true;
//	  }
//
//	}
