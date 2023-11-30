package io.nurgissa.queueoverflow.service;

import io.nurgissa.queueoverflow.dto.UserDto;
import io.nurgissa.queueoverflow.dto.question.CreateQuestionDto;
import io.nurgissa.queueoverflow.dto.question.QuestionDto;
import io.nurgissa.queueoverflow.dto.question.ResponseQuestionDto;
import io.nurgissa.queueoverflow.repository.dao.QuestionSearchRequest;
import io.nurgissa.queueoverflow.repository.dao.SearchRequest;
import io.nurgissa.queueoverflow.request.ChangeQuestionRequest;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface QuestionService {
    void createQuestion(CreateQuestionDto createQuestionDto, Principal connectedUser);

    Optional<ResponseQuestionDto> getQuestionById(Long id);

    void deleteQuestionById(Long id, Principal connectedUser);

    void updateQuestion(ChangeQuestionRequest request, Principal connectedUser);
    List<QuestionDto> findAllByCriteria(QuestionSearchRequest searchRequest);

}
