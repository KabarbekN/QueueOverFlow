package io.nurgissa.queueoverflow.service.impl;

import io.nurgissa.queueoverflow.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class VoteServiceImpl {
    private final VoteRepository voteRepository;
}
