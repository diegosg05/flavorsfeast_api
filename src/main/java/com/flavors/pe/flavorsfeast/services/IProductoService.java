package com.flavors.pe.flavorsfeast.services;

import com.flavors.pe.flavorsfeast.dto.ListarCategoriaDto;

import java.util.List;

public interface IProductoService {
    List<ListarCategoriaDto> listarProductosPorCategoria();
}
