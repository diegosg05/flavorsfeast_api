package com.flavors.pe.flavorsfeast.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tbl_product")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Integer id;
    @Column(unique = true, updatable = false)
    @Builder.Default
    private String uid = UUID.randomUUID().toString().replace("-", "");
    private String name;
    private String description;
    private Double price;
    private String image;

    @OneToMany(mappedBy = "product")
    @Builder.Default
    private List<PurchaseDetail> purchaseDetails = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_category")
    private Category category;
}
