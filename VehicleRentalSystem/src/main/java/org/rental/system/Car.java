package org.rental.system;

public class Car extends Vehicle implements Rentable {
    private final int seatingCapacity;
    private final String transmissionType;
    private static final double insuranceRate = 10.0;
    private Customer currentRenter;

    public Car(String vehicleId, String model, int year, double baseRentalRate, boolean isAvailable, int seatingCapacity, String transmissionType) {
        super(vehicleId, model, year, baseRentalRate, isAvailable);
        this.seatingCapacity = seatingCapacity;
        this.transmissionType = transmissionType;
    }

    public int getSeatingCapacity() { return seatingCapacity; }
    public String getTransmissionType() { return transmissionType; }

//    @Override
//    public double calculateRentalCost(int days) {
//        if (days <= 0){
//            throw new IllegalArgumentException("Number of days must be greater than 0");
//        }
//        double cost = getBaseRentalRate() * days;
//        if (getSeatingCapacity() < 5){
//            cost += cost * 0.1;
//        }
//        double insuranceCost = getInsuranceCost(days);
//        return cost + insuranceCost;
//    }
    //In Car class
    @Override
    public double calculateRentalCost(int rentalDays) {
        return rentalDays * getBaseRentalRate();
    }

    @Override
    public double calculateDelayPrice(int delayDays) {
        return delayDays * getBaseRentalRate() * 0.5; //Example: 50% of daily rate as penalty
    }

    @Override
    public boolean isAvailableForRental() { return isAvailable(); }

    @Override
    protected double getInsuranceCost(int days) { return insuranceRate * days; }


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
