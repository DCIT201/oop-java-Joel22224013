package org.rental.system;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String customerId;
    private String name;
    private String licenseNumber;
    private List<RentableHistory> history;
    private int loyaltyPoints;

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.history = new ArrayList<>();
        this.loyaltyPoints = 0;
    }

    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) {
        if(licenseNumber == null){throw new IllegalArgumentException("license number must not be null");}
        this.licenseNumber = licenseNumber;
    }
    public List<RentableHistory> getHistory() { return history; }

    private void addLoyaltyPoints(int points){
        if(points > 0){
            this.loyaltyPoints += points; // Correctly adds points
        }
    }

    public int getLoyaltyPoints() { return loyaltyPoints; }

    public void addHistory(RentableHistory rentableHistory) {
        if(rentableHistory == null){
            throw new IllegalArgumentException("rentable history must not be null");
        }
        history.add(rentableHistory);
        addLoyaltyPoints(5);
    }
}
