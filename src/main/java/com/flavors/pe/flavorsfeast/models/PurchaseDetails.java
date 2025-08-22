package com.flavors.pe.flavorsfeast.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "purchase_details")
public class PurchaseDetails {
    @Column(name = "id_purchase_details")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPurchaseDetails;

    @ManyToOne
    @JoinColumn(name = "id_purchase")
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    @Column(name = "quantity_purchase_details")
    private Integer quantityPurchaseDetails;

    @Column(name = "unit_price_purchase_details")
    private Double unitPricePurchaseDetails;
}
