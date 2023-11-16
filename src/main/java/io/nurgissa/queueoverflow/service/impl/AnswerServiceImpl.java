package io.nurgissa.queueoverflow.service.impl;

import io.nurgissa.queueoverflow.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl {
    private final AnswerRepository answerRepository;
}
