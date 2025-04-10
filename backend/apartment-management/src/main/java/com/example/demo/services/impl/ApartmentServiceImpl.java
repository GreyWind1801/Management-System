package com.example.demo.services.impl;

import com.example.demo.entities.Apartment;
import com.example.demo.repositories.ApartmentRepository;
import com.example.demo.services.ApartmentService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ApartmentServiceImpl implements ApartmentService {

    private final ApartmentRepository apartmentRepository;

    public ApartmentServiceImpl(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    @Override
    public Apartment addApartment(Apartment apartment) {
        return apartmentRepository.save(apartment);
    }

    @Override
    public Optional<Apartment> getApartmentById(Long id) {
        return apartmentRepository.findById(id);
    }

    @Override
    public List<Apartment> getAllApartments() {
        return apartmentRepository.findAll();
    }

    @Override
    public Apartment updateApartment(Long id, Apartment apartment) {
        return apartmentRepository.findById(id).map(existingApartment -> {
            existingApartment.setBlock(apartment.getBlock());
            existingApartment.setFlatNumber(apartment.getFlatNumber());
            return apartmentRepository.save(existingApartment);
        }).orElseThrow(() -> new RuntimeException("Apartment not found"));
    }

    @Override
    public void deleteApartment(Long id) {
        apartmentRepository.deleteById(id);
    }
    
    @Override
    public Apartment updateRentInfo(Long apartmentId, Map<String, Object> updates) {
        Apartment apartment = apartmentRepository.findById(apartmentId)
                .orElseThrow(() -> new RuntimeException("Apartment not found"));

        if (updates.containsKey("rentAmount")) {
            apartment.setRentAmount(new BigDecimal(updates.get("rentAmount").toString()));
        }

        if (updates.containsKey("billingCycle")) {
            apartment.setBillingCycle(updates.get("billingCycle").toString());
        }

        return apartmentRepository.save(apartment);
    }
}
