package com.incubyte.sweetshop.service;

import com.incubyte.sweetshop.model.Sweet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SweetServiceTest {

    @Test
    void shouldAddSweetToInventory() {
        SweetService service = new SweetService();
        Sweet sweet = service.addSweet(1001, "Kaju Katli", "Nut-Based", 20, 50.0);

        assertNotNull(sweet);
        assertEquals(1001, sweet.getId());
        assertEquals("Kaju Katli", sweet.getName());
        assertEquals("Nut-Based", sweet.getCategory());
        assertEquals(20, sweet.getQuantity());
        assertEquals(50.0, sweet.getPrice());
    }
}
