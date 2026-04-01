package com.example.web.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ImageController {

    // Route pour une image spécifique
    @GetMapping("/api/manga/{mangaId}/image/{imageNumber}.jpg")
    public ResponseEntity<Resource> getImageJpg(@PathVariable Integer mangaId, @PathVariable Integer imageNumber) {
        return getImage(mangaId, imageNumber, "jpg");
    }

    @GetMapping("/api/manga/{mangaId}/image/{imageNumber}.png")
    public ResponseEntity<Resource> getImagePng(@PathVariable Integer mangaId, @PathVariable Integer imageNumber) {
        return getImage(mangaId, imageNumber, "png");
    }

    @GetMapping("/api/manga/{mangaId}/image/{imageNumber}.gif")
    public ResponseEntity<Resource> getImageGif(@PathVariable Integer mangaId, @PathVariable Integer imageNumber) {
        return getImage(mangaId, imageNumber, "gif");
    }

    @GetMapping("/api/manga/{mangaId}/image/{imageNumber}.webp")
    public ResponseEntity<Resource> getImageWebp(@PathVariable Integer mangaId, @PathVariable Integer imageNumber) {
        return getImage(mangaId, imageNumber, "webp");
    }

    @GetMapping("/api/images/{mangaId}")
    public ResponseEntity<List<String>> getImageUrls(@PathVariable Integer mangaId, @RequestParam Integer pages) {
        List<String> urls = new ArrayList<>();
        for (int i = 1; i <= pages; i++) {
            urls.add("http://localhost:8080/api/manga/" + mangaId + "/image/" + i + ".jpg");
        }
        return ResponseEntity.ok(urls);
    }

    private ResponseEntity<Resource> getImage(Integer mangaId, Integer imageNumber, String format) {
        try {
            String currentDir = System.getProperty("user.dir");
            
            String formattedNumber = String.format("%03d", imageNumber);
            
            Path imagePath = Paths.get(currentDir, "src", "main", "java", "com", "example", "web", "asset", 
                                       mangaId.toString(), "page_" + formattedNumber + "." + format);
            
            File imageFile = imagePath.toFile();

            System.out.println("Recherche du fichier: " + imageFile.getAbsolutePath());
            System.out.println("Fichier existe: " + imageFile.exists());

            if (!imageFile.exists()) {
                System.out.println("ERREUR: Fichier non trouvé!");
                return ResponseEntity.notFound().build();
            }

            Resource resource = new FileSystemResource(imageFile);

            String contentType = "image/" + format;
            if (format.equals("jpg")) {
                contentType = "image/jpeg";
            }

            return ResponseEntity.ok()
                    .header("Content-Type", contentType)
                    .body(resource);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}