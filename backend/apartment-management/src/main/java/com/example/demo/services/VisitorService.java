package com.example.demo.services;

import com.example.demo.entities.VisitorLog;
import java.util.List;
import java.util.Optional;

public interface VisitorService {
    VisitorLog registerVisitor(VisitorLog visitor);
    Optional<VisitorLog> getVisitorById(Long id);
    List<VisitorLog> getAllVisitors();
    void checkoutVisitor(Long id);
}
