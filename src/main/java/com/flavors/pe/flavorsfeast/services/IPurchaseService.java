package com.flavors.pe.flavorsfeast.services;

import com.flavors.pe.flavorsfeast.dto.RequestPurchaseDto;
import com.flavors.pe.flavorsfeast.dto.ResponsePurchaseDto;

import java.util.List;

public interface IPurchaseService {
    Integer registerPurchase(RequestPurchaseDto requestPurchaseDto);
    List<ResponsePurchaseDto> getAllPurchases();
}
