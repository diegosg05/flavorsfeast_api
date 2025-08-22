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
@Table(name = "product")
public class Product {
    @Column(name = "id_product")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduct;

    @Column(name = "name_product")
    private String nameProduct;

    @Column(name = "description_product")
    private String descriptionProduct;

    @Column(name = "price_product")
    private Double priceProduct;

    @Column(name = "image_product")
    private String imageProduct;

    @OneToMany(mappedBy = "product")
    private List<PurchaseDetails> purchaseDetails;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;
}
