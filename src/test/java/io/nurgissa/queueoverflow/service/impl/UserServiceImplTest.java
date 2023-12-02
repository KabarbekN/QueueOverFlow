package io.nurgissa.queueoverflow.service.impl;

import io.nurgissa.queueoverflow.dto.UserDto;
import io.nurgissa.queueoverflow.exceptions.ServiceException;
import io.nurgissa.queueoverflow.mapper.UserMapper;
import io.nurgissa.queueoverflow.models.User;
import io.nurgissa.queueoverflow.models.enums.Role;
import io.nurgissa.queueoverflow.repository.AnswerRepository;
import io.nurgissa.queueoverflow.repository.CommentRepository;
import io.nurgissa.queueoverflow.repository.QuestionRepository;
import io.nurgissa.queueoverflow.repository.UserRepository;
import io.nurgissa.queueoverflow.repository.dao.UserSearchDao;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@DataJpaTest
@AutoConfigureDataJpa
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserSearchDao userSearchDao;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private AnswerRepository answerRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository, userMapper, passwordEncoder, userSearchDao, questionRepository, commentRepository, answerRepository);
    }

    @Test
    void getUserByID() {
        //  given

        User user = createTestUser();
        userRepository.save(user);
        userRepository.findAll();
        for (User u: userRepository.findAll()){
            System.out.println(u);
        }
        System.out.println(user);
        userRepository.getUserByUserid(user.getUserid());
        System.out.println(user.getUserid());
        System.out.println(userRepository.getUserByUserid(user.getUserid()));

        when(userRepository.getUserByUserid(user.getUserid())).thenReturn(Optional.of(user));
        when(userMapper.userToUserDto(user)).thenReturn(new UserDto());


        // when
        userService.getUserByID(user.getUserid());
        UserDto userDto = userService.getUserByID(user.getUserid());
        // then

        assertNotNull(userDto);
        verify(userRepository).getUserByUserid(user.getUserid());
        verify(userMapper).userToUserDto(user);
    }


    @Test
    void getUserByID_UserNotFound() {
        // Given
        Long userId = 1L;
        when(userRepository.getUserByUserid(userId)).thenReturn(Optional.empty());

        // When, Then
        assertThrows(ServiceException.class, () -> userService.getUserByID(userId));
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void changePassword() {
    }

    @Test
    void findAllByCriteria() {
    }

    @Test
    void testGetUserByID() {
    }


    private User createTestUser(){
        Long userId = 1L;
        String username = "testUser";
        String email = "test@example.com";
        String password = "testPassword";
        String role = "ADMIN";
        User user = User.builder()
                .userid(userId)
                .username(username)
                .password(password)
                .email(email)
                .role(Role.valueOf(role))
                .build();

        return user;
    }
}