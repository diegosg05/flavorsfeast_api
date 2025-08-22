package com.flavors.pe.flavorsfeast.repositories;

import com.flavors.pe.flavorsfeast.models.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @EntityGraph(attributePaths = "products")
    @Query("SELECT c FROM Category c")
    List<Category> findAllWithProducts();
}
