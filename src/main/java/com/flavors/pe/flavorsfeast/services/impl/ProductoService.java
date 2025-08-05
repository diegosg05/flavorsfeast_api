package com.flavors.pe.flavorsfeast.services.impl;

import com.flavors.pe.flavorsfeast.dto.ListarCategoriaDto;
import com.flavors.pe.flavorsfeast.dto.ListarProductoDto;
import com.flavors.pe.flavorsfeast.models.Categoria;
import com.flavors.pe.flavorsfeast.repositories.CategoriaRepository;
import com.flavors.pe.flavorsfeast.services.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<ListarCategoriaDto> listarProductosPorCategoria() {
        // Recoger a todas las categorias juntos con sus productos
        List<Categoria> categorias = categoriaRepository.findAllWithProductos();

        // Mapear las categorias en ListarCategoriaDto y
        // en cada catgoria mapear los productos a ListarProductoDto
        return categorias.stream().map(c -> {
            List<ListarProductoDto> productos = c.getProductos().stream()
                    .map(p -> ListarProductoDto
                            .builder()
                            .idProducto(p.getIdProducto())
                            .nombre(p.getNombre())
                            .descripcion(p.getDescripcion())
                            .precio(p.getPrecio())
                            .imagenUrl(p.getImagenUrl())
                            .build())
                    .toList();

            return new ListarCategoriaDto(c.getNombre(), productos);
        }).toList();
    }
}
