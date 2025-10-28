package com.flavors.pe.flavorsfeast.controllers;

import com.flavors.pe.flavorsfeast.dto.*;
import com.flavors.pe.flavorsfeast.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/auth")
@Tag(
        name = "Authentication Controller",
        description = "Controlador para login, register y logout"
)
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    @Operation(
            summary = "Inicio de Sesión",
            description = "El usuario se autentica, devuelve los datos del usuario autenticado",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Se requiere el email y contraseña",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LoginDto.class)
                    )
            ),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Successful authentication"
                    )
            }
    )
    public ResponseEntity<ApiResponse<UserResponseDto>> login(
            @Valid @RequestBody LoginDto login,
            HttpServletResponse response
    ) {
        var user = userService.login(login, response);

        var apiResponse = new ApiResponse<>(
                user,
                null
        );

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/register")
    @Operation(
            summary = "Registro",
            description = "Los usuarios nuevos de registran, devolviendo los datos del usuario nuevo",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Se quiere su nombre, apellido, email, contraseña y teléfono",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RegisterDto.class)
                    )
            ),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "201",
                            description = "Successful register"
                    )
            }
    )
    public ResponseEntity<ApiResponse<UserResponseDto>> register(
            @Valid @RequestBody RegisterDto register,
            HttpServletResponse response
    ) {
        var userSaved = userService.register(register, response);

        var apiResponse = new ApiResponse<>(
                userSaved,
                null
        );

        var uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/users/{uid}")
                .buildAndExpand(userSaved.uid())
                .toUri();

        return ResponseEntity.created(uri).body(apiResponse);
    }

    @PostMapping("/logout")
    @Operation(
            summary = "Logout",
            description = "El usuario cierra sesión",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Successful logout"
                    )
            }
    )
    public ResponseEntity<ApiResponse<String>> logout(HttpServletResponse response) {
        userService.logout(response);

        var apiResponse = new ApiResponse<>(
                "Logout realizado exitosamente",
                null
        );
        return ResponseEntity.ok(apiResponse);
    }

}
