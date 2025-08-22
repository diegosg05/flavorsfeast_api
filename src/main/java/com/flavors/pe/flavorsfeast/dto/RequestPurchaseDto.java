package com.flavors.pe.flavorsfeast.dto;

import com.flavors.pe.flavorsfeast.models.TypePurchase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestPurchaseDto {
    private TypePurchase typePurchase;
    private String address;
    private String location;
    private Double subtotal;
    private List<RequestPurchaseDetailsDto> products;
}
