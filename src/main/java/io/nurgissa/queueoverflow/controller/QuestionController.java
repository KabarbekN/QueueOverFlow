package io.nurgissa.queueoverflow.controller;

import io.nurgissa.queueoverflow.dto.CreateQuestionDto;
import io.nurgissa.queueoverflow.service.impl.QuestionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionServiceImpl questionService;

    @PostMapping
    public ResponseEntity<?> createQuestion(@RequestBody CreateQuestionDto createQuestionDto){
            questionService.createQuestion(createQuestionDto);
            return ResponseEntity.accepted().body("Question was created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestionById(@PathVariable Long id){
        System.out.println(id);
        return ResponseEntity.ok(questionService.getQuestionById(id).orElse(null));
    }
}
