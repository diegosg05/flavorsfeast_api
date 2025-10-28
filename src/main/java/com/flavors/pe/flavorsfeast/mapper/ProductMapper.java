package com.flavors.pe.flavorsfeast.mapper;

import com.flavors.pe.flavorsfeast.dto.ProductDto;
import com.flavors.pe.flavorsfeast.models.Product;

public class ProductMapper {

    public static ProductDto toDto(Product product) {
        return new ProductDto(
                product.getUid(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getImage(),
                CategoryMapper.toDto(product.getCategory())
        );
    }
}
