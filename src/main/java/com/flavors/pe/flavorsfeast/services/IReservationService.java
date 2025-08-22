package com.flavors.pe.flavorsfeast.services;

import com.flavors.pe.flavorsfeast.dto.RequestReservationDto;
import com.flavors.pe.flavorsfeast.dto.ResponseReservationDto;

import java.util.List;

public interface IReservationService {
    Integer registerReservation(RequestReservationDto requestReservationDto);
    List<ResponseReservationDto> getAllReservations();
}
