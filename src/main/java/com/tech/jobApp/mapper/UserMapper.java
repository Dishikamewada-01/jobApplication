package com.tech.jobApp.mapper;

import com.tech.jobApp.dto.response.UserDto;
import com.tech.jobApp.model.Users;

public class UserMapper {
    public static UserDto toDto(Users user) {
        return new UserDto(user.getUsername(), user.getEmail());
    }
}
