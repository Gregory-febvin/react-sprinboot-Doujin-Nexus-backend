package com.example.web.controller;

import com.example.web.model.Magazine;
import com.example.web.service.MagazineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/magazines")
@CrossOrigin(origins = "*")
public class MagazineController {
    @Autowired
    private MagazineService magazineService;

    @GetMapping
    public ResponseEntity<List<Magazine>> getAllMagazines() {
        return ResponseEntity.ok(magazineService.getAllMagazines());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Magazine> getMagazineById(@PathVariable Integer id) {
        Optional<Magazine> magazine = magazineService.getMagazineById(id);
        return magazine.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Magazine> createMagazine(@RequestBody Magazine magazine) {
        Magazine created = magazineService.createMagazine(magazine);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Magazine> updateMagazine(@PathVariable Integer id, @RequestBody Magazine magazine) {
        Magazine updated = magazineService.updateMagazine(id, magazine);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMagazine(@PathVariable Integer id) {
        if (magazineService.deleteMagazine(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}