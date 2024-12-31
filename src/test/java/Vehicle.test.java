import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rental.system.Customer;
import org.rental.system.Vehicle;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {
    private Vehicle testVehicle;

    @BeforeEach
    void setUp() {
    }

    private class ConcreteVehicle extends Vehicle {
        public ConcreteVehicle(String vehicleId, String model, int year, double baseRentalRate, boolean isAvailable) {
            super(vehicleId, model, year, baseRentalRate, isAvailable);
        }

        @Override
        public double calculateRentalCost(int days) {
            return 0;
        }

        @Override
        public boolean isAvailableForRental() {
            return false;
        }

        @Override
        protected double getInsuranceCost(int days) {
            return 0;
        }

        @Override
        public double calculateDelayPrice(int lateDays) {
            return 0;
        }
    }

    @Test
    void setVehicleIdShouldThrowExceptionWhenNull() {
        ConcreteVehicle vehicle = new ConcreteVehicle("123", "TestModel", 2023, 50.0, true);
        assertThrows(IllegalArgumentException.class, () -> vehicle.setVehicleId(null));
    }

    @Test
    void setModelShouldThrowExceptionWhenNull() {
        ConcreteVehicle vehicle = new ConcreteVehicle("123", "TestModel", 2023, 50.0, true);
        assertThrows(IllegalArgumentException.class, () -> vehicle.setModel(null));
    }



    @Test
    void setBaseRentalRateShouldThrowExceptionWhenZero() {
        ConcreteVehicle vehicle = new ConcreteVehicle("123", "TestModel", 2023, 50.0, true);
        assertThrows(IllegalArgumentException.class, () -> vehicle.setBaseRentalRate(0));
    }

    @Test
    void returnVehicleShouldReturnFalseWhenAlreadyAvailable() {
        ConcreteVehicle vehicle = new ConcreteVehicle("123", "TestModel", 2023, 50.0, true);
        assertFalse(vehicle.returnVehicle());
    }

    @Test
    void rentShouldReturnFalseWhenVehicleIsUnavailable() {
        ConcreteVehicle vehicle = new ConcreteVehicle("123", "TestModel", 2023, 50.0, false);
        Customer customer = new Customer("C001", "John Doe");
        assertFalse(vehicle.rent(customer, 5));
    }

    @Test
    void rentShouldSetIsAvailableToFalseAfterSuccessfulRental() {
        ConcreteVehicle vehicle = new ConcreteVehicle("123", "TestModel", 2023, 50.0, true);
        Customer customer = new Customer("C001", "John Doe");
        assertTrue(vehicle.rent(customer, 5));
        assertFalse(vehicle.isAvailable());
    }

    @Test
    void returnVehicleShouldSetIsAvailableToTrueAfterSuccessfulReturn() {
        ConcreteVehicle vehicle = new ConcreteVehicle("123", "TestModel", 2023, 50.0, false);
        assertTrue(vehicle.returnVehicle());
        assertTrue(vehicle.isAvailable());
    }

    @Test
    void toStringShouldCorrectlyFormatVehicleDetails() {
        ConcreteVehicle vehicle = new ConcreteVehicle("V123", "TestModel", 2023, 50.0, true);
        String expected = "Vehicle[vehicleId: V123, model: TestModel, year: 2023, baseRentalRate: 50.0, isAvailable: true]";
        assertEquals(expected, vehicle.toString());
    }
}
