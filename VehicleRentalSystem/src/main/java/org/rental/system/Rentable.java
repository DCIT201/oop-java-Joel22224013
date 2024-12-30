package org.rental.system;

interface Rentable {
    double calculateRentalCost(int days);
    boolean isAvailableForRental();
    double calculateDelayPrice(int lateDays);
    boolean rent(Customer customer, int days);
    boolean returnVehicle();
}