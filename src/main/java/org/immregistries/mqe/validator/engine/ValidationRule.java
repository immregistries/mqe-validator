package org.immregistries.mqe.validator.engine;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.immregistries.mqe.core.util.DateUtility;
import org.immregistries.mqe.validator.ValidatorProperties;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.codes.CodeRepository;
import org.immregistries.mqe.validator.engine.common.CodeHandler;
import org.immregistries.mqe.validator.engine.common.CommonRules;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.VxuField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A pass of a Validation Rule means that the concept expressed in the name is true.
 *
 * @author Josh
 */
@SuppressWarnings("rawtypes")
public abstract class ValidationRule<T> {

  protected static final Logger LOGGER = LoggerFactory.getLogger(ValidationRule.class);
  /**
   * I wanted this to be a singleton because I know I'll have tons of rule objects floating around
   * and I wanted to limit the number of util objects.
   */
  private final Set<Detection> ruleDetections = new HashSet<>();
  private final Set<ImplementationDetail> ruleDocumentation = new HashSet<>();
  protected final ValidationUtility util = ValidationUtility.INSTANCE;
  protected final CommonRules common = CommonRules.INSTANCE;
  protected final CodeHandler codr = CodeHandler.INSTANCE;
  protected final DateUtility datr = DateUtility.INSTANCE;
  protected final CodeRepository repo = CodeRepository.INSTANCE;
  protected final ValidatorProperties props = ValidatorProperties.INSTANCE;

  protected Class[] getDependencies() {
    return new Class[] {};
  }
  

  /**
   * This is the primary method to call for this class from a validation driver.
   */
  public final ValidationRuleResult evaluate(T target, MqeMessageReceived m) {
    try {
      return executeRule(target, m);
    } catch (Exception e) {
      LOGGER.error("Error running rule - " + this.getClass() + " problem: " + e.getMessage(), e);
      ValidationReport[] issues =
          new ValidationReport[] {Detection.GeneralProcessingException.build(this.getClass()
              .getName(), null)};
      return buildResults(Arrays.asList(issues), false);
    }
  }

  /**
   * This builds a results object and determines if issues represent a failure or a pass.
   */
  protected ValidationRuleResult buildResults(List<ValidationReport> issues, boolean passed) {
    ValidationRuleResult result = new ValidationRuleResult();
    result.setRuleClass(this.getClass());
    result.setValidationDetections(issues);
    result.setRulePassed(passed);
    result.getPossible().addAll(ruleDetections);
    return result;
  }

  /**
   * This needs to be implemented for each rule. The implementation should evaluate the rule, and
   * return a list of problems found during evaluation.
   */
  protected abstract ValidationRuleResult executeRule(T target, MqeMessageReceived m);

  public boolean dependenciesAreMet(List<Class<? extends ValidationRule>> passedValidations) {
    Class[] dependencies = this.getDependencies();

    // If there are no dependencies, they are already met!
    if (dependencies == null || dependencies.length == 0) {
      return true;
    }

    // At this point there are dependencies. If there aren't any passed, they're not met!
    if (passedValidations == null || passedValidations.size() <= 0) {
      return false;
    }

    // Now we have to make sure none are missing.
    for (Class c : dependencies) {
      if (!passedValidations.contains(c)) {
        return false;
      };
    }

    // At this point, none are missing! Dependencies are met!
    return true;
  }

	public Set<Detection> getRuleDetections() {
		return ruleDetections;
	}
  
	public Set<ImplementationDetail> getImplementationDocumentation() {
		return ruleDocumentation;
	}

//	public void addRuleDetections(Detection detection) {
//		List<Detection> detections = new ArrayList<Detection>();
//		detections.add(detection);
//		this.addRuleDetection(Detections);
//	}
  
//	public void addRuleDetections(List<Detection> detections) {
//		for (Detection detection : detections) {
//			if (detection != null) {
//				this.ruleDetections.add(detection);
//				this.ruleDocumentation.add(new ImplementationDetail(detection));
//			}
//		}
//	}

  public ImplementationDetail addRuleDetection(Detection d) {
    this.ruleDetections.add(d);
    ImplementationDetail id = new ImplementationDetail(d);
    this.ruleDocumentation.add(id);
    id.setSeverityLevel(d.getSeverity());
    return id;
  }

  protected void addRuleDetections(List<Detection> detectionsForField) {
    for (Detection d : detectionsForField) {
      this.addRuleDetection(d);
    }
  }


  protected void addRuleDetectionsForFields(VxuField... fields) {
    for (VxuField f : fields) {
      this.addRuleDetections(this.codr.getDetectionsForField(f));
    }
  }

//	public void addImplementationMessage(Detection detection, String message) {
//		ImplementationDetail implDetail = new ImplementationDetail(detection, message);
//		this.ruleDocumentation.remove(implDetail);
//		this.ruleDocumentation.add(implDetail);
//	}
}
