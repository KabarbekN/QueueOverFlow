package io.nurgissa.queueoverflow.controller;

import io.nurgissa.queueoverflow.repository.AnswerRepository;
import io.nurgissa.queueoverflow.repository.CommentRepository;
import io.nurgissa.queueoverflow.repository.QuestionRepository;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/moderator")
@PreAuthorize("hasAnyRole('ADMIN','MODEERATOR')")
@RequiredArgsConstructor
public class ModeratorController {

    private final QuestionRepository questionRepository;
    private final CommentRepository commentRepository;
    private final AnswerRepository answerRepository;

    @GetMapping
    public String get() {
        return "GET:: moderator controller";
    }
    @PostMapping
    @Hidden
    public String post() {
        return "POST:: moderator controller";
    }
    @PutMapping
    @Hidden
    public String put() {
        return "PUT:: moderator controller";
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
