package io.nurgissa.queueoverflow.controller;

import io.nurgissa.queueoverflow.dto.question.CreateQuestionDto;
import io.nurgissa.queueoverflow.request.ChangeQuestionRequest;
import io.nurgissa.queueoverflow.service.impl.QuestionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionServiceImpl questionService;

    @PostMapping
    public ResponseEntity<?> createQuestion(@RequestBody CreateQuestionDto createQuestionDto, Principal user){
            questionService.createQuestion(createQuestionDto, user);
            return ResponseEntity.accepted().body("Question was created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestionById(@PathVariable Long id){
        return ResponseEntity.ok(questionService.getQuestionById(id).orElse(null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestionById(@PathVariable Long id, Principal connectedUser){
        questionService.deleteQuestionById(id, connectedUser);
        return ResponseEntity.accepted().body("Question was successfully deleted");
    }

    @PatchMapping
    public ResponseEntity<?> changeQuestion(
            @RequestBody ChangeQuestionRequest request,
            Principal connectedUser){
        questionService.updateQuestion(request, connectedUser);
        return ResponseEntity.accepted().body("Question successfully changed");
    }
}
