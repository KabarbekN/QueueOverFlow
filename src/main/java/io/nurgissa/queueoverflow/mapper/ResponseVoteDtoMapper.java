package io.nurgissa.queueoverflow.mapper;


import io.nurgissa.queueoverflow.dto.vote.ResponseVoteDto;
import io.nurgissa.queueoverflow.models.Vote;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResponseVoteDtoMapper {
    private final VoteMapper voteMapper;
    private final UserMapper userMapper;
    public ResponseVoteDto voteToResponseVoteDto(Vote vote){

        return ResponseVoteDto.builder()
                .userDto(userMapper.userToUserDto(vote.getAuthor()))
                .voteDto(voteMapper.voteToVoteDto(vote))
                .build();
    }
}
