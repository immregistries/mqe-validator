package org.immregistries.mqe.validator.detection;

/**
 * The values a {@link DetectionStatus} annotation can assign to a {@link Detection} constant. See
 * {@code docs/detection-status-lifecycle.md} for the full rationale and worked examples of each.
 */
public enum DetectionLifecycle {
  /** Reserved code from the original spec, not yet implemented by any rule. */
  PLANNED,
  /** Implemented, wired to a rule, actively maintained. */
  ACTIVE,
  /** Implemented and firing, but logic/thresholds are not yet calibrated or finalized. */
  EXPERIMENTAL,
  /** Implemented and still firing, but no longer actively maintained or tuned. */
  UNSUPPORTED,
  /** No longer fires; kept as an enum stub for backward compatibility with historical data. */
  RETIRED
}
