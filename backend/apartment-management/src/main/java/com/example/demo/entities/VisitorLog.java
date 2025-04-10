package com.example.demo.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "visitor_logs")
public class VisitorLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long visitorId;

    @Column(nullable = false, length = 100)
    private String visitorName;

    @ManyToOne
    @JoinColumn(name = "resident_id", nullable = false)
    private ApartmentResident resident;

    @Column(nullable = false)
    private LocalDateTime checkinTime = LocalDateTime.now();

    @Column
    private LocalDateTime checkoutTime;

    @ManyToOne
    @JoinColumn(name = "security_staff_id")
    private User securityStaff;

    // Constructors
    public VisitorLog() {}

    public VisitorLog(String visitorName, ApartmentResident resident, User securityStaff) {
        this.visitorName = visitorName;
        this.resident = resident;
        this.securityStaff = securityStaff;
        this.checkinTime = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getVisitorId() { return visitorId; }
    public void setVisitorId(Long visitorId) { this.visitorId = visitorId; }

    public String getVisitorName() { return visitorName; }
    public void setVisitorName(String visitorName) { this.visitorName = visitorName; }

    public ApartmentResident getResident() { return resident; }
    public void setResident(ApartmentResident resident) { this.resident = resident; }

    public LocalDateTime getCheckinTime() { return checkinTime; }

    public LocalDateTime getCheckoutTime() { return checkoutTime; }
    public void setCheckoutTime(LocalDateTime checkoutTime) { this.checkoutTime = checkoutTime; }

    public User getSecurityStaff() { return securityStaff; }
    public void setSecurityStaff(User securityStaff) { this.securityStaff = securityStaff; }
}
