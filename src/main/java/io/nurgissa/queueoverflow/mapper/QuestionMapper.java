package io.nurgissa.queueoverflow.mapper;

import io.nurgissa.queueoverflow.dto.question.QuestionDto;
import io.nurgissa.queueoverflow.models.Question;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);
    QuestionDto questionToQuestionDto(Question question);

    // think about implementation of this
}
