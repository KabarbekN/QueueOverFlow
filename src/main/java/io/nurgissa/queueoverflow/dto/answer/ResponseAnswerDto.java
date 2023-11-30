package io.nurgissa.queueoverflow.dto.answer;


import io.nurgissa.queueoverflow.dto.UserDto;
import io.nurgissa.queueoverflow.dto.answer.AnswerDto;
import io.nurgissa.queueoverflow.dto.comment.ResponseCommentDto;
import io.nurgissa.queueoverflow.dto.vote.ResponseVoteDto;
import io.nurgissa.queueoverflow.mapper.ResponseCommentDtoMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseAnswerDto {
    private AnswerDto answerDto;
    private UserDto userDto;
    private List<ResponseCommentDto> commentDtos;
    private List<ResponseVoteDto> voteDtos;
    private Integer positiveVotes;
    private Integer negativeVotes;
//    private String createdTime;

}
