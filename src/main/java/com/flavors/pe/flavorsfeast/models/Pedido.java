package com.flavors.pe.flavorsfeast.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pedido")
public class Pedido {
    @Column(name = "id_pedido")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPedido;
    @Column(name = "fecha_pedido")
    private LocalDate fechaPedido;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @Column(name = "tipo_entrega")
    private String tipoEntrega;
    private String direccion;
    private String sucursal;
    private Double subtotal;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<DetallePedido> detallePedidos;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
