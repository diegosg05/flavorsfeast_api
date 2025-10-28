package com.flavors.pe.flavorsfeast.controllers;

import com.flavors.pe.flavorsfeast.dto.ApiResponse;
import com.flavors.pe.flavorsfeast.dto.ProductDto;
import com.flavors.pe.flavorsfeast.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(
        name = "Product Controller",
        description = "Controlador para obtener todos los productos"
)
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @Operation(
            summary = "Obtener todos los productos",
            description = "End point no protegido, cualquiera puede solicitarlos",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Successful Get Products"
                    )
            }
    )
    public ResponseEntity<ApiResponse<List<ProductDto>>> getAllProducts() {
        var products = productService.getAllProducts();

        var apiResponse = new ApiResponse<>(
                products,
                null
        );

        return ResponseEntity.ok(apiResponse);
    }
}
