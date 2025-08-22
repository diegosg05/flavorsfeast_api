package com.flavors.pe.flavorsfeast.services;

import com.flavors.pe.flavorsfeast.dto.CategoryDto;

import java.util.List;

public interface IProductService {
    List<CategoryDto> getAllCategoriesWithProducts();
}
