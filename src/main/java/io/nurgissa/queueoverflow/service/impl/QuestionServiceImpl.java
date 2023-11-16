package io.nurgissa.queueoverflow.service.impl;

import io.nurgissa.queueoverflow.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class QuestionServiceImpl {
    private final QuestionRepository questionRepository;
}
