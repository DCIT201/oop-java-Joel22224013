import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rental.system.Customer;
import org.rental.system.RentableHistory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer("C001", "John Doe");
    }

    @Test
    void testCustomerInitialization() {
        assertEquals("C001", customer.getCustomerId());
        assertEquals("John Doe", customer.getName());
        assertEquals("L12345", customer.getLicenseNumber());
        assertNotNull(customer.getHistory());
        assertTrue(customer.getHistory().isEmpty());
        assertEquals(0, customer.getLoyaltyPoints());
    }

    @Test
    void testSetName() {
        customer.setName("Jane Smith");
        assertEquals("Jane Smith", customer.getName());
    }

    @Test
    void testSetLicenseNumber() {
        customer.setLicenseNumber("L67890");
        assertEquals("L67890", customer.getLicenseNumber());
    }

    @Test
    void testSetLicenseNumberThrowsExceptionForNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.setLicenseNumber(null);
        });
        assertEquals("license number must not be null", exception.getMessage());
    }

    @Test
    void testAddHistory() {
        RentableHistory historyItem = new RentableHistory("R001", "2024-12-30");
        customer.addHistory(historyItem);
        List<RentableHistory> history = customer.getHistory();
        assertEquals(1, history.size());
        assertEquals(historyItem, history.get(0));
        assertEquals(5, customer.getLoyaltyPoints());
    }

    @Test
    void testAddHistoryThrowsExceptionForNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.addHistory(null);
        });
        assertEquals("rentable history must not be null", exception.getMessage());
    }

    @Test
    void testLoyaltyPointsNotAddedForNegativePoints() {
        RentableHistory historyItem1 = new RentableHistory("R001", "2024-12-30");
        RentableHistory historyItem2 = new RentableHistory("R002", "2024-12-31");

        customer.addHistory(historyItem1);
        customer.addHistory(historyItem2);

        assertEquals(10, customer.getLoyaltyPoints());
    }
}
