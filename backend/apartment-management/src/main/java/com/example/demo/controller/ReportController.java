package com.example.demo.controller;

import com.example.demo.services.ReportService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    // 1. Total Payments by Month for a Given Year
    @GetMapping("/payments/monthly")
    public List<Object[]> getTotalPaymentsGroupedByMonth(@RequestParam int year) {
        return reportService.getTotalPaymentsGroupedByMonth(year);
    }

    // 2. Pending Maintenance Requests grouped by Apartment
    @GetMapping("/maintenance/pending")
    public List<Object[]> getPendingRequestsGroupedByApartment() {
        return reportService.getPendingRequestsGroupedByApartment();
    }

    // 3. Occupancy Report - Count of Current Residents per Apartment
    @GetMapping("/occupancy")
    public List<Object[]> getOccupancyReport() {
        return reportService.getOccupancyReport();
    }

    // 4. Count of Documents grouped by Type
    @GetMapping("/documents/type-count")
    public List<Object[]> countDocumentsByType() {
        return reportService.countDocumentsByType();
    }

    // 5. Total Revenue collected between 2 Dates
    @GetMapping("/revenue")
    public BigDecimal calculateTotalRevenueBetween(
            @RequestParam("start")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam("end")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return reportService.calculateTotalRevenueBetween(start, end);
    }
}
