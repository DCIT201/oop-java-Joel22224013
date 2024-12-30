import org.junit.jupiter.api.Test;
import org.rental.system.*;
import static org.junit.jupiter.api.Assertions.*;

public class RentalTransactionTest {

    @Test
    void testRentalTransactionCreation() {
        // Create a vehicle (Car)
        Vehicle vehicle = new Car("C1", "Toyota Camry", 2023, 50.0, true, 5, "Automatic");

        // Create a customer
        Customer customer = new Customer("cust1", "Test");

        // Create a rental transaction
        RentalTransaction transaction = new RentalTransaction(vehicle, customer, 5);

        // Assertions for initialization
        assertEquals(vehicle, transaction.getVehicle(), "Vehicle should match");
        assertEquals(customer, transaction.getCustomer(), "Customer should match");
        assertEquals(5, transaction.getRentalDays(), "Rental days should be 5");
        assertEquals("PENDING", transaction.getStatus(), "Initial status should be PENDING");
        assertEquals(250.0, transaction.getTotalCost(), "Total cost should be calculated as baseRate * rentalDays");
    }

    @Test
    void testProcessRental() {
        // Create a vehicle (Car)
        Vehicle vehicle = new Car("C1", "Toyota Camry", 2023, 50.0, true, 5, "Automatic");

        // Create a customer
        Customer customer = new Customer("cust1", "Test");

        // Create a rental transaction
        RentalTransaction transaction = new RentalTransaction(vehicle, customer, 5);

        // Process the rental
        assertTrue(transaction.processRental(), "Rental should be successfully processed");

        // Assert the transaction status is updated
        assertEquals("ACTIVE", transaction.getStatus(), "Status should be ACTIVE after processing rental");
    }

    @Test
    void testProcessReturn() {
        // Create a vehicle (Car)
        Vehicle vehicle = new Car("C1", "Toyota Camry", 2023, 50.0, true, 5, "Automatic");

        // Create a customer
        Customer customer = new Customer("cust1", "Test");

        // Create a rental transaction
        RentalTransaction transaction = new RentalTransaction(vehicle, customer, 5);

        // Process the rental
        transaction.processRental();

        // Process the return (5 days late)
        assertTrue(transaction.processReturn(10), "Return should be successfully processed");

        // Assert the transaction status is updated
        assertEquals("COMPLETED", transaction.getStatus(), "Status should be COMPLETED after returning");

        // Assert the total cost is correctly calculated (5 extra days at base rate)
        assertEquals(375.0, transaction.getTotalCost(), "Total cost should include late fees (baseRate * extraDays)");
    }
}