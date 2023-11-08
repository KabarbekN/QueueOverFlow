package io.nurgissa.queueoverflow.service.impl;

import io.nurgissa.queueoverflow.dto.UserDto;
import io.nurgissa.queueoverflow.mapper.UserMapper;
import io.nurgissa.queueoverflow.models.User;
import io.nurgissa.queueoverflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.control.MappingControl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public Optional<UserDto> getUserByID(Long id){
        User user = userRepository.getUserByUserid(id).orElse(null);


        return Optional.of(userMapper.userToUserDto(user));
    }

    public List<UserDto> getAllUsers(){
        return userRepository.findAll().stream().map(userMapper::userToUserDto).collect(Collectors.toList());
    }
}
