package com.example.demo.controller;

import com.example.demo.entities.VisitorLog;
import com.example.demo.services.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/visitors")
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
    
    @PutMapping("/{id}/checkout")
    public ResponseEntity<String> checkoutVisitor(@PathVariable Long id) {
        visitorService.checkoutVisitor(id);
        return ResponseEntity.ok("Visitor checked out successfully.");
    }
}
