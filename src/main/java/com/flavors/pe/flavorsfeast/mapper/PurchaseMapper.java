package com.flavors.pe.flavorsfeast.mapper;

import com.flavors.pe.flavorsfeast.dto.PurchaseRequestDto;
import com.flavors.pe.flavorsfeast.dto.PurchaseResponseDto;
import com.flavors.pe.flavorsfeast.models.Purchase;
import com.flavors.pe.flavorsfeast.models.PurchaseDetail;

public class PurchaseMapper {

    public static PurchaseResponseDto toDto(Purchase purchase) {
        return new PurchaseResponseDto(
                purchase.getUid(),
                purchase.getDate(),
                purchase.getType(),
                purchase.getAddress(),
                purchase.getLocation(),
                purchase.getSubtotal(),
                purchase.getPurchaseDetails()
                        .stream()
                        .map(PurchaseDetail::getProduct)
                        .map(ProductMapper::toDto)
                        .toList()
        );
    }

    public static Purchase toEntity(PurchaseRequestDto purchase) {
        return Purchase.builder()
                .type(purchase.type())
                .address(purchase.address())
                .location(purchase.location())
                .subtotal(purchase.subtotal())
                .build();
    }
}
