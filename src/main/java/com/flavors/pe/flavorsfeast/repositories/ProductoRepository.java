package com.flavors.pe.flavorsfeast.repositories;

import com.flavors.pe.flavorsfeast.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
