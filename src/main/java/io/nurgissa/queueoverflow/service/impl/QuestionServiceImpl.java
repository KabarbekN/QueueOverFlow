package io.nurgissa.queueoverflow.service.impl;

import io.nurgissa.queueoverflow.dto.CreateQuestionDto;
import io.nurgissa.queueoverflow.dto.ResponseQuestionDto;
import io.nurgissa.queueoverflow.mapper.QuestionMapper;
import io.nurgissa.queueoverflow.mapper.ResponseQuestionDtoMapper;
import io.nurgissa.queueoverflow.mapper.UserMapper;
import io.nurgissa.queueoverflow.models.Question;
import io.nurgissa.queueoverflow.repository.QuestionRepository;
import io.nurgissa.queueoverflow.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final UserMapper userMapper;
    private final ResponseQuestionDtoMapper questionDtoMapper;
    @Override
    public void createQuestion(CreateQuestionDto createQuestionDto) {
        Question question = Question.builder()
                .title(createQuestionDto.getTitle())
                .author(createQuestionDto.getUser())
                .content(createQuestionDto.getContent())
                .tags(createQuestionDto.getTags())
                .createdTime(System.currentTimeMillis() / 1000)
                .build();
        questionRepository.save(question);
    }

    @Override
    public Optional<ResponseQuestionDto> getQuestionById(Long id) {
        Optional<Question> question = questionRepository.findByQuestionid(id);
        return Optional.ofNullable(questionDtoMapper.questionToResponseQuestionDto(question.get()));
    }
}
