package com.example.web.repository;

import com.example.web.model.Magazine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagazineRepository extends JpaRepository<Magazine, Integer> {
    Magazine findByName(String name);
}