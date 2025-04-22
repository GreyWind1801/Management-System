package com.example.demo.services.impl;

import com.example.demo.entities.MaintenanceRequest;
import com.example.demo.repositories.MaintenanceRequestRepository;
import com.example.demo.services.MaintenanceRequestService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceRequestServiceImpl implements MaintenanceRequestService {

    private final MaintenanceRequestRepository requestRepository;

    public MaintenanceRequestServiceImpl(MaintenanceRequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public MaintenanceRequest createRequest(MaintenanceRequest request) {
        return requestRepository.save(request);
    }

    @Override
    public Optional<MaintenanceRequest> getRequestById(Long id) {
        return requestRepository.findById(id);
    }

    @Override
    public List<MaintenanceRequest> getAllRequests() {
        return requestRepository.findAll();
    }
    
    @Override
    public String getStatus(Long id) {
        MaintenanceRequest request = requestRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Maintenance Request not found"));

        return request.getStatus(); // Return the status
    }

    public void updateRequestStatus(Long id, String status) {
        MaintenanceRequest request = requestRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Maintenance Request not found"));

        request.setStatus(status);  // Update the status
        requestRepository.save(request); // Save the updated entity
    }
    
    @Override
    public List<MaintenanceRequest> getRequestsByUserId(Long userId) {
        return requestRepository.findByUserUserId(userId);
    }
}
