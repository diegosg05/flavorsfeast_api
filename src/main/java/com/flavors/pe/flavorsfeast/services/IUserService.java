package com.flavors.pe.flavorsfeast.services;

import com.flavors.pe.flavorsfeast.dto.AuthResponse;
import com.flavors.pe.flavorsfeast.dto.UserLoginDto;
import com.flavors.pe.flavorsfeast.dto.UserProfileDto;
import com.flavors.pe.flavorsfeast.dto.UserRegisterDto;
import jakarta.servlet.http.HttpServletRequest;

public interface IUserService {
    AuthResponse login(UserLoginDto userLoginDto);
    boolean verificarUsuarioExistenteRegister(String email);
    boolean verificarUsuarioLogin(String email, String pass);
    AuthResponse register(UserRegisterDto userRegisterDto);
    UserProfileDto getUserProfileFromRequest(HttpServletRequest request);
}
