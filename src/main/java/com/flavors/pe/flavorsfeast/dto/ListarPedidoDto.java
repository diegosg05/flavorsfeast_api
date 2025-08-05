package com.flavors.pe.flavorsfeast.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListarPedidoDto {
    private Integer idPedido;
    private LocalDate fechaPedido;
    private String Estado;
    private String tipoEntrega;
    private String direccion;
    private String sucursal;
    private Double subtotal;
    private List<DetallePedidoDto> detallePedido;
}
