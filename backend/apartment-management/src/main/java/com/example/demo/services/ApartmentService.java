package com.example.demo.services;

import com.example.demo.entities.Apartment;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ApartmentService {
    Apartment addApartment(Apartment apartment);
    Optional<Apartment> getApartmentById(Long id);
    List<Apartment> getAllApartments();
    Apartment updateApartment(Long id, Apartment apartment);
    void deleteApartment(Long id);
    Apartment updateRentInfo(Long apartmentId, Map<String, Object> updates);
}
