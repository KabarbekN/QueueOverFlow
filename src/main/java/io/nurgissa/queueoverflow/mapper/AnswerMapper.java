package io.nurgissa.queueoverflow.mapper;

import io.nurgissa.queueoverflow.dto.answer.AnswerDto;
import io.nurgissa.queueoverflow.models.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface AnswerMapper {
    AnswerMapper INSTANCE = Mappers.getMapper(AnswerMapper.class);

    AnswerDto answerToAnswerDto(Answer answer);
}
