package com.flavors.pe.flavorsfeast.services;

import com.flavors.pe.flavorsfeast.dto.ListarReservaDto;
import com.flavors.pe.flavorsfeast.dto.RegistrarReservaDto;

import java.util.List;

public interface IReservaService {
    void registrarReserva(RegistrarReservaDto registrarReservaDto);
    List<ListarReservaDto> listarReservas();
}
