package com.example.demo.controller;

import com.example.demo.entities.ResourceBooking;
import com.example.demo.services.ResourceBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/resource-bookings")
public class ResourceBookingController {

    @Autowired
    private ResourceBookingService resourceBookingService;

    @PostMapping
    public ResponseEntity<ResourceBooking> createBooking(@RequestBody ResourceBooking booking) {
        return ResponseEntity.ok(resourceBookingService.createBooking(booking));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ResourceBooking>> getBookingById(@PathVariable Long id) {
        return ResponseEntity.ok(resourceBookingService.getBookingById(id));
    }

    @GetMapping
    public ResponseEntity<List<ResourceBooking>> getAllBookings() {
        return ResponseEntity.ok(resourceBookingService.getAllBookings());
    }
    
    @GetMapping("/{id}/status")
    public ResponseEntity<String> getStatus(@PathVariable Long id) {
        String status = resourceBookingService.getStatus(id);
        return ResponseEntity.ok(status);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResourceBooking> updateBookingStatus(@PathVariable Long id, @RequestBody ResourceBooking booking) {
        return ResponseEntity.ok(resourceBookingService.updateBookingStatus(id, resourceBookingService.getStatus(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        resourceBookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}
