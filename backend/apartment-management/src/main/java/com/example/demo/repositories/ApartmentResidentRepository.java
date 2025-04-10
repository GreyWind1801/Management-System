package com.example.demo.repositories;

import com.example.demo.entities.ApartmentResident;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ApartmentResidentRepository extends JpaRepository<ApartmentResident, Long> {
	// Find all residents by apartment ID
	List<ApartmentResident> findByApartment_ApartmentId(Long apartmentId);

    // Find all residents by user ID
	 List<ApartmentResident> findByUser_UserId(Long userId);
	 
	 List<ApartmentResident> findByApartmentIdOrderByStartDateDesc(Long apartmentId);

	 List<ApartmentResident> findByResidentIdAndEndDateIsNull(Long residentId);
	 
	 List<ApartmentResident> findByApartmentIdAndEndDateIsNull(Long apartmentId);
	 
	 @Query("SELECT ar FROM ApartmentResident ar WHERE ar.apartment.id = :apartmentId AND ar.isCurrent = true")
	 ApartmentResident findCurrentResidentByApartmentId(@Param("apartmentId") Long apartmentId);
	 
	 List<ApartmentResident> findByApartmentIdAndIsCurrentTrue(Long apartmentId);
	 
	 @Modifying
	 @Query("UPDATE ApartmentResident ar SET ar.isCurrent = false, ar.endDate = :endDate " +
	        "WHERE ar.apartment.apartmentId = :apartmentId AND ar.isCurrent = true")
	 void deactivatePreviousResidents(@Param("apartmentId") Long apartmentId, @Param("endDate") LocalDate endDate);
	 
	 @Query("SELECT ar.apartment.apartmentId, ar.isCurrent, COUNT(ar) " +
		       "FROM ApartmentResident ar " +
		       "WHERE ar.isCurrent = true " +
		       "GROUP BY ar.apartment.apartmentId, ar.isCurrent")
		List<Object[]> getOccupancyReport();


}
