package com.incubyte.sweetshop.service;

import com.incubyte.sweetshop.model.Sweet;
import com.incubyte.sweetshop.repository.InMemorySweetRepository;
import com.incubyte.sweetshop.repository.SweetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SweetServiceTest {

    private SweetService service;

    @BeforeEach
    void setUp() {
        SweetRepository repository = new InMemorySweetRepository();
        service = new SweetService(repository);
    }

    @Test
    void shouldReturnAllAddedSweets() {
        Sweet s1 = service.addSweet(1001, "Kaju Katli", "Nut-Based", 20, 50.0);
        Sweet s2 = service.addSweet(1002, "Gajar Halwa", "Vegetable-Based", 15, 30.0);

        List<Sweet> all = service.getAllSweets();

        assertEquals(2, all.size());
        assertTrue(all.contains(s1));
        assertTrue(all.contains(s2));
    }

    @Test
    void shouldDeleteSweetById() {
        service.addSweet(1001, "Kaju Katli", "Nut-Based", 20, 50.0);
        service.addSweet(1002, "Gajar Halwa", "Vegetable-Based", 15, 30.0);

        boolean deleted = service.deleteSweet(1001);

        assertTrue(deleted);

        List<Sweet> remaining = service.getAllSweets();
        assertEquals(1, remaining.size());
        assertEquals(1002, remaining.get(0).getId());
    }

    @Test
    void deleteShouldReturnFalseWhenSweetIdNotFound() {
        service.addSweet(1001, "Kaju Katli", "Nut-Based", 20, 50.0);

        boolean deleted = service.deleteSweet(9999);
        assertFalse(deleted);

        List<Sweet> inventory = service.getAllSweets();
        assertEquals(1, inventory.size());
        assertEquals(1001, inventory.get(0).getId());
    }

    @Test
    void shouldFindSweetByName() {
        service.addSweet(1001, "Kaju Katli", "Nut-Based", 20, 50.0);
        service.addSweet(1002, "Gajar Halwa", "Vegetable-Based", 15, 30.0);

        Sweet result = service.searchSweetByName("Gajar Halwa");

        assertNotNull(result);
        assertEquals(1002, result.getId());
        assertEquals("Gajar Halwa", result.getName());
    }

    @Test
    void searchShouldReturnNullIfSweetNotFound() {
        service.addSweet(1001, "Kaju Katli", "Nut-Based", 20, 50.0);

        Sweet result = service.searchSweetByName("Rasgulla");

        assertNull(result);
    }

    @Test
    void shouldReduceQuantityWhenSweetIsPurchased() {
        service.addSweet(1001, "Kaju Katli", "Nut-Based", 20, 50.0);

        boolean result = service.purchaseSweet(1001, 5);

        assertTrue(result);

        Sweet updated = service.searchSweetByName("Kaju Katli");
        assertEquals(15, updated.getQuantity());
    }

    @Test
    void shouldNotPurchaseIfInsufficientQuantity() {
        service.addSweet(1001, "Kaju Katli", "Nut-Based", 5, 50.0);

        boolean result = service.purchaseSweet(1001, 10);

        assertFalse(result);

        Sweet sweet = service.searchSweetByName("Kaju Katli");
        assertEquals(5, sweet.getQuantity());
    }

    @Test
    void shouldGenerateInventoryReportAsString() {
        service.addSweet(1001, "Kaju Katli", "Nut-Based", 20, 50.0);
        service.addSweet(1002, "Gajar Halwa", "Vegetable-Based", 15, 30.0);

        String report = service.getInventoryReport();

        String expected = "ID\tName\t\tCategory\t\tPrice\tQuantity\n" +
                "1001\tKaju Katli\tNut-Based\t50.0\t20\n" +
                "1002\tGajar Halwa\tVegetable-Based\t30.0\t15";

        assertEquals(expected, report.trim());
    }

    @Test
    void shouldIncreaseQuantityWhenRestocked() {
        service.addSweet(1001, "Kaju Katli", "Nut-Based", 10, 50.0);

        boolean result = service.restockSweet(1001, 5);

        assertTrue(result, "Restock should succeed");
        Sweet updated = service.searchSweetByName("Kaju Katli");
        assertEquals(15, updated.getQuantity(), "Quantity should increase after restocking");
    }

    @Test
    void restockShouldReturnFalseIfSweetNotFoundOrInvalidQty() {

        assertFalse(service.restockSweet(999, 10), "Should return false if sweet not found");
        service.addSweet(1002, "Ladoo", "Gram-Based", 5, 20.0);
        assertFalse(service.restockSweet(1002, -5), "Should return false for invalid quantity");
    }

}
