package com.example.web.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "magazine")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "mangas")
public class Magazine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "magazines")
    @JsonIgnore
    private java.util.Set<Manga> mangas = new java.util.HashSet<>();
}