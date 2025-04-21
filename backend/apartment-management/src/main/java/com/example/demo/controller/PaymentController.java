package com.example.demo.controller;

import com.example.demo.entities.Payment;
import com.example.demo.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        return ResponseEntity.ok(paymentService.createPayment(payment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Payment>> getPaymentById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }
    
    @GetMapping("/rent")
    public List<Payment> getAllRentPayments() {
        return paymentService.getAllRentPayments();
    }
    
    @GetMapping("/rent/user/{userId}")
    public List<Payment> getRentPaymentsByUser(@PathVariable Long userId) {
        return paymentService.getRentPaymentsByUserId(userId);
    }
    
    @GetMapping("/rent/apartment/{apartmentId}")
    public List<Payment> getRentPaymentsByApartment(@PathVariable Long apartmentId) {
        return paymentService.getRentPaymentsByApartmentId(apartmentId);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<String> updatePaymentStatus(@PathVariable Long id, @RequestParam String status) {
        paymentService.updatePaymentStatus(id, status);
        return ResponseEntity.ok("Payment status updated successfully.");
    }
    
}
