package com.flavors.pe.flavorsfeast.services;

import com.flavors.pe.flavorsfeast.dto.AuthResponse;
import com.flavors.pe.flavorsfeast.dto.UsuarioLoginDto;
import com.flavors.pe.flavorsfeast.dto.UsuarioRegisterDto;

public interface IUsuarioService {
    AuthResponse login(UsuarioLoginDto usuarioLoginDto);
    boolean verificarUsuarioExistenteRegister(String email);
    boolean verificarUsuarioLogin(String email, String pass);
    AuthResponse register(UsuarioRegisterDto usuarioRegisterDto);
}
