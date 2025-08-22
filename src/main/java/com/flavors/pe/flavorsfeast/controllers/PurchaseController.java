package com.flavors.pe.flavorsfeast.controllers;

import com.flavors.pe.flavorsfeast.dto.GenericResponseDto;
import com.flavors.pe.flavorsfeast.dto.RequestPurchaseDto;
import com.flavors.pe.flavorsfeast.dto.ResponsePurchaseDto;
import com.flavors.pe.flavorsfeast.services.impl.PurchaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    public ResponseEntity<?> registerPurchase(@RequestBody RequestPurchaseDto requestPurchaseDto) {
        Integer id = purchaseService.registerPurchase(requestPurchaseDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<GenericResponseDto<List<ResponsePurchaseDto>>> getAllPurchases() {
        List<ResponsePurchaseDto> purchasesDto = purchaseService.getAllPurchases();
        if(purchasesDto.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        GenericResponseDto<List<ResponsePurchaseDto>> response = new GenericResponseDto<>();
        response.setResponse(purchasesDto);
        return ResponseEntity.ok(response);
    }
}
