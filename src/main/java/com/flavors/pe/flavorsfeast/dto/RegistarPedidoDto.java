package com.flavors.pe.flavorsfeast.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistarPedidoDto {
    private String tipoEntrega;
    private String direccion;
    private String sucursal;
    private Double subtotal;
    private List<RegistrarDetallePedidoDto> detallePedido;
}
