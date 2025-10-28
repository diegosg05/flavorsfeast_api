package com.flavors.pe.flavorsfeast.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginDto(
        @NotNull @NotBlank String email,
        @NotNull @NotBlank String password
) {
}
