package com.example.demo.repositories;

import com.example.demo.entities.MaintenanceRequest;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MaintenanceRequestRepository extends JpaRepository<MaintenanceRequest, Long> {

    // Find all maintenance requests by status
    List<MaintenanceRequest> findByStatus(String status);
    
    @Query("SELECT m.apartment.apartmentId, COUNT(m) " +
    	       "FROM MaintenanceRequest m " +
    	       "WHERE m.status = 'PENDING' " +
    	       "GROUP BY m.apartment.apartmentId")
    	List<Object[]> getPendingRequestsGroupedByApartment();
    	
    	List<MaintenanceRequest> findByUserUserId(Long userId);
}
