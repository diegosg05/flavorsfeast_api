package com.flavors.pe.flavorsfeast.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioProfileDto {
    private String nombre;
    private String username;
    private String correo;
    private String telefono;
    private LocalDate fechaCreacion;
}
