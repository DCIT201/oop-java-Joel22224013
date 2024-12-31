package org.rental.system;

public abstract class Vehicle {
    private String vehicleId;
    private String model;
    private int year;
    private double baseRentalRate;
    private boolean isAvailable;

    public Vehicle(String vehicleId, String model, int year, double baseRentalRate, boolean isAvailable) {
        this.vehicleId = vehicleId;
        this.model = model;
        this.year = year;
        this.baseRentalRate = baseRentalRate;
        this.isAvailable = isAvailable;
    }

    public String getVehicleId() { return vehicleId; }
    public void setVehicleId(String vehicleId) {
        if (vehicleId == null) {
            throw new IllegalArgumentException("VehicleId cannot be empty");
        }
        this.vehicleId = vehicleId;
    }
    public String getModel() { return model; }
    public void setModel(String model) {
        if (model == null) {
            throw new IllegalArgumentException("Model must not be null");
        }
        this.model = model;
    }
    public int getYear() { return year; }
    public void setYear(int year) {
        if (year < 1900 || year > 2026) {
            throw new IllegalArgumentException("Year must be between 1900 and 2026");
        }
        this.year = year;
    }
    public double getBaseRentalRate() { return baseRentalRate; }
    public void setBaseRentalRate(double baseRentalRate) {
        if (baseRentalRate <= 0) {
            throw new IllegalArgumentException("BaseRentalRate must be greater than 0");
        }
        this.baseRentalRate = baseRentalRate;
    }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    public boolean returnVehicle() {
        if(isAvailable()){
            return false;
        }
        setAvailable(true);
        return true;
    }
    public boolean rent(Customer customer, int days) {
        if (!isAvailable()) {
            return false;
        }
        setAvailable(false);
        return true;
    }
    public abstract double calculateRentalCost(int days);
    public abstract boolean isAvailableForRental();
    protected abstract double getInsuranceCost(int days);
    public abstract double calculateDelayPrice(int lateDays);


    @Override
    public String toString() {
        return "Vehicle[" +
                "vehicleId: " + vehicleId +
                ", model: " + model +
                ", year: " + year +
                ", baseRentalRate: " + baseRentalRate +
                ", isAvailable: " + isAvailable +
                ']';
    }

    public void setAvailableForRental(boolean b) {
    }
}

