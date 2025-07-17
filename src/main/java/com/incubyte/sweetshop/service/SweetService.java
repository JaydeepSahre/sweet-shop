package com.incubyte.sweetshop.service;

import com.incubyte.sweetshop.model.Sweet;
import com.incubyte.sweetshop.repository.SweetRepository;

import java.util.List;

public class SweetService {

    private final SweetRepository repository;

    public SweetService(SweetRepository repository) {
        this.repository = repository;
    }

    public Sweet addSweet(int id, String name, String category, int quantity, double price) {
        Sweet sweet = new Sweet(id, name, category, quantity, price);
        repository.add(sweet);
        return sweet;
    }

    public boolean deleteSweet(int id) {
        return repository.deleteById(id);
    }

    public Sweet searchSweetByName(String name) {
        return repository.findByName(name);
    }

    public boolean purchaseSweet(int id, int qty) {
        Sweet sweet = repository.findById(id);
        if (sweet != null && sweet.getQuantity() >= qty) {
            sweet.setQuantity(sweet.getQuantity() - qty);
            return true;
        }
        return false;
    }

    public List<Sweet> getAllSweets() {
        return repository.findAll();
    }

    public String getInventoryReport() {
        List<Sweet> inventory = repository.findAll();

        StringBuilder sb = new StringBuilder();
        sb.append("ID\tName\t\tCategory\t\tPrice\tQuantity\n");

        for (Sweet sweet : inventory) {
            sb.append(sweet.getId()).append("\t")
                    .append(sweet.getName()).append("\t")
                    .append(sweet.getCategory()).append("\t")
                    .append(sweet.getPrice()).append("\t")
                    .append(sweet.getQuantity()).append("\n");
        }

        return sb.toString().trim();
    }

    public boolean restockSweet(int id, int quantity) {
        Sweet sweet = repository.findById(id);
        if (sweet == null || quantity <= 0) return false;
        sweet.setQuantity(sweet.getQuantity() + quantity);
        return true;
    }

}
