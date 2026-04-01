package com.example.web.controller;

import com.example.web.model.Manga;
import com.example.web.service.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/manga")
@CrossOrigin(origins = "*")
public class MangaController {
    @Autowired
    private MangaService mangaService;

    @GetMapping
    public ResponseEntity<List<Manga>> getAllMangas() {
        return ResponseEntity.ok(mangaService.getAllMangas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manga> getMangaById(@PathVariable Integer id) {
        Optional<Manga> manga = mangaService.getMangaById(id);
        return manga.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Manga>> searchByTitle(@RequestParam String title) {
        return ResponseEntity.ok(mangaService.searchByTitle(title));
    }

    @PostMapping
    public ResponseEntity<Manga> createManga(@RequestBody Manga manga) {
        Manga created = mangaService.createManga(manga);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Manga> updateManga(@PathVariable Integer id, @RequestBody Manga manga) {
        Manga updated = mangaService.updateManga(id, manga);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManga(@PathVariable Integer id) {
        if (mangaService.deleteManga(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}