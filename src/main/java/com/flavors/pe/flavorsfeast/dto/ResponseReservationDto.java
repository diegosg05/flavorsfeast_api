package com.flavors.pe.flavorsfeast.dto;

import com.flavors.pe.flavorsfeast.models.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseReservationDto {
    private LocalDate date;
    private LocalTime time;
    private Integer persons;
    private String location;
    private State state;
}
