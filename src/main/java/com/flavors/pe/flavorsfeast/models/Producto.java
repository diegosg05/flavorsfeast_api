package com.flavors.pe.flavorsfeast.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "producto")
public class Producto {
    @Column(name = "id_producto")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String imagenUrl;

    @OneToMany(mappedBy = "producto")
    private List<DetallePedido> detallePedidos;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
}
