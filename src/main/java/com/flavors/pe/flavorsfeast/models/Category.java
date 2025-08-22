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
@Table(name = "category")
public class Category {
    @Column(name = "id_category")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategory;

    @Column(name = "name_category")
    private String nameCategory;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
