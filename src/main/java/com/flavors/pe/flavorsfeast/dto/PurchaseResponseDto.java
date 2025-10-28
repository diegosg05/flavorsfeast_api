package com.flavors.pe.flavorsfeast.dto;

import java.util.Date;
import java.util.List;

public record PurchaseResponseDto(
        String uid,
        Date date,
        String type,
        String address,
        String location,
        Double subtotal,
        List<ProductDto> products
) {
}
