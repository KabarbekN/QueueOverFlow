package io.nurgissa.queueoverflow.service;

import io.nurgissa.queueoverflow.dto.comment.CreateCommentDto;
import io.nurgissa.queueoverflow.models.Comment;

import java.security.Principal;


public interface CommentService {
    Comment findById(Long id);
    void createCommentForQuestion(CreateCommentDto createCommentDto, Principal connectedUser);
    void createCommentForAnswer(CreateCommentDto createCommentDto, Principal connectedUser);

    void deleteCommentById(Long id, Principal connectedUser);
}
