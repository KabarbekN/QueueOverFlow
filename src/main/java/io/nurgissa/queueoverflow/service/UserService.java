package io.nurgissa.queueoverflow.service;

import io.nurgissa.queueoverflow.models.User;
import io.nurgissa.queueoverflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService {

    public Optional<User> getUserByID(Long id);

    public List<User> getAllUsers();

}
