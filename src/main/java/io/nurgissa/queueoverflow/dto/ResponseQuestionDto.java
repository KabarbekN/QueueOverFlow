package io.nurgissa.queueoverflow.dto;

import io.nurgissa.queueoverflow.models.Question;
import io.nurgissa.queueoverflow.models.Tag;
import io.nurgissa.queueoverflow.models.User;
import jakarta.persistence.NamedStoredProcedureQueries;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseQuestionDto {
    private QuestionDto questionDto;
    private UserDto user;
    private Set<TagDto> tags;
// add func for comment, answer and etc

}
