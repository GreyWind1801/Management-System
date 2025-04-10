package com.example.demo.controller;

import com.example.demo.entities.MaintenanceRequest;
import com.example.demo.services.MaintenanceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/maintenance")
public class MaintenanceRequestController {

    @Autowired
    private MaintenanceRequestService maintenanceRequestService;

    @PostMapping
    public ResponseEntity<MaintenanceRequest> createRequest(@RequestBody MaintenanceRequest request) {
        return ResponseEntity.ok(maintenanceRequestService.createRequest(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<MaintenanceRequest>> getRequestById(@PathVariable Long id) {
        return ResponseEntity.ok(maintenanceRequestService.getRequestById(id));
    }

    @GetMapping
    public ResponseEntity<List<MaintenanceRequest>> getAllRequests() {
        return ResponseEntity.ok(maintenanceRequestService.getAllRequests());
    }
    
    @GetMapping("/{id}/status")
    public ResponseEntity<String> getStatus(@PathVariable Long id) {
        String status = maintenanceRequestService.getStatus(id);
        return ResponseEntity.ok(status);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateRequestStatus(@PathVariable Long id, @RequestBody Map<String, String> requestBody) {
        String newStatus = requestBody.get("status");

        if (newStatus == null || newStatus.isEmpty()) {
            return ResponseEntity.badRequest().body("Status cannot be empty");
        }

        maintenanceRequestService.updateRequestStatus(id, newStatus);
        return ResponseEntity.ok("Status updated successfully");
    }

    
}
