package com.flavors.pe.flavorsfeast.mapper;

import com.flavors.pe.flavorsfeast.dto.RegisterDto;
import com.flavors.pe.flavorsfeast.dto.UserResponseDto;
import com.flavors.pe.flavorsfeast.models.User;

public class UserMapper {

    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(
                user.getUid(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getPhone(),
                user.getRole().name().toLowerCase(),
                user.getCreatedAt()
        );
    }

    public static User toEntity(RegisterDto register) {
        return User.builder()
                .firstname(register.firstname())
                .lastname(register.lastname())
                .email(register.email())
                .phone(register.phone())
                .build();
    }
}
