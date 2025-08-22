package com.flavors.pe.flavorsfeast.dto;

import com.flavors.pe.flavorsfeast.models.TypePurchase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePurchaseDto {
    private Integer id;
    private LocalDate date;
    private TypePurchase typePurchase;
    private String address;
    private String location;
    private Double subtotal;
    private List<ResponsePurchaseDetailsDto> products;
}
