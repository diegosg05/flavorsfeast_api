package com.flavors.pe.flavorsfeast.services;

import com.flavors.pe.flavorsfeast.dto.ReservationDto;

import java.util.List;

public interface ReservationService {
    ReservationDto registerReservation(ReservationDto reservation, String email);
    List<ReservationDto> getAllReservationsByUser(String email);
    void cancelReservation(String uid, String email);
}
