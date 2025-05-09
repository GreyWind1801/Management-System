package com.example.demo.repositories;

import com.example.demo.entities.ResourceBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ResourceBookingRepository extends JpaRepository<ResourceBooking, Long> {

    // Find all bookings by facility name
    List<ResourceBooking> findByFacilityName(String facilityName);
    List<ResourceBooking> findByUserUserId(Long userId);
}
