package com.example.demo.services.impl;

import com.example.demo.entities.Payment;
import com.example.demo.repositories.PaymentRepository;
import com.example.demo.services.PaymentService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public void updatePaymentStatus(Long id, String status) {
        paymentRepository.findById(id).ifPresent(payment -> {
            payment.setStatus(status);
            paymentRepository.save(payment);
        });
    }
    
    @Override
    public List<Payment> getAllRentPayments() {
        return paymentRepository.findByType("Rent");
    }
    
    @Override
    public List<Payment> getRentPaymentsByUserId(Long userId) {
        return paymentRepository.findByTypeAndUserUserId("Rent", userId);
    }
    
    @Override
    public List<Payment> getRentPaymentsByApartmentId(Long apartmentId) {
        return paymentRepository.findByTypeAndApartmentApartmentId("Rent", apartmentId);
    }
}
