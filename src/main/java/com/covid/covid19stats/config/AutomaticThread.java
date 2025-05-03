
package com.covid.covid19stats.config;

import com.covid.covid19stats.model.ExecutedReport;
import com.covid.covid19stats.model.ExecutedReportId;
import com.covid.covid19stats.repository.ExecutedReportRepository;
import com.covid.covid19stats.service.ProvinceService;
import com.covid.covid19stats.service.RegionService;
import com.covid.covid19stats.service.ReportService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Automatic thread to fetch and store COVID-19 data, with execution control
 */
@Component
public class AutomaticThread {

    private static final Logger logger = LogManager.getLogger(AutomaticThread.class);

    @Autowired
    private RegionService regionService;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private ExecutedReportRepository executedReportRepository;

    @Value("${covid19stats.default-iso}")
    private String defaultIso;

    @Value("${covid.report.date}")
    private String reportDate; // Format yyyy-MM-dd

    @Scheduled(initialDelay = 15000, fixedDelay = Long.MAX_VALUE)
    public void autoRunCovidDataLoad() {
        logger.info("Starting Automatic Thread for COVID-19 data load...");

        try {
            LocalDate date = LocalDate.parse(reportDate);

            ExecutedReportId executedReportId = new ExecutedReportId(date, defaultIso);

            if (executedReportRepository.existsById(executedReportId)) {
                logger.info("Execution skipped: Country {} already processed for date {}", defaultIso, reportDate);
                return;
            }

            regionService.fetchAndSaveRegions();
            provinceService.fetchAndSaveProvinces(defaultIso);
            reportService.fetchAndSaveReports(defaultIso);

            ExecutedReport executedReport = new ExecutedReport(date, defaultIso);
            executedReportRepository.save(executedReport);

            logger.info("Execution completed and recorded for Country: {} on Date: {}", defaultIso, reportDate);

        } catch (Exception e) {
            logger.error("Error during Automatic Thread execution: ", e);
        }
    }
}
