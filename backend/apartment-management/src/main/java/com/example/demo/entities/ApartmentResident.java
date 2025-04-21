package com.example.demo.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "apartment_residents")
public class ApartmentResident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resident_id")
    private Long residentId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "userId", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "apartment_id", referencedColumnName = "apartment_Id")
    private Apartment apartment;

    @Enumerated(EnumType.STRING)
    @Column(name = "resident_type")
    private ResidentType residentType;
    
    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;
    
    @Column(name = "is_current")
    private Boolean isCurrent;

    @Column(name = "joined_At", nullable = false)
    private LocalDateTime joinedAt = LocalDateTime.now();

    // Constructors
    public ApartmentResident() {}

    public ApartmentResident(User user, Apartment apartment, ResidentType residentType) {
        this.user = user;
        this.apartment = apartment;
        this.residentType = residentType;
        this.joinedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getResidentId() { return residentId; }
    public void setResidentId(Long residentId) { this.residentId = residentId; }

    public User getUser() { return user; }
    public void setUser(User user2) { this.user = user2; }
    
    public ResidentType getResidentType() {return residentType;}
    public void setResidentType(ResidentType residentType) {this.residentType = residentType;}

    public Apartment getApartment() { return apartment; }
    public void setApartment(Apartment apartment2) { this.apartment = apartment2; }
    
    public LocalDate getStartDate() { return startDate;}
    public void setStartDate(LocalDate startDate) {this.startDate = startDate;}

    public LocalDate getEndDate() {return endDate;}
    public void setEndDate(LocalDate endDate) {this.endDate = endDate;}

    public Boolean getIsCurrent() {return isCurrent;}
    public void setIsCurrent(Boolean isCurrent) {this.isCurrent = isCurrent;}

	public LocalDateTime getJoinedAt() { return joinedAt; }
}
