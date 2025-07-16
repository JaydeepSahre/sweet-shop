package com.incubyte.sweetshop.service;

import com.incubyte.sweetshop.model.Sweet;
import java.util.ArrayList;
import java.util.List;

public class SweetService {

    private final List<Sweet> inventory = new ArrayList<>();

    public Sweet addSweet(int id, String name, String category, int quantity, double price) {
        Sweet sweet = new Sweet(id, name, category, quantity, price);
        inventory.add(sweet);
        return sweet;
    }

    public List<Sweet> getAllSweets() {
        return inventory;
    }

    /**
     * Deletes a sweet by its ID from the inventory.
     * @param id the ID of the sweet to delete
     * @return true if deleted, false if not found
     */
    public boolean deleteSweet(int id) {
        return inventory.removeIf(sweet -> sweet.getId() == id);
    }
}
