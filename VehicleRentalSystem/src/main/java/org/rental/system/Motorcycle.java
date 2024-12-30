package org.rental.system;

public class Motorcycle extends Vehicle implements Rentable {
    private static final double insuranceRate = 15.0;

    public Motorcycle(String vehicleId, String model, int year, double baseRentalRate, boolean isAvailable) {
        super(vehicleId, model, year, baseRentalRate, isAvailable);
    }

    @Override
    public double calculateRentalCost(int days) {
        if(days <= 0){
            throw new IllegalArgumentException("days must be greater than 0");
        }
        double cost = getBaseRentalRate() * days;
        double insuranceCost = getInsuranceCost(days);
        return cost + insuranceCost;
    }

    @Override
    public boolean isAvailableForRental() { return isAvailable(); }

    @Override
    protected double getInsuranceCost(int days) { return insuranceRate * days; }

    @Override
    public double calculateDelayPrice(int lateDays) { return lateDays * (getBaseRentalRate() * 0.5); }

    @Override
    public boolean rent(Customer customer, int days) {
        if (!isAvailable()) return false;
        setAvailable(false);
        return true;
    }

    @Override
    public boolean returnVehicle() {
        if (isAvailable()) return false;
        setAvailable(true);
        return true;
    }
}
