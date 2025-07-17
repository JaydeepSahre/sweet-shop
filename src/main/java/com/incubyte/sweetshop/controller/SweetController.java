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
        // In-memory repository setup for now
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

    @DeleteMapping("/sweets/{id}")
    public ResponseEntity<String> deleteSweet(@PathVariable int id) {
        boolean deleted = service.deleteSweet(id);
        if (deleted) {
            return ResponseEntity.ok("Sweet deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sweet not found.");
        }
    }


}
