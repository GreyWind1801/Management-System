package com.example.demo.repositories;

import com.example.demo.entities.Payment;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // Find all payments for a specific user
    List<Payment> findByUserUserId(Long userId);
    List<Payment> findByType(String type);
    List<Payment> findByTypeAndUserUserId(String type, Long userId);
    List<Payment> findByTypeAndApartmentApartmentId(String type, Long apartmentId);
    
    @Query("SELECT FUNCTION('MONTH', p.paymentDate), SUM(p.amount) " +
    	       "FROM Payment p " +
    	       "WHERE FUNCTION('YEAR', p.paymentDate) = :year " +
    	       "GROUP BY FUNCTION('MONTH', p.paymentDate) " +
    	       "ORDER BY FUNCTION('MONTH', p.paymentDate)")
    	List<Object[]> getTotalPaymentsGroupedByMonth(@Param("year") int year);

    @Query("SELECT SUM(p.amount) " +
    		   "FROM Payment p " +
    		   "WHERE p.paymentDate BETWEEN :start AND :end")
    	BigDecimal calculateTotalRevenueBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

}
