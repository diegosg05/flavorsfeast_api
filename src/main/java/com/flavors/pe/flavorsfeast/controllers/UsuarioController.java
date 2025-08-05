package com.flavors.pe.flavorsfeast.controllers;

import com.flavors.pe.flavorsfeast.dto.AuthResponse;
import com.flavors.pe.flavorsfeast.dto.UsuarioLoginDto;
import com.flavors.pe.flavorsfeast.dto.UsuarioRegisterDto;
import com.flavors.pe.flavorsfeast.repositories.UsuarioRepository;
import com.flavors.pe.flavorsfeast.services.impl.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v2/")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UsuarioLoginDto usuarioLoginDto) {
        AuthResponse authResponse = usuarioService.login(usuarioLoginDto);
        if(authResponse == null){
            Map<String, String> response = Map.of("mensaje", "El email y/o la contraseña con incorrectas");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody UsuarioRegisterDto usuarioRegisterDto) {
        AuthResponse authResponse = usuarioService.register(usuarioRegisterDto);
        if(authResponse == null){
            Map<String, String> response = Map.of("mensaje", "Ya existe una cuenta con el corrreo registrado");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
        return ResponseEntity.ok(authResponse);
    }
}
