
package com.covid.covid19stats.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Composite key class for ExecutedReport entity
 */
public class ExecutedReportId implements Serializable {

    private LocalDate executionDate;
    private String countryIso;

    public ExecutedReportId() {
    }

    public ExecutedReportId(LocalDate executionDate, String countryIso) {
        this.executionDate = executionDate;
        this.countryIso = countryIso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExecutedReportId)) return false;
        ExecutedReportId that = (ExecutedReportId) o;
        return Objects.equals(executionDate, that.executionDate) &&
               Objects.equals(countryIso, that.countryIso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(executionDate, countryIso);
    }
}
