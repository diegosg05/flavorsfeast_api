package com.flavors.pe.flavorsfeast.mapper;

import com.flavors.pe.flavorsfeast.dto.ReservationDto;
import com.flavors.pe.flavorsfeast.models.Reservation;

public class ReservationMapper {

    public static ReservationDto toDto(Reservation reservation) {
        return new ReservationDto(
                reservation.getUid(),
                reservation.getDate(),
                reservation.getTime(),
                reservation.getPersons(),
                reservation.getLocation(),
                reservation.getState()
        );
    }

    public static Reservation toEntity(ReservationDto reservation) {
        return Reservation.builder()
                .uid(reservation.uid())
                .date(reservation.date())
                .time(reservation.time())
                .persons(reservation.persons())
                .location(reservation.location())
                .state(reservation.state())
                .build();
    }
}
