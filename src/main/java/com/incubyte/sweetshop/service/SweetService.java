package com.incubyte.sweetshop.service;

import com.incubyte.sweetshop.model.Sweet;

public class SweetService {

    /**
     * Adds a new sweet to the inventory.
     * For now, this just returns a new Sweet instance.
     */
    public Sweet addSweet(int id, String name, String category, int quantity, double price) {
        return new Sweet(id, name, category, quantity, price);
    }
}
