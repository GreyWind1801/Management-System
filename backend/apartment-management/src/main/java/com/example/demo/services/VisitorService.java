package com.example.demo.services;

import com.example.demo.entities.VisitorLog;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface VisitorService {
    VisitorLog registerVisitor(VisitorLog visitor);
    Optional<VisitorLog> getVisitorById(Long id);
    List<VisitorLog> getAllVisitors();
    void checkoutVisitor(Long id);
	List<Map<String, Object>> getLogs(LocalDateTime fromDate, LocalDateTime toDate);
}
