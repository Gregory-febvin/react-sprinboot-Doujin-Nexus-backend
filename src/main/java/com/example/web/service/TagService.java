package com.example.web.service;

import com.example.web.model.Tag;
import com.example.web.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Optional<Tag> getTagById(Integer id) {
        return tagRepository.findById(id);
    }

    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    public Tag updateTag(Integer id, Tag tagDetails) {
        Optional<Tag> tag = tagRepository.findById(id);
        if (tag.isPresent()) {
            Tag t = tag.get();
            t.setName(tagDetails.getName());
            return tagRepository.save(t);
        }
        return null;
    }

    public boolean deleteTag(Integer id) {
        if (tagRepository.existsById(id)) {
            tagRepository.deleteById(id);
            return true;
        }
        return false;
    }
}