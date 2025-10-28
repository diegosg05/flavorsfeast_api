package com.flavors.pe.flavorsfeast.models;

import com.flavors.pe.flavorsfeast.util.ReservationState;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "tbl_reservation")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation")
    private Integer id;
    @Column(unique = true, updatable = false)
    @Builder.Default
    private String uid = UUID.randomUUID().toString().replace("-", "");
    private LocalDate date;
    private LocalTime time;
    private Integer persons;
    private String location;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ReservationState state = ReservationState.PENDING;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
}
