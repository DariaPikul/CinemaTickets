package com.dev.cinema.service.mapper;

import com.dev.cinema.model.dto.UserDto;
import com.dev.cinema.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto mapToResponseDto(User user) {
        return new UserDto(user.getId(), user.getEmail(), user.getPassword());
    }

    public User mapFromRequestDto(UserDto userDto) {
        return new User(userDto.getEmail(), userDto.getPassword());
    }
}
