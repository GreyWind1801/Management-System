package com.example.demo.services;

import com.example.demo.entities.MaintenanceRequest;
import java.util.List;
import java.util.Optional;

public interface MaintenanceRequestService {
    MaintenanceRequest createRequest(MaintenanceRequest request);
    Optional<MaintenanceRequest> getRequestById(Long id);
    List<MaintenanceRequest> getAllRequests();
    String getStatus(Long id);
    void updateRequestStatus(Long id, String status);
}
