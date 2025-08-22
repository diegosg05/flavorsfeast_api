package com.flavors.pe.flavorsfeast.security.controllers;

import com.flavors.pe.flavorsfeast.dto.*;
import com.flavors.pe.flavorsfeast.exception.BadRequestException;
import com.flavors.pe.flavorsfeast.exception.ResourceNotFoundException;
import com.flavors.pe.flavorsfeast.exception.UnauthorizedException;
import com.flavors.pe.flavorsfeast.services.impl.UserService;
import com.flavors.pe.flavorsfeast.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @Value("${jwt.accessTokenCookieName}")
    private String cookieName;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<GenericResponseDto<String>> login(HttpServletResponse httpServletResponse, @RequestBody UserLoginDto userLoginDto) {
        AuthResponse authResponse = userService.login(userLoginDto);

        if(authResponse == null){
            throw new UnauthorizedException("El usuario o/y la contraseña son incorrectas");
        }

        CookieUtil.create(httpServletResponse, cookieName, authResponse.getToken(), false, -1, "localhost");

        GenericResponseDto<String> response = new GenericResponseDto<>();
        response.setResponse("Login realizado exitosamente");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<GenericResponseDto<String>> register(HttpServletResponse httpServletResponse,@RequestBody UserRegisterDto userRegisterDto) {
        AuthResponse authResponse = userService.register(userRegisterDto);

        if(authResponse == null){
            throw new BadRequestException("El correo ingresado ya está registrado");
        }

        CookieUtil.create(httpServletResponse, cookieName, authResponse.getToken(), false, -1, "localhost");

        GenericResponseDto<String> response = new GenericResponseDto<>();
        response.setResponse("Registro realizado exitosamente");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/logout")
    public ResponseEntity<GenericResponseDto<String>> logout(HttpServletResponse httpServletResponse) {

        CookieUtil.clear(httpServletResponse, cookieName);

        GenericResponseDto<String> response = new GenericResponseDto<>();
        response.setResponse("Logout realizado exitosamente");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/profile")
    public ResponseEntity<GenericResponseDto<UserProfileDto>> status(HttpServletRequest request) {
        UserProfileDto userProfileDto = userService.getUserProfileFromRequest(request);
        if(userProfileDto != null){
            GenericResponseDto<UserProfileDto> response = new GenericResponseDto<>();
            response.setResponse(userProfileDto);
            return ResponseEntity.ok(response);
        } else {
            throw new ResourceNotFoundException("No se encontró el usuario");
        }
    }
}
