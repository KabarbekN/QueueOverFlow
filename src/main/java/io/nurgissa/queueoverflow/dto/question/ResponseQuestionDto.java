package io.nurgissa.queueoverflow.dto.question;

import io.nurgissa.queueoverflow.dto.TagDto;
import io.nurgissa.queueoverflow.dto.UserDto;
import io.nurgissa.queueoverflow.dto.answer.AnswerDto;
import io.nurgissa.queueoverflow.dto.answer.ResponseAnswerDto;
import io.nurgissa.queueoverflow.dto.comment.ResponseCommentDto;
import io.nurgissa.queueoverflow.dto.question.QuestionDto;
import io.nurgissa.queueoverflow.dto.vote.ResponseVoteDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseQuestionDto {
    private QuestionDto questionDto;
    private UserDto userDto;
    private Set<TagDto> tagDtos;
    private List<ResponseAnswerDto> answerDtos;
    private List<ResponseCommentDto> commentDtos;
    private List<ResponseVoteDto> voteDtos;
    private Integer positiveVotes;
    private Integer negativeVotes;
//    private String createdTime;
// add func for comment, answer and etc

}
