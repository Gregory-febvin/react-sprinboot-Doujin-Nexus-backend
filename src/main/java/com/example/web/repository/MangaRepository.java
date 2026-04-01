package com.example.web.repository;

import com.example.web.model.Manga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MangaRepository extends JpaRepository<Manga, Integer> {
    java.util.List<Manga> findByTitle(String title);
}