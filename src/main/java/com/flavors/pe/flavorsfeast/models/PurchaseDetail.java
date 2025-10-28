package com.flavors.pe.flavorsfeast.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tbl_purchase_detail")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_purchase_detail")
    private Integer id;

    @Column(unique = true, updatable = false)
    @Builder.Default
    private String uid = UUID.randomUUID().toString().replace("-", "");

    @ManyToOne
    @JoinColumn(name = "id_purchase")
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;
    private Integer quantity;
    private Double unitPrice;
}
