package com.flavors.pe.flavorsfeast.services;

import com.flavors.pe.flavorsfeast.dto.ListarPedidoDto;
import com.flavors.pe.flavorsfeast.dto.RegistarPedidoDto;

import java.util.List;

public interface IPedidoService {
    void registrarPedido (RegistarPedidoDto registarPedidoDto);
    List<ListarPedidoDto> listarPedidos ();
}
