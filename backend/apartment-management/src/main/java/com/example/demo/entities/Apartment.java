package com.example.demo.entities;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "apartments")
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apartment_id")
    private Long apartmentId;

    @Column(nullable = false, length = 50)
    private String block;

    @Column(name = "flat_number", nullable = false, length = 20, unique = true)
    private String flatNumber;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "userId", nullable = false)
    private User owner;
    
    @Column(name = "rent_amount", nullable = false)
    private BigDecimal rentAmount;

    @Column(name = "billing_cycle", nullable = false)
    private String billingCycle; // Monthly, Yearly etc.

    // Constructors
    public Apartment() {}

    public Apartment(String block, String flatNumber, User owner, BigDecimal rentAmount, String billingCycle ) {
        this.block = block;
        this.flatNumber = flatNumber;
        this.owner = owner;
        this.rentAmount = rentAmount;
        this.billingCycle = billingCycle;
    }

    // Getters and Setters
    public Long getApartmentId() { return apartmentId; }
    public void setApartmentId(Long apartmentId) { this.apartmentId = apartmentId; }

    public String getBlock() { return block; }
    public void setBlock(String block) { this.block = block; }

    public String getFlatNumber() { return flatNumber; }
    public void setFlatNumber(String flatNumber) { this.flatNumber = flatNumber; }

    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }

	public BigDecimal getRentAmount() {return rentAmount;}
	public void setRentAmount(BigDecimal rentAmount) {this.rentAmount = rentAmount;}

	public String getBillingCycle() {return billingCycle;}
	public void setBillingCycle(String billingCycle) {this.billingCycle = billingCycle;}
    
}
