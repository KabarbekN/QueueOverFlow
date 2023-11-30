package io.nurgissa.queueoverflow.controller;

import io.nurgissa.queueoverflow.repository.AnswerRepository;
import io.nurgissa.queueoverflow.repository.CommentRepository;
import io.nurgissa.queueoverflow.repository.QuestionRepository;
import io.nurgissa.queueoverflow.service.impl.QuestionServiceImpl;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminController {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final CommentRepository commentRepository;
    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')") // it works anyway without this because in security configuration all of this was added
    @Hidden
    public String get() {
        return "GET:: admin controller";
    }
    @PostMapping
    @Hidden
    public String post() {
        return "POST:: admin controller";
    }
    @PutMapping
    @Hidden
    public String put() {
        return "PUT:: admin controller";
    }
    @DeleteMapping
    @Hidden
    public String delete() {
        return "DELETE:: admin controller";
    }


    @DeleteMapping("/question/{id}")
    @Hidden
    public ResponseEntity<?> deleteQuestionById(@PathVariable Long id) {
        questionRepository.deleteById(id);
        return ResponseEntity.accepted().body("Question was successfully deleted");
    }
    @DeleteMapping("/answer/{id}")
    @Hidden
    public ResponseEntity<?> deleteAnswerById(@PathVariable Long id) {
        answerRepository.deleteById(id);
        return ResponseEntity.accepted().body("Answer was successfully deleted");

    }
    @DeleteMapping("/comment/{id}")
    @Hidden
    public ResponseEntity<?> deleteCommentById(@PathVariable Long id) {
        commentRepository.deleteById(id);
        return ResponseEntity.accepted().body("Comment was successfully deleted");
    }

}




