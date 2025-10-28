package com.flavors.pe.flavorsfeast.services.impl;

import com.flavors.pe.flavorsfeast.dto.*;
import com.flavors.pe.flavorsfeast.mapper.UserMapper;
import com.flavors.pe.flavorsfeast.repositories.UserRepository;
import com.flavors.pe.flavorsfeast.services.UserService;
import com.flavors.pe.flavorsfeast.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtUtil jwtUtil,
                           AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @Value("${jwt.access.token.cookie-name}")
    private String cookieName;

    @Override
    public UserResponseDto login(LoginDto login, HttpServletResponse response) {
        try {
            var user = userRepository.findByEmail(login.email())
                    .orElseThrow(() -> new UsernameNotFoundException("No se encontró el usuario con email: " + login.email()));

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login.email(), login.password()));

            String token = jwtUtil.getToken(user);

            var cookie = ResponseCookie.from(cookieName, token)
                    .httpOnly(true)
                    .sameSite("Strict")
                    .maxAge(24 * 60 * 60)
                    .secure(false)
                    .path("/")
                    .build();

            response.setHeader("Set-Cookie", cookie.toString());
            return UserMapper.toDto(user);

        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("La contraseña es incorrecta");
        }
    }

    @Override
    public UserResponseDto register(RegisterDto register, HttpServletResponse response) {
        var userOptional = userRepository.findByEmail(register.email());

        if (userOptional.isPresent())
            throw new BadCredentialsException("El email ingresado ya se encuentra en uso");

        var newUser = UserMapper.toEntity(register);
        newUser.setPassword(passwordEncoder.encode(register.password()));

        var userSaved = userRepository.save(newUser);
        String token = jwtUtil.getToken(newUser);

        var cookie = ResponseCookie.from(cookieName, token)
                .httpOnly(true)
                .sameSite("Strict")
                .maxAge(24 * 60 * 60)
                .secure(false)
                .path("/")
                .build();

        response.setHeader("Set-Cookie", cookie.toString());
        return UserMapper.toDto(userSaved);
    }

    @Override
    public void logout(HttpServletResponse response) {
        var cookie = ResponseCookie.from(cookieName, null)
                .httpOnly(true)
                .sameSite("Strict")
                .maxAge(0)
                .secure(false)
                .path("/")
                .build();

        response.setHeader("Set-Cookie", cookie.toString());
    }

    @Override
    public UserResponseDto updateUserDto(UserUpdateDto userUpdateDto, String email) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No se encontró el usuario con email: " + email));

        user.setFirstname(userUpdateDto.firstname());
        user.setLastname(userUpdateDto.lastname());
        user.setPhone(userUpdateDto.phone());

        var userUpdated = userRepository.save(user);

        return UserMapper.toDto(userUpdated);
    }

    @Override
    public void updatePassword(UpdatePasswordDto updatePasswordDto, String email) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No se encontró el usuario con email: " + email));

        if (passwordEncoder.matches(updatePasswordDto.actualPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(updatePasswordDto.newPassword()));
            userRepository.save(user);
        } else {
            throw new BadCredentialsException("La contraseña ingresada es incorrecta");
        }
    }
}
