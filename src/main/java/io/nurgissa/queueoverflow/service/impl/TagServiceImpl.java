package io.nurgissa.queueoverflow.service.impl;


import io.nurgissa.queueoverflow.dto.TagDto;
import io.nurgissa.queueoverflow.exceptions.TagExistException;
import io.nurgissa.queueoverflow.models.Tag;
import io.nurgissa.queueoverflow.repository.TagRepository;
import io.nurgissa.queueoverflow.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    @Override
    @SneakyThrows
    public void createTag(TagDto tagDto) {
        Optional<Tag> tag = tagRepository.findByName(tagDto.getName());
        if (tag.isPresent()){
            throw new TagExistException("This tag already exists");
        }
        Tag tag1 = Tag.builder()
                .name(tagDto.getName())
                .build();
        tagRepository.save(tag1);
    }

    @Override
    public Optional<Tag> getByTagId(Long tagId) {
        return tagRepository.findById(tagId);
    }

    @Override
    @SneakyThrows
    public void deleteTagById(Long tagId) {
        if (tagRepository.existsById(tagId)){
            tagRepository.deleteById(tagId);
        }
        else {
            throw new TagExistException("Tag by this id does not exist");
        }
    }
}
