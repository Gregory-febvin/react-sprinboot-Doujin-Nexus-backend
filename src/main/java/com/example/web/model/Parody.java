package com.example.web.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "parody")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "mangas")
public class Parody {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "parodies")
    @JsonIgnore
    private java.util.Set<Manga> mangas = new java.util.HashSet<>();
}