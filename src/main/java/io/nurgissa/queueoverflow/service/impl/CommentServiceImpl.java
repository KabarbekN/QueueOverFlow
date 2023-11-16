package io.nurgissa.queueoverflow.service.impl;


import io.nurgissa.queueoverflow.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl {
    private final CommentRepository commentRepository;
}
