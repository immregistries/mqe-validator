package org.immregistries.mqe.validator.detection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a {@link Detection} constant with its lifecycle status - independent of whether it's
 * currently wired to a rule. A detection can be wired *and* experimental, or wired *and*
 * unsupported; "wired" and "lifecycle status" are orthogonal concerns. See
 * {@code docs/detection-status-lifecycle.md} for the full rationale and worked examples of each
 * {@link DetectionLifecycle} value.
 *
 * <p>Independent from {@link Documentation}: {@code @Documentation} is the stable conceptual
 * definition of what a detection means; {@code @DetectionStatus} is its lifecycle/maintenance
 * state. A detection can (and should) carry both.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DetectionStatus {
  DetectionLifecycle status();

  /** The version or date this status was assigned, e.g. {@code "1.5.0"} or {@code "2026-08"}. */
  String since();
}
