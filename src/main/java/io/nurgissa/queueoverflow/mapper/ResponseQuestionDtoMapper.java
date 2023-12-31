package io.nurgissa.queueoverflow.mapper;

import io.nurgissa.queueoverflow.dto.answer.AnswerDto;
import io.nurgissa.queueoverflow.dto.answer.ResponseAnswerDto;
import io.nurgissa.queueoverflow.dto.comment.ResponseCommentDto;
import io.nurgissa.queueoverflow.dto.question.QuestionDto;
import io.nurgissa.queueoverflow.dto.question.ResponseQuestionDto;
import io.nurgissa.queueoverflow.dto.TagDto;
import io.nurgissa.queueoverflow.dto.vote.ResponseVoteDto;
import io.nurgissa.queueoverflow.dto.vote.VoteDto;
import io.nurgissa.queueoverflow.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ResponseQuestionDtoMapper {

    private final UserMapper userMapper;
    private final TagMapper tagMapper;
    private final QuestionMapper questionMapper;
    private final AnswerMapper answerMapper;
    private final ResponseAnswerDtoMapper responseAnswerDtoMapper;
    private final CommentMapper commentMapper;
    private final ResponseCommentDtoMapper responseCommentDtoMapper;
    private final VoteMapper voteMapper;
    private final ResponseVoteDtoMapper responseVoteDtoMapper;

    public ResponseQuestionDto questionToResponseQuestionDto(Question question){

        // Tag
        Set<Tag> tags = question.getTags();
        Set<TagDto> tagDtos = tags.stream()
                .map(tagMapper::tagToTagDto)
                .collect(Collectors.toSet());
        // Tag

        // Answer
        List<Answer> answers = question.getAnswers();

        List<ResponseAnswerDto> answerDtos = answers.stream()
                .map(responseAnswerDtoMapper::answerToResponseAnswerDto)
                .collect(Collectors.toList());
        // Answer

        // Comment

        List<Comment> comments = question.getComments();

        List<ResponseCommentDto> commentDtos = comments.stream()
                .map(responseCommentDtoMapper::commentToResponseCommentDto)
                .collect(Collectors.toList());

        // Comment


        // Question

        QuestionDto questionDto = questionMapper.questionToQuestionDto(question);
        questionDto.setCreatedTime(formatToDate(Long.parseLong(questionDto.getCreatedTime()) * 1000));

        // Question

        // Vote
        List<Vote> votes = question.getVotes();
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



        return  ResponseQuestionDto.builder()
                .questionDto(questionDto)
                .userDto(userMapper.userToUserDto(question.getAuthor()))
                .tagDtos(tagDtos)
                .answerDtos(answerDtos)
                .commentDtos(commentDtos)
                .voteDtos(responseVoteDtos)
                .positiveVotes(positives)
                .negativeVotes(negatives)
                .build();
    }

    public String formatToDate(Long timestamp){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH.mm");
        return simpleDateFormat.format(new Date(timestamp));
    }
}
