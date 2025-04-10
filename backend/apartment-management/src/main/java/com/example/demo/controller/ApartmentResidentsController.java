package com.example.demo.controller;

import com.example.demo.entities.ApartmentResident;
import com.example.demo.entities.ResidentType;
import com.example.demo.services.ApartmentResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apartment-residents")
public class ApartmentResidentsController {

    @Autowired
    private ApartmentResidentService apartmentResidentsService;

    @PostMapping
    public ResponseEntity<String> addResident(@RequestBody ApartmentResident resident) {
        apartmentResidentsService.addResident(resident);
        return ResponseEntity.ok("Resident added successfully.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ApartmentResident>> getResidentById(@PathVariable Long id) {
        return ResponseEntity.ok(apartmentResidentsService.getResidentById(id));
    }

    @GetMapping("/apartment/{apartmentId}")
    public ResponseEntity<List<ApartmentResident>> getResidentsByApartment(@PathVariable Long apartmentId) {
        return ResponseEntity.ok(apartmentResidentsService.getResidentsByApartment(apartmentId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ApartmentResident>> getResidentsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(apartmentResidentsService.getResidentsByUser(userId));
    }

    @PutMapping("/{id}/update-role")
    public ResponseEntity<String> updateResidentRole(@PathVariable Long id, @RequestParam ResidentType role) {
        apartmentResidentsService.updateResidentRole(id, role);
        return ResponseEntity.ok("Resident role updated successfully.");
    }

    /*@DeleteMapping("/{id}")
    public ResponseEntity<Void> removeResident(@PathVariable Long id) {
        apartmentResidentsService.removeResident(id);
        return ResponseEntity.noContent().build();
    }*/
    
    @GetMapping("/current/apartment/{apartmentId}")
    public List<ApartmentResident> getCurrentResidents(@PathVariable Long apartmentId) {
        return apartmentResidentsService.getCurrentResidentsByApartment(apartmentId);
    }

    @GetMapping("/history/resident/{residentId}")
    public List<ApartmentResident> getResidentHistory(@PathVariable Long residentId) {
        return apartmentResidentsService.getResidentHistoryByResidentId(residentId);
    }

    @GetMapping("/history/apartment/{apartmentId}")
    public List<ApartmentResident> getApartmentHistory(@PathVariable Long apartmentId) {
        return apartmentResidentsService.getResidentHistoryByApartmentId(apartmentId);
    }
    
    @PostMapping("/assign")
    public ResponseEntity<ApartmentResident> assignResident(@RequestBody ApartmentResident apartmentResident) {
        ApartmentResident saved = apartmentResidentsService.assignResidentToApartment(apartmentResident);
        return ResponseEntity.ok(saved);
    }
    
    @PutMapping("/remove/{residentId}")
    public ResponseEntity<String> removeResident(@PathVariable Long residentId) {
    	apartmentResidentsService.removeResidentFromApartment(residentId);
        return ResponseEntity.ok("Resident removed from apartment successfully.");
    }
    
}
