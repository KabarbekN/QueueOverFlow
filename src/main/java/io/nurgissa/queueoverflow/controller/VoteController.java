package io.nurgissa.queueoverflow.controller;


import io.nurgissa.queueoverflow.dto.vote.CreateVoteDto;
import io.nurgissa.queueoverflow.service.impl.VoteServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/vote")
@RequiredArgsConstructor
public class VoteController {
    private final VoteServiceImpl voteService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getVoteById(@PathVariable Long id){
        return ResponseEntity.ok(voteService.getVoteById(id));
    }

    @PostMapping("/question")
    public ResponseEntity<?> createVoteForQuestion(
           @RequestBody CreateVoteDto createVoteDto, Principal connectedUser){
        voteService.createVoteForQuestion(createVoteDto, connectedUser);
        return ResponseEntity.accepted().body("Vote was successfully added for this question");
    }


    @PostMapping("/answer")
    public ResponseEntity<?> createVoteForAnswer(
            @RequestBody CreateVoteDto createVoteDto, Principal connectedUser){
        voteService.createVoteForAnswer(createVoteDto, connectedUser);
        return ResponseEntity.accepted().body("Vote was successfully added for this answer");
    }




}
