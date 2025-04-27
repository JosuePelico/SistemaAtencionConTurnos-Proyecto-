package com.covid.covid19stats;

import com.covid.covid19stats.service.ReportService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *
 * @author rodol
 */
@SpringBootApplication
@EnableScheduling
public class Covid19Stats {
private static final Logger logger = LogManager.getLogger(Covid19Stats.class);
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Covid19Stats.class, args);

   
        ReportService reportService = context.getBean(ReportService.class);

        logger.info("\n===== Loading Guatemala Reports (GTM) =====\n");

        reportService.showReports("GTM");

        logger.info("\n===== General Stats =====\n");

        reportService.showStatistics();

        logger.info("\n===== Confirmed Cases Graphic =====\n");

        reportService.showGraph();

         logger.info("\n===== Exporting to txt =====\n");

        reportService.exportReport();

         logger.info("\nProcess finished.");
    }
}
