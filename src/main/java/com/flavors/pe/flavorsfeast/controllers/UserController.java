package com.flavors.pe.flavorsfeast.controllers;

import com.flavors.pe.flavorsfeast.dto.ApiResponse;
import com.flavors.pe.flavorsfeast.dto.UpdatePasswordDto;
import com.flavors.pe.flavorsfeast.dto.UserResponseDto;
import com.flavors.pe.flavorsfeast.dto.UserUpdateDto;
import com.flavors.pe.flavorsfeast.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping
    public ResponseEntity<ApiResponse<UserResponseDto>> updateUser(
            @Valid @RequestBody UserUpdateDto userUpdateDto,
            @CurrentSecurityContext(expression = "authentication?.name") String email
            ) {
        var userUpdated = userService.updateUserDto(userUpdateDto, email);

        var apiResponse = new ApiResponse<>(
                userUpdated,
                null
        );
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/reset-password")
    public ResponseEntity<Void> updatePassword(
            @Valid @RequestBody UpdatePasswordDto updatePasswordDto,
            @CurrentSecurityContext(expression = "authentication?.name") String email
            ) {
        userService.updatePassword(updatePasswordDto, email);

        return ResponseEntity.noContent().build();
    }
}
