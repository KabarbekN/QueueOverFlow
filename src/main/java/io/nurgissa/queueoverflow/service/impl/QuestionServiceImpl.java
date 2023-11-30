package io.nurgissa.queueoverflow.service.impl;

import io.nurgissa.queueoverflow.dto.question.CreateQuestionDto;
import io.nurgissa.queueoverflow.dto.question.QuestionDto;
import io.nurgissa.queueoverflow.dto.question.ResponseQuestionDto;
import io.nurgissa.queueoverflow.exceptions.NotFoundException;
import io.nurgissa.queueoverflow.exceptions.ServiceException;
import io.nurgissa.queueoverflow.mapper.QuestionMapper;
import io.nurgissa.queueoverflow.mapper.ResponseQuestionDtoMapper;
import io.nurgissa.queueoverflow.mapper.UserMapper;
import io.nurgissa.queueoverflow.models.Question;
import io.nurgissa.queueoverflow.models.Tag;
import io.nurgissa.queueoverflow.models.User;
import io.nurgissa.queueoverflow.repository.QuestionRepository;
import io.nurgissa.queueoverflow.repository.TagRepository;
import io.nurgissa.queueoverflow.repository.UserRepository;
import io.nurgissa.queueoverflow.request.ChangeQuestionRequest;
import io.nurgissa.queueoverflow.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final UserMapper userMapper;
    private final ResponseQuestionDtoMapper questionDtoMapper;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;

    //    @Override
//    public void createQuestion(CreateQuestionDto createQuestionDto) {
//        Question question = Question.builder()
//                .title(createQuestionDto.getTitle())
//                .author(createQuestionDto.getUser())
//                .content(createQuestionDto.getContent())
//                .tags(createQuestionDto.getTags())
//                .createdTime(System.currentTimeMillis() / 1000)
//                .build();
//        questionRepository.save(question);
//    }
    @Override
    @SneakyThrows
    public void createQuestion(CreateQuestionDto createQuestionDto, Principal connectedUser) {
        if (!(connectedUser instanceof UsernamePasswordAuthenticationToken)) {
            throw new NotFoundException("User not registered");
        }
        var user = (User)((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        if (user == null){
            throw new NotFoundException("User not registered");
        }

        Set<Tag> tags = new HashSet<>();
        for (Long id : createQuestionDto.getTagIds()) {
            tagRepository.findById(id).ifPresent(tags::add);
        }
        Question question = Question.builder()
                .title(createQuestionDto.getTitle())
                .author(user)
                .content(createQuestionDto.getContent())
                .tags(tags)
                .createdTime(System.currentTimeMillis() / 1000)
                .build();
        questionRepository.save(question);
    }


    @Override
    @SneakyThrows
    public Optional<ResponseQuestionDto> getQuestionById(Long id) {
        Optional<Question> question = questionRepository.findByQuestionid(id);
        if (question.isEmpty()){
            throw new ServiceException("Question not found");
        }
        return Optional.ofNullable(questionDtoMapper.questionToResponseQuestionDto(question.get()));
    }

    @Override
    @SneakyThrows
    public void deleteQuestionById(Long id, Principal connectedUser) {
        if (questionRepository.existsById(id)){
            Optional<Question> optionalQuestion = questionRepository.findById(id);
            Question question = optionalQuestion.get();
            User user = checkForUserAuthorityAndReturnUser(connectedUser);
            if (Objects.equals(question.getAuthor().getUserid(), user.getUserid())){
                questionRepository.deleteById(id);
            }
            else {
                throw new ServiceException("Do not have such authority to delete question");
            }
        }
        else {
            throw new ServiceException("No such question exception");
        }
    }

    @Override
    @SneakyThrows
    public void updateQuestion(ChangeQuestionRequest request, Principal connectedUser) {
        if (questionRepository.existsById(request.getQuestionId())){
            Optional<Question> optionalQuestion = questionRepository.findById(request.getQuestionId());
            Question question = optionalQuestion.get();
            User user = checkForUserAuthorityAndReturnUser(connectedUser);
            if (Objects.equals(question.getAuthor().getUserid(), user.getUserid())){

                Question updatedQuestion = Question.builder()
                                .title(request.getNewTitle())
                        .content(request.getNewContent())
                                        .build();
                questionRepository.save(updatedQuestion);
            }
            else {
                throw new ServiceException("Do not have such authority to delete question");
            }
        }
        else {
            throw new ServiceException("No such question exception");
        }
    }

    @SneakyThrows
    private User checkForUserAuthorityAndReturnUser(Principal connectedUser){
        if (!(connectedUser instanceof UsernamePasswordAuthenticationToken)) {
            throw new NotFoundException("User not registered");
        }
        var user = (User)((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        if (user == null){
            throw new NotFoundException("User not registered");
        }
        return user;

    }
}
