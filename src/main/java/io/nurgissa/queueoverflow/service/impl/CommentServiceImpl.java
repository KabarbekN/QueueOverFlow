package io.nurgissa.queueoverflow.service.impl;


import io.nurgissa.queueoverflow.dto.comment.CreateCommentDto;
import io.nurgissa.queueoverflow.exceptions.NotFoundException;
import io.nurgissa.queueoverflow.exceptions.ServiceException;
import io.nurgissa.queueoverflow.models.Answer;
import io.nurgissa.queueoverflow.models.Comment;
import io.nurgissa.queueoverflow.models.Question;
import io.nurgissa.queueoverflow.models.User;
import io.nurgissa.queueoverflow.repository.AnswerRepository;
import io.nurgissa.queueoverflow.repository.CommentRepository;
import io.nurgissa.queueoverflow.repository.QuestionRepository;
import io.nurgissa.queueoverflow.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Override
    @SneakyThrows
    public Comment findById(Long id) {
        if (commentRepository.findById(id).isEmpty())
            throw new ServiceException("No such comment with this id");
        return commentRepository.findById(id).get();
    }

    @Override
    @SneakyThrows
    public void createCommentForQuestion(CreateCommentDto createCommentDto, Principal connectedUser) {
        System.out.println(createCommentDto);
        System.out.println(questionRepository.findByQuestionid(createCommentDto.getAttributeId()));
        if (questionRepository.findByQuestionid(createCommentDto.getAttributeId()).isEmpty()){
            throw new ServiceException("No such question");
        }
        User user = checkForUserAuthorityAndReturnUser(connectedUser);
        Optional<Question> optionalQuestion = questionRepository.findByQuestionid(createCommentDto.getAttributeId());
        Question question = optionalQuestion.get();
        Comment comment = Comment.builder()
                .question(question)
                .content(createCommentDto.getContent())
                .author(user)
                .createdTime(System.currentTimeMillis() / 1000)
                .build();
        commentRepository.save(comment);
    }

    @Override
    @SneakyThrows
    public void createCommentForAnswer(CreateCommentDto createCommentDto, Principal connectedUser) {
        if (answerRepository.findById(createCommentDto.getAttributeId()).isEmpty()){
            throw new ServiceException("No such answer");
        }
        User user = checkForUserAuthorityAndReturnUser(connectedUser);
        Optional<Answer> optionalAnswer = answerRepository.findById(createCommentDto.getAttributeId());
        Answer answer = optionalAnswer.get();
        Comment comment = Comment.builder()
                .answer(answer)
                .content(createCommentDto.getContent())
                .author(user)
                .createdTime(System.currentTimeMillis() / 1000)
                .build();

        commentRepository.save(comment);
    }

    @Override
    @SneakyThrows
    public void deleteCommentById(Long id, Principal connectedUser) {
        if (commentRepository.findById(id).isEmpty())
            throw new ServiceException("Comment by this id not found");
        User user = checkForUserAuthorityAndReturnUser(connectedUser);
        Optional<Comment> optionalComment = commentRepository.findById(id);
        Comment comment  = optionalComment.get();
        if (!Objects.equals(comment.getAuthor().getUserid(), user.getUserid())){
            throw new ServiceException("No such authority");
        }
        commentRepository.deleteById(id);
    }


    @SneakyThrows
    private User checkForUserAuthorityAndReturnUser(Principal connectedUser){
        if (!(connectedUser instanceof UsernamePasswordAuthenticationToken)) {
            throw new NotFoundException("User not registered");
        }
        var user = (User)((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        if (user == null){
            throw new NotFoundException("User not registered");
        }
        return user;

    }
}
