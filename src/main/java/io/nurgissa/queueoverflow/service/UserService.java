package io.nurgissa.queueoverflow.service;

import io.nurgissa.queueoverflow.request.ChangePasswordRequest;
import io.nurgissa.queueoverflow.models.User;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


public interface UserService {

    Optional<User> getUserByID(Long id);

    List<User> getAllUsers();

    void changePassword(ChangePasswordRequest request, Principal connectedUser);

}
