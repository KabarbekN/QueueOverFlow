package io.nurgissa.queueoverflow.service;

import io.nurgissa.queueoverflow.dto.question.CreateQuestionDto;
import io.nurgissa.queueoverflow.dto.question.ResponseQuestionDto;
import io.nurgissa.queueoverflow.request.ChangeQuestionRequest;

import java.security.Principal;
import java.util.Optional;

public interface QuestionService {
    void createQuestion(CreateQuestionDto createQuestionDto, Principal connectedUser);

    Optional<ResponseQuestionDto> getQuestionById(Long id);

    void deleteQuestionById(Long id, Principal connectedUser);

    void updateQuestion(ChangeQuestionRequest request, Principal connectedUser);
}
