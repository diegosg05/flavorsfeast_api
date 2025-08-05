package com.flavors.pe.flavorsfeast.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListarProductoDto {
    private Integer idProducto;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String imagenUrl;
}
