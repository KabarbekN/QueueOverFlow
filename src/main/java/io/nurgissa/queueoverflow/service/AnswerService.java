package io.nurgissa.queueoverflow.service;

import io.nurgissa.queueoverflow.dto.answer.CreateAnswerDto;
import io.nurgissa.queueoverflow.dto.answer.ResponseAnswerDto;
import io.nurgissa.queueoverflow.models.Answer;

import java.security.Principal;
import java.util.List;

public interface AnswerService {

    List<ResponseAnswerDto> getAllAnswersForQuestion(Long questionId);

    void createAnswer(CreateAnswerDto createAnswerDto, Principal connectedUser);
    void deleteAnswerById(Long id, Principal connectedUser);
}
