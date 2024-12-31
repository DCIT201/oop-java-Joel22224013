
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.rental.system.*;

import static org.junit.jupiter.api.Assertions.*;

@Nested
class RentalAgencyTest {

    @Test
    void testAddVehicle() {
        RentalAgency agency = new RentalAgency("Test Agency");
        Vehicle vehicle = new Car("C1", "Toyota", 2023, 50.0, true, 4, "Camry"); // Added two arguments
        agency.addVehicle(vehicle);
        assertEquals(1, agency.vehicles.size());
    }

    @Test
    void testGetAvailableVehicles() {
        RentalAgency agency = new RentalAgency("Test Agency");
        Vehicle availableVehicle = new Car("C1", "Toyota", 2023, 50.0, true, 4, "Camry"); // Added two arguments
        Vehicle unavailableVehicle = new Car("C2", "Honda", 2022, 60.0, false, 2, "Civic"); // Added two arguments
        agency.addVehicle(availableVehicle);
        agency.addVehicle(unavailableVehicle);
        assertEquals(1, agency.getAvailableVehicles().size());
    }
//Similar changes need to be made to other test methods where Car objects are created.

    @Test
    void testRentVehicle() {
        RentalAgency agency = new RentalAgency("Test Agency");
        Vehicle vehicle = new Car("C1", "Toyota", 2023, 50.0, true, 4, "Camry"); // Added numberOfDoors and model
        Customer customer = new Customer("Cust1", "John Doe");
        agency.addVehicle(vehicle);
        RentalTransaction transaction = agency.rentVehicle("C1", customer, 5);
        assertNotNull(transaction);
        assertEquals(1, agency.transactions.size());
        assertFalse(vehicle.isAvailableForRental());
    }

    @Test
    void testFindTransaction() {
        RentalAgency agency = new RentalAgency("Test Agency");
        Vehicle vehicle = new Car("C1", "Toyota", 2023, 50.0, true, 4, "Camry"); // Added numberOfDoors and model
        Customer customer = new Customer("Cust1", "John Doe");
        agency.addVehicle(vehicle);
        RentalTransaction transaction = agency.rentVehicle("C1", customer, 5);
        RentalTransaction foundTransaction = agency.findTransaction(transaction.getTransactionId());
        assertEquals(transaction, foundTransaction);
    }

    @Test
    void testGetAllTransactions() {
        RentalAgency agency = new RentalAgency("Test Agency");
        // Add some transactions...
        assertEquals(0, agency.getAllTransactions().size()); //Initially empty
    }
}

//Add more tests as needed to cover other scenarios and edge cases.
