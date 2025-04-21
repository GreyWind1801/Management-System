package com.example.demo.controller;

import com.example.demo.entities.Apartment;
import com.example.demo.entities.ApartmentResident;
import com.example.demo.entities.ResidentType;
import com.example.demo.services.ApartmentResidentService;
import com.example.demo.entities.User;
import com.example.demo.repositories.ApartmentRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/apartment-residents")
public class ApartmentResidentsController {

    @Autowired
    private ApartmentResidentService apartmentResidentsService;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApartmentRepository apartmentRepository;
    
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    @Transactional
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
    
    @GetMapping("/current")
    public ResponseEntity<List<Map<String, Object>>> getCurrentResidents() {
        List<ApartmentResident> currentResidents = apartmentResidentsService.getCurrentResidents();

        List<Map<String, Object>> response = currentResidents.stream().map(resident -> {
            Map<String, Object> map = new HashMap<>();

            User user = userRepository.findById(resident.getUser().getUserId()).orElse(null);
            Apartment apartment = apartmentRepository.findById(resident.getApartment().getApartmentId()).orElse(null);;
            
            resident.setUser(user);
            resident.setApartment(apartment);
            
            map.put("name", user.getName());
            map.put("email", resident.getUser().getEmail());
            map.put("phone", resident.getUser().getPhone());
            map.put("residentType", resident.getResidentType());
            map.put("startDate", resident.getStartDate());
            
            
            Map<String, Object> apt = new HashMap<>();
            apt.put("block", resident.getApartment().getBlock());
            apt.put("flatNumber", resident.getApartment().getFlatNumber());

            map.put("apartment", apt);
            return map;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/current-user")
    public ResponseEntity<?> getCurrentResidentForLoggedInUser(HttpServletRequest request) {
        String token = extractTokenFromHeader(request);
        String email = jwtUtil.extractEmail(token); // You may use a utility to extract the email
        ApartmentResident resident = apartmentResidentsService.getCurrentResidentByEmail(email);

        if (resident == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No current apartment resident found for the user");
        }
        
        User user = userRepository.findById(resident.getUser().getUserId()).orElse(null);
        Apartment apartment1 = apartmentRepository.findById(resident.getApartment().getApartmentId()).orElse(null);;
        
        resident.setUser(user);
        resident.setApartment(apartment1);

        Map<String, Object> response = new HashMap<>();
        response.put("userId", resident.getUser().getUserId());
        response.put("name", resident.getUser().getName());
        response.put("email", resident.getUser().getEmail());
        response.put("phone", resident.getUser().getPhone());
        response.put("residentType", resident.getResidentType());
        response.put("startDate", resident.getStartDate());

        Map<String, Object> apt = new HashMap<>();
        Apartment apartment = resident.getApartment();
        apt.put("apartmentId", apartment.getApartmentId());
        apt.put("block", apartment.getBlock());
        apt.put("flatNumber", apartment.getFlatNumber());

        response.put("apartment", apt);
        response.put("defaultRentAmount", 10000); // Optional default rent

        return ResponseEntity.ok(response);
    }

    private String extractTokenFromHeader(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        return authHeader != null && authHeader.startsWith("Bearer ")
                ? authHeader.substring(7)
                : null;
    }
    
    
    
}
