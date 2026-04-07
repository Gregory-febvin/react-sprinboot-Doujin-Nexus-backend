package com.example.web.controller;

import com.example.web.model.Parody;
import com.example.web.model.Manga;
import com.example.web.service.ParodyService;
import com.example.web.service.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/parodies")
@CrossOrigin(origins = "*")
public class ParodyController {
    @Autowired
    private ParodyService parodyService;

    @Autowired
    private MangaService mangaService;

    @GetMapping
    public ResponseEntity<List<Parody>> getAllParodies() {
        return ResponseEntity.ok(parodyService.getAllParodies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parody> getParodyById(@PathVariable Integer id) {
        Optional<Parody> parody = parodyService.getParodyById(id);
        return parody.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search/{parodyName}")
    public ResponseEntity<List<Manga>> getMangasByParody(@PathVariable String parodyName) {
        return ResponseEntity.ok(mangaService.getMangasByParody(parodyName));
    }

    @PostMapping
    public ResponseEntity<Parody> createParody(@RequestBody Parody parody) {
        Parody created = parodyService.createParody(parody);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parody> updateParody(@PathVariable Integer id, @RequestBody Parody parody) {
        Parody updated = parodyService.updateParody(id, parody);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParody(@PathVariable Integer id) {
        if (parodyService.deleteParody(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}