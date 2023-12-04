package io.nurgissa.queueoverflow.service.impl;

import io.nurgissa.queueoverflow.dto.answer.CreateAnswerDto;
import io.nurgissa.queueoverflow.dto.answer.ResponseAnswerDto;
import io.nurgissa.queueoverflow.exceptions.NotFoundException;
import io.nurgissa.queueoverflow.exceptions.ServiceException;
import io.nurgissa.queueoverflow.mapper.ResponseAnswerDtoMapper;
import io.nurgissa.queueoverflow.models.Answer;
import io.nurgissa.queueoverflow.models.Question;
import io.nurgissa.queueoverflow.models.User;
import io.nurgissa.queueoverflow.repository.AnswerRepository;
import io.nurgissa.queueoverflow.repository.QuestionRepository;
import io.nurgissa.queueoverflow.service.AnswerService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final ResponseAnswerDtoMapper responseAnswerDtoMapper;

    @Override
    public List<ResponseAnswerDto> getAllAnswersForQuestion(Long questionId) {
        List<Answer> answers = answerRepository.findAllByQuestionQuestionid(questionId);
        log.info("List of answer dto was returned");
        return answers.stream().map(responseAnswerDtoMapper::answerToResponseAnswerDto).collect(Collectors.toList());
    }

    @Override
    @SneakyThrows
    public void createAnswer(CreateAnswerDto createAnswerDto, Principal connectedUser) {
        User user = checkForUserAuthorityAndReturnUser(connectedUser);
        Optional<Question> optionalQuestion = questionRepository.findByQuestionid(createAnswerDto.getQuestionId());
        if (optionalQuestion.isEmpty()){
            log.info("Not existing question id was searched");
            throw new ServiceException("Question do not exist by this id");
        }

        Question question = optionalQuestion.get();

        Answer answer = Answer.builder()
                .author(user)
                .content(createAnswerDto.getContent())
                .question(question)
                .createdTime(System.currentTimeMillis() / 1000)
                .build();
        log.info(answer.getAuthor() + " " + answer.getContent() + " was saved");
        answerRepository.save(answer);
    }

    @Override
    @SneakyThrows
    public void deleteAnswerById(Long id, Principal connectedUser) {
        if (answerRepository.findById(id).isEmpty()){
            throw new ServiceException("No such answer");
        }
        User user = checkForUserAuthorityAndReturnUser(connectedUser);
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        Answer answer = optionalAnswer.get();
        if (!Objects.equals(answer.getAuthor().getUserid(), user.getUserid())){
            log.info(user.getUsername() + " try to delete other users answer by id");
            throw new ServiceException("No such authority");
        }

        answerRepository.deleteById(id);
    }


    @SneakyThrows
    private User checkForUserAuthorityAndReturnUser(Principal connectedUser){
        if (!(connectedUser instanceof UsernamePasswordAuthenticationToken)) {
            log.info("Not connected user");
            throw new NotFoundException("User not registered");
        }
        var user = (User)((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        if (user == null){
            log.info("Not connected user");
            throw new NotFoundException("User not registered");
        }
        return user;

    }
}
