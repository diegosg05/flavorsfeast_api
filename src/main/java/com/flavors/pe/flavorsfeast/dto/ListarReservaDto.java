package com.flavors.pe.flavorsfeast.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListarReservaDto {
    private LocalDate fechaReserva;
    private LocalTime horaReserva;
    private Integer personas;
    private String sucursal;
    private String estado;
}
