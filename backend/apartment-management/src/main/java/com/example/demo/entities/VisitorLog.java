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
    
    @Column(nullable = false, length = 300)
    private String purpose;
    
    @Column(nullable = false, length = 10)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "apartment_id", referencedColumnName = "apartment_Id", nullable = false)
    private Apartment visitApartment;

    @Column(nullable = false)
    private LocalDateTime checkinTime = LocalDateTime.now();

    @Column
    private LocalDateTime checkoutTime;

    @ManyToOne
    @JoinColumn(name = "security_staff_id", referencedColumnName = "userId")
    private User securityStaff;

    // Constructors
    public VisitorLog() {}

    public VisitorLog(String visitorName, Apartment visitApartment, User securityStaff) {
        this.visitorName = visitorName;
        this.visitApartment = visitApartment;
        this.securityStaff = securityStaff;
        this.checkinTime = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getVisitorId() { return visitorId; }
    public void setVisitorId(Long visitorId) { this.visitorId = visitorId; }

    public String getVisitorName() { return visitorName; }
    public void setVisitorName(String visitorName) { this.visitorName = visitorName; }

    public String getPurpose() {return purpose;}
    public void setPurpose(String purpose) {this.purpose = purpose;}

	public String getPhone() {return phone;}
	public void setPhone(String phone) {this.phone = phone;}

	public Apartment getVisitApartment() {return visitApartment;}
	public void setVisitApartment(Apartment visitApartment) {this.visitApartment = visitApartment;}

	public LocalDateTime getCheckinTime() { return checkinTime; }

    public LocalDateTime getCheckoutTime() { return checkoutTime; }
    public void setCheckoutTime(LocalDateTime checkoutTime) { this.checkoutTime = checkoutTime; }

    public User getSecurityStaff() { return securityStaff; }
    public void setSecurityStaff(User securityStaff) { this.securityStaff = securityStaff; }
}
