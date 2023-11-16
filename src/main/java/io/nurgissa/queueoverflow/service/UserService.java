package io.nurgissa.queueoverflow.service;

import io.nurgissa.queueoverflow.models.ChangePasswordRequest;
import io.nurgissa.queueoverflow.models.User;
import io.nurgissa.queueoverflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


public interface UserService {

    Optional<User> getUserByID(Long id);

    List<User> getAllUsers();

    void changePassword(ChangePasswordRequest request, Principal connectedUser);

}
