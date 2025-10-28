package com.flavors.pe.flavorsfeast.services;

import com.flavors.pe.flavorsfeast.dto.PurchaseRequestDto;
import com.flavors.pe.flavorsfeast.dto.PurchaseResponseDto;

import java.util.List;

public interface PurchaseService {
    PurchaseResponseDto registerPurchase(PurchaseRequestDto purchase, String email);
    List<PurchaseResponseDto> getAllPurchasesByUser(String email);
}
