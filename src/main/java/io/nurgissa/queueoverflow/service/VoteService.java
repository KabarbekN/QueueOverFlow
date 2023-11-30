package io.nurgissa.queueoverflow.service;


import io.nurgissa.queueoverflow.dto.vote.CreateVoteDto;
import io.nurgissa.queueoverflow.dto.vote.VoteDto;
import io.nurgissa.queueoverflow.models.Vote;

import java.security.Principal;
import java.util.Optional;

public interface VoteService {
    VoteDto getVoteById(Long id);

    void createVoteForQuestion(CreateVoteDto createVoteDto, Principal connectedUser);

    void createVoteForAnswer(CreateVoteDto createVoteDto, Principal connectedUser);
}
