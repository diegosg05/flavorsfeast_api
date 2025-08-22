package com.flavors.pe.flavorsfeast.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reservation")
public class Reservation {
    @Column(name = "id_reservation")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReservation;

    @Column(name = "date_reservation")
    private LocalDate dateReservation;

    @Column(name = "time_reservation")
    private LocalTime timeReservation;

    @Column(name = "persons_reservation")
    private Integer personsReservation;

    @Column(name = "location_reservation")
    private String locationReservation;

    @Column(name = "state_reservation")
    @Enumerated(EnumType.STRING)
    private State state;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
}
