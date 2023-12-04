package io.nurgissa.queueoverflow.service.impl;

import io.nurgissa.queueoverflow.dto.vote.CreateVoteDto;
import io.nurgissa.queueoverflow.dto.vote.VoteDto;
import io.nurgissa.queueoverflow.exceptions.NotFoundException;
import io.nurgissa.queueoverflow.exceptions.ServiceException;
import io.nurgissa.queueoverflow.mapper.VoteMapper;
import io.nurgissa.queueoverflow.models.Answer;
import io.nurgissa.queueoverflow.models.Question;
import io.nurgissa.queueoverflow.models.User;
import io.nurgissa.queueoverflow.models.Vote;
import io.nurgissa.queueoverflow.repository.AnswerRepository;
import io.nurgissa.queueoverflow.repository.QuestionRepository;
import io.nurgissa.queueoverflow.repository.VoteRepository;
import io.nurgissa.queueoverflow.service.VoteService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Objects;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {
    private final VoteRepository voteRepository;
    private final VoteMapper voteMapper;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;


    @SneakyThrows
    @Override
    public VoteDto getVoteById(Long id) {
        if (voteRepository.findById(id).isEmpty())
            throw new ServiceException("Vote with this id does not exist");
        Optional<Vote> optionalVote = voteRepository.findById(id);
        Vote vote = optionalVote.get();



        return voteMapper.voteToVoteDto(vote);
    }

    @Override
    @SneakyThrows
    public void createVoteForQuestion(CreateVoteDto createVoteDto, Principal connectedUser) {

        User user = checkForUserAuthorityAndReturnUser(connectedUser);
        if (voteRepository.findByQuestionQuestionid(createVoteDto.getAttributeId()).isPresent()){
            Optional<Vote> optionalVote = voteRepository.findByQuestionQuestionid(createVoteDto.getAttributeId());
            Vote vote = optionalVote.get();
            if (Objects.equals(vote.getAuthor().getUserid(), user.getUserid())){
                log.info("More than one attempt to make vote for specific question");
                throw new ServiceException("You can not vote more than one time, for same attribute");
            }
        }
        if (createVoteDto.getValue() != 1 && createVoteDto.getValue() != -1){
            log.info(createVoteDto.getValue() + " is not 1 or -1");
            throw new ServiceException("Value can be only 1 or -1");
        }


        Optional<Question> optionalQuestion = questionRepository.findByQuestionid(createVoteDto.getAttributeId());
        if (optionalQuestion.isEmpty()){
            throw new ServiceException("Question with this id does not exist");
        }
        Question question = optionalQuestion.get();

        Vote vote = Vote.builder()
                .value(createVoteDto.getValue())
                .author(user)
                .question(question)
                .createdTime(System.currentTimeMillis() / 1000)
                .build();
        voteRepository.save(vote);
        log.info("new vote for question was saved " + vote.getAuthor() + " " + vote.getVoteId());
    }

    @Override
    @SneakyThrows
    public void createVoteForAnswer(CreateVoteDto createVoteDto, Principal connectedUser) {
        User user = checkForUserAuthorityAndReturnUser(connectedUser);

        if (voteRepository.findByAnswerAnswerId(createVoteDto.getAttributeId()).isPresent()){
            Optional<Vote> optionalVote = voteRepository.findByAnswerAnswerId(createVoteDto.getAttributeId());
            Vote vote = optionalVote.get();
            if (Objects.equals(vote.getAuthor().getUserid(), user.getUserid())){
                throw new ServiceException("You can not vote more than one time, for same answer");
            }
        }

        if (createVoteDto.getValue() != 1 && createVoteDto.getValue() != -1){
            throw new ServiceException("Value can be only 1 or -1");
        }

        Optional<Answer> optionalAnswer = answerRepository.findById(createVoteDto.getAttributeId());
        if (optionalAnswer.isEmpty()){
            throw new ServiceException("Answer with this id does not exist");
        }
        Answer answer = optionalAnswer.get();

        Vote vote = Vote.builder()
                .value(createVoteDto.getValue())
                .author(user)
                .answer(answer)
                .createdTime(System.currentTimeMillis() / 1000)
                .build();
        voteRepository.save(vote);
        log.info("New vote for answer was saved " + vote.getAuthor() + vote.getVoteId());

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
