package io.nurgissa.queueoverflow.repository;

import io.nurgissa.queueoverflow.configutation.ApplicationConfigurationTest;
import io.nurgissa.queueoverflow.models.User;
import io.nurgissa.queueoverflow.models.enums.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@DataJpaTest
@AutoConfigureDataJpa
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void getUserByUserid() {
        // given
        User user = createTestUser();
        userRepository.save(user);

        // when
        Optional<User> foundUserOptional = userRepository.getUserByUserid(user.getUserid());

        // then


        assertTrue(foundUserOptional.isPresent());
        User foundUser = foundUserOptional.get();
        assertEquals(user.getUsername(), foundUser.getUsername());
        assertEquals(user.getPassword(), foundUser.getPassword());
        assertEquals(user.getEmail(), foundUser.getEmail());
        assertEquals(user.getRole(), foundUser.getRole());
    }


    @Test
    void findByUsername() {
        // given
        User user = createTestUser();
        userRepository.save(user);

        // when
        Optional<User> foundUserOptional = userRepository.findByUsername(user.getUsername());

        // then


        assertTrue(foundUserOptional.isPresent());
        User foundUser = foundUserOptional.get();
        assertEquals(user.getUsername(), foundUser.getUsername());
        assertEquals(user.getPassword(), foundUser.getPassword());
        assertEquals(user.getEmail(), foundUser.getEmail());
        assertEquals(user.getRole(), foundUser.getRole());
    }

    @Test
    void findByEmail() {
        // given

        User user = createTestUser();
        userRepository.save(user);

        // when
        Optional<User> foundUserOptional = userRepository.findByEmail(user.getEmail());

        // then


        assertTrue(foundUserOptional.isPresent());
        User foundUser = foundUserOptional.get();
        assertEquals(user.getUsername(), foundUser.getUsername());
        assertEquals(user.getPassword(), foundUser.getPassword());
        assertEquals(user.getEmail(), foundUser.getEmail());
        assertEquals(user.getRole(), foundUser.getRole());
    }
    private User createTestUser(){
        String username = "testUser";
        String email = "test@example.com";
        String password = "testPassword";
        String role = "ADMIN";
        User user = User.builder()
                .username(username)
                .password(password)
                .email(email)
                .role(Role.valueOf(role))
                .build();

        return user;
    }
}