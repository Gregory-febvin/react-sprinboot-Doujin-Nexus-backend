package com.example.web.service;

import com.example.web.model.Manga;
import com.example.web.repository.MangaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MangaService {
    @Autowired
    private MangaRepository mangaRepository;

    private static final String IMAGE_BASE_URL = "http://localhost:8080/api/manga/";

    public List<Manga> getAllMangas() {
        List<Manga> mangas = mangaRepository.findAll();
        mangas.forEach(this::setCoverUrlAndSortTags);
        return mangas;
    }

    public Optional<Manga> getMangaById(Integer id) {
        Optional<Manga> manga = mangaRepository.findById(id);
        manga.ifPresent(this::setCoverUrlAndSortTags);
        return manga;
    }

    public List<Manga> searchByTitle(String title) {
        List<Manga> mangas = mangaRepository.findByTitle(title);
        mangas.forEach(this::setCoverUrlAndSortTags);
        return mangas;
    }

    public Manga createManga(Manga manga) {
        return mangaRepository.save(manga);
    }

    public Manga updateManga(Integer id, Manga mangaDetails) {
        Optional<Manga> manga = mangaRepository.findById(id);
        if (manga.isPresent()) {
            Manga m = manga.get();
            m.setTitle(mangaDetails.getTitle());
            m.setPages(mangaDetails.getPages());
            m.setPublisher(mangaDetails.getPublisher());
            m.setArtists(mangaDetails.getArtists());
            m.setMagazines(mangaDetails.getMagazines());
            m.setParodies(mangaDetails.getParodies());
            m.setTags(mangaDetails.getTags());
            return mangaRepository.save(m);
        }
        return null;
    }

    public boolean deleteManga(Integer id) {
        if (mangaRepository.existsById(id)) {
            mangaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private void setCoverUrl(Manga manga) {
        manga.setCover(IMAGE_BASE_URL + manga.getId() + "/image/1.jpg");
    }

    private void setCoverUrlAndSortTags(Manga manga) {
        setCoverUrl(manga);
        
        // Pour trier les tags par ASC car je sui un connard et j'arrive pas à le faire via la querry directement
        if (manga.getTags() != null && !manga.getTags().isEmpty()) {
            manga.setTags(
                manga.getTags().stream()
                    .sorted((tag1, tag2) -> tag1.getName().compareToIgnoreCase(tag2.getName()))
                    .collect(Collectors.toCollection(java.util.LinkedHashSet::new))
            );
        }
    }
}