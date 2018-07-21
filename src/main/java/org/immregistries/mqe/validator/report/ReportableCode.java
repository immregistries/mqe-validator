package org.immregistries.mqe.validator.report;

/**
 * The intention is to generalize the interface for the various types of things we report.
 *
 * @author Josh Hull
 */
public interface ReportableCode {

  String getCodeTable();

  String getCodeValue();
  // Object
}
