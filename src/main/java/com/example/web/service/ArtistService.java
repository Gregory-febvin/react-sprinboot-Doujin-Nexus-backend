package com.example.web.service;

import com.example.web.model.Artist;
import com.example.web.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {
    @Autowired
    private ArtistRepository artistRepository;

    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    public Optional<Artist> getArtistById(Integer id) {
        return artistRepository.findById(id);
    }

    public Artist createArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    public Artist updateArtist(Integer id, Artist artistDetails) {
        Optional<Artist> artist = artistRepository.findById(id);
        if (artist.isPresent()) {
            Artist a = artist.get();
            a.setName(artistDetails.getName());
            return artistRepository.save(a);
        }
        return null;
    }

    public boolean deleteArtist(Integer id) {
        if (artistRepository.existsById(id)) {
            artistRepository.deleteById(id);
            return true;
        }
        return false;
    }
}