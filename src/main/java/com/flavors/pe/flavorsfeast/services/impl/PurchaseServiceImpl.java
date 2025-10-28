package com.flavors.pe.flavorsfeast.services.impl;

import com.flavors.pe.flavorsfeast.dto.*;
import com.flavors.pe.flavorsfeast.exception.ResourceNotFoundException;
import com.flavors.pe.flavorsfeast.mapper.PurchaseMapper;
import com.flavors.pe.flavorsfeast.models.Product;
import com.flavors.pe.flavorsfeast.models.PurchaseDetail;
import com.flavors.pe.flavorsfeast.repositories.ProductRepository;
import com.flavors.pe.flavorsfeast.repositories.PurchaseRepository;
import com.flavors.pe.flavorsfeast.repositories.UserRepository;
import com.flavors.pe.flavorsfeast.services.PurchaseService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository,
                               UserRepository userRepository,
                               ProductRepository productRepository) {
        this.purchaseRepository = purchaseRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public PurchaseResponseDto registerPurchase(PurchaseRequestDto purchaseDto, String email) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("No se encontró el usuario con email: " + email));

        var purchase = PurchaseMapper.toEntity(purchaseDto);
        purchase.setUser(user);

        var purchaseDetail = purchaseDto
                .products()
                .stream()
                .map(productPurchase -> {
                    Product product = productRepository.findByUid(productPurchase.uid())
                            .orElseThrow(() ->
                                    new ResourceNotFoundException(
                                            "No se encontró el producto con id: " + productPurchase.uid()));

                    return PurchaseDetail.builder()
                            .purchase(purchase)
                            .product(product)
                            .quantity(productPurchase.quantity())
                            .unitPrice(product.getPrice())
                            .build();
                }).toList();

        purchase.setPurchaseDetails(purchaseDetail);
        return PurchaseMapper.toDto(purchaseRepository.save(purchase));
    }

    @Override
    public List<PurchaseResponseDto> getAllPurchasesByUser(String email) {
        var purchases = purchaseRepository.findAllPurchasesByUser(email);

        return purchases
                .stream()
                .map(PurchaseMapper::toDto)
                .toList();
    }
}
