package com.flavors.pe.flavorsfeast.services.impl;

import com.flavors.pe.flavorsfeast.dto.AuthResponse;
import com.flavors.pe.flavorsfeast.dto.UsuarioLoginDto;
import com.flavors.pe.flavorsfeast.dto.UsuarioRegisterDto;
import com.flavors.pe.flavorsfeast.models.Rol;
import com.flavors.pe.flavorsfeast.models.Usuario;
import com.flavors.pe.flavorsfeast.repositories.UsuarioRepository;
import com.flavors.pe.flavorsfeast.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Llamamos a JwtService para poder crear el token y devolver al endpoint en el body
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AuthResponse login(UsuarioLoginDto usuarioLoginDto) {
        if(verificarUsuarioLogin(usuarioLoginDto.getCorreo(), usuarioLoginDto.getPassword())){
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(usuarioLoginDto.getCorreo(), usuarioLoginDto.getPassword()));
            UserDetails userDetails = usuarioRepository.findByCorreo(usuarioLoginDto.getCorreo()).orElseThrow();
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
        Optional<Usuario> usuario = usuarioRepository.findByCorreo(email);
        return usuario.isPresent();
    }

    @Override
    public boolean verificarUsuarioLogin(String email, String pass) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(email);
        if (usuarioOpt.isEmpty()) return false;

        Usuario usuario = usuarioOpt.get();
        return passwordEncoder.matches(pass, usuario.getPassword());
    }

    @Override
    public AuthResponse register(UsuarioRegisterDto usuarioRegisterDto) {
        if(verificarUsuarioExistenteRegister(usuarioRegisterDto.getCorreo()))
            return null;

        Usuario usuario = Usuario
                .builder()
                .realUsername(usuarioRegisterDto.getUsername())
                .password(passwordEncoder.encode(usuarioRegisterDto.getPassword()))
                .correo(usuarioRegisterDto.getCorreo())
                .telefono(usuarioRegisterDto.getTelefono())
                .nombre(usuarioRegisterDto.getNombre())
                .fechaCreacion(LocalDate.now())
                .rol(Rol.Cliente)
                .build();

        usuarioRepository.save(usuario);

        return AuthResponse
                .builder()
                .token(jwtService.getToken(usuario))
                .build();
    }
}
