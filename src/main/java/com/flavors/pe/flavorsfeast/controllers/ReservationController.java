package com.flavors.pe.flavorsfeast.controllers;

import com.flavors.pe.flavorsfeast.dto.ApiResponse;
import com.flavors.pe.flavorsfeast.dto.ReservationDto;
import com.flavors.pe.flavorsfeast.services.impl.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ReservationDto>> registerReservation(
            @Valid @RequestBody ReservationDto reservation,
            @CurrentSecurityContext(expression = "authentication?.name") String email
    ) {
        var reservationSaved = reservationService.registerReservation(reservation, email);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{uid}")
                .buildAndExpand(reservationSaved.uid())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{uid}")
    public ResponseEntity<Void> cancelReservation(
            @PathVariable String uid,
            @CurrentSecurityContext(expression = "authentication?.name") String email
    ) {
        reservationService.cancelReservation(uid, email);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ReservationDto>>> GetAllReservationsByUser(
            @CurrentSecurityContext(expression = "authentication?.name") String email
    ) {
        var reservationsDto = reservationService.getAllReservationsByUser(email);

        var apiResponse = new ApiResponse<>(
                reservationsDto,
                null
        );

        return ResponseEntity.ok(apiResponse);
    }
}
