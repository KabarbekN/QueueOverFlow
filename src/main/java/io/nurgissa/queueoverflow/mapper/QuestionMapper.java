package io.nurgissa.queueoverflow.mapper;

import io.nurgissa.queueoverflow.dto.CreateQuestionDto;
import io.nurgissa.queueoverflow.dto.QuestionDto;
import io.nurgissa.queueoverflow.dto.ResponseQuestionDto;
import io.nurgissa.queueoverflow.models.Question;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);
    QuestionDto questionToQuestionDto(Question question);

   // think about implementation of this
}
