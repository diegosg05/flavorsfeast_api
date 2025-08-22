package com.flavors.pe.flavorsfeast.dto;

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
public class RequestReservationDto {
    private LocalDate date;
    private LocalTime time;
    private Integer persons;
    private String location;
}
