package com.flavors.pe.flavorsfeast.controllers;

import com.flavors.pe.flavorsfeast.services.impl.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("productos")
    public ResponseEntity<?> listarProductos() {
        return ResponseEntity.ok(productoService.listarProductosPorCategoria());
    }
}
