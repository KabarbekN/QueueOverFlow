package io.nurgissa.queueoverflow.service.impl;


import io.nurgissa.queueoverflow.dto.TagDto;
import io.nurgissa.queueoverflow.exceptions.ServiceException;
import io.nurgissa.queueoverflow.exceptions.TagExistException;
import io.nurgissa.queueoverflow.models.Tag;
import io.nurgissa.queueoverflow.repository.TagRepository;
import io.nurgissa.queueoverflow.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    @Override
    @SneakyThrows
    public void createTag(TagDto tagDto) {
        Optional<Tag> tag = tagRepository.findByName(tagDto.getName());
        if (tag.isPresent()){
            log.info("Same tag creation attempt " + tagDto.getName());
            throw new TagExistException("This tag already exists");
        }
        Tag tag1 = Tag.builder()
                .name(tagDto.getName())
                .build();
        tagRepository.save(tag1);
    }

    @Override
    @SneakyThrows
    public Optional<Tag> getByTagId(Long tagId) {
        if ( tagRepository.findById(tagId).isEmpty()){
            throw new ServiceException("Tag by this id not found");
        }
        log.info("Return optional tag by id");

        return tagRepository.findById(tagId);
    }

    @Override
    @SneakyThrows
    public void deleteTagById(Long tagId) {
        if (tagRepository.existsById(tagId)){
            tagRepository.deleteById(tagId);
            log.info("Tag was deleted by id: " + tagId);
        }
        else {
            throw new TagExistException("Tag by this id does not exist");
        }
    }
}
