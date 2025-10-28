package com.flavors.pe.flavorsfeast.dto;

import com.flavors.pe.flavorsfeast.util.ReservationState;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record ReservationDto(
        String uid,
        @NotNull LocalDate date,
        @NotNull LocalTime time,
        @NotNull Integer persons,
        @NotNull String location,
        ReservationState state
) {
    public ReservationDto {
        if (uid == null || state == null) {
            uid = UUID.randomUUID().toString().replace("-", "");
            state = ReservationState.PENDING;
        }

    }
}
