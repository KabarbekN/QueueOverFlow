package io.nurgissa.queueoverflow.mapper;


import io.nurgissa.queueoverflow.dto.UserDto;
import io.nurgissa.queueoverflow.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);
}
