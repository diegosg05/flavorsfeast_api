package com.flavors.pe.flavorsfeast.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdatePasswordDto(
        @NotNull @NotBlank String actualPassword,
        @NotNull @NotBlank String newPassword
) {
}
