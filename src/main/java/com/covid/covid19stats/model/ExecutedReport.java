
package com.covid.covid19stats.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Entity for executed_reports table
 */
@Entity
@Table(name = "executed_reports")
@IdClass(ExecutedReportId.class)
public class ExecutedReport implements Serializable {

    @Id
    private LocalDate executionDate;

    @Id
    private String countryIso;

    public ExecutedReport() {
    }

    public ExecutedReport(LocalDate executionDate, String countryIso) {
        this.executionDate = executionDate;
        this.countryIso = countryIso;
    }

    public LocalDate getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(LocalDate executionDate) {
        this.executionDate = executionDate;
    }

    public String getCountryIso() {
        return countryIso;
    }

    public void setCountryIso(String countryIso) {
        this.countryIso = countryIso;
    }
}
