package com.flavors.pe.flavorsfeast.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tbl_purchase")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_purchase")
    private Integer id;
    @Column(unique = true, updatable = false)
    @Builder.Default
    private String uid = UUID.randomUUID().toString().replace("-", "");
    @CreationTimestamp
    @Column(updatable = false)
    private Date date;
    private String type;
    private String address;
    private String location;
    private Double subtotal;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Builder.Default
    private List<PurchaseDetail> purchaseDetails = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
}
