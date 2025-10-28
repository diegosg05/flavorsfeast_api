package com.flavors.pe.flavorsfeast.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PurchaseRequestDto(
        @NotNull @NotBlank String type,
        String address,
        String location,
        @NotNull Double subtotal,
        List<PurchaseProductsDto> products
) {
}
