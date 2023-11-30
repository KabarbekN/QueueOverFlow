package io.nurgissa.queueoverflow.mapper;

import io.nurgissa.queueoverflow.dto.UserDto;
import io.nurgissa.queueoverflow.dto.answer.AnswerDto;
import io.nurgissa.queueoverflow.dto.answer.ResponseAnswerDto;
import io.nurgissa.queueoverflow.dto.comment.ResponseCommentDto;
import io.nurgissa.queueoverflow.dto.vote.ResponseVoteDto;
import io.nurgissa.queueoverflow.models.Answer;
import io.nurgissa.queueoverflow.models.Comment;
import io.nurgissa.queueoverflow.models.User;
import io.nurgissa.queueoverflow.models.Vote;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ResponseAnswerDtoMapper {
    private final AnswerMapper answerMapper;
    private final UserMapper userMapper;
    private final ResponseCommentDtoMapper responseCommentDtoMapper;
    private final ResponseVoteDtoMapper responseVoteDtoMapper;
    public ResponseAnswerDto answerToResponseAnswerDto(Answer answer){
        // Answer
        AnswerDto answerDto = answerMapper.answerToAnswerDto(answer);
        answerDto.setCreatedTime(formatToDate( Long.parseLong(answerDto.getCreatedTime()) * 1000));
        // Answer

        // Comment
        List<Comment> comments = answer.getComments();
        List<ResponseCommentDto> responseCommentDtos = comments.stream()
                .map(responseCommentDtoMapper::commentToResponseCommentDto)
                .collect(Collectors.toList());
        // Comment

        // Vote
        List<Vote> votes = answer.getVotes();
        Integer positives = 0, negatives = 0;
        List<ResponseVoteDto> responseVoteDtos = votes.stream().map(responseVoteDtoMapper::voteToResponseVoteDto).collect(Collectors.toList());
        for (ResponseVoteDto responseVoteDto: responseVoteDtos){
            if (responseVoteDto.getVoteDto().getValue() == 1){
                positives++;
            }
            else {
                negatives--;
            }
            responseVoteDto
                    .getVoteDto()
                    .setCreatedTime(
                            formatToDate(
                                    Long.parseLong(responseVoteDto
                                            .getVoteDto()
                                            .getCreatedTime()) * 1000
                            ));
        }

        // Vote

        return ResponseAnswerDto.builder()
                .answerDto(answerDto)
                .userDto(userMapper.userToUserDto(answer.getAuthor()))
                .commentDtos(responseCommentDtos)
                .voteDtos(responseVoteDtos)
                .negativeVotes(positives)
                .positiveVotes(negatives)
//                .createdTime(formatToDate(answer.getCreatedTime() * 1000))
                .build();

    }
    public String formatToDate(Long timestamp){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH.mm");
        return simpleDateFormat.format(new Date(timestamp));
    }
}
