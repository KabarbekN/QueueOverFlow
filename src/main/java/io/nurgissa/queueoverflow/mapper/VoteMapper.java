package io.nurgissa.queueoverflow.mapper;

import io.nurgissa.queueoverflow.dto.vote.VoteDto;
import io.nurgissa.queueoverflow.models.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VoteMapper {
    VoteMapper INSTANCE = Mappers.getMapper(VoteMapper.class);

    VoteDto voteToVoteDto(Vote vote);
}
