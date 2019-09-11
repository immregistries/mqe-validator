package org.immregistries.mqe.validator.detection;

import java.util.Objects;
import org.immregistries.mqe.hl7util.SeverityLevel;

public class ImplementationDetail {
	private Detection detection;
	private String description; /* implementation notes */
	private String howToFix;
	private String whyToFix;
	private SeverityLevel severityLevel;

	public SeverityLevel getSeverityLevel() {
		return severityLevel;
	}

	public void setSeverityLevel(SeverityLevel severityLevel) {
		this.severityLevel = severityLevel;
	}

	public void setImplementationDescription(String description) {
		this.description = description;
	}

	public String getImplementationDescription() {
		return this.description;
	}

	public String getHowToFix() {
		return howToFix;
	}

	public void setHowToFix(String howToFix) {
		this.howToFix = howToFix;
	}

	public String getWhyToFix() {
		return whyToFix;
	}

	public void setWhyToFix(String whyToFix) {
		this.whyToFix = whyToFix;
	}

	public ImplementationDetail(Detection detection) {
		this.detection = detection;
		if (detection != null) {
			this.description = detection.getDetectionType().getDescription();
			this.severityLevel = detection.getSeverity();
		}
	}

	public Detection getDetection() {
		return detection;
	}

	@Override
	public String toString() {
		return "ImplementationDetail{" +
				"detection=" + detection +
				", description='" + description + '\'' +
				", howToFix='" + howToFix + '\'' +
				", whyToFix='" + whyToFix + '\'' +
				", severityLevel=" + severityLevel +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ImplementationDetail that = (ImplementationDetail) o;
		return detection == that.detection &&
				Objects.equals(description, that.description) &&
				Objects.equals(howToFix, that.howToFix) &&
				Objects.equals(whyToFix, that.whyToFix) &&
				severityLevel == that.severityLevel;
	}

	@Override
	public int hashCode() {
		return Objects.hash(detection, description, howToFix, whyToFix, severityLevel);
	}
}
