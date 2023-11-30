package io.nurgissa.queueoverflow.controller;


import io.nurgissa.queueoverflow.dto.comment.CreateCommentDto;
import io.nurgissa.queueoverflow.service.impl.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentServiceImpl commentService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getCommentById(@PathVariable Long id){
        return ResponseEntity.ok(commentService.findById(id));
    }

    @PostMapping("/question")
    public ResponseEntity<?> createCommentForQuestion(@RequestBody CreateCommentDto createCommentDto, Principal connectedUser){
        commentService.createCommentForQuestion(createCommentDto, connectedUser);
        return ResponseEntity.accepted().body("Comment for question was successfully created");
    }

    @PostMapping("/answer")
    public ResponseEntity<?> createCommentForAnswer(@RequestBody CreateCommentDto createCommentDto, Principal connectedUser){
        commentService.createCommentForAnswer(createCommentDto, connectedUser);
        return ResponseEntity.accepted().body("Comment for answer was successfully created");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCommentById(@PathVariable Long id, Principal connectedUser){
        commentService.deleteCommentById(id, connectedUser);
        return ResponseEntity.accepted().body("Comment was successfully deleted");
    }
}
