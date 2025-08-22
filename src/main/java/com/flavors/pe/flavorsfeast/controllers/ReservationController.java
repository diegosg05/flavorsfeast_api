package com.flavors.pe.flavorsfeast.controllers;

import com.flavors.pe.flavorsfeast.dto.GenericResponseDto;
import com.flavors.pe.flavorsfeast.dto.RequestReservationDto;
import com.flavors.pe.flavorsfeast.dto.ResponseReservationDto;
import com.flavors.pe.flavorsfeast.services.impl.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<?> registerReservation(@RequestBody RequestReservationDto requestReservationDto) {
        Integer id = reservationService.registerReservation(requestReservationDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<GenericResponseDto<List<ResponseReservationDto>>> GetAllReservations() {
        List<ResponseReservationDto> reservationsDto = reservationService.getAllReservations();

        if(reservationsDto.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        GenericResponseDto<List<ResponseReservationDto>> response = new GenericResponseDto<>();
        response.setResponse(reservationsDto);
        return ResponseEntity.ok(response);
    }
}
