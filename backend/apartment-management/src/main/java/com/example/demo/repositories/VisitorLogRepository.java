package com.example.demo.repositories;

import com.example.demo.entities.VisitorLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VisitorLogRepository extends JpaRepository<VisitorLog, Long> {

    // Find all visitors for a specific resident
    List<VisitorLog> findByResidentResidentId(Long residentId);
}
