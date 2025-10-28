package com.flavors.pe.flavorsfeast.controllers;

import com.flavors.pe.flavorsfeast.dto.*;
import com.flavors.pe.flavorsfeast.services.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/purchases")
@Tag(
        name = "Purchase Controller",
        description = "Controlador donde los usuarios registrados pueden comprar y ver sus compras"
)
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    @Operation(
            summary = "Realizar compra",
            description = "Los usuarios realizan su compra, donde pueden retirarlos en tienda o por delivery",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Las compras pueden ser por delivery o por retiro, requiere su dirección o local de retiro",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PurchaseRequestDto.class)
                    )
            ),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "201",
                            description = "Successful Purchase"
                    )
            }
    )
    public ResponseEntity<ApiResponse<PurchaseResponseDto>> registerPurchase(
            @Valid @RequestBody PurchaseRequestDto purchaseDto,
            @CurrentSecurityContext(expression = "authentication?.name") String email
    ) {
        var purchaseSaved = purchaseService.registerPurchase(purchaseDto, email);

        var apiResponse = new ApiResponse<>(
                purchaseSaved,
                null
        );

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{uid}")
                .buildAndExpand(purchaseSaved.uid())
                .toUri();

        return ResponseEntity.created(uri).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PurchaseResponseDto>>> getAllPurchasesByUser(
            @CurrentSecurityContext(expression = "authentication?.name") String email
    ) {
        var purchasesDto = purchaseService.getAllPurchasesByUser(email);

        var apiResponse = new ApiResponse<>(
                purchasesDto,
                null
        );

        return ResponseEntity.ok(apiResponse);
    }
}
