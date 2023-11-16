package io.nurgissa.queueoverflow.service.impl;

import io.nurgissa.queueoverflow.dto.UserDto;
import io.nurgissa.queueoverflow.exceptions.NotSamePasswordsException;
import io.nurgissa.queueoverflow.mapper.UserMapper;
import io.nurgissa.queueoverflow.models.ChangePasswordRequest;
import io.nurgissa.queueoverflow.models.User;
import io.nurgissa.queueoverflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    public Optional<UserDto> getUserByID(Long id){
        User user = userRepository.getUserByUserid(id).orElse(null);


        return Optional.of(userMapper.userToUserDto(user));
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
}

