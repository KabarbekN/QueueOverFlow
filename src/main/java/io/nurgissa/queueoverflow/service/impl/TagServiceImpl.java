package io.nurgissa.queueoverflow.service.impl;


import io.nurgissa.queueoverflow.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagServiceImpl {
    private final TagRepository tagRepository;
}
