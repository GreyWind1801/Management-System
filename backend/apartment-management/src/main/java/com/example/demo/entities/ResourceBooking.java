package com.example.demo.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "resource_bookings")
public class ResourceBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "userId", nullable = false)
    private User user;

    @Column(nullable = false, length = 100)
    private String facilityName;

    @Column(nullable = false)
    private LocalDate bookingDate;

    @Column(nullable = false, length = 50)
    private String status; // Pending, Approved, Rejected

    // Constructors
    public ResourceBooking() {}

    public ResourceBooking(User user, String facilityName, LocalDate bookingDate, String status) {
        this.user = user;
        this.facilityName = facilityName;
        this.bookingDate = bookingDate;
        this.status = status;
    }

    // Getters and Setters
    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getFacilityName() { return facilityName; }
    public void setFacilityName(String facilityName) { this.facilityName = facilityName; }

    public LocalDate getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
