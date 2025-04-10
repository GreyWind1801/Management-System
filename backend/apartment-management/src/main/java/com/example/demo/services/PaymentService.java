package com.example.demo.services;

import com.example.demo.entities.Payment;
import java.util.List;
import java.util.Optional;

public interface PaymentService {
    Payment createPayment(Payment payment);
    Optional<Payment> getPaymentById(Long id);
    List<Payment> getAllPayments();
    void updatePaymentStatus(Long id, String status);
    
    List<Payment> getAllRentPayments();
    List<Payment> getRentPaymentsByUserId(Long userId);
    List<Payment> getRentPaymentsByApartmentId(Long apartmentId);
}
