package com.flavors.pe.flavorsfeast.mapper;

import com.flavors.pe.flavorsfeast.dto.CategoryDto;
import com.flavors.pe.flavorsfeast.models.Category;

public class CategoryMapper {

    public static CategoryDto toDto(Category category) {
        return new CategoryDto(
                category.getUid(),
                category.getName()
        );
    }
}
