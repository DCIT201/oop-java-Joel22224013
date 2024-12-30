package org.rental.system;

import java.util.UUID;

public class RentalTransaction {
    private final String transactionId;
    private final Vehicle vehicle;
    private final Customer customer;
    private int rentalDays;
    private double totalCost;
    private String status;
    private RentableHistory rentalHistory;
    private int returnDay;


    public RentalTransaction(Vehicle vehicle, Customer customer, int days) {
        if (vehicle == null || customer == null || days <= 0) {
            throw new IllegalArgumentException("Invalid rental parameters");
        }

        this.transactionId = UUID.randomUUID().toString();
        this.vehicle = vehicle;
        this.customer = customer;
        this.rentalDays = days;
        this.status = "PENDING";
        this.totalCost = vehicle.calculateRentalCost(rentalDays);

        calculateInitialCost();
    }

    public double calculateInitialCost() {
        this.totalCost = vehicle.calculateRentalCost(rentalDays);
        return 0;
    }

    public String getTransactionId() { return transactionId; }
    public Vehicle getVehicle() { return vehicle; }
    public Customer getCustomer() { return customer; }
    public int getRentalDays() { return rentalDays; }
    public double getTotalCost() { return totalCost; }
    public String getStatus() { return status; }

    public boolean processRental() {
        if (!vehicle.isAvailableForRental()) {
            return false;
        }
        if (vehicle.rent(customer, rentalDays)) {
            this.status = "ACTIVE";
            return true;
        }
        return false;
    }
    public boolean processReturn(int returnDay) {
       int delayDays = returnDay - rentalDays;
        if (delayDays > 0) {
            double lateFee = vehicle.calculateDelayPrice(delayDays);
            totalCost += lateFee; // Add the calculated late fee

    }

        if (!"ACTIVE".equals(status)) {
            return false;
        }
        if (vehicle.returnVehicle()) {
            this.status = "COMPLETED";
            rentalHistory = new RentableHistory(vehicle.getVehicleId(), customer.getCustomerId(), rentalDays, totalCost, delayDays);
            customer.addHistory(rentalHistory);
        }
        return true;
    }

    @Override
    public String toString() {
        return "RentalTransaction{" +
                "transactionId='" + transactionId + '\'' +
                ", vehicle=" + vehicle +
                ", customer=" + customer +
                ", rentalDays=" + rentalDays +
                ", totalCost=" + totalCost +
                ", status='" + status + '\'' +
                ", rentalHistory=" + rentalHistory +
                ", returnDay=" + returnDay +
                '}';
    }
}
