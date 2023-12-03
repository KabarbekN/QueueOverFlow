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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
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

        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        User savedUser = userRepository.save(user);

        assertNotNull(savedUser);
        UserDto userDto = Mockito.mock(UserDto.class);

        when(userRepository.findById(savedUser.getUserid())).thenReturn(Optional.of(user));
        when(userRepository.getUserByUserid(savedUser.getUserid())).thenReturn(Optional.of(user));
        when(userMapper.userToUserDto(Mockito.any(User.class))).thenReturn(userDto);


        // when
//        assertNotNull(userService.getUserByID(savedUser.getUserid()));
//        System.out.println(userService.getUserByID(savedUser.getUserid()));
        userDto = userService.getUserByID(savedUser.getUserid());
        // then

        assertNotNull(userDto);
        verify(userRepository).getUserByUserid(user.getUserid());
        verify(userRepository).findById(savedUser.getUserid());
        verify(userMapper).userToUserDto(user);
    }




    @Test
    void getUserByID_UserNotFound() {
        // Given

//        when(userRepository.getUserByUserid(1L)).thenReturn(Optional.empty());

        // When, Then
        assertThrows(ServiceException.class,  () -> userService.getUserByID(1L), "No user with this id");
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
//        Long userId = 1L;
        String username = "testUser";
        String email = "test@example.com";
        String password = "testPassword";
        String role = "ADMIN";
        User user = User.builder()
//                .userid(userId)
                .username(username)
                .password(password)
                .email(email)
                .role(Role.valueOf(role))
                .build();

        return user;
    }
}