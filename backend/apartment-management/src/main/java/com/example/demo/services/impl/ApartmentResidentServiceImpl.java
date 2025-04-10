package com.example.demo.services.impl;

import com.example.demo.entities.ApartmentResident;
import com.example.demo.entities.ResidentType;
import com.example.demo.repositories.ApartmentResidentRepository;
import com.example.demo.services.ApartmentResidentService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ApartmentResidentServiceImpl implements ApartmentResidentService {

    private final ApartmentResidentRepository residentRepository;

    public ApartmentResidentServiceImpl(ApartmentResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    @Override
    public ApartmentResident addResident(ApartmentResident resident) {
    	resident.setStartDate(LocalDate.now());
        resident.setEndDate(null);  // active resident
        return residentRepository.save(resident);
    }

    @Override
    public Optional<ApartmentResident> getResidentById(Long id) {
        return residentRepository.findById(id);
    }

    @Override
    public List<ApartmentResident> getResidentsByApartment(Long apartmentId) {
        return residentRepository.findByApartment_ApartmentId(apartmentId);
    }

    @Override
    public List<ApartmentResident> getResidentsByUser(Long userId) {
        return residentRepository.findByUser_UserId(userId);
    }

    @Override
    public void updateResidentRole(Long id, ResidentType newRole) {
        residentRepository.findById(id).ifPresent(resident -> {
            resident.setResidentType(newRole);
            residentRepository.save(resident);
        });
    }

    /*@Override
    public String removeResident(Long id) {
    	 Optional<ApartmentResident> optionalResident = residentRepository.findById(id);
         if (optionalResident.isPresent()) {
             ApartmentResident resident = optionalResident.get();
             resident.setEndDate(LocalDate.now());
             residentRepository.save(resident);
             return "Resident marked as exited successfully.";
         } else {
             return "Resident not found.";
         }
	}*/
    
    @Override
    @Transactional
    public ApartmentResident assignResidentToApartment(ApartmentResident apartmentResident) {
        // End previous residents
        residentRepository.deactivatePreviousResidents(apartmentResident.getApartment().getApartmentId(), LocalDate.now());

        // Set tracking fields
        apartmentResident.setStartDate(LocalDate.now());
        apartmentResident.setIsCurrent(true);
        apartmentResident.setEndDate(null);

        return residentRepository.save(apartmentResident);
    }
    
    @Override
    public List<ApartmentResident> getCurrentResidentsByApartment(Long apartmentId) {
        return residentRepository.findByApartmentIdAndEndDateIsNull(apartmentId);
    }

    @Override
    public List<ApartmentResident> getResidentHistoryByResidentId(Long residentId) {
        return residentRepository.findByUser_UserId(residentId);
    }

    @Override
    public List<ApartmentResident> getResidentHistoryByApartmentId(Long apartmentId) {
        return residentRepository.findByApartment_ApartmentId(apartmentId);
    }
    
    @Override
    @Transactional
    public void removeResidentFromApartment(Long residentId) {
        Optional<ApartmentResident> optionalResident = residentRepository.findById(residentId);

        if (optionalResident.isPresent()) {
            ApartmentResident resident = optionalResident.get();
            resident.setIsCurrent(false);
            resident.setEndDate(LocalDate.now());
            residentRepository.save(resident);
        } else {
            throw new EntityNotFoundException("Resident not found with ID: " + residentId);
        }
    }

}
