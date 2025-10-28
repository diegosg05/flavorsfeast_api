package com.flavors.pe.flavorsfeast.dto;

public record ProductDto(
        String uid,
        String name,
        String description,
        Double price,
        String image,
        CategoryDto category
) {
}
