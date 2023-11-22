package io.nurgissa.queueoverflow.mapper;

import io.nurgissa.queueoverflow.dto.ResponseQuestionDto;
import io.nurgissa.queueoverflow.dto.TagDto;
import io.nurgissa.queueoverflow.models.Question;
import io.nurgissa.queueoverflow.models.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ResponseQuestionDtoMapper {

    private final UserMapper userMapper;
    private final TagMapper tagMapper;
    private final QuestionMapper questionMapper;

    public ResponseQuestionDto questionToResponseQuestionDto(Question question){
        Set<Tag> tags = question.getTags();
        Set<TagDto> tagDtos = tags.stream()
                .map(tagMapper::tagToTagDto)
                .collect(Collectors.toSet());
        return new ResponseQuestionDto().builder()
                .questionDto(questionMapper.questionToQuestionDto(question))
                .user(userMapper.userToUserDto(question.getAuthor()))
                .tags(tagDtos)
                .build();
    }
}
