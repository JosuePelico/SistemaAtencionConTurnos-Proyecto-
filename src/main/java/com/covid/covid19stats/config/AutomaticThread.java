/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.covid.covid19stats.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.covid.covid19stats.service.RegionService;
import com.covid.covid19stats.service.ProvinceService;
import com.covid.covid19stats.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
/**
 *
 * @author rodol
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

    @Value("${covid19stats.default-iso}")
    private String defaultIso; 

    @Scheduled(initialDelay = 15000, fixedDelay = Long.MAX_VALUE)
    public void autoRunCovidDataLoad() {
        logger.info("Executing Thread");

        regionService.fetchAndSaveRegions();
        provinceService.fetchAndSaveProvinces(defaultIso);
        reportService.fetchAndSaveReports(defaultIso);

        logger.info("Data Saved Successfully");
    }
   }