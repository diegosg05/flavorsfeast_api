package com.flavors.pe.flavorsfeast.services.impl;

import com.flavors.pe.flavorsfeast.dto.ProductDto;
import com.flavors.pe.flavorsfeast.exception.ResourceNotFoundException;
import com.flavors.pe.flavorsfeast.mapper.ProductMapper;
import com.flavors.pe.flavorsfeast.repositories.ProductRepository;
import com.flavors.pe.flavorsfeast.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        var products = productRepository.findAll();

        return products
                .stream()
                .map(ProductMapper::toDto)
                .toList();
    }
}
