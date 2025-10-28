package com.flavors.pe.flavorsfeast.services;

import com.flavors.pe.flavorsfeast.dto.*;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {
    UserResponseDto login(LoginDto login, HttpServletResponse response);
    UserResponseDto register(RegisterDto register, HttpServletResponse response);
    void logout(HttpServletResponse response);
    UserResponseDto updateUserDto(UserUpdateDto userUpdateDto, String email);
    void updatePassword(UpdatePasswordDto updatePasswordDto, String email);
}
