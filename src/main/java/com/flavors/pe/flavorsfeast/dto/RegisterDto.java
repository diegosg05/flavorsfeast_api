package com.flavors.pe.flavorsfeast.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDto(
        @NotNull @NotBlank String firstname,
        @NotNull @NotBlank String lastname,
        @NotNull @NotBlank String email,
        @NotNull @NotBlank String password,
        @NotNull @NotBlank String phone
) {
}
