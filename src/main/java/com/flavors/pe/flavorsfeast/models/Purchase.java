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
@Table(name = "purchase")
public class Purchase {
    @Column(name = "id_purchase")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPurchase;

    @Column(name = "date_purchase")
    private LocalDate datePurchase;

    @Column(name = "type_purchase")
    @Enumerated(EnumType.STRING)
    private TypePurchase typePurchase;

    @Column(name = "address_purchase")
    private String addressPurchase;

    @Column(name = "location_purchase")
    private String locationPurchase;

    @Column(name = "subtotal_purchase")
    private Double subtotalPurchase;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL)
    private List<PurchaseDetails> purchaseDetails;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
}
