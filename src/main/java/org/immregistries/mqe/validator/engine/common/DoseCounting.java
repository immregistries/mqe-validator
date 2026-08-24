package org.immregistries.mqe.validator.engine.common;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.joda.time.DateTime;

/**
 * Small shared helper for counting administered doses that match a CVX code set and a date
 * condition - used by the dose-count-anomaly ("possible mismerge") signals in issues #101 and
 * #102. Deliberately just a counting loop, not a config-driven mechanism: both issues were
 * explicitly filed as hard-coded, one-off rules (see docs/changes-needed-2028-08.md's design
 * discussion under #3312/#3313 for why a general mechanism was considered and set aside for now).
 */
public final class DoseCounting {

  private DoseCounting() {}

  /**
   * @param vaccinations doses to consider (typically {@code MqeMessageReceived.getVaccinations()})
   * @param cvxCodes CVX codes to count; a dose not in this set is ignored
   * @param dateMatches applied to each matching dose's admin date; only doses where this returns
   *     true are counted
   * @return count of doses whose CVX code is in {@code cvxCodes}, has a non-null admin date, and
   *     whose admin date satisfies {@code dateMatches}
   */
  public static int countMatchingDoses(List<MqeVaccination> vaccinations, Set<String> cvxCodes,
      Predicate<DateTime> dateMatches) {
    int count = 0;
    for (MqeVaccination v : vaccinations) {
      if (cvxCodes.contains(v.getAdminCvxCode()) && v.getAdminDate() != null) {
        DateTime adminDate = new DateTime(v.getAdminDate());
        if (dateMatches.test(adminDate)) {
          count++;
        }
      }
    }
    return count;
  }
}
