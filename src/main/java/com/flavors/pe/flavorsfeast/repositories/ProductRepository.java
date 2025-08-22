package com.flavors.pe.flavorsfeast.repositories;

import com.flavors.pe.flavorsfeast.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
