package com.example.web.repository;

import com.example.web.model.Parody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParodyRepository extends JpaRepository<Parody, Integer> {
    Parody findByName(String name);
}