package com.example.web.service;

import com.example.web.model.Parody;
import com.example.web.repository.ParodyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ParodyService {
    @Autowired
    private ParodyRepository parodyRepository;

    public List<Parody> getAllParodies() {
        return parodyRepository.findAll();
    }

    public Optional<Parody> getParodyById(Integer id) {
        return parodyRepository.findById(id);
    }

    public Parody createParody(Parody parody) {
        return parodyRepository.save(parody);
    }

    public Parody updateParody(Integer id, Parody parodyDetails) {
        Optional<Parody> parody = parodyRepository.findById(id);
        if (parody.isPresent()) {
            Parody p = parody.get();
            p.setName(parodyDetails.getName());
            return parodyRepository.save(p);
        }
        return null;
    }

    public boolean deleteParody(Integer id) {
        if (parodyRepository.existsById(id)) {
            parodyRepository.deleteById(id);
            return true;
        }
        return false;
    }
}