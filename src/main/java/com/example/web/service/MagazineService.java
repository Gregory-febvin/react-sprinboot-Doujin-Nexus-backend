package com.example.web.service;

import com.example.web.model.Magazine;
import com.example.web.repository.MagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MagazineService {
    @Autowired
    private MagazineRepository magazineRepository;

    public List<Magazine> getAllMagazines() {
        return magazineRepository.findAll();
    }

    public Optional<Magazine> getMagazineById(Integer id) {
        return magazineRepository.findById(id);
    }

    public Magazine createMagazine(Magazine magazine) {
        return magazineRepository.save(magazine);
    }

    public Magazine updateMagazine(Integer id, Magazine magazineDetails) {
        Optional<Magazine> magazine = magazineRepository.findById(id);
        if (magazine.isPresent()) {
            Magazine m = magazine.get();
            m.setName(magazineDetails.getName());
            return magazineRepository.save(m);
        }
        return null;
    }

    public boolean deleteMagazine(Integer id) {
        if (magazineRepository.existsById(id)) {
            magazineRepository.deleteById(id);
            return true;
        }
        return false;
    }
}