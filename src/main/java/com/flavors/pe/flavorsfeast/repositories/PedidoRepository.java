package com.flavors.pe.flavorsfeast.repositories;

import com.flavors.pe.flavorsfeast.models.Pedido;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    @EntityGraph(attributePaths = {
            "detallePedidos",
            "detallePedidos.producto"
    })
    @Query("SELECT p FROM Pedido p WHERE p.usuario.correo = :correo")
    List<Pedido> findAllByUsuarioCorreoConDetallesYProductos(@Param("correo") String correo);

}
