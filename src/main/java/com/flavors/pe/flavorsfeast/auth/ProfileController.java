package com.flavors.pe.flavorsfeast.auth;

import com.flavors.pe.flavorsfeast.dto.UsuarioProfileDto;
import com.flavors.pe.flavorsfeast.models.Usuario;
import com.flavors.pe.flavorsfeast.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/v2/")
public class ProfileController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("profile")
    public ResponseEntity<?> profile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String correo = authentication.getName();

        Usuario usuario = usuarioRepository.findByCorreo(correo).orElseThrow();

        UsuarioProfileDto usuarioProfileDto = UsuarioProfileDto
                .builder()
                .correo(usuario.getCorreo())
                .nombre(usuario.getNombre())
                .telefono(usuario.getTelefono())
                .username(usuario.getRealUsername())
                .fechaCreacion(usuario.getFechaCreacion())
                .build();

        return ResponseEntity.ok(usuarioProfileDto);
    }

}
