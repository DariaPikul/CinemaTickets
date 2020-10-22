package com.dev.cinema.service.mapper;

import com.dev.cinema.model.dto.UserDto;
import com.dev.cinema.model.entity.User;

public class UserMapper {
    public static UserDto mapToResponseDto(User user) {
        return new UserDto(user.getId(), user.getEmail(), user.getPassword());
    }

    public static User mapFromRequestDto(UserDto userDto) {
        return new User(userDto.getEmail(), userDto.getPassword());
    }
}
