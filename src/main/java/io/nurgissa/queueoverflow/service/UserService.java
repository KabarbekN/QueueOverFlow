package io.nurgissa.queueoverflow.service;

import io.nurgissa.queueoverflow.models.User;
import io.nurgissa.queueoverflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> getUserByID(Long id){
        return userRepository.getUserByUserid(id);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

}
