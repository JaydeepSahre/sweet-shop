package com.incubyte.sweetshop.repository;

import com.incubyte.sweetshop.model.Sweet;

import java.util.ArrayList;
import java.util.List;

public class InMemorySweetRepository implements SweetRepository {

    private final List<Sweet> inventory = new ArrayList<>();

    public void add(Sweet sweet) {
        inventory.add(sweet);
    }

    public boolean deleteById(int id) {
        return inventory.removeIf(s -> s.getId() == id);
    }

    public Sweet findByName(String name) {
        for (Sweet s : inventory) {
            if (s.getName().equalsIgnoreCase(name)) return s;
        }
        return null;
    }

    public Sweet findById(int id) {
        for (Sweet s : inventory) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    public List<Sweet> findAll() {
        return inventory;
    }
}
