package org.immregistries.mqe.validator.engine;

import java.util.*;
import static org.junit.Assert.assertEquals;

import org.immregistries.mqe.validator.detection.MqeCode;
import org.immregistries.mqe.validator.engine.rules.ValidationRuleDescriptor;
import org.immregistries.mqe.validator.engine.rules.ValidationRuleEntityLists;
import org.immregistries.mqe.validator.engine.rules.ValidationRuleEntry;
import org.immregistries.mqe.validator.engine.rules.ValidationRuleHolder;
import org.immregistries.mqe.validator.engine.rules.patient.PatientBirthDateIsValid;
import org.immregistries.mqe.validator.engine.rules.patient.PatientExists;
import org.immregistries.mqe.validator.engine.rules.patient.VaccinationAdminCountIsAsExpectedForAge;
import org.immregistries.mqe.vxu.TargetType;
import org.junit.Before;
import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import static org.immregistries.mqe.validator.engine.ValidationRuleService.RULE_PACKAGE;
import static org.junit.Assert.assertTrue;

@SuppressWarnings({"unchecked", "rawtypes"})
public class RulePairBuilderTest {
  RulePairBuilder rpb = RulePairBuilder.INSTANCE;

  @Before
  public void reset() {
    rpb.resetRules();
  }

  public Set<Class<? extends ValidationRule>> getValidationRulesInPackage(String pkg) {
    final String PACKAGE = pkg;
    List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
    classLoadersList.add(ClasspathHelper.contextClassLoader());
    classLoadersList.add(ClasspathHelper.staticClassLoader());

    Reflections reflections = new Reflections(new ConfigurationBuilder()
            .setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
            .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
            .addUrls(ClasspathHelper.forClass(ValidationRule.class))
            .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(PACKAGE))));

    Set<Class<? extends ValidationRule>> allPackageRules = reflections.getSubTypesOf(ValidationRule.class);
    return allPackageRules;
  }

  @Test
  public void allPatientRulesInPackageAreAnnotatedCorrectlyAndAddedToValidationRuleEntityLists() {
    String pkg = RULE_PACKAGE + ".engine.rules.patient";
    Set<Class<? extends ValidationRule>> classes = getValidationRulesInPackage(pkg);
    int numberOfPatientRulesInPackage = classes.size();
    boolean allAreAnnotatedCorrectly = classes.stream().allMatch((clazz) -> clazz.getAnnotation(ValidationRuleEntry.class).value().equals(TargetType.Patient));
    Set<ValidationRule> PATIENT_RULES = ValidationRuleEntityLists.PATIENT_RULES.getRules();
    assertEquals("All rules in package " + pkg + " are added to ValidationRuleEntityLists.PATIENT_RULES", PATIENT_RULES.size(), numberOfPatientRulesInPackage);
    assertTrue("All rules in package " + pkg + " are annotated with TargetType.Patient", allAreAnnotatedCorrectly);
  }

  @Test
  public void allVaccinationRulesInPackageAreAnnotatedCorrectlyAndAddedToValidationRuleEntityLists() {
    String pkg = RULE_PACKAGE + ".engine.rules.vaccination";
    Set<Class<? extends ValidationRule>> classes = getValidationRulesInPackage(pkg);
    int numberOfVaccinationRulesInPackage = classes.size();
    boolean allAreAnnotatedCorrectly = classes.stream().allMatch((clazz) -> clazz.getAnnotation(ValidationRuleEntry.class).value().equals(TargetType.Vaccination));
    Set<ValidationRule> VACCINATION_RULES = ValidationRuleEntityLists.VACCINATION_RULES.getRules();
    assertEquals("All rules in package " + pkg + " are added to ValidationRuleEntityLists.VACCINATION_RULES", VACCINATION_RULES.size(), numberOfVaccinationRulesInPackage);
    assertTrue("All rules in package " + pkg + " are annotated with TargetType.Vaccination", allAreAnnotatedCorrectly);
  }

  @Test
  public void allNextOfKinRulesInPackageAreAnnotatedCorrectlyAndAddedToValidationRuleEntityLists() {
    String pkg = RULE_PACKAGE + ".engine.rules.nextofkin";
    Set<Class<? extends ValidationRule>> classes = getValidationRulesInPackage(pkg);
    int numberOfNextOfKinRulesInPackage = classes.size();
    boolean allAreAnnotatedCorrectly = classes.stream().allMatch((clazz) -> clazz.getAnnotation(ValidationRuleEntry.class).value().equals(TargetType.NextOfKin));
    Set<ValidationRule> NEXT_OF_KIN_RULES = ValidationRuleEntityLists.NEXT_OF_KIN_RULES.getRules();
    assertEquals("All rules in package " + pkg + " are added to ValidationRuleEntityLists.NEXT_OF_KIN_RULES", NEXT_OF_KIN_RULES.size(), numberOfNextOfKinRulesInPackage);
    assertTrue("All rules in package " + pkg + " are annotated with TargetType.NextOfKin", allAreAnnotatedCorrectly);
  }

  @Test
  public void allMsgHeaderRulesInPackageAreAnnotatedCorrectlyAndAddedToValidationRuleEntityLists() {
    String pkg = RULE_PACKAGE + ".engine.rules.header";
    Set<Class<? extends ValidationRule>> classes = getValidationRulesInPackage(pkg);
    int numberOfMsgHeaderRulesInPackage = classes.size();
    boolean allAreAnnotatedCorrectly = classes.stream().allMatch((clazz) -> clazz.getAnnotation(ValidationRuleEntry.class).value().equals(TargetType.MessageHeader));
    Set<ValidationRule> MESSAGE_HEADER_RULES = ValidationRuleEntityLists.MESSAGE_HEADER_RULES.getRules();
    assertEquals("All rules in package " + pkg + " are added to ValidationRuleEntityLists.MESSAGE_HEADER_RULES", MESSAGE_HEADER_RULES.size(), numberOfMsgHeaderRulesInPackage);
    assertTrue("All rules in package " + pkg + " are annotated with TargetType.MessageHeader", allAreAnnotatedCorrectly);
  }

  @Test
  public void allRulesInValidationRuleEntityListsHaveCorrectTargetType() {
    assertEquals("ValidationRuleEntityLists.PATIENT_RULES has a TargetType.Patient", ValidationRuleEntityLists.PATIENT_RULES.getTargetType(), TargetType.Patient);
    assertEquals("ValidationRuleEntityLists.NEXT_OF_KIN_RULES has a TargetType.NextOfKin", ValidationRuleEntityLists.NEXT_OF_KIN_RULES.getTargetType(), TargetType.NextOfKin);
    assertEquals("ValidationRuleEntityLists.VACCINATION_RULES has a TargetType.Patient", ValidationRuleEntityLists.VACCINATION_RULES.getTargetType(), TargetType.Vaccination);
    assertEquals("ValidationRuleEntityLists.MESSAGE_HEADER_RULES has a TargetType.MessageHeader", ValidationRuleEntityLists.MESSAGE_HEADER_RULES.getTargetType(), TargetType.MessageHeader);
  }

  @Test
  public void byDefaultAllRulesAreActive() {
    ValidationRuleHolder validationRuleHolder = rpb.getActiveValidationRules();
    assertTrue("Default ValidationRuleHolder patient contains all ValidationRuleEntityLists.PATIENT_RULES", validationRuleHolder.getPatientRules().stream().allMatch(
            ValidationRuleEntityLists.PATIENT_RULES.getRules()::contains
    ) && ValidationRuleEntityLists.PATIENT_RULES.getRules().size() == validationRuleHolder.getPatientRules().size());
    assertTrue("Default ValidationRuleHolder nextOfKin contains all ValidationRuleEntityLists.NEXT_OF_KIN_RULES", validationRuleHolder.getNextOfKinRules().stream().allMatch(
            ValidationRuleEntityLists.NEXT_OF_KIN_RULES.getRules()::contains
    ) && ValidationRuleEntityLists.NEXT_OF_KIN_RULES.getRules().size() == validationRuleHolder.getNextOfKinRules().size());
    assertTrue("Default ValidationRuleHolder vaccination contains all ValidationRuleEntityLists.VACCINATION_RULES", validationRuleHolder.getVaccinationRules().stream().allMatch(
            ValidationRuleEntityLists.VACCINATION_RULES.getRules()::contains
    ) && ValidationRuleEntityLists.VACCINATION_RULES.getRules().size() == validationRuleHolder.getVaccinationRules().size());
    assertTrue("Default ValidationRuleHolder messageHeader contains all ValidationRuleEntityLists.MESSAGE_HEADER_RULES", validationRuleHolder.getMessageHeaderRules().stream().allMatch(
            ValidationRuleEntityLists.MESSAGE_HEADER_RULES.getRules()::contains
    ) && ValidationRuleEntityLists.MESSAGE_HEADER_RULES.getRules().size() == validationRuleHolder.getMessageHeaderRules().size());
  }

  @Test
  public void activatingMqeCodeAddsRuleAndDependencies() {
    // Dependencies graph : VaccinationAdminCountIsAsExpectedForAge -> PatientBirthDateIsValid -> PatientExists
    // MqeCode : VaccinationAdminCountIsAsExpectedForAge => Detection.AdministeredVaccinationsCountIsLargerThanExpected =>

    rpb.setActiveDetections(Collections.singleton(MqeCode.MQE0568));
    ValidationRuleHolder validationRuleHolder = rpb.getActiveValidationRules();

    assertTrue("RulePairBuilder's ValidationRuleHolder contains 3 rules", validationRuleHolder.getRules().size() == 3);
    assertTrue("RulePairBuilder's ValidationRuleHolder contains VaccinationAdminCountIsAsExpectedForAge", validationRuleHolder.getRules().stream().filter(
            (rule) -> rule instanceof VaccinationAdminCountIsAsExpectedForAge
    ).count() == 1);
    assertTrue("RulePairBuilder's ValidationRuleHolder contains PatientBirthDateIsValid", validationRuleHolder.getRules().stream().filter(
            (rule) -> rule instanceof PatientBirthDateIsValid
    ).count() == 1);
    assertTrue("RulePairBuilder's ValidationRuleHolder contains PatientExists", validationRuleHolder.getRules().stream().filter(
            (rule) -> rule instanceof PatientExists
    ).count() == 1);
  }

  @Test
  public void detectWhenADependencyIsMissing() {
    // Dependencies graph : VaccinationAdminCountIsAsExpectedForAge -> PatientBirthDateIsValid -> PatientExists
    Map<TargetType, List<ValidationRuleDescriptor<?>>> allRuleDefinitions = new HashMap<>();
    allRuleDefinitions.put(TargetType.Patient, Arrays.asList(
            new ValidationRuleDescriptor<>(TargetType.Patient, VaccinationAdminCountIsAsExpectedForAge.class)
    ));

    List<ValidationRule<?>> withUnmetDependencies = ValidationRuleService.getRulesWithUnmetDependencies(allRuleDefinitions);

    assertTrue("List of rules with unmet dependencies contains one rule", withUnmetDependencies.size() == 1);
    assertTrue("List of rules with unmet dependencies contains VaccinationAdminCountIsAsExpectedForAge",
            withUnmetDependencies.get(0) instanceof VaccinationAdminCountIsAsExpectedForAge
    );
  }

  @Test
  public void detectWhenNoDependencyIsMissing() {
    // Dependencies graph : VaccinationAdminCountIsAsExpectedForAge -> PatientBirthDateIsValid -> PatientExists
    Map<TargetType, List<ValidationRuleDescriptor<?>>> allRuleDefinitions = new HashMap<>();
    allRuleDefinitions.put(TargetType.Patient, Arrays.asList(
            new ValidationRuleDescriptor<>(TargetType.Patient, VaccinationAdminCountIsAsExpectedForAge.class),
            new ValidationRuleDescriptor<>(TargetType.Patient, PatientBirthDateIsValid.class),
            new ValidationRuleDescriptor<>(TargetType.Patient, PatientExists.class)
    ));

    List<ValidationRule<?>> withUnmetDependencies = ValidationRuleService.getRulesWithUnmetDependencies(allRuleDefinitions);

    assertTrue("List of rules with unmet dependencies contains 0 rule", withUnmetDependencies.size() == 0);
  }
}
