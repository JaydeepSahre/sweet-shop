package com.incubyte.sweetshop.controller;

import com.incubyte.sweetshop.model.Sweet;
import com.incubyte.sweetshop.repository.InMemorySweetRepository;
import com.incubyte.sweetshop.service.SweetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/sweets")
public class SweetController {

    private final SweetService sweetService;

    public SweetController() {
        this.sweetService = new SweetService(new InMemorySweetRepository());
    }

    @GetMapping
    public List<Sweet> getAllSweets() {
        return sweetService.getAllSweets();
    }

    @PostMapping
    public Sweet addSweet(@RequestBody Sweet sweet) {
        return sweetService.addSweet(
                sweet.getId(),
                sweet.getName(),
                sweet.getCategory(),
                sweet.getQuantity(),
                sweet.getPrice()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSweet(@PathVariable int id) {
        boolean deleted = sweetService.deleteSweet(id); // also fixed typo: was `service`
        if (deleted) {
            return ResponseEntity.ok("Sweet deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sweet not found.");
        }
    }

    @PutMapping("/{id}/purchase")
    public ResponseEntity<String> purchaseSweet(
            @PathVariable int id,
            @RequestParam int quantity) {
        boolean success = sweetService.purchaseSweet(id, quantity);
        if (success) {
            return ResponseEntity.ok("Purchase successful");
        } else {
            return ResponseEntity.badRequest().body("Purchase failed: insufficient stock or sweet not found");
        }
    }
    
    @GetMapping("/report")
    public ResponseEntity<String> getInventoryReport() {
        String report = sweetService.getInventoryReport();
        return ResponseEntity.ok(report);
    }

    @PutMapping("/sweets/{id}/restock")
    public ResponseEntity<String> restockSweet(@PathVariable int id, @RequestParam int quantity) {
        boolean success = sweetService.restockSweet(id, quantity);
        if (success) {
            return ResponseEntity.ok("Restock successful");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sweet not found");
        }
    }

}
