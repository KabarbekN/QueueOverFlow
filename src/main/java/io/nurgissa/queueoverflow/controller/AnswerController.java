package io.nurgissa.queueoverflow.controller;


import io.nurgissa.queueoverflow.dto.answer.CreateAnswerDto;
import io.nurgissa.queueoverflow.dto.answer.ResponseAnswerDto;
import io.nurgissa.queueoverflow.models.Answer;
import io.nurgissa.queueoverflow.service.impl.AnswerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/answer")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerServiceImpl answerService;

    @GetMapping("/question/{id}")
    public List<ResponseAnswerDto> getAllAnswersForQuestion(@PathVariable Long id){
        return answerService.getAllAnswersForQuestion(id);
    }

    @PostMapping
    public ResponseEntity<?> createAnswerForQuestion(@RequestBody CreateAnswerDto answerDto, Principal connectedUser){
        answerService.createAnswer(answerDto, connectedUser);
        return ResponseEntity.accepted().body("Answer for question was successfully created");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnswerById(@PathVariable Long id, Principal connectedUser){

        answerService.deleteAnswerById(id, connectedUser);
        return ResponseEntity.accepted().body("Answer was successfully deleted");
    }
}
