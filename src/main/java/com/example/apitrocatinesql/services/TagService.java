package com.example.apitrocatinesql.services;

import com.example.apitrocatinesql.models.Category;
import com.example.apitrocatinesql.models.Tag;
import com.example.apitrocatinesql.repositories.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TagService {

    private TagRepository tagRepository;

    public List<String> findTagByType(String type) {
        List<Tag> listCategory = tagRepository.findTagByType(type);
        return listCategory.stream().map(Tag::getName).collect(Collectors.toList());

    }
}
