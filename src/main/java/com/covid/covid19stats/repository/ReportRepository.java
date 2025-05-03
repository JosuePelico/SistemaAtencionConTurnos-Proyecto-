package com.covid.covid19stats.repository;

import com.covid.covid19stats.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository for accessing reports
 */
@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

    List<Report> findByIso(String iso);

    List<Report> findByDateAndCountryIso(LocalDate date, String countryIso);


}

