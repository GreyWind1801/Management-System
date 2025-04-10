package com.example.demo.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface ReportService {
    List<Object[]> getTotalPaymentsGroupedByMonth(int year);
    List<Object[]> getPendingRequestsGroupedByApartment();
    List<Object[]> getOccupancyReport();
    List<Object[]> countDocumentsByType();
    BigDecimal calculateTotalRevenueBetween(LocalDateTime start, LocalDateTime end);
}

