/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.covid.covid19stats.controller;
import com.covid.covid19stats.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author rodol
 */
@RestController
public class ReportController {
        
    @Autowired
    private ReportService reportService;

    @GetMapping("/reports/load")
    public String loadReports(@RequestParam String iso) {
        reportService.fetchAndSaveReports(iso);
        return "Reports loaded successfully.";
    }
}