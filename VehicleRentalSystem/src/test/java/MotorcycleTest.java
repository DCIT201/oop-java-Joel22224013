import org.junit.jupiter.api.Test;
import org.rental.system.*;
import static org.junit.jupiter.api.Assertions.*;

public class MotorcycleTest {
    @Test
    void testMotorcycleCreation() {
        Motorcycle motorcycle = new Motorcycle("M1", "Harley Davidson", 2024, 30.0, true);
        assertEquals("M1", motorcycle.getVehicleId());
        assertEquals("Harley Davidson", motorcycle.getModel());
        assertEquals(2024, motorcycle.getYear());
        assertEquals(30.0, motorcycle.getBaseRentalRate());
        assertTrue(motorcycle.isAvailable());
    }

    @Test
    void testCalculateRentalCost() {
        Motorcycle motorcycle = new Motorcycle("M1", "Harley Davidson", 2024, 30.0, true);
        assertEquals(225.0, motorcycle.calculateRentalCost(5)); // Corrected expected value
    }
    @Test
    void testCalculateDelayPrice(){
        Motorcycle motorcycle = new Motorcycle("M1", "Harley Davidson", 2024, 30.0, true);
        assertEquals(75.0,motorcycle.calculateDelayPrice(5)); // 5 days * ($30 * 0.5)
    }
    @Test
    void testRentAndReturn(){
        Motorcycle motorcycle = new Motorcycle("M1", "Harley Davidson", 2024, 30.0, true);
        assertTrue(motorcycle.rent(new Customer("cust1","Test"),5));
        assertFalse(motorcycle.isAvailable());
        assertTrue(motorcycle.returnVehicle());
        assertTrue(motorcycle.isAvailable());
    }
}
