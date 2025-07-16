package com.incubyte.sweetshop.service;

import com.incubyte.sweetshop.model.Sweet;

public class SweetService {

    /**
     * Adds a new sweet to the inventory.
     * For now, this just returns a new Sweet instance.
     */
    public Sweet addSweet(String name, int quantity, double price) {
        return new Sweet(name, quantity, price);
    }
}
