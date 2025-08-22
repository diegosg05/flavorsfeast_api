package com.flavors.pe.flavorsfeast.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePurchaseDetailsDto {
    private String name;
    private String description;
    private String image;
    private Integer quantity;
    private Double price;
}
