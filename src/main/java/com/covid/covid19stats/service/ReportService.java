/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.covid.covid19stats.service;
import com.covid.covid19stats.model.Report;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.covid.covid19stats.repository.ReportRepository;
import java.io.PrintWriter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
/**
 *
 * @author rodol
 */
@Service
public class ReportService {
    private static final String API_URL = "https://covid-19-statistics.p.rapidapi.com/reports";
    private static final Logger logger = LogManager.getLogger(ReportService.class);
    
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ReportRepository reportRepository;

public void fetchAndSaveReports(String iso) {
    try {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", "cd21a4ae79mshfd69918880cb8acp1dad3djsn376033f678b5");
        headers.set("X-RapidAPI-Host", "covid-19-statistics.p.rapidapi.com");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        LocalDate randomDate = getRandomDateBetween2020And2023();
        String fullUrl = API_URL + "?iso=" + iso + "&date=" + randomDate.toString();

        logger.info("Consulting reports for ISO: {} in random date: {}", iso, randomDate);

        ResponseEntity<String> response = restTemplate.exchange(
                fullUrl,
                HttpMethod.GET,
                entity,
                String.class
        );

        JSONObject json = new JSONObject(response.getBody());
        JSONArray dataArray = json.getJSONArray("data");

        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject reportJson = dataArray.getJSONObject(i);

            Report report = new Report();
            report.setIso(reportJson.getJSONObject("region").optString("iso", null));
            report.setProvince(reportJson.optString("province", null));
            report.setDate(LocalDate.parse(reportJson.getString("date"))); // Esto lo puedes dejar igual, ya que viene de la API.
            report.setConfirmed(reportJson.optInt("confirmed", 0));
            report.setDeaths(reportJson.optInt("deaths", 0));
            report.setRecovered(reportJson.optInt("recovered", 0));

            reportRepository.save(report);
        }

        logger.info("Reports Saved Successfully for ISO {} at {}", iso, randomDate);

    } catch (Exception e) {
        logger.error("Error consuming reports API: ", e);
    }
}


    private LocalDate getRandomDateBetween2020And2023() {
        LocalDate startDate = LocalDate.of(2020, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);

        long start = startDate.toEpochDay();
        long end = endDate.toEpochDay();

        long randomDay = ThreadLocalRandom.current().nextLong(start, end + 1);

        return LocalDate.ofEpochDay(randomDay);
    }

public void showReports(String iso) {
    List<Report> reports = reportRepository.findByIso(iso);

    if (reports.isEmpty()) {
        logger.info("No reports for country: " + iso);
        return;
    }

    for (Report report : reports) {
        logger.info(report.getDate() + " | " +
                           report.getProvince() + " | " +
                           "Confirmed: " + report.getConfirmed() +
                           ",Deaths: " + report.getDeaths() +
                           ",Cured: " + report.getRecovered());
    }
}
public void showStatistics() {
    List<Report> reports = reportRepository.findAll();

    int totalCases = 0;
    int totalDeaths = 0;
    int totalRecovered = 0;

    for (Report report : reports) {
        totalCases += report.getConfirmed();
        totalDeaths += report.getDeaths();
        totalRecovered += report.getRecovered();
    }
    logger.info("Confirmed Cases: " + totalCases);
    logger.info("Total Deaths: " + totalDeaths);
    logger.info("Total Cured: " + totalRecovered);

}
public void showGraph() {
    List<Report> reports = reportRepository.findAll();

    for (Report report : reports) {
        System.out.print(report.getDate() + ": ");
        int bars = report.getConfirmed() / 1000; 
        for (int i = 0; i < bars; i++) {

            logger.info("#");
        }

        logger.info(" (" + report.getConfirmed() + ")");
    }
}
public void exportReport() {
    List<Report> reports = reportRepository.findAll();
    String fileName = "report-" + LocalDate.now() + ".txt";

    try (PrintWriter writer = new PrintWriter(fileName)) {
        for (Report report : reports) {
            writer.println(report.getDate() + " | " +
                           report.getProvince() + " | " +
                           "Confirmed: " + report.getConfirmed() +
                           ", Deaths: " + report.getDeaths() +
                           ", Cured: " + report.getRecovered());
        }

        logger.info("Reporte exportado a: " + fileName);
    } catch (Exception e) {
        logger.info("Error al exportar reporte: " + e.getMessage());
    }
}


}