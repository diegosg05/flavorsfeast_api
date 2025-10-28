package com.flavors.pe.flavorsfeast.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserUpdateDto(
        @NotNull @NotBlank String firstname,
        @NotNull @NotBlank String lastname,
        @NotNull @NotBlank String phone
) {
}
