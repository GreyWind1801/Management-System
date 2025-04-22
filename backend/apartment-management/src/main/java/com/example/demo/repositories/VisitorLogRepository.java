package com.example.demo.repositories;

import com.example.demo.entities.VisitorLog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface VisitorLogRepository extends JpaRepository<VisitorLog, Long> {

    // Find all visitors for a specific apartment
    List<VisitorLog> findByVisitApartment_ApartmentId(Long apartmentId);

    @Query(value = """
    	    SELECT 
    	        vl.visitor_id, vl.visitor_name, vl.phone, vl.purpose,
    	        (a.block || '-' || a.flat_number) AS apartment,
    	        vl.checkin_time, vl.checkout_time
    	    FROM visitor_logs vl
    	    JOIN apartments a ON a.apartment_id = vl.apartment_id
    	    WHERE
    	        (:fromDate IS NULL OR vl.checkin_time >= :fromDate) AND
    	        (:toDate IS NULL OR vl.checkin_time <= :toDate)
    	    ORDER BY vl.checkin_time DESC
    	    """, nativeQuery = true)
    	List<Map<String, Object>> getFilteredVisitorLogs(
    	    @Param("fromDate") Timestamp fromDate,
    	    @Param("toDate") Timestamp toDate
    	);

}
