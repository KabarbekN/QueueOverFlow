package io.nurgissa.queueoverflow.dto.vote;


import io.nurgissa.queueoverflow.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseVoteDto {
    private VoteDto voteDto;
    private UserDto userDto;
}
