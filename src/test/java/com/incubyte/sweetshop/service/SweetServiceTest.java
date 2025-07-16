package com.incubyte.sweetshop.service;

import com.incubyte.sweetshop.model.Sweet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class SweetServiceTest {

    @Test
    void shouldReturnAllAddedSweets() {
        SweetService service = new SweetService();

        // Add two sweets
        Sweet s1 = service.addSweet(1001, "Kaju Katli", "Nut-Based", 20, 50.0);
        Sweet s2 = service.addSweet(1002, "Gajar Halwa", "Vegetable-Based", 15, 30.0);

        // Expect getAllSweets() to return exactly those two
        List<Sweet> all = service.getAllSweets();

        assertEquals(2, all.size(), "Inventory size should be 2 after adding two sweets");
        assertTrue(all.contains(s1), "Inventory should contain the first sweet");
        assertTrue(all.contains(s2), "Inventory should contain the second sweet");
    }

    @Test
    void shouldDeleteSweetById() {
        SweetService service = new SweetService();

        service.addSweet(1001, "Kaju Katli", "Nut-Based", 20, 50.0);
        service.addSweet(1002, "Gajar Halwa", "Vegetable-Based", 15, 30.0);

        // Act: delete one sweet
        boolean deleted = service.deleteSweet(1001);

        assertTrue(deleted, "Sweet with ID 1001 should be deleted");

        // Confirm it's removed from inventory
        List<Sweet> remaining = service.getAllSweets();
        assertEquals(1, remaining.size(), "Only one sweet should remain after deletion");
        assertEquals(1002, remaining.get(0).getId(), "Remaining sweet should have ID 1002");
    }

    @Test
    void deleteShouldReturnFalseWhenSweetIdNotFound() {
        SweetService service = new SweetService();

        service.addSweet(1001, "Kaju Katli", "Nut-Based", 20, 50.0);

        boolean deleted = service.deleteSweet(9999); // Non-existent ID
        assertFalse(deleted, "Should return false when trying to delete a sweet that doesn’t exist");

        // Confirm nothing was removed
        List<Sweet> inventory = service.getAllSweets();
        assertEquals(1, inventory.size(), "Inventory should remain unchanged");
        assertEquals(1001, inventory.get(0).getId(), "The original sweet should still exist");
    }

    @Test
    void shouldFindSweetByName() {
        SweetService service = new SweetService();

        service.addSweet(1001, "Kaju Katli", "Nut-Based", 20, 50.0);
        service.addSweet(1002, "Gajar Halwa", "Vegetable-Based", 15, 30.0);

        Sweet result = service.searchSweetByName("Gajar Halwa");

        assertNotNull(result, "searchSweetByName should return a sweet object");
        assertEquals(1002, result.getId());
        assertEquals("Gajar Halwa", result.getName());
    }

    @Test
    void searchShouldReturnNullIfSweetNotFound() {
        SweetService service = new SweetService();

        service.addSweet(1001, "Kaju Katli", "Nut-Based", 20, 50.0);

        Sweet result = service.searchSweetByName("Rasgulla");

        assertNull(result, "searchSweetByName should return null when sweet is not found");
    }

    @Test
    void shouldReduceQuantityWhenSweetIsPurchased() {
        SweetService service = new SweetService();
        service.addSweet(1001, "Kaju Katli", "Nut-Based", 20, 50.0);

        boolean result = service.purchaseSweet(1001, 5);

        assertTrue(result, "Purchase should succeed if quantity is available");

        Sweet updated = service.searchSweetByName("Kaju Katli");
        assertEquals(15, updated.getQuantity(), "Quantity should be reduced by 5");
    }

    @Test
    void shouldNotPurchaseIfInsufficientQuantity() {
        SweetService service = new SweetService();
        service.addSweet(1001, "Kaju Katli", "Nut-Based", 5, 50.0);

        boolean result = service.purchaseSweet(1001, 10); // Ask for more than available

        assertFalse(result, "Purchase should fail if not enough quantity is available");

        Sweet sweet = service.searchSweetByName("Kaju Katli");
        assertEquals(5, sweet.getQuantity(), "Quantity should remain unchanged after failed purchase");
    }

    @Test
    void shouldGenerateInventoryReportAsString() {
        SweetService service = new SweetService();
        service.addSweet(1001, "Kaju Katli", "Nut-Based", 20, 50.0);
        service.addSweet(1002, "Gajar Halwa", "Vegetable-Based", 15, 30.0);

        String report = service.getInventoryReport();

        String expected = "ID\tName\t\tCategory\t\tPrice\tQuantity\n" +
                "1001\tKaju Katli\tNut-Based\t50.0\t20\n" +
                "1002\tGajar Halwa\tVegetable-Based\t30.0\t15";

        assertEquals(expected, report.trim());
    }


}
