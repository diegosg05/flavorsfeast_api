package com.flavors.pe.flavorsfeast.services.impl;

import com.flavors.pe.flavorsfeast.dto.AuthResponse;
import com.flavors.pe.flavorsfeast.dto.UserLoginDto;
import com.flavors.pe.flavorsfeast.dto.UserProfileDto;
import com.flavors.pe.flavorsfeast.dto.UserRegisterDto;
import com.flavors.pe.flavorsfeast.models.Role;
import com.flavors.pe.flavorsfeast.models.User;
import com.flavors.pe.flavorsfeast.repositories.UserRepository;
import com.flavors.pe.flavorsfeast.security.services.JwtService;
import com.flavors.pe.flavorsfeast.services.IUserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Value("${jwt.accessTokenCookieName}")
    private String cookieName;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService,
                       AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthResponse login(UserLoginDto userLoginDto) {
        if(verificarUsuarioLogin(userLoginDto.getEmail(), userLoginDto.getPass())){
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPass()));
            UserDetails userDetails = userRepository.findByEmailUser(userLoginDto.getEmail()).orElseThrow();
            String token = jwtService.getToken(userDetails);

            return AuthResponse
                    .builder()
                    .token(token)
                    .build();
        } else {
            return null;
        }
    }

    @Override
    public boolean verificarUsuarioExistenteRegister(String email) {
        Optional<User> user = userRepository.findByEmailUser(email);
        return user.isPresent();
    }

    @Override
    public boolean verificarUsuarioLogin(String email, String pass) {
        Optional<User> userOptional = userRepository.findByEmailUser(email);
        if (userOptional.isEmpty())
            return false;

        User user = userOptional.get();
        return passwordEncoder.matches(pass, user.getPassword());
    }

    @Override
    public AuthResponse register(UserRegisterDto userRegisterDto) {
        if(verificarUsuarioExistenteRegister(userRegisterDto.getEmail()))
            return null;

        User user = User
                .builder()
                .emailUser(userRegisterDto.getEmail())
                .passwordUser(passwordEncoder.encode(userRegisterDto.getPass()))
                .phoneUser(userRegisterDto.getPhone())
                .fullNameUser(userRegisterDto.getFullName())
                .dateCreationUser(LocalDate.now())
                .roleUser(Role.USER)
                .build();

        userRepository.save(user);

        return AuthResponse
                .builder()
                .token(jwtService.getToken(user))
                .build();
    }

    @Override
    public UserProfileDto getUserProfileFromRequest(HttpServletRequest request) {
        String token = null;

        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookieName.equals(cookie.getName())) {
                    token = cookie.getValue();
                }
            }
        }

        if(token == null)
            return null;

        String email = jwtService.getUsernameFromToken(token);
        User usuario = userRepository.findByEmailUser(email).orElse(null);

        if(usuario == null)
            return null;

        return UserProfileDto
                .builder()
                .fullName(usuario.getFullNameUser())
                .email(usuario.getEmailUser())
                .phone(usuario.getPhoneUser())
                .dateCreation(usuario.getDateCreationUser())
                .build();
    }
}
