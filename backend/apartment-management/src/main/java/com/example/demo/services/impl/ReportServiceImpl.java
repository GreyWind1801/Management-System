package com.example.demo.services.impl;

import com.example.demo.repositories.*;
import com.example.demo.services.ReportService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    private final PaymentRepository paymentRepository;
    private final MaintenanceRequestRepository maintenanceRequestRepository;
    private final ApartmentResidentRepository apartmentResidentRepository;
    private final DocumentRepository documentRepository;

    public ReportServiceImpl(
            PaymentRepository paymentRepository,
            MaintenanceRequestRepository maintenanceRequestRepository,
            ApartmentResidentRepository apartmentResidentRepository,
            DocumentRepository documentRepository
    ) {
        this.paymentRepository = paymentRepository;
        this.maintenanceRequestRepository = maintenanceRequestRepository;
        this.apartmentResidentRepository = apartmentResidentRepository;
        this.documentRepository = documentRepository;
    }

    @Override
    public List<Object[]> getTotalPaymentsGroupedByMonth(int year) {
        return paymentRepository.getTotalPaymentsGroupedByMonth(year);
    }

    @Override
    public List<Object[]> getPendingRequestsGroupedByApartment() {
        return maintenanceRequestRepository.getPendingRequestsGroupedByApartment();
    }

    @Override
    public List<Object[]> getOccupancyReport() {
        return apartmentResidentRepository.getOccupancyReport();
    }

    @Override
    public List<Object[]> countDocumentsByType() {
        return documentRepository.countDocumentsByType();
    }

    @Override
    public BigDecimal calculateTotalRevenueBetween(LocalDateTime start, LocalDateTime end) {
        return paymentRepository.calculateTotalRevenueBetween(start, end);
    }
}
