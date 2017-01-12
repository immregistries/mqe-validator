///*
// * Copyright 2013 by Dandelion Software & Research, Inc (DSR)
// * 
// * This application was written for immunization information system (IIS) community and has
// * been released by DSR under an Apache 2 License with the hope that this software will be used
// * to improve Public Health.  
// */
//package com.openimmunizationsoftware.dqa.parser;
///*
// * Copyright 2013 by Dandelion Software & Research, Inc (DSR)
// * 
// * This application was written for immunization information system (IIS) community and has
// * been released by DSR under an Apache 2 License with the hope that this software will be used
// * to improve Public Health.  
// */
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import org.springframework.boot.autoconfigure.web.ServerProperties.Session;
//
//import com.openimmunizationsoftware.dqa.model.CodeTable;
//import com.openimmunizationsoftware.dqa.model.MessageHeader;
//import com.openimmunizationsoftware.dqa.model.MessageReceived;
//import com.openimmunizationsoftware.dqa.model.MessageReceivedGeneric;
//import com.openimmunizationsoftware.dqa.model.NextOfKin;
//import com.openimmunizationsoftware.dqa.model.Observation;
//import com.openimmunizationsoftware.dqa.model.Patient;
//import com.openimmunizationsoftware.dqa.model.SubmitterProfile;
//import com.openimmunizationsoftware.dqa.model.Vaccination;
//import com.openimmunizationsoftware.dqa.model.VaccinationVIS;
//import com.openimmunizationsoftware.dqa.model.types.Address;
//import com.openimmunizationsoftware.dqa.model.types.CodedEntity;
//import com.openimmunizationsoftware.dqa.model.types.Id;
//import com.openimmunizationsoftware.dqa.model.types.Name;
//import com.openimmunizationsoftware.dqa.model.types.OrganizationName;
//import com.openimmunizationsoftware.dqa.model.types.PatientAddress;
//import com.openimmunizationsoftware.dqa.model.types.PatientIdNumber;
//import com.openimmunizationsoftware.dqa.model.types.PatientImmunity;
//import com.openimmunizationsoftware.dqa.model.types.PatientPhone;
//import com.openimmunizationsoftware.dqa.model.types.PhoneNumber;
//import com.openimmunizationsoftware.dqa.quality.ForecastActual;
//import com.openimmunizationsoftware.dqa.quality.QueryResult;
//import com.openimmunizationsoftware.dqa.validator.issues.IssueAction;
//import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
//import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;
//
//public class VaccinationParserHL7 extends VaccinationParser
//{
//
//  public static final String PROCESSING_ID_DEBUG = "D";
//
//  private char[] separators = new char[5];
//
//  public VaccinationParserHL7(SubmitterProfile profile) {
//    super(profile);
//  }
//
//  private int startField = 0;
//  private int currentSegmentPos = -1;
//  private String segmentName = "";
//  private List<List<String>> segments;
//  private List<String> currentSegment;
//  private int vaccinationCount = 0;
//  private int nextOfKinCount = 0;
//  private Session session = null;
//
//  private void setup()
//  {
//    startField = 0;
//    currentSegmentPos = -1;
//    segmentName = "";
//    segments = new ArrayList<List<String>>();
//    currentSegment = new ArrayList<String>();
//
//    patient = null;
//    vaccination = null;
//    nextOfKin = null;
//
//    vaccinationCount = 0;
//    nextOfKinCount = 0;
//
//    pi = PotentialIssues.getPotentialIssues();
//  }
//
//  private static final String[] RECOGNIZED_SEGMENTS = { "MSH", "SFT", "PID", "PD1", "NK1", "PV1", "PV2", "GT1", "IN1", "IN3", "IN4", "PD2", "ORC",
//      "TQ1", "TQ2", "RXA", "RXR", "OBX", "NTE", "BTS", "FTS", "FHS", "BHS" };
//
//  @Override
//  public void createVaccinationUpdateMessage(MessageReceived messageReceived)
//  {
//    String messageText = messageReceived.getRequestText();
//    message = messageReceived;
//    issuesFound = message.getIssuesFound();
//    setup();
//    boolean okayToParse = readSeparators(messageText);
//    if (!okayToParse)
//    {
//      return;
//    }
//    readFields(messageText);
//
//    patient = message.getPatient();
//    currentSegment = segments.get(0);
//    boolean foundPID = false;
//    boolean foundPV1 = false;
//    boolean foundOBX = false;
//    boolean foundRXA = false;
//    boolean foundORC = false;
//    boolean foundNK1 = false;
//    boolean foundRXR = false;
//    boolean previouslyStartedAdminSegments = false;
//    populateMSH(message);
//    while (moveNext())
//    {
//      if (segmentName.equals("PID"))
//      {
//        if (previouslyStartedAdminSegments)
//        {
//          registerIssue(pi.Hl7SegmentsOutOfOrder);
//        }
//        if (foundPID)
//        {
//          registerIssue(pi.Hl7PidSegmentIsRepeated);
//        } else
//        {
//          foundPID = true;
//          populatePID(message);
//        }
//      } else if (segmentName.equals("PV1"))
//      {
//        if (previouslyStartedAdminSegments)
//        {
//          registerIssue(pi.Hl7SegmentsOutOfOrder);
//        }
//        if (foundPV1)
//        {
//          registerIssue(pi.Hl7Pv1SegmentIsRepeated);
//        } else
//        {
//          foundPID = assertPIDFound(foundPID);
//          populatePV1(message);
//          foundPV1 = true;
//        }
//      } else if (segmentName.equals("PD1"))
//      {
//        if (previouslyStartedAdminSegments)
//        {
//          registerIssue(pi.Hl7SegmentsOutOfOrder);
//        }
//        foundPID = assertPIDFound(foundPID);
//        populatePD1(message);
//      } else if (segmentName.equals("NK1"))
//      {
//
//        foundNK1 = true;
//        if (foundPV1 || previouslyStartedAdminSegments)
//        {
//          registerIssue(pi.Hl7SegmentsOutOfOrder);
//        }
//        foundPID = assertPIDFound(foundPID);
//        nextOfKinCount++;
//        positionId = nextOfKinCount;
//        nextOfKin = new NextOfKin();
//        skippableItem = nextOfKin;
//        nextOfKin.setPositionId(nextOfKinCount);
//        message.getNextOfKins().add(nextOfKin);
//        nextOfKin.setReceivedId(message.getNextOfKins().size());
//        populateNK1(message);
//      } else if (segmentName.equals("ORC") || segmentName.equals("RXA"))
//      {
//        if (previouslyStartedAdminSegments)
//        {
//          if (!foundOBX)
//          {
//            registerIssue(pi.Hl7ObxSegmentIsMissing);
//          }
//          if (!foundRXR)
//          {
//            registerIssue(pi.Hl7RxrSegmentIsMissing);
//          }
//        }
//        previouslyStartedAdminSegments = true;
//        foundORC = false;
//        foundRXA = false;
//        foundRXR = false;
//        foundOBX = false;
//
//        vaccinationCount++;
//        positionId = vaccinationCount;
//        vaccination = new Vaccination();
//        skippableItem = vaccination;
//        vaccination.setPositionId(vaccinationCount);
//        message.getVaccinations().add(vaccination);
//        if (segmentName.equals("ORC"))
//        {
//          foundORC = true;
//          populateORC(messageReceived);
//          boolean moved = false;
//          if (!(moved = moveNext()) || !segmentName.equals("RXA"))
//          {
//            if (moved && segmentName.equals("ORC"))
//            {
//              registerIssue(pi.Hl7OrcSegmentIsRepeated);
//            }
//            registerIssue(pi.Hl7RxaSegmentIsMissing);
//            moveBack();
//            continue;
//          }
//        } else
//        {
//          foundRXA = true;
//          if (!message.getMessageHeader().getMessageVersion().startsWith("2.3") && !message.getMessageHeader().getMessageVersion().startsWith("2.4"))
//          {
//            registerIssue(pi.Hl7OrcSegmentIsMissing);
//          }
//        }
//        populateRXA(message);
//      } else if (segmentName.equals("RXR"))
//      {
//        if (vaccination == null)
//        {
//          registerIssue(pi.Hl7RxaSegmentIsMissing);
//          continue;
//        }
//        if (foundRXR)
//        {
//          registerIssue(pi.Hl7RxrSegmentIsRepeated);
//        }
//        foundRXR = true;
//        populateRXR(message);
//      } else if (segmentName.equals("OBX"))
//      {
//        if (vaccination == null)
//        {
//          continue;
//        }
//        populateOBX(message);
//        foundOBX = true;
//      } else
//      {
//        if (segmentName.length() > 0 && segmentName.charAt(0) > ' ')
//        {
//          boolean recognized = false;
//          for (String recognizedSegment : RECOGNIZED_SEGMENTS)
//          {
//            if (recognizedSegment.equals(segmentName))
//            {
//              recognized = true;
//              continue;
//            }
//          }
//          if (!recognized)
//          {
//            registerIssue(pi.Hl7SegmentIsUnrecognized);
//          }
//        }
//      }
//    }
//    positionId = 0;
//    assertPIDFound(foundPID);
//    if (!foundPV1)
//    {
//      registerIssue(pi.Hl7Pv1SegmentIsMissing);
//    }
//    if (!foundNK1)
//    {
//      registerIssue(pi.Hl7Nk1SegmentIsMissing);
//    }
//    if (!foundPV1)
//    {
//      registerIssue(pi.Hl7Pv1SegmentIsMissing);
//      foundPV1 = true;
//    }
//    if (previouslyStartedAdminSegments)
//    {
//      if (!foundRXR)
//      {
//        registerIssue(pi.Hl7RxrSegmentIsMissing);
//      }
//      if (!foundOBX)
//      {
//        registerIssue(pi.Hl7ObxSegmentIsMissing);
//      }
//    }
//
//  }
//
//  private boolean assertPIDFound(boolean foundPID)
//  {
//    if (!foundPID)
//    {
//      registerIssue(pi.Hl7PidSegmentIsMissing);
//      foundPID = true;
//    }
//    return foundPID;
//  }
//
//  private boolean moveNext()
//  {
//    currentSegmentPos++;
//    while (currentSegmentPos < segments.size())
//    {
//      currentSegment = segments.get(currentSegmentPos);
//      if (currentSegment.size() > 0)
//      {
//        segmentName = currentSegment.get(0);
//        return true;
//      }
//      currentSegmentPos++;
//    }
//    return false;
//  }
//
//  private void moveBack()
//  {
//    currentSegmentPos--;
//    while (currentSegmentPos >= 0)
//    {
//      currentSegment = segments.get(currentSegmentPos);
//      if (currentSegment.size() > 0)
//      {
//        segmentName = currentSegment.get(0);
//        return;
//      }
//      currentSegmentPos--;
//    }
//
//  }
//
//  private void populateMSH(MessageReceivedGeneric message)
//  {
//    positionId = 1;
//    MessageHeader header = message.getMessageHeader();
//    header.setSendingApplication(getValue(3));
//    header.setSendingFacility(getValue(4));
//    header.setReceivingApplication(getValue(5));
//    header.setReceivingFacility(getValue(6));
//    header.setMessageDate(getValueDate(7, pi.Hl7MshMessageDateIsInvalid, pi.Hl7MshMessageDateIsMissingTimezone));
//    String[] field = getValues(9);
//    header.setMessageType(field.length >= 1 ? field[0] : "");
//    header.setMessageTrigger(field.length >= 2 ? field[1] : "");
//    header.setMessageStructure(field.length >= 3 ? field[2] : "");
//    header.setMessageControl(getValue(10));
//    header.setProcessingIdCode(getValue(11));
//    header.setMessageVersion(getValue(12));
//    header.setAckTypeAcceptCode(getValue(15));
//    header.setAckTypeApplicationCode(getValue(16));
//    header.setCountryCode(getValue(17));
//    header.setCharacterSetCode(getValue(18));
//    header.setCharacterSetAltCode(getValue(20));
//    header.setMessageProfile(getValue(21));
//  }
//
//  private void populatePID(MessageReceived message)
//  {
//    positionId = 1;
//    readAddress(11, patient);
//    // private Name alias = new Name();
//    patient.setBirthDate(getValueDate(7, pi.PatientBirthDateIsInvalid, null));
//    patient.setBirthMultiple(getValue(24));
//    patient.setBirthOrderCode(getValue(25));
//    patient.setBirthPlace(getValue(23));
//    patient.setDeathDate(getValueDate(29, pi.PatientDeathDateIsInvalid, null));
//    patient.setDeathIndicator(getValue(30));
//    readCodeEntity(22, patient.getEthnicity());
//    // TODO private OrganizationName facility = new OrganizationName();
//    readPatientId(3, patient);
//    patient.setMotherMaidenName(getValue(6));
//    readName(5, patient.getName(), patient.getAlias());
//    readPhoneNumber(13, patient);
//    readCodeEntity(15, patient.getPrimaryLanguage());
//    readCodeEntity(10, patient.getRace());
//    patient.setSexCode(getValue(8));
//  }
//
//  private void populatePD1(MessageReceived message)
//  {
//    patient.setRegistryStatusCode(getValue(16));
//    patient.setPublicityCode(getValue(11));
//    patient.setProtectionCode(getValue(12));
//    // TODO private OrganizationName facility = new OrganizationName();
//    // TODO private Id idSubmitter = new Id();
//    // TODO private Id physician = new Id();
//  }
//
//  private void populateNK1(MessageReceived message)
//  {
//    String setId = getValue(1);
//    if (setId.equals(""))
//    {
//      registerIssue(pi.Hl7Nk1SetIdIsMissing);
//    }
//    readAddress(4, nextOfKin.getAddress());
//    readPhoneNumber(5, nextOfKin.getPhone());
//    readCodeEntity(3, nextOfKin.getRelationship());
//    readName(2, nextOfKin.getName());
//  }
//
//  private void populateRXA(MessageReceived message)
//  {
//    registerIssueIfEmpty(1, pi.Hl7RxaGiveSubIdIsMissing);
//    registerIssueIfEmpty(2, pi.Hl7RxaAdminSubIdCounterIsMissing);
//    vaccination.setAdminDate(getValueDate(3, pi.VaccinationAdminDateIsInvalid, null));
//    vaccination.setAdminDateEnd(getValueDate(4, null, null));
//    readCodeEntity(5, vaccination.getAdmin());
//    CodedEntity admin = vaccination.getAdmin();
//    readCptCvxCodes(admin);
//    vaccination.setAmount(getValue(6));
//    vaccination.setAmountUnitCode(getValue(7));
//    readCodeEntity(9, vaccination.getInformationSource());
//    // TODO 10 XCN private Id givenBy = new Id();
//    readLocationWithAddress(11, vaccination.getFacility());
//    vaccination.setLotNumber(getValue(15));
//    vaccination.setExpirationDate(getValueDate(16, pi.VaccinationLotExpirationDateIsInvalid, null));
//    readCodeEntity(17, vaccination.getManufacturer());
//    readCodeEntity(18, vaccination.getRefusal());
//    vaccination.setCompletionCode(getValue(20));
//    vaccination.setActionCode(getValue(21));
//    vaccination.setSystemEntryDate(getValueDate(22, pi.VaccinationSystemEntryTimeIsInvalid, null));
//  }
//
//  private void populateRXR(MessageReceived message)
//  {
//    readCodeEntity(1, vaccination.getBodyRoute());
//    readCodeEntity(2, vaccination.getBodySite());
//  }
//
//  private void populateOBX(MessageReceived message)
//  {
//    Observation obs = new Observation();
//    vaccination.getObservations().add(obs);
//    readCodeEntity(2, obs.getValueType());
//    readCodeEntity(3, obs.getObservationIdentifier());
//    obs.setObservationSubId(getValue(4));
//    obs.setObservationValue(getValue(5));
//  }
//
//  private void readCptCvxCodes(CodedEntity admin)
//  {
//    boolean codeFound = false;
//    if (admin.getTable().equals("CVX") || admin.getTable().equals("HL70292"))
//    {
//      vaccination.getAdminCvx().setCode(admin.getCode());
//      vaccination.getAdminCvx().setText(admin.getText());
//      vaccination.getAdminCvx().setTable(admin.getTable());
//      codeFound = true;
//    } else if (admin.getAltTable().equals("CVX") || admin.getAltTable().equals("HL70292"))
//    {
//      vaccination.getAdminCvx().setCode(admin.getAltCode());
//      vaccination.getAdminCvx().setText(admin.getAltText());
//      vaccination.getAdminCvx().setTable(admin.getAltTable());
//      codeFound = true;
//    }
//    if (admin.getTable().equals("CPT") || admin.getTable().equals("C4"))
//    {
//      vaccination.getAdminCpt().setCode(admin.getCode());
//      vaccination.getAdminCpt().setText(admin.getText());
//      vaccination.getAdminCpt().setTable(admin.getTable());
//      codeFound = true;
//    } else if (admin.getAltTable().equals("CPT") || admin.getAltTable().equals("C4"))
//    {
//      vaccination.getAdminCpt().setCode(admin.getAltCode());
//      vaccination.getAdminCpt().setText(admin.getAltText());
//      vaccination.getAdminCpt().setTable(admin.getAltTable());
//      codeFound = true;
//    }
//    if (!codeFound)
//    {
//      if (admin.getCode().equals(""))
//      {
//        registerIssue(pi.VaccinationAdminCodeTableIsMissing);
//      } else
//      {
//        registerIssue(pi.VaccinationAdminCodeTableIsInvalid);
//      }
//      String possible = admin.getCode();
//      if (possible != null)
//      {
//        try
//        {
//          Integer.parseInt(possible);
//          if (possible.length() >= 2 && possible.length() <= 3)
//          {
//            vaccination.getAdminCvx().setCode(admin.getCode());
//            vaccination.getAdminCvx().setText(admin.getText());
//            vaccination.getAdminCvx().setTable(admin.getTable());
//          } else if (possible.length() == 5 && possible.startsWith("90"))
//          {
//            vaccination.getAdminCpt().setCode(admin.getCode());
//            vaccination.getAdminCpt().setText(admin.getText());
//            vaccination.getAdminCpt().setTable(admin.getTable());
//          }
//        } catch (NumberFormatException nfe)
//        {
//          // not CVX
//        }
//      }
//    }
//
//  }
//
//  private void populateORC(MessageReceived message)
//  {
//    readCodeEntity(1, vaccination.getOrderControl());
//    vaccination.setIdPlacer(getValue(2));
//    vaccination.setIdSubmitter(getValue(3));
//    readCodeEntity(28, vaccination.getConfidentiality());
//  }
//
//  private void populatePV1(MessageReceived message)
//  {
//    positionId = 1;
//    patient.setPatientClassCode(getValue(2));
//    String[] field = getValues(20);
//    if (field.length > 0)
//    {
//      patient.setFinancialEligibilityCode(field[0]);
//      if (field.length > 1)
//      {
//        patient.setFinancialEligibilityDate(createDate(pi.PatientVfcEffectiveDateIsInvalid, null, field[1]));
//      }
//    }
//  }
//
//  private void readPhoneNumber(int fieldNumber, Patient patient)
//  {
//    List<PatientPhone> patientPhoneList = patient.getPatientPhoneList();
//    List<String[]> fieldList = getRepeatValues(fieldNumber);
//    int position = 0;
//    PatientPhone phoneNumber = null;
//    for (String[] field : fieldList)
//    {
//      position++;
//      if (phoneNumber == null)
//      {
//        phoneNumber = patient.getPhone();
//      } else
//      {
//        phoneNumber = new PatientPhone();
//      }
//      phoneNumber.setPatient(patient);
//      phoneNumber.setPositionId(position);
//      patientPhoneList.add(phoneNumber);
//      if (field.length == 0)
//      {
//        continue;
//      }
//      readPhoneNumber(phoneNumber, field);
//    }
//  }
//
//  private void readPhoneNumber(int fieldNumber, PhoneNumber phoneNumber)
//  {
//    String[] field = getValues(fieldNumber);
//    if (field.length == 0)
//    {
//      return;
//    }
//    readPhoneNumber(phoneNumber, field);
//  }
//
//  public void readPhoneNumber(PhoneNumber phoneNumber, String[] field)
//  {
//    phoneNumber.setNumber(field.length >= 1 ? field[0] : "");
//    if (field.length >= 2 && field[1].length() > 0)
//    {
//      phoneNumber.setTelUseCode(field[1]);
//    }
//    if (field.length >= 3 && field[2].length() > 0)
//    {
//      phoneNumber.setTelEquipCode(field[2]);
//    }
//    if (field.length >= 4 && field[3].length() > 0)
//    {
//      phoneNumber.setEmail(field[3]);
//    }
//    if (field.length >= 5 && field[4].length() > 0)
//    {
//      phoneNumber.setCountryCode(field[4]);
//    }
//    if (field.length >= 6 && field[5].length() > 0)
//    {
//      phoneNumber.setAreaCode(field[5]);
//    }
//    if (field.length >= 7 && field[6].length() > 0)
//    {
//      phoneNumber.setLocalNumber(field[6]);
//    }
//    if (field.length >= 8 && field[7].length() > 0)
//    {
//      phoneNumber.setExtension(field[7]);
//    }
//  }
//
//  private void readAddress(int fieldNumber, Patient patient)
//  {
//    List<String[]> fieldList = getRepeatValues(fieldNumber);
//    int positionId = 0;
//    for (String[] field : fieldList)
//    {
//      positionId++;
//      PatientAddress address = new PatientAddress();
//      address.setPatient(patient);
//      address.setPositionId(positionId);
//      patient.getPatientAddressList().add(address);
//      readAddress(field, address);
//    }
//  }
//
//  private void readAddress(int fieldNumber, Address address)
//  {
//    readAddress(getValues(fieldNumber), address);
//  }
//
//  private void readAddress(String[] field, Address address)
//  {
//    address.setStreet(field.length >= 1 ? field[0] : "");
//    address.setStreet2(field.length >= 2 ? field[1] : "");
//    address.setCity(field.length >= 3 ? field[2] : "");
//    address.setStateCode(field.length >= 4 ? field[3] : "");
//    address.setZip(field.length >= 5 ? field[4] : "");
//    address.setCountryCode(field.length >= 6 ? field[5] : "");
//    address.setTypeCode(field.length >= 7 ? field[6] : "");
//    address.setCountyParishCode(field.length >= 9 ? field[8] : "");
//  }
//
//  private void readPatientId(int position, Patient patient)
//  {
//    List<String[]> values = getRepeatValues(position);
//    boolean mrFound = false;
//    for (int i = 0; i < values.size(); i++)
//    {
//      String[] fields = values.get(i);
//      PatientIdNumber id = null;
//      String typeCode = "";
//      if (fields.length >= 5)
//      {
//        typeCode = fields[4];
//        if ("SS".equals(typeCode))
//        {
//          id = patient.getIdSsn();
//        } else if ("MA".equals(typeCode))
//        {
//          id = patient.getIdMedicaid();
//        } else if ("SR".equals(typeCode))
//        {
//          id = patient.getIdRegistry();
//        } else
//        {
//          if (mrFound)
//          {
//            id = new PatientIdNumber();
//          } else if (i == 1 && values.size() == 1 && "".equals(typeCode))
//          {
//            id = patient.getIdSubmitter();
//            mrFound = true;
//          } else if ("MR".equals(typeCode))
//          {
//            id = patient.getIdSubmitter();
//            mrFound = true;
//          } else if ("PT".equals(typeCode))
//          {
//            id = patient.getIdSubmitter();
//            mrFound = true;
//          } else if ("PI".equals(typeCode))
//          {
//            id = patient.getIdSubmitter();
//            mrFound = true;
//          }
//        }
//      }
//      if (id == null && !mrFound && values.size() == 1 && "".equals(typeCode))
//      {
//        id = patient.getIdSubmitter();
//      }
//      if (id != null)
//      {
//        id.setPatient(patient);
//        id.setPositionId((i + 1));
//        patient.getPatientIdNumberList().add(id);
//        readId(fields, id);
//      }
//    }
//  }
//
//  private void readId(String[] fields, Id id)
//  {
//    id.setNumber(fields.length >= 1 ? fields[0] : "");
//    id.setAssigningAuthorityCode(fields.length >= 4 ? fields[3] : "");
//    id.setTypeCode(fields.length >= 5 ? fields[4] : "");
//  }
//
//  private void readCodeEntity(int fieldNumber, CodedEntity ce)
//  {
//    String[] field = getValues(fieldNumber);
//    ce.setCode(field.length >= 1 ? field[0] : "");
//    ce.setText(field.length >= 2 ? field[1] : "");
//    ce.setTable(field.length >= 3 ? field[2] : "");
//    ce.setAltCode(field.length >= 4 ? field[3] : "");
//    ce.setAltText(field.length >= 5 ? field[4] : "");
//    ce.setAltTable(field.length >= 6 ? field[5] : "");
//  }
//
//  private void readLocationWithAddress(int fieldNumber, OrganizationName orgName)
//  {
//    String[] field = getValues(fieldNumber);
//    orgName.getId().setNumber(field.length >= 4 ? field[3] : "");
//    if (orgName.getId().getNumber().isEmpty())
//    {
//      orgName.getId().setNumber(field.length >= 1 ? field[0] : "");
//    }
//    orgName.setName(field.length >= 4 ? field[3] : "");
//  }
//
//  private void readName(int fieldNumber, Name name)
//  {
//    String[] field = getValues(fieldNumber);
//    name.setLast(field.length >= 1 ? field[0] : "");
//    name.setFirst(field.length >= 2 ? field[1] : "");
//    name.setMiddle(field.length >= 3 ? field[2] : "");
//    name.setSuffix(field.length >= 4 ? field[3] : "");
//    name.setPrefix(field.length >= 5 ? field[4] : "");
//    name.setTypeCode(field.length >= 7 ? field[6] : "");
//  }
//
//  private void readName(int fieldNumber, Name name, Name alias)
//  {
//    List<String[]> fieldList = getRepeatValues(fieldNumber);
//    int positionId = 0;
//    for (String[] field : fieldList)
//    {
//      positionId++;
//      if (positionId == 1)
//      {
//        name.setLast(field.length >= 1 ? field[0] : "");
//        name.setFirst(field.length >= 2 ? field[1] : "");
//        name.setMiddle(field.length >= 3 ? field[2] : "");
//        name.setSuffix(field.length >= 4 ? field[3] : "");
//        name.setPrefix(field.length >= 5 ? field[4] : "");
//        name.setTypeCode(field.length >= 7 ? field[6] : "");
//      } else if (field.length >= 7 && field[6] != null && field[6].equals("A"))
//      {
//        alias.setLast(field.length >= 1 ? field[0] : "");
//        alias.setFirst(field.length >= 2 ? field[1] : "");
//        alias.setMiddle(field.length >= 3 ? field[2] : "");
//        alias.setSuffix(field.length >= 4 ? field[3] : "");
//        alias.setPrefix(field.length >= 5 ? field[4] : "");
//        alias.setTypeCode(field.length >= 7 ? field[6] : "");
//      }
//
//    }
//  }
//
//  private void readFields(String messageText)
//  {
//    int totalLength = messageText.length();
//    startField = 0;
//    for (int i = 0; i < totalLength; i++)
//    {
//      char c = messageText.charAt(i);
//      if (c < ' ')
//      {
//        // end of segment, break
//        currentSegment.add(messageText.substring(startField, i));
//        segments.add(currentSegment);
//        currentSegment = new ArrayList<String>();
//        while (c < ' ' && i < (totalLength - 1))
//        {
//          i++;
//          c = messageText.charAt(i);
//        }
//        startField = i;
//      } else if (c == separators[BAR])
//      {
//        String fieldValue = messageText.substring(startField, i);
//        currentSegment.add(fieldValue);
//        if (currentSegment.size() == 1 && fieldValue.equals("MSH"))
//        {
//          // MSH is a special case where the first separator is the first
//          // field.
//          currentSegment.add(String.valueOf(separators[0]));
//        }
//        startField = i + 1;
//      }
//    }
//    // The last segment should have ended with a \r so the startField would be
//    // equal to the length of the message. If not, then there is a mistake, but
//    // the last line should still be added on as is.
//    if (startField < messageText.length())
//    {
//      currentSegment.add(messageText.substring(startField));
//    }
//    // This shouldn't happen, unless no \r is sent at the end of the last
//    // segment.
//    if (currentSegment.size() > 0)
//    {
//      segments.add(currentSegment);
//    }
//  }
//
//  private Date getValueDate(int fieldNumber, PotentialIssue piInvalid, PotentialIssue piNoTimeZone)
//  {
//    String fieldValue = getValue(fieldNumber);
//    return createDate(piInvalid, piNoTimeZone, fieldValue);
//  }
//
//  private void registerIssueIfEmpty(int fieldNumber, PotentialIssue pi)
//  {
//    if (isEmpty(fieldNumber))
//    {
//      registerIssue(pi);
//    }
//  }
//
//  private boolean isEmpty(int fieldNumber)
//  {
//    return getValue(fieldNumber).equals("");
//  }
//
//  private String getValue(int fieldNumber)
//  {
//    String value = null;
//    if (currentSegment.size() > fieldNumber)
//    {
//      value = currentSegment.get(fieldNumber);
//    }
//    if (value == null)
//    {
//      return "";
//    }
//    int valueLength = value.length();
//    for (int i = 0; i < valueLength; i++)
//    {
//      char c = value.charAt(i);
//      if (c == separators[TIL] || c == separators[CAR] || c == separators[AMP])
//      {
//        return value.substring(0, i);
//      }
//    }
//    return value;
//  }
//
//  private String[] getValues(int fieldNumber)
//  {
//    String value = null;
//    if (currentSegment.size() > fieldNumber)
//    {
//      value = currentSegment.get(fieldNumber);
//    }
//    if (value == null)
//    {
//      return new String[] { "" };
//    }
//    int valueLength = value.length();
//    for (int i = 0; i < valueLength; i++)
//    {
//      char c = value.charAt(i);
//      if (c == separators[TIL])
//      {
//        value = value.substring(0, i);
//        break;
//      }
//    }
//    return value.split("\\" + separators[CAR]);
//  }
//
//  private List<String[]> getRepeatValues(int fieldNumber)
//  {
//    List<String[]> values = new ArrayList<String[]>();
//    String value = null;
//    if (currentSegment.size() > fieldNumber)
//    {
//      value = currentSegment.get(fieldNumber);
//    }
//    if (value == null)
//    {
//      values.add(new String[] { "" });
//      return values;
//    }
//    int valueLength = value.length();
//    int startPos = 0;
//    for (int i = 0; i < valueLength; i++)
//    {
//      char c = value.charAt(i);
//      if (c == separators[TIL])
//      {
//        values.add(value.substring(startPos, i).split("\\" + separators[CAR]));
//        startPos = i + 1;
//      }
//    }
//    if (startPos < valueLength)
//    {
//      values.add(value.substring(startPos).split("\\" + separators[CAR]));
//    }
//    return values;
//  }
//
//  private boolean readSeparators(String messageText)
//  {
//    if (HL7Util.setupSeparators(messageText, separators))
//    {
//      if (separators[BAR] == separators[CAR])
//      {
//        registerError(pi.Hl7MshEncodingCharacterIsMissing);
//        HL7Util.setDefault(separators);
//      }
//
//      boolean unique = HL7Util.checkSeparatorsAreValid(separators);
//      if (!unique)
//      {
//        registerError(pi.Hl7MshEncodingCharacterIsInvalid);
//        HL7Util.setDefault(separators);
//      }
//      if (separators[BAR] != '|' || separators[CAR] != '^' || separators[TIL] != '~' || separators[SLA] != '\\' || separators[AMP] != '&')
//      {
//        registerIssue(pi.Hl7MshEncodingCharacterIsNonStandard);
//      }
//      // Bar should be used again after the 4 encoding characters
//      if (separators[BAR] != messageText.charAt(8))
//      {
//        registerError(pi.Hl7MshEncodingCharacterIsInvalid);
//      }
//    } else
//    {
//      if (!messageText.startsWith("MSH"))
//      {
//        registerError(pi.Hl7MshSegmentIsMissing);
//        return false;
//      } else if (messageText.length() < 10)
//      {
//        registerError(pi.Hl7MshEncodingCharacterIsMissing);
//        return false;
//      } else
//      {
//        registerError(pi.GeneralParseException);
//        return false;
//      }
//    }
//    return true;
//  }
//
//  public String makeAckMessage(QueryReceived queryReceived, QueryResult queryResult, Session session)
//  {
//    this.session = session;
//    StringBuilder ack = new StringBuilder();
//    if (queryResult.getPatient() == null)
//    {
//      makeHeader(ack, queryReceived, HL7Util.QUERY_RESULT_NO_MATCHES, HL7Util.QUERY_RESPONSE_TYPE);
//      makeMSA(queryReceived, ack);
//      makeQAK(queryReceived, ack, "NF");
//      makeQPD(queryReceived, ack);
//    } else
//    {
//      Date today = new Date();
//      makeHeader(ack, queryReceived, HL7Util.QUERY_RESULT_IMMUNIZATION_HISTORY, HL7Util.QUERY_RESPONSE_TYPE);
//      makeMSA(queryReceived, ack);
//      makeQAK(queryReceived, ack, "OK");
//      makeQPD(queryReceived, ack);
//      makePID(queryResult, ack);
//      int position = 0;
//      for (NextOfKin nextOfKin : queryResult.getNextOfKinList())
//      {
//        position++;
//        makeNK1(ack, nextOfKin, position);
//      }
//      if (queryResult.getPatient().getPatientImmunityList().size() > 0)
//      {
//        int count = 0;
//        makeRXA998(ack);
//        for (PatientImmunity patientImmunity : queryResult.getPatient().getPatientImmunityList())
//        {
//          count++;
//          CodedEntity obsId = new CodedEntity(CodeTable.Type.OBSERVATION_IDENTIFIER);
//          CodedEntity obsValue = new CodedEntity(CodeTable.Type.EVIDENCE_OF_IMMUNITY);
//          CodedEntity obsMethod = null;
//          obsId.setCode("59784-9");
//          obsId.setText("Disease with presumed immunity");
//          obsId.setTable("LN");
//          obsValue.setCode(patientImmunity.getImmunityCode());
//          obsValue.setTable("SCT");
//          makeOBX(ack, count, obsId, obsValue, null, obsMethod, 0);
//        }
//      }
//      for (Vaccination vaccination : queryResult.getVaccinationList())
//      {
//        makeORC(ack, vaccination);
//        makeRXA(ack, vaccination);
//        if (!vaccination.getBodyRouteCode().equals(""))
//        {
//          makeRXR(ack, vaccination);
//        }
//        int count = 1;
//        int subId = 0;
//        if (!vaccination.getFinancialEligibilityCode().equals(""))
//        {
//          subId++;
//          CodedEntity obsId = new CodedEntity(CodeTable.Type.OBSERVATION_IDENTIFIER);
//          CodedEntity obsValue = new CodedEntity(CodeTable.Type.FINANCIAL_STATUS_CODE);
//          CodedEntity obsMethod = null;
//          obsId.setCode("64994-7");
//          obsId.setText("Vaccine funding program eligibility category");
//          obsId.setTable("LN");
//          obsValue.setCode(vaccination.getFinancialEligibilityCode());
//          obsValue.setTable("HL70064");
//          makeOBX(ack, count++, obsId, obsValue, null, obsMethod, subId);
//        }
//        for (VaccinationVIS vaccinationVIS : vaccination.getVaccinationVisList())
//        {
//          subId++;
//          if (!vaccinationVIS.getCvxCode().equals(""))
//          {
//            CodedEntity obsId = new CodedEntity(CodeTable.Type.OBSERVATION_IDENTIFIER);
//            CodedEntity obsValue = new CodedEntity(CodeTable.Type.VACCINATION_CVX_CODE);
//            obsId.setCode("30956-7");
//            obsId.setText("Vaccine Type");
//            obsId.setTable("LN");
//            obsValue.setCode(vaccinationVIS.getCvxCode());
//            obsValue.setTable("CVX");
//            makeOBX(ack, count++, obsId, obsValue, subId);
//          }
//          if (vaccinationVIS.getPublishedDate() != null)
//          {
//            CodedEntity obsId = new CodedEntity(CodeTable.Type.OBSERVATION_IDENTIFIER);
//            obsId.setCode("29768-9");
//            obsId.setText("Date vaccine information statement published");
//            obsId.setTable("LN");
//            makeOBX(ack, 1, obsId, vaccinationVIS.getPublishedDate(), subId);
//          }
//          if (vaccinationVIS.getPresentedDate() != null)
//          {
//            CodedEntity obsId = new CodedEntity(CodeTable.Type.OBSERVATION_IDENTIFIER);
//            obsId.setCode("29769-7");
//            obsId.setText("Date vaccine information statement presented");
//            obsId.setTable("LN");
//            makeOBX(ack, 1, obsId, vaccinationVIS.getPresentedDate(), subId);
//          }
//        }
//        if (vaccination.getTestEvent() != null)
//        {
//          List<EvaluationActual> evaluationActualList = vaccination.getTestEvent().getEvaluationActualList();
//          if (evaluationActualList != null)
//          {
//            int groupCount = 1;
//            for (EvaluationActual evaluationActual : evaluationActualList)
//            {
//              CodedEntity obsId = new CodedEntity(CodeTable.Type.OBSERVATION_IDENTIFIER);
//              CodedEntity obsValue = new CodedEntity(CodeTable.Type.VACCINATION_CVX_CODE);
//              obsId.setCode("30956-7");
//              obsId.setText("Vaccine Type");
//              obsId.setTable("LN");
//              obsValue.setCode(evaluationActual.getVaccineCvx());
//              obsValue.setTable("CVX");
//              makeOBX(ack, count++, obsId, obsValue, today);
//
//              obsId = new CodedEntity(CodeTable.Type.OBSERVATION_IDENTIFIER);
//              obsValue = new CodedEntity(CodeTable.Type.CDC_PHIN_VADS);
//              obsId.setCode("59779-9");
//              obsId.setText("Immunization Schedule used");
//              obsId.setTable("LN");
//              obsValue.setCode("VXC16");
//              obsValue.setText("ACIP Schedule");
//              obsValue.setTable("CDCPHINVS");
//              makeOBX(ack, count++, obsId, obsValue, today);
//
//              obsId = new CodedEntity(CodeTable.Type.OBSERVATION_IDENTIFIER);
//              obsId.setCode("30982-3");
//              obsId.setText("Evaluation reason");
//              obsId.setTable("LN");
//              makeOBX(ack, count++, obsId, "U^" + evaluationActual.getReasonText() + "^MCIR", today);
//
//              obsId = new CodedEntity(CodeTable.Type.OBSERVATION_IDENTIFIER);
//              obsId.setCode("59781-5");
//              obsId.setText("Dose Validity");
//              obsId.setTable("LN");
//              makeOBX(ack, count++, obsId, evaluationActual.getDoseValid(), today);
//              groupCount++;
//            }
//          }
//        }
//      }
//      int count = 0;
//      for (ForecastActual forecastActual : queryResult.getForecastActualList())
//      {
//        count++;
//        makeORC(ack, System.currentTimeMillis() + "." + count);
//        makeRXA998(ack);
//
//        CodedEntity obsId;
//        CodedEntity obsValue;
//
//        obsId = new CodedEntity(CodeTable.Type.OBSERVATION_IDENTIFIER);
//        obsValue = new CodedEntity(CodeTable.Type.VACCINATION_CVX_CODE);
//        obsId.setCode("30979-9");
//        obsId.setText("Vaccine due next");
//        obsId.setTable("LN");
//        String vaccineCvx = forecastActual.getVaccineCvx();
//        if (vaccineCvx.equals(""))
//        {
//          vaccineCvx = forecastActual.getForecastItem().getVaccineCvx();
//        }
//        obsValue.setCode(vaccineCvx);
//        obsValue.setText(forecastActual.getForecastItem().getLabel());
//        obsValue.setTable("CVX");
//        makeOBX(ack, 1, obsId, obsValue, today);
//
//        obsId = new CodedEntity(CodeTable.Type.OBSERVATION_IDENTIFIER);
//        obsValue = new CodedEntity(CodeTable.Type.CDC_PHIN_VADS);
//        obsId.setCode("59779-9");
//        obsId.setText("Immunization Schedule used");
//        obsId.setTable("LN");
//        obsValue.setCode("VXC16");
//        obsValue.setText("ACIP Schedule");
//        obsValue.setTable("CDCPHINVS");
//        makeOBX(ack, 2, obsId, obsValue, today);
//
//        obsId = new CodedEntity(CodeTable.Type.OBSERVATION_IDENTIFIER);
//        obsId.setCode("59783-1");
//        obsId.setText("Status in immunization series");
//        obsId.setTable("LN");
//        if (forecastActual.isComplete())
//        {
//          makeOBX(ack, 3, obsId, "1^Complete^MCIR", today);
//        } else if (forecastActual.getDueDate().after(today))
//        {
//          makeOBX(ack, 3, obsId, "2^Up-to-date^MCIR", today);
//        } else if (forecastActual.getOverdueDate().before(today))
//        {
//          makeOBX(ack, 3, obsId, "3^Due^MCIR", today);
//        } else
//        {
//          makeOBX(ack, 3, obsId, "4^Overdue^MCIR", today);
//        }
//
//        if (!forecastActual.isComplete())
//        {
//          obsId = new CodedEntity(CodeTable.Type.OBSERVATION_IDENTIFIER);
//          obsId.setCode("30980-7");
//          obsId.setText("Date vaccine due");
//          obsId.setTable("LN");
//          makeOBX(ack, 1, obsId, forecastActual.getDueDate(), today);
//          if (forecastActual.getValidDate() != null)
//          {
//            obsId = new CodedEntity(CodeTable.Type.OBSERVATION_IDENTIFIER);
//            obsId.setTable("LN");
//            obsId.setCode("30981-5");
//            obsId.setText("Earliest date to give");
//            makeOBX(ack, 1, obsId, forecastActual.getValidDate(), today);
//          }
//        }
//        if (!forecastActual.getDoseNumber().equals("*") && !forecastActual.getDoseNumber().equals(""))
//        {
//          obsId = new CodedEntity(CodeTable.Type.OBSERVATION_IDENTIFIER);
//          obsId.setCode("30973-2");
//          obsId.setText("Vaccine due next dose number");
//          obsId.setTable("LN");
//          makeOBX(ack, 2, obsId, forecastActual.getDoseNumber(), today);
//        }
//      }
//    }
//    return ack.toString();
//  }
//
//  public void makeOBX(StringBuilder ack, int position, CodedEntity obsId, CodedEntity obsValue, Date date, CodedEntity obsMethod, int subId)
//  {
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//    ack.append("OBX|");
//    ack.append(position + "|");
//    ack.append("CE|");
//    ack.append(makeCodedValue(obsId) + "|");
//    ack.append(subId + "|");
//    ack.append(makeCodedValue(obsValue) + "|");
//    ack.append("|");
//    ack.append("|");
//    ack.append("|");
//    ack.append("|");
//    ack.append("|");
//    ack.append("F|");
//    ack.append("|");
//    ack.append("|");
//    if (date != null)
//    {
//      ack.append(sdf.format(date));
//    }
//    ack.append("|");
//    ack.append("|");
//    ack.append("|");
//    if (obsMethod != null)
//    {
//      ack.append(makeCodedValue(obsMethod));
//    }
//    ack.append("|");
//    ack.append("\r");
//  }
//
//  public void makeOBX(StringBuilder ack, int position, CodedEntity obsId, CodedEntity obsValue, int subId)
//  {
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//    ack.append("OBX|");
//    ack.append(position + "|");
//    ack.append("CE|");
//    ack.append(makeCodedValue(obsId) + "|");
//    ack.append(subId + "|");
//    ack.append(makeCodedValue(obsValue) + "|");
//    ack.append("|");
//    ack.append("|");
//    ack.append("|");
//    ack.append("|");
//    ack.append("|");
//    ack.append("F|");
//    ack.append("\r");
//  }
//
//  public void makeOBX(StringBuilder ack, int position, CodedEntity obsId, CodedEntity obsValue)
//  {
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//    ack.append("OBX|");
//    ack.append(position + "|");
//    ack.append("CE|");
//    ack.append(makeCodedValue(obsId) + "|");
//    ack.append("0|");
//    ack.append(makeCodedValue(obsValue) + "|");
//    ack.append("|");
//    ack.append("|");
//    ack.append("|");
//    ack.append("|");
//    ack.append("|");
//    ack.append("F|");
//
//    ack.append("\r");
//  }
//
//  public void makeOBX(StringBuilder ack, int position, CodedEntity obsId, CodedEntity obsValue, Date date)
//  {
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//    ack.append("OBX|");
//    ack.append(position + "|");
//    ack.append("CE|");
//    ack.append(makeCodedValue(obsId) + "|");
//    ack.append("0|");
//    ack.append(makeCodedValue(obsValue) + "|");
//    ack.append("|");
//    ack.append("|");
//    ack.append("|");
//    ack.append("|");
//    ack.append("|");
//    ack.append("F|");
//    if (date != null)
//    {
//      ack.append("|");
//      ack.append("|");
//      ack.append(sdf.format(date));
//      ack.append("|");
//    }
//
//    ack.append("\r");
//  }
//
//  public void makeOBX(StringBuilder ack, int position, CodedEntity obsId, String obsValue, Date date)
//  {
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//    ack.append("OBX|");
//    ack.append(position + "|");
//    ack.append("CE|");
//    ack.append(makeCodedValue(obsId) + "|");
//    ack.append("0|");
//    ack.append(obsValue + "|");
//    ack.append("|");
//    ack.append("|");
//    ack.append("|");
//    ack.append("|");
//    ack.append("|");
//    ack.append("F|");
//    if (date != null)
//    {
//      ack.append("|");
//      ack.append("|");
//      ack.append(sdf.format(date));
//      ack.append("|");
//    }
//    ack.append("\r");
//  }
//
//  public void makeOBX(StringBuilder ack, int position, CodedEntity obsId, Date obsValue, Date date)
//  {
//    makeOBX(ack, position, obsId, obsValue, date, 0);
//  }
//
//  public void makeOBX(StringBuilder ack, int position, CodedEntity obsId, Date obsValue, int subId)
//  {
//    makeOBX(ack, position, obsId, obsValue, null, subId);
//  }
//
//  public void makeOBX(StringBuilder ack, int position, CodedEntity obsId, Date obsValue, Date date, int subId)
//  {
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//    ack.append("OBX|");
//    ack.append(position + "|");
//    ack.append("CE|");
//    ack.append(makeCodedValue(obsId) + "|");
//    ack.append(subId + "|");
//    ack.append(sdf.format(obsValue) + "|");
//    ack.append("|");
//    ack.append("|");
//    ack.append("|");
//    ack.append("|");
//    ack.append("|");
//    ack.append("F|");
//    if (date != null)
//    {
//      ack.append("|");
//      ack.append("|");
//      ack.append(sdf.format(date));
//      ack.append("|");
//    }
//
//    ack.append("\r");
//  }
//
//  public void makeRXR(StringBuilder ack, Vaccination vaccination)
//  {
//    ack.append("RXR|");
//
//    ack.append(makeCodedValue(vaccination.getBodyRoute(), "HL70162") + "|");
//    ack.append(makeCodedValue(vaccination.getBodySite(), "HL70163") + "|");
//    ack.append("\r");
//  }
//
//  public void makeRXA(StringBuilder ack, Vaccination vaccination)
//  {
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//    ack.append("RXA|");
//    ack.append("0|");
//    ack.append("1|");
//    ack.append(sdf.format(vaccination.getAdminDate()) + "|");
//    ack.append("|");
//    ack.append(makeCodedValue(vaccination.getAdminCvx(), "CVX") + "|");
//    if (vaccination.getAmount() == null || vaccination.getAmount().equals(""))
//    {
//      ack.append("999|");
//      ack.append("|");
//    } else
//    {
//      ack.append(vaccination.getAmount() + "|");
//      ack.append(makeCodedValue(vaccination.getAmountUnit(), "UCUM") + "|");
//    }
//    ack.append("|");
//    ack.append(makeCodedValue(vaccination.getInformationSource(), "NIP001") + "|");
//    ack.append("|");
//    ack.append("|");
//    ack.append("|");
//    ack.append("|");
//    ack.append("|");
//    ack.append(vaccination.getLotNumber() + "|");
//    if (vaccination.getExpirationDate() != null)
//    {
//      ack.append(sdf.format(vaccination.getExpirationDate()));
//    }
//    ack.append("|");
//    ack.append(makeCodedValue(vaccination.getManufacturer(), "MVX") + "|");
//    ack.append(makeCodedValue(vaccination.getRefusal(), "NIP002") + "|");
//    ack.append("|");
//    ack.append(vaccination.getCompletionCode() + "|");
//    ack.append(vaccination.getActionCode() + "|");
//    ack.append("\r");
//  }
//
//  public void makeRXA998(StringBuilder ack)
//  {
//    Date today = new Date();
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//    ack.append("RXA|");
//    ack.append("0|");
//    ack.append("1|");
//    ack.append(sdf.format(today) + "|");
//    ack.append("|");
//    ack.append("998^No vaccine administered^CVX|");
//    ack.append("999|");
//    ack.append("\r");
//  }
//
//  private String makeCodedValue(CodedEntity codedEntity)
//  {
//    return makeCodedValue(codedEntity, null);
//  }
//
//  private String makeCodedValue(CodedEntity codedEntity, String codeTableNameOverride)
//  {
//    if (codedEntity == null || codedEntity.getCode() == null || codedEntity.getCode().equals(""))
//    {
//      return "";
//    }
//    CodeMaster codeMaster = CodeMasterManager.getCodeMaster(codedEntity, null, session);
//    if (codeMaster != null && codeMaster.getCodeLabel() != null)
//    {
//      codedEntity.setText(codeMaster.getCodeLabel().trim());
//    }
//    if (codeTableNameOverride != null)
//    {
//      codedEntity.setTable(codeTableNameOverride);
//    }
//    String part1 = codedEntity.getCode() + "^" + codedEntity.getText() + "^" + codedEntity.getTable();
//    if (codedEntity.getAltCode().equals(""))
//    {
//      return part1;
//    }
//    String part2 = codedEntity.getAltCode() + "^" + codedEntity.getAltText() + "^" + codedEntity.getAltTable();
//    return part1 + "^" + part2;
//
//  }
//
//  public void makeORC(StringBuilder ack, Vaccination vaccination)
//  {
//    ack.append("ORC|");
//    ack.append("RE|");
//    ack.append(vaccination.getIdSubmitter() + "|");
//    ack.append(vaccination.getVaccinationId() + "^DQA|");
//    ack.append("\r");
//  }
//
//  public void makeORC(StringBuilder ack, String id)
//  {
//    ack.append("ORC|");
//    ack.append("RE|");
//    ack.append("|");
//    ack.append(id + "^DQA|");
//    ack.append("\r");
//  }
//
//  private void makeQPD(QueryReceived queryReceived, StringBuilder ack)
//  {
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//    ack.append("QPD|");
//    ack.append(queryReceived.getMessageQueryName() + "|");
//    ack.append(queryReceived.getQueryTag() + "|");
//    Patient patient = queryReceived.getPatient();
//    if (patient != null)
//    {
//      ack.append(patient.getIdSubmitterNumber() + "^^^" + patient.getIdSubmitterAssigningAuthorityCode() + "^" + patient.getIdSubmitterTypeCode()
//          + "|");
//      ack.append(patient.getNameLast() + "^" + patient.getNameFirst() + "^" + patient.getNameMiddle() + "^" + patient.getNameSuffix() + "^"
//          + patient.getNamePrefix() + "^^" + patient.getNameTypeCode() + "|");
//      ack.append(patient.getMotherMaidenName() + "|");
//      if (patient.getBirthDate() != null)
//      {
//        ack.append(sdf.format(patient.getBirthDate()) + "|");
//      } else
//      {
//        ack.append("|");
//      }
//      ack.append(patient.getSexCode() + "|");
//      Address add = patient.getAddress();
//      printAddress(ack, add);
//      ack.append("|");
//      PhoneNumber phone = patient.getPhone();
//      printPhone(ack, phone);
//      ack.append("|");
//      ack.append(patient.getBirthMultiple() + "|");
//      ack.append(patient.getBirthOrderCode() + "|");
//    }
//    ack.append("\r");
//  }
//
//  private void makeNK1(StringBuilder ack, NextOfKin nextOfKin, int position)
//  {
//    ack.append("NK1|");
//    ack.append(position + "|");
//    ack.append(nextOfKin.getNameLast() + "^" + nextOfKin.getNameFirst() + "^" + nextOfKin.getNameMiddle() + "^" + nextOfKin.getNameSuffix() + "^"
//        + nextOfKin.getNamePrefix() + "^^" + nextOfKin.getNameTypeCode() + "|");
//    ack.append(makeCodedValue(nextOfKin.getRelationship(), "HL70063") + "|");
//    printAddress(ack, nextOfKin.getAddress());
//    ack.append("|");
//    printPhone(ack, nextOfKin.getPhone());
//    ack.append("|");
//    ack.append("\r");
//  }
//
//  private void makePID(QueryResult queryResult, StringBuilder ack)
//  {
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//    ack.append("PID|");
//    ack.append("|");
//    ack.append("|");
//    Patient patient = queryResult.getPatient();
//    if (patient != null)
//    {
//
//      ack.append(patient.getIdSubmitterNumber() + "^^^" + patient.getIdSubmitterAssigningAuthorityCode() + "^" + patient.getIdSubmitterTypeCode());
//      if (!patient.getIdMedicaid().isEmpty())
//      {
//        ack.append("~" + patient.getIdMedicaid().getNumber() + "^^^OIS^MA");
//      }
//      if (!patient.getIdSsn().isEmpty())
//      {
//        ack.append("~" + patient.getIdSsn().getNumber() + "^^^USA^SS");
//      }
//      ack.append("|");
//      ack.append("|");
//      ack.append(patient.getNameLast() + "^" + patient.getNameFirst() + "^" + patient.getNameMiddle() + "^" + patient.getNameSuffix() + "^"
//          + patient.getNamePrefix() + "^^" + patient.getNameTypeCode());
//      if ((patient.getAliasFirst() != null && !patient.getAliasFirst().equals(""))
//          || (patient.getAliasLast() != null && !patient.getAliasLast().equals("")))
//      {
//        ack.append("~" + (patient.getAliasLast() == null ? "" : patient.getAliasLast()) + "^"
//            + (patient.getAliasFirst() == null ? "" : patient.getAliasFirst()) + "^^^^^A");
//      }
//      ack.append("|");
//      ack.append(patient.getMotherMaidenName() + "|");
//      if (patient.getBirthDate() != null)
//      {
//        ack.append(sdf.format(patient.getBirthDate()) + "|");
//      } else
//      {
//        ack.append("|");
//      }
//      ack.append(patient.getSexCode() + "|");
//      ack.append("|");
//      ack.append(makeCodedValue(patient.getRace(), "HL70005") + "|");
//      printAddress(ack, patient);
//      ack.append("|");
//      ack.append("|");
//      printPhone(ack, patient);
//      ack.append("|");
//      ack.append("|");
//      ack.append(makeCodedValue(patient.getPrimaryLanguage(), "HL70296") + "|");
//      ack.append("|");
//      ack.append("|");
//      ack.append("|");
//      ack.append("|");
//      ack.append("|");
//      ack.append("|");
//      ack.append(makeCodedValue(patient.getEthnicity(), "CDCREC") + "|");
//      ack.append("|");
//      ack.append(patient.getBirthMultiple() + "|");
//      ack.append(patient.getBirthOrderCode() + "|");
//    }
//    ack.append("\r");
//  }
//
//  public void printAddress(StringBuilder ack, Patient patient)
//  {
//    boolean first = true;
//    for (PatientAddress patientAddress : patient.getPatientAddressList())
//    {
//      if (!first)
//      {
//        ack.append("~");
//      }
//      printAddress(ack, patientAddress);
//      first = false;
//    }
//  }
//
//  public void printPhone(StringBuilder ack, Patient patient)
//  {
//    boolean first = true;
//    for (PatientPhone patientPhone : patient.getPatientPhoneList())
//    {
//      if (!first)
//      {
//        ack.append("~");
//      }
//      printPhone(ack, patientPhone);
//      first = false;
//    }
//  }
//
//  public void printPhone(StringBuilder ack, PhoneNumber phone)
//  {
//    if (phone != null && !phone.getNumber().equals(""))
//    {
//      if (phone.getTelUseCode().equals(""))
//      {
//        phone.setTelUseCode("PRN");
//      }
//      if (phone.getTelEquipCode().equals(""))
//      {
//        phone.setTelEquipCode("PH");
//      }
//      ack.append("^" + phone.getTelUseCode() + "^" + phone.getTelEquipCode() + "^" + phone.getEmail() + "^" + phone.getCountryCode() + "^"
//          + phone.getAreaCode() + "^" + phone.getLocalNumber() + "^" + phone.getExtension());
//    }
//  }
//
//  public void printAddress(StringBuilder ack, Address add)
//  {
//    if (add != null)
//    {
//      ack.append(add.getStreet() + "^" + add.getStreet2() + "^" + add.getCity() + "^" + add.getStateCode() + "^" + add.getZip() + "^"
//          + add.getCountryCode() + "^" + add.getTypeCode() + "^^" + add.getCountyParishCode());
//    }
//  }
//
//  private void makeQAK(QueryReceived queryReceived, StringBuilder ack, String queryResponse)
//  {
//    ack.append("QAK|" + queryReceived.getQueryTag() + "|" + queryResponse + "|" + queryReceived.getMessageQueryName() + "|\r");
//  }
//
//  private void makeMSA(QueryReceived queryReceived, StringBuilder ack)
//  {
//    ack.append("MSA|AA|" + queryReceived.getMessageHeader().getMessageControl() + "|\r");
//  }
//
//  public String makeAckMessage(MessageReceived messageReceived)
//  {
//    String controlId = messageReceived.getMessageHeader().getMessageControl();
//    String processingId = message.getMessageHeader().getProcessingStatusCode();
//    String ackCode = ACK_ACCEPT;
//    String hl7ErrorCode = "0";
//    String severity = "I";
//    int countVaccNotSkipped = 0;
//    for (Vaccination vaccination : messageReceived.getVaccinations())
//    {
//      if (!vaccination.isSkipped())
//      {
//        countVaccNotSkipped++;
//      }
//    }
//    String text = "Message accepted";
//    if (countVaccNotSkipped == 0)
//    {
//      text = "Message accepted with no vaccinations";
//    } else if (countVaccNotSkipped == 1)
//    {
//      text = "Message accepted with 1 vaccination";
//    } else
//    {
//      text = "Message accepted with " + countVaccNotSkipped + " vaccinations";
//    }
//    PotentialIssue potentialIssue = null;
//    if (messageReceived.getPatient().isSkipped())
//    {
//      text = "Message skipped: Message did not have any errors but because of business rules listed above, none of the information submitted will be accepted";
//      ackCode = ACK_ACCEPT;
//      severity = "I";
//      for (IssueFound issueFound : messageReceived.getIssuesFound())
//      {
//        if (issueFound.isSkip())
//        {
//          // text += issueFound.getDisplayText();
//          potentialIssue = issueFound.getIssue();
//          break;
//        }
//      }
//    } else if (hasErrors(messageReceived))
//    {
//      text = "Message rejected: Because of serious problems encountered none of the information in this message will be accepted (see error details above)";
//      ackCode = ACK_ERROR;
//      severity = "E";
//      for (IssueFound issueFound : messageReceived.getIssuesFound())
//      {
//        if (issueFound.isError())
//        {
//          // text += issueFound.getDisplayText();
//
//          potentialIssue = issueFound.getIssue();
//          hl7ErrorCode = issueFound.getIssue().getHl7ErrorCode();
//          if (hl7ErrorCode != null && hl7ErrorCode.startsWith("2"))
//          {
//            ackCode = ACK_REJECT;
//          }
//          break;
//        }
//      }
//    }
//    StringBuilder ack = new StringBuilder();
//    makeHeader(ack, message, null, null);
//    ack.append("SFT|" + SoftwareVersion.VENDOR + "|" + SoftwareVersion.VERSION + "|" + SoftwareVersion.PRODUCT + "|" + SoftwareVersion.BINARY_ID
//        + "|\r");
//    ack.append("MSA|" + ackCode + "|" + controlId + "|\r");
//    for (IssueFound issueFound : messageReceived.getIssuesFound())
//    {
//      if (issueFound.isError())
//      {
//        HL7Util.makeERRSegment(ack, issueFound);
//      }
//    }
//    for (IssueFound issueFound : messageReceived.getIssuesFound())
//    {
//      if (issueFound.isWarn())
//      {
//        HL7Util.makeERRSegment(ack, issueFound);
//      }
//      if (issueFound.isSkip())
//      {
//        HL7Util.makeERRSegment(ack, issueFound);
//      }
//    }
//    if (processingId.equals(PROCESSING_ID_DEBUG))
//    {
//      for (IssueFound issueFound : messageReceived.getIssuesFound())
//      {
//        if (issueFound.isAccept())
//        {
//          HL7Util.makeERRSegment(ack, issueFound);
//        }
//      }
//    }
//    HL7Util.makeERRSegment(ack, severity, hl7ErrorCode, text, null);
//    return ack.toString();
//
//  }
//
//  public static void makeHeader(StringBuilder ack, MessageReceivedGeneric message, String profileId, String responseType)
//  {
//    String receivingApplication = message.getMessageHeader().getSendingApplication();
//    String receivingFacility = message.getMessageHeader().getSendingFacility();
//    String sendingApplication = message.getMessageHeader().getReceivingApplication();
//    String sendingFacility = message.getMessageHeader().getReceivingFacility();
//    if (receivingApplication == null)
//    {
//      receivingApplication = "";
//    }
//    if (receivingFacility == null)
//    {
//      receivingFacility = "";
//    }
//    if (sendingApplication == null || sendingApplication.equals(""))
//    {
//      sendingApplication = "MCIR";
//    }
//    if (sendingFacility == null || sendingFacility.equals(""))
//    {
//      sendingFacility = "MCIR";
//    }
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssZ");
//    String messageDate = sdf.format(new Date());
//    // MSH
//    ack.append("MSH|^~\\&");
//    ack.append("|" + sendingApplication); // MSH-3 Sending Application
//    ack.append("|" + sendingFacility); // MSH-4 Sending Facility
//    ack.append("|" + receivingApplication); // MSH-5 Receiving Application
//    ack.append("|" + receivingFacility); // MSH-6 Receiving Facility
//    ack.append("|" + messageDate); // MSH-7 Date/Time of Message
//    ack.append("|"); // MSH-8 Security
//    if (responseType == null)
//    {
//      responseType = "ACK^" + message.getMessageHeader().getMessageTrigger() + "^" + message.getMessageHeader().getMessageStructure();
//    }
//    ack.append("|" + responseType); // MSH-9
//    // Message
//    // Type
//    ack.append("|" + messageDate + "." + getNextAckCount()); // MSH-10 Message
//                                                             // Control ID
//    ack.append("|P"); // MSH-11 Processing ID
//    ack.append("|2.5.1"); // MSH-12 Version ID
//    ack.append("|");
//    if (profileId != null)
//    {
//      ack.append("||NE|AL|||||" + profileId + "^CDCPHINVS|");
//    }
//    ack.append("\r");
//
//  }
//
//  private static boolean hasErrors(MessageReceivedGeneric messageReceived)
//  {
//    for (IssueFound issueFound : messageReceived.getIssuesFound())
//    {
//      if (issueFound.getIssueAction().equals(IssueAction.ERROR))
//      {
//        return true;
//      }
//    }
//    return false;
//  }
//
//  @Override
//  public void createQueryMessage(QueryReceived queryReceived)
//  {
//    String messageText = queryReceived.getRequestText();
//
//    issuesFound = queryReceived.getIssuesFound();
//    setup();
//    boolean okayToParse = readSeparators(messageText);
//    if (!okayToParse)
//    {
//      return;
//    }
//    readFields(messageText);
//
//    patient = new Patient();
//    queryReceived.setPatient(patient);
//
//    currentSegment = segments.get(0);
//    populateMSH(queryReceived);
//    while (moveNext())
//    {
//      if (segmentName.equals("QPD"))
//      {
//        populateQPD(queryReceived);
//      } else if (segmentName.equals("RCP"))
//      {
//        populateRCP(queryReceived);
//      }
//    }
//  }
//
//  private void populateQPD(QueryReceived queryReceived)
//  {
//
//    // 1 MessageQueryName should be Z34
//    queryReceived.setMessageQueryName(getValue(1));
//    // 2 QueryTag
//    queryReceived.setQueryTag(getValue(2));
//    // 3 PatientList
//    readPatientId(3, patient);
//    // 4 PatientName
//    readName(4, patient.getName());
//    // 5 PatientMotherMaiden
//    patient.setMotherMaidenName(getValue(5));
//    // 6 Patient Date of Birth
//    patient.setBirthDate(getValueDate(6, pi.PatientBirthDateIsInvalid, null));
//    // 7 Patient Sex
//    patient.setSexCode(getValue(7));
//    // 8 Patient Address
//    readAddress(8, patient.getAddress());
//    // 9 Patient home phone
//    readPhoneNumber(9, patient.getPhone());
//    // 10 Patient multiple birth indicator
//    patient.setBirthMultiple(getValue(10));
//    // 11 Patient birth order
//    patient.setBirthOrderCode(getValue(11));
//    // 12 client last updated date
//    // 13 client last update facility
//
//  }
//
//  private void populateRCP(QueryReceived queryReceived)
//  {
//
//    // not currently supporting fields in this one
//  }
//
//}
