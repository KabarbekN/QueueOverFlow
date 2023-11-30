package io.nurgissa.queueoverflow.service;

import io.nurgissa.queueoverflow.dto.UserDto;
import io.nurgissa.queueoverflow.dto.UserProfileDto;
import io.nurgissa.queueoverflow.repository.dao.SearchRequest;
import io.nurgissa.queueoverflow.request.ChangePasswordRequest;
import io.nurgissa.queueoverflow.models.User;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


public interface UserService {

    UserDto getUserByID(Long id);

    List<UserDto> getAllUsers();

    void changePassword(ChangePasswordRequest request, Principal connectedUser);

    List<UserDto> findAllByCriteria(SearchRequest searchRequest);

    Optional<UserProfileDto> getUserByID(Long id, Principal connectedUser);
}
