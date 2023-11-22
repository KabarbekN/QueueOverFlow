package io.nurgissa.queueoverflow.service;

import io.nurgissa.queueoverflow.dto.TagDto;
import io.nurgissa.queueoverflow.models.Tag;

import java.util.Optional;

public interface TagService {
    void createTag(TagDto tagDto);
    Optional<Tag> getByTagId(Long tagId);
    void deleteTagById(Long tagId);
}
