package com.example.demo.services.impl;

import com.example.demo.entities.VisitorLog;
import com.example.demo.repositories.VisitorLogRepository;
import com.example.demo.services.VisitorService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VisitorServiceImpl implements VisitorService {

    private final VisitorLogRepository visitorLogRepository;

    public VisitorServiceImpl(VisitorLogRepository visitorRepository) {
        this.visitorLogRepository = visitorRepository;
    }

    @Override
    public VisitorLog registerVisitor(VisitorLog visitor) {
        return visitorLogRepository.save(visitor);
    }

    @Override
    public Optional<VisitorLog> getVisitorById(Long id) {
        return visitorLogRepository.findById(id);
    }

    @Override
    public List<VisitorLog> getAllVisitors() {
        return visitorLogRepository.findAll();
    }
    
    @Override
    public List<Map<String, Object>> getLogs(LocalDateTime fromDate, LocalDateTime toDate) {
        Timestamp fromTimestamp = (fromDate != null) ? Timestamp.valueOf(fromDate) : null;
        Timestamp toTimestamp = (toDate != null) ? Timestamp.valueOf(toDate) : null;
        return visitorLogRepository.getFilteredVisitorLogs(fromTimestamp, toTimestamp);
    }

    @Override
    public void checkoutVisitor(Long id) {
    	visitorLogRepository.findById(id).ifPresent(visitor -> {
            visitor.setCheckoutTime(java.time.LocalDateTime.now());
            visitorLogRepository.save(visitor);
        });
    }
}
