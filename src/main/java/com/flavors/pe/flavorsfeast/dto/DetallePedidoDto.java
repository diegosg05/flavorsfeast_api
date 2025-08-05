package com.flavors.pe.flavorsfeast.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetallePedidoDto {
    private String nombre;
    private String descripcion;
    private String imagenUrl;
    private Integer cantidad;
    private Double precioUnitario;
}
