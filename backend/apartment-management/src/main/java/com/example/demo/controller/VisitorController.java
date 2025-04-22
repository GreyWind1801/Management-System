package com.example.demo.controller;

import com.example.demo.entities.VisitorLog;
import com.example.demo.services.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/visitors")
public class VisitorController {

    @Autowired
    private VisitorService visitorService;

    @PostMapping
    public ResponseEntity<VisitorLog> registerVisitor(@RequestBody VisitorLog visitor) {
        return ResponseEntity.ok(visitorService.registerVisitor(visitor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<VisitorLog>> getVisitorById(@PathVariable Long id) {
        return ResponseEntity.ok(visitorService.getVisitorById(id));
    }

    @GetMapping
    public ResponseEntity<List<VisitorLog>> getAllVisitors() {
        return ResponseEntity.ok(visitorService.getAllVisitors());
    }
    
    @PutMapping("/logout/{id}")
    public ResponseEntity<String> checkoutVisitor(@PathVariable Long id) {
        visitorService.checkoutVisitor(id);
        return ResponseEntity.ok("Visitor checked out successfully.");
    }
    
    @GetMapping("/logs")
    public ResponseEntity<List<Map<String, Object>>> getVisitorLogs(
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate
    ) {
        LocalDateTime fromDateTime = (fromDate != null) ? fromDate.atStartOfDay() : null;
        LocalDateTime toDateTime = (toDate != null) ? toDate.atTime(LocalTime.MAX) : null;

        return ResponseEntity.ok(visitorService.getLogs(fromDateTime, toDateTime));
    }

}
