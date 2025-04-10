package com.example.demo.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "maintenance_requests")
public class MaintenanceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "apartment_id", nullable = false)
    private Apartment apartment;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false, length = 50)
    private String status; // Open, In Progress, Resolved

    @ManyToOne
    @JoinColumn(name = "assigned_staff")
    private User assignedStaff;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // Constructors
    public MaintenanceRequest() {}

    public MaintenanceRequest(User user, Apartment apartment, String description, String status, User assignedStaff) {
        this.user = user;
        this.apartment = apartment;
        this.description = description;
        this.status = status;
        this.assignedStaff = assignedStaff;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getRequestId() { return requestId; }
    public void setRequestId(Long requestId) { this.requestId = requestId; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Apartment getApartment() { return apartment; }
    public void setApartment(Apartment apartment) { this.apartment = apartment; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public User getAssignedStaff() { return assignedStaff; }
    public void setAssignedStaff(User assignedStaff) { this.assignedStaff = assignedStaff; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}
