package io.nurgissa.queueoverflow.service.impl;

import io.nurgissa.queueoverflow.dto.UserDto;
import io.nurgissa.queueoverflow.dto.UserProfileDto;
import io.nurgissa.queueoverflow.exceptions.NotSamePasswordsException;
import io.nurgissa.queueoverflow.exceptions.ServiceException;
import io.nurgissa.queueoverflow.mapper.UserMapper;
import io.nurgissa.queueoverflow.models.Answer;
import io.nurgissa.queueoverflow.models.Comment;
import io.nurgissa.queueoverflow.models.Question;
import io.nurgissa.queueoverflow.repository.AnswerRepository;
import io.nurgissa.queueoverflow.repository.CommentRepository;
import io.nurgissa.queueoverflow.repository.QuestionRepository;
import io.nurgissa.queueoverflow.repository.dao.SearchRequest;
import io.nurgissa.queueoverflow.repository.dao.UserSearchDao;
import io.nurgissa.queueoverflow.request.ChangePasswordRequest;
import io.nurgissa.queueoverflow.models.User;
import io.nurgissa.queueoverflow.repository.UserRepository;
import io.nurgissa.queueoverflow.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserSearchDao userSearchDao;
    private final QuestionRepository questionRepository;
    private final CommentRepository commentRepository;
    private final AnswerRepository answerRepository;



@SneakyThrows
    public UserDto getUserByID(Long id){
        if (userRepository.findById(id).isEmpty()){
            throw new ServiceException("No user with this id");
        }
        Optional<User> optionalUser = userRepository.getUserByUserid(id);
        User user = optionalUser.get();

        return userMapper.userToUserDto(user);
    }

    public List<UserDto> getAllUsers(){
        return userRepository.findAll().stream().map(userMapper::userToUserDto).collect(Collectors.toList());
    }

    @SneakyThrows
    public void changePassword(
            ChangePasswordRequest changePasswordRequest,
            Principal connectedUser) {
        var user =  (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        if (!passwordEncoder.matches(changePasswordRequest.getCurrentPassword(), user.getPassword())){
            throw new NotSamePasswordsException("Wrong Password");
        }

        if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmationPassword())){
            throw new NotSamePasswordsException("Passwords are not same");
        }

        user.setPassword(passwordEncoder.encode(changePasswordRequest.getConfirmationPassword()));
        userRepository.save(user);

    }

    @Override
    public List<UserDto> findAllByCriteria(SearchRequest searchRequest) {
        return userSearchDao.findAllByCriteria(searchRequest).stream().map(userMapper::userToUserDto).collect(Collectors.toList());
    }

    @Override
    public Optional<UserProfileDto> getUserByID(Long id, Principal connectedUser) {
        Integer numberOfQuestions = 0, numberOfAnswers = 0, numberOfComments = 0;
        var user =  (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        Optional<User> optionalUser = userRepository.findById(user.getUserid());
        User systemUser = optionalUser.get();

        Optional<List<Question>> questions = questionRepository.findByAuthor(systemUser);
        numberOfQuestions = questions.get().toArray().length;
        Optional<List<Answer>> answers = answerRepository.findByAuthor(systemUser);
        numberOfAnswers = answers.get().toArray().length;
        Optional<List<Comment>> comments = commentRepository.findByAuthor(systemUser);
        numberOfComments = comments.get().toArray().length;

        return Optional.ofNullable(UserProfileDto.builder()
                .numberOfQuestions(numberOfQuestions)
                .userDto(userMapper.userToUserDto(systemUser))
                .numberOfAnswers(numberOfAnswers)
                .numberOfComments(numberOfComments)
                .build());


    }
}

