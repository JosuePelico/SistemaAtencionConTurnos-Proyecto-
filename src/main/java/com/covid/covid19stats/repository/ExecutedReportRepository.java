
package com.covid.covid19stats.repository;

import com.covid.covid19stats.model.ExecutedReport;
import com.covid.covid19stats.model.ExecutedReportId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for accessing executed_reports table
 */
@Repository
public interface ExecutedReportRepository extends JpaRepository<ExecutedReport, ExecutedReportId> {
}
