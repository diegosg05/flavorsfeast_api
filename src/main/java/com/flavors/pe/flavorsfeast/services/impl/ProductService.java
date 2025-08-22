package com.flavors.pe.flavorsfeast.services.impl;

import com.flavors.pe.flavorsfeast.dto.CategoryDto;
import com.flavors.pe.flavorsfeast.dto.ProductDto;
import com.flavors.pe.flavorsfeast.models.Category;
import com.flavors.pe.flavorsfeast.repositories.CategoryRepository;
import com.flavors.pe.flavorsfeast.services.IProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    private final CategoryRepository categoryRepository;

    public ProductService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> getAllCategoriesWithProducts() {
        // Recoger a todas las categorias juntos con sus productos
        List<Category> categories = categoryRepository.findAllWithProducts();

        // Transformar la Lista Category en Lista CategoryDto
        List<CategoryDto> categoriesDto = categories.stream().map(category -> {
            // Transformar la lista product en lista productDto
            List<ProductDto> productsDto = category.getProducts().stream().map(product ->
                ProductDto.builder()
                        .id(product.getIdProduct())
                        .name(product.getNameProduct())
                        .description(product.getDescriptionProduct())
                        .price(product.getPriceProduct())
                        .image(product.getImageProduct())
                        .build()
            ).toList();

            return CategoryDto.builder()
                    .name(category.getNameCategory())
                    .products(productsDto)
                    .build();
        }).toList();

        return categoriesDto;
    }
}
