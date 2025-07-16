package com.incubyte.sweetshop.service;

import com.incubyte.sweetshop.model.Sweet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SweetServiceTest {

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
}
