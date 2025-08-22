package com.flavors.pe.flavorsfeast.controllers;

import com.flavors.pe.flavorsfeast.dto.CategoryDto;
import com.flavors.pe.flavorsfeast.dto.GenericResponseDto;
import com.flavors.pe.flavorsfeast.services.impl.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<GenericResponseDto<List<CategoryDto>>> getAllProducts() {
        List<CategoryDto> categoriesDto = productService.getAllCategoriesWithProducts();

        if(categoriesDto.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        GenericResponseDto<List<CategoryDto>> response = new GenericResponseDto<>();
        response.setResponse(categoriesDto);
        return ResponseEntity.ok(response);
    }
}
