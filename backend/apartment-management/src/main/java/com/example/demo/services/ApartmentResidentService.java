package com.example.demo.services;

import com.example.demo.entities.ApartmentResident;
import com.example.demo.entities.ResidentType;

import java.util.List;
import java.util.Optional;

public interface ApartmentResidentService {
    ApartmentResident addResident(ApartmentResident resident);
    Optional<ApartmentResident> getResidentById(Long id);
    List<ApartmentResident> getResidentsByApartment(Long apartmentId);
    List<ApartmentResident> getResidentsByUser(Long userId);
    /*String removeResident(Long id);*/
	void updateResidentRole(Long id, ResidentType newRole);
	List<ApartmentResident> getCurrentResidentsByApartment(Long apartmentId);
	List<ApartmentResident> getResidentHistoryByResidentId(Long residentId);
	List<ApartmentResident> getResidentHistoryByApartmentId(Long apartmentId);
	ApartmentResident assignResidentToApartment(ApartmentResident apartmentResident);
	void removeResidentFromApartment(Long residentId);
}
