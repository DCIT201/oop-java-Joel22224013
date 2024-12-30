package org.rental.system;

import java.util.ArrayList;
import java.util.List;

public class RentalAgency {
    private String name;
    public List<Vehicle> vehicles;
    public List<RentalTransaction> transactions;

    public RentalAgency(String name) {
        this.name = name;
        this.vehicles = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public List<Vehicle> getAvailableVehicles() {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.isAvailableForRental()) {
                availableVehicles.add(vehicle);
            }
        }
        return availableVehicles;
    }

    public RentalTransaction rentVehicle(String vehicleId, Customer customer, int days) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleId().equals(vehicleId) && vehicle.isAvailableForRental()) {
                RentalTransaction transaction = new RentalTransaction(vehicle, customer, days);
                if (transaction.processRental()) {
                    transactions.add(transaction);
                    return transaction;
                }
            }
        }
        return null;
    }

    public RentalTransaction findTransaction(String transactionId) {
        for (RentalTransaction transaction : transactions) {
            if (transaction.getTransactionId().equals(transactionId)) {
                return transaction;
            }
        }
        return null;
    }

    public List<RentalTransaction> getAllTransactions() {
        return new ArrayList<>(transactions);
    }

}
