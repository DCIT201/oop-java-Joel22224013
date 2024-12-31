package org.rental.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RentalAgency agency = new RentalAgency("My Rental Agency");

        agency.addVehicle(new Car("111Car", "Toyota Camry", 2023, 50.0, true, 5, "Automatic"));
        agency.addVehicle(new Truck("111Truck", "Ford F-150", 2022, 100.0, true, 2500.0));
        agency.addVehicle(new Motorcycle("111Motor", "Harley Davidson", 2024, 30.0, true));

        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("110","Joel Ansah"));


        int choice;
        do {
            System.out.println("\nVehicle Rental System Menu:");
            System.out.println("1. Rent a vehicle");
            System.out.println("2. Return a vehicle");
            System.out.println("3. View available vehicles");
            System.out.println("4. Add Customer");
            System.out.println("5. View transactions");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter customer ID: ");
                    String customerId = scanner.nextLine();
                    Customer customer = findCustomer(customers, customerId);
                    if (customer == null) {
                        System.out.println("Customer not found.");
                        break;
                    }
                    System.out.print("Enter vehicle ID: ");
                    String vehicleId = scanner.nextLine();
                    System.out.print("Enter rental days: ");
                    int rentalDays = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        RentalTransaction transaction = agency.rentVehicle(vehicleId, customer, rentalDays);
                        if (transaction != null) {
                            System.out.println("Vehicle rented successfully. Transaction ID: " + transaction.getTransactionId());
                        } else {
                            System.out.println("Vehicle rental failed.");
                        }
                    } catch (IllegalStateException e)
                    {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Enter transaction ID: ");
                    String transactionId = scanner.nextLine();
                    RentalTransaction transaction = agency.findTransaction(transactionId);
                    if (transaction == null) {
                        System.out.println("Transaction not found.");
                        break;
                    }
                    System.out.print("Enter return day:(integer)  ");
                    int returnDay = scanner.nextInt();
                    scanner.nextLine();
                    if (transaction.processReturn(returnDay)) {
                        System.out.println("Vehicle returned successfully.");
                    } else {
                        System.out.println("Vehicle return failed.");
                    }
                    break;
                case 3:
                    List<Vehicle> availableVehicles = agency.getAvailableVehicles();
                    if (availableVehicles.isEmpty()) {
                        System.out.println("No vehicles available for rent.");
                    } else {
                        System.out.println("Available vehicles:");
                        for (Vehicle vehicle : availableVehicles) {
                            System.out.println(vehicle);
                        }
                    }
                    break;
                case 4:
                    System.out.print("Enter customer name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter customer license number: ");
                    String licenseNumber = scanner.nextLine();
                    String newCustomerId = UUID.randomUUID().toString();
                    Customer newCustomer = new Customer(newCustomerId, name);
                    customers.add(newCustomer);
                    System.out.println("Customer added successfully. Customer ID: " + newCustomerId);
                    break;
                case 5:
                    List<RentalTransaction> transactions = agency.getAllTransactions();
                    if (transactions.isEmpty()) {
                        System.out.println("No transactions found.");
                    } else {
                        System.out.println("All Transactions:");
                        for (RentalTransaction t : transactions) {
                            System.out.println("Transaction ID: " + t.getTransactionId());
                            System.out.println("Customer: " + t.getCustomer().getName());
                            System.out.println("Vehicle: " + t.getVehicle() + " " + t.getVehicle().getModel());
                            System.out.println("Rental Days: " + t.getRentalDays());
                            System.out.println("Total Cost: $" + String.format("%.2f", t.getTotalCost()));
                            System.out.println("Status: " + t.getStatus());
                            System.out.println("--------------------");
                        }
                    }
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
        scanner.close();
    }

    private static Customer findCustomer(List<Customer> customers, String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }
}