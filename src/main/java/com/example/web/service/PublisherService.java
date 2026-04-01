package com.example.web.service;

import com.example.web.model.Publisher;
import com.example.web.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;

    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    public Optional<Publisher> getPublisherById(Integer id) {
        return publisherRepository.findById(id);
    }

    public Publisher createPublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    public Publisher updatePublisher(Integer id, Publisher publisherDetails) {
        Optional<Publisher> publisher = publisherRepository.findById(id);
        if (publisher.isPresent()) {
            Publisher p = publisher.get();
            p.setName(publisherDetails.getName());
            return publisherRepository.save(p);
        }
        return null;
    }

    public boolean deletePublisher(Integer id) {
        if (publisherRepository.existsById(id)) {
            publisherRepository.deleteById(id);
            return true;
        }
        return false;
    }
}