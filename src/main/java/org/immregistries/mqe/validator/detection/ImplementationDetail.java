package org.immregistries.mqe.validator.detection;

public class ImplementationDetail {
	private Detection detection;
	private String description;
	
	public ImplementationDetail(Detection detection) {
		this.setDetection(detection);
		this.description = detection.getDetectionType().getDescription();
	}
	
	public ImplementationDetail(Detection detection, String description) {
		this.setDetection(detection);
		this.description = description;
	}

	public Detection getDetection() {
		return detection;
	}

	public void setDetection(Detection detection) {
		this.detection = detection;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "ImplementationDetail [detection=" + detection.name() + ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((detection == null) ? 0 : detection.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImplementationDetail other = (ImplementationDetail) obj;
		if (detection != other.detection)
			return false;
		return true;
	}
	

}
