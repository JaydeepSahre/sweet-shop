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
}
