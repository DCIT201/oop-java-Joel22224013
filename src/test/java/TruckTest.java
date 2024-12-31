import org.junit.Test;
import org.rental.system.Customer;
import org.rental.system.Truck;

import static org.junit.Assert.*;

public class TruckTest {

    private Truck truck;


@Test
public void calculateRentalCost_standardRentalPeriod_returnsCorrectCost() {
    // Arrange
    Truck truck = new Truck("T001", "Model X", 2022, 100.0, true, 2000.0);
    int rentalDays = 5;

    // Act
    double rentalCost = truck.calculateRentalCost(rentalDays);

    // Assert
    double expectedCost = (100.0 * 5) + (30.0 * 5 * (2000.0 / 1000.0));
    assertEquals(expectedCost, rentalCost, 0.001);
}

@Test
public void calculateRentalCost_zeroDays_throwsIllegalArgumentException() {
    // Arrange
    Truck truck = new Truck("T002", "Model Y", 2023, 120.0, true, 2500.0);

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> truck.calculateRentalCost(0));
}

@Test
public void calculateDelayPrice_lateDays_returnsCorrectPrice() {
    // Arrange
    Truck truck = new Truck("T003", "Model Z", 2021, 150.0, true, 3000.0);
    int lateDays = 3;

    // Act
    double delayPrice = truck.calculateDelayPrice(lateDays);

    // Assert
    double expectedPrice = 3 * (150.0 * 2);
    assertEquals(expectedPrice, delayPrice, 0.001);
}

@Test
public void rent_availableTruck_rentSuccessfully() {
    // Arrange
    Truck truck = new Truck("T004", "Model W", 2023, 200.0, true, 2800.0);
    Customer customer = new Customer("C001", "John Doe");

    // Act
    boolean result = truck.rent(customer, 7);

    // Assert
    assertTrue(result);
    assertFalse(truck.isAvailable());
}

@Test
public void rent_unavailableTruck_rentFails() {
    // Arrange
    Truck truck = new Truck("T005", "Model V", 2022, 180.0, false, 2500.0);
    Customer customer = new Customer("C002", "Jane Smith");

    // Act
    boolean result = truck.rent(customer, 5);

    // Assert
    assertFalse(result);
    assertFalse(truck.isAvailable());
}

@Test
public void returnVehicle_rentedTruck_returnsSuccessfully() {
    // Arrange
    Truck truck = new Truck("T006", "Model U", 2023, 180.0, true, 2800.0);
    Customer customer = new Customer("C003", "Alice Johnson");
    truck.rent(customer, 5);

    // Act
    boolean result = truck.returnVehicle();

    // Assert
    assertTrue(result);
    assertTrue(truck.isAvailable());
}

@Test
public void returnVehicle_availableTruck_returnsFalse() {
    // Arrange
    Truck truck = new Truck("T007", "Model S", 2023, 200.0, true, 3000.0);

    // Act
    boolean result = truck.returnVehicle();

    // Assert
    assertFalse(result);
    assertTrue(truck.isAvailable());
}

@Test
public void getInsuranceCost_calculateCorrectly() {
    // Arrange
    Truck truck = new Truck("T008", "Model R", 2023, 150.0, true, 2500.0);
    int rentalDays = 7;

    // Act
    double insuranceCost = truck.getInsuranceCost(rentalDays);

    // Assert
    double expectedCost = 30.0 * 7 * (2500.0 / 1000.0);
    assertEquals(expectedCost, insuranceCost, 0.001);
}
}
