package org.rental.system;

public class Truck extends Vehicle implements Rentable{
    private final double cargoCapacity;
    private static final double insuranceRate=30.0;
    private Customer currentRenter;

    public Truck(String vehicleId, String model, int year, double baseRentalRate, boolean isAvailable, double cargoCapacity) {
        super(vehicleId, model, year, baseRentalRate, isAvailable);
        this.cargoCapacity = cargoCapacity;
    }

    @Override
    public double calculateDelayPrice(int lateDays) {
        return lateDays * (getBaseRentalRate() * 2);
    }

    @Override
    public double calculateRentalCost(int days) {
        if(days<=0){
            throw new IllegalArgumentException("days must be greater than 0");
        }
        double cost = getBaseRentalRate()*days;
        double insuranceCost = getInsuranceCost(days);
        return cost + insuranceCost;
    }

    @Override
    public boolean isAvailableForRental() {
        return isAvailable();
    }

    @Override
    public double getInsuranceCost(int days) {
        return insuranceRate*days*(cargoCapacity/1000.0);
    }

    public boolean requiresCommercialLicense() {
        return cargoCapacity > 3500.0;
    }

    public double getCargoCapacity() { return cargoCapacity; }

    @Override
    public String toString() {
        return "Truck{" +
                "vehicleId='" + getVehicleId() + '\'' +
                ", model='" + getModel() + '\'' +
                ", year=" + getYear() +
                ", baseRentalRate=" + getBaseRentalRate() +
                ", isAvailable=" + isAvailable() +
                ", cargoCapacity=" + cargoCapacity +
                '}';
    }
    @Override
    public boolean rent(Customer customer, int days) {
        if (!isAvailable()) return false;
        this.currentRenter = customer;
        setAvailable(false);
        return true;
    }

    @Override
    public boolean returnVehicle() {
        if (isAvailable()) return false;
        this.currentRenter = null;
        setAvailable(true);
        return true;
    }
}
