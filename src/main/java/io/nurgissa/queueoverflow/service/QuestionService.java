package io.nurgissa.queueoverflow.service;

import io.nurgissa.queueoverflow.dto.CreateQuestionDto;
import io.nurgissa.queueoverflow.dto.ResponseQuestionDto;

import java.util.Optional;

public interface QuestionService {
    void createQuestion(CreateQuestionDto createQuestionDto);

    Optional<ResponseQuestionDto> getQuestionById(Long id);
}
