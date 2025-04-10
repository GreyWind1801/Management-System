package com.example.demo.controller;

import com.example.demo.entities.Apartment;
import com.example.demo.services.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/apartments")
public class ApartmentController {

    @Autowired
    private ApartmentService apartmentService;

    @PostMapping
    public ResponseEntity<Apartment> createApartment(@RequestBody Apartment apartment) {
        return ResponseEntity.ok(apartmentService.addApartment(apartment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Apartment>> getApartmentById(@PathVariable Long id) {
        return ResponseEntity.ok(apartmentService.getApartmentById(id));
    }

    @GetMapping
    public ResponseEntity<List<Apartment>> getAllApartments() {
        return ResponseEntity.ok(apartmentService.getAllApartments());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Apartment> updateApartment(@PathVariable Long id, @RequestBody Apartment apartment) {
        return ResponseEntity.ok(apartmentService.updateApartment(id, apartment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApartment(@PathVariable Long id) {
        apartmentService.deleteApartment(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}/rent-info")
    public ResponseEntity<?> getRentInfo(@PathVariable Long id) {
        Optional<Apartment> optionalApartment = apartmentService.getApartmentById(id);

        if (optionalApartment.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Apartment not found with id: " + id);
        }

        Apartment apartment = optionalApartment.get(); // ✅ Extract actual object

        Map<String, Object> response = new HashMap<>();
        response.put("rentAmount", apartment.getRentAmount());
        response.put("billingCycle", apartment.getBillingCycle());

        return ResponseEntity.ok(response);
    }

    // Update rent amount and billing cycle
    @PatchMapping("/{id}/rent-info")
    public Apartment updateRentInfo(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return apartmentService.updateRentInfo(id, updates);
    }
}
