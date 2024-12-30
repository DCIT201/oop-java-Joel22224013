package org.rental.system;

public class RentableHistory {
    private String vehicleId;
    private String customerId;
    private int rentalDays;
    private double totalCost;
    private int delayDays; // Added to track delay

    public RentableHistory(String vehicleId, String customerId, int rentalDays, double totalCost, int delayDays) {
        this.vehicleId = vehicleId;
        this.customerId = customerId;
        this.rentalDays = rentalDays;
        this.totalCost = totalCost;
        this.delayDays = delayDays;
    }

    public RentableHistory(String r001, String date) {

    }

    // Getters
    public String getVehicleId() { return vehicleId; }
    public String getCustomerId() { return customerId; }
    public int getRentalDays() { return rentalDays; }
    public double getTotalCost() { return totalCost; }
    public int getDelayDays() { return delayDays; }

    @Override
    public String toString() {
        return "RentableHistory{" +
                "vehicleId='" + vehicleId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", rentalDays=" + rentalDays +
                ", totalCost=" + totalCost +
                ", delayDays=" + delayDays +
                '}';
    }
}