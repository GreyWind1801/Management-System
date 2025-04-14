package com.example.demo.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "apartment_id",referencedColumnName = "apartment_id", nullable = false)
    private Apartment apartment;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private LocalDateTime paymentDate = LocalDateTime.now();

    @Column(nullable = false, length = 50)
    private String status; // Pending, Completed, Failed
    
    @Column(nullable = false, length = 50)
    private String type; // Rent, Maintenance, Booking, etc

    // Constructors
    public Payment() {}

    public Payment(User user, Apartment apartment, Double amount, String status, String type) {
        this.user = user;
        this.apartment = apartment;
        this.amount = amount;
        this.status = status;
        this.paymentDate = LocalDateTime.now();
        this.type = type;
    }

    // Getters and Setters
    public Long getPaymentId() { return paymentId; }
    public void setPaymentId(Long paymentId) { this.paymentId = paymentId; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Apartment getApartment() { return apartment; }
    public void setApartment(Apartment apartment) { this.apartment = apartment; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public LocalDateTime getPaymentDate() { return paymentDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
