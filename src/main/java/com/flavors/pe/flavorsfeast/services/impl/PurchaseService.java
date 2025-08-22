package com.flavors.pe.flavorsfeast.services.impl;

import com.flavors.pe.flavorsfeast.dto.RequestPurchaseDto;
import com.flavors.pe.flavorsfeast.dto.ResponsePurchaseDetailsDto;
import com.flavors.pe.flavorsfeast.dto.ResponsePurchaseDto;
import com.flavors.pe.flavorsfeast.models.Product;
import com.flavors.pe.flavorsfeast.models.Purchase;
import com.flavors.pe.flavorsfeast.models.PurchaseDetails;
import com.flavors.pe.flavorsfeast.models.User;
import com.flavors.pe.flavorsfeast.repositories.ProductRepository;
import com.flavors.pe.flavorsfeast.repositories.PurchaseRepository;
import com.flavors.pe.flavorsfeast.repositories.UserRepository;
import com.flavors.pe.flavorsfeast.services.IPurchaseService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PurchaseService implements IPurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public PurchaseService(PurchaseRepository purchaseRepository,
                           UserRepository userRepository,
                           ProductRepository productRepository) {
        this.purchaseRepository = purchaseRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Integer registerPurchase(RequestPurchaseDto requestPurchaseDto) {
        // Conseguir el usuario con la autenticación
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepository.findByEmailUser(email).orElseThrow();

        Purchase purchase = Purchase
                .builder()
                .datePurchase(LocalDate.now())
                .addressPurchase(requestPurchaseDto.getAddress())
                .locationPurchase(requestPurchaseDto.getLocation())
                .user(user)
                .typePurchase(requestPurchaseDto.getTypePurchase())
                .subtotalPurchase(requestPurchaseDto.getSubtotal())
                .build();

        // Registrar cada producto con su cantidad y precio en el detalle
        List<PurchaseDetails> purchasesDetails = requestPurchaseDto
                .getProducts()
                .stream().map(productPurchase -> {
                    Product product = productRepository.findById(productPurchase.getId())
                            .orElseThrow(() -> new RuntimeException("Product Not Found"));

                    PurchaseDetails purchaseDetails = PurchaseDetails.builder()
                            .purchase(purchase)
                            .product(product)
                            .quantityPurchaseDetails(productPurchase.getQuantity())
                            .unitPricePurchaseDetails(product.getPriceProduct())
                            .build();
                    return purchaseDetails;
                }).toList();

        purchase.setPurchaseDetails(purchasesDetails);
        purchaseRepository.save(purchase);
        return purchase.getIdPurchase();
    }

    @Override
    public List<ResponsePurchaseDto> getAllPurchases() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        List<Purchase> purchases = purchaseRepository.findAllByEmailUserWithPurchaseDetailsAndProducts(email);

        List<ResponsePurchaseDto> responsePurchasesDto = purchases.stream().map(purchase -> {
            ResponsePurchaseDto responsePurchaseDto = ResponsePurchaseDto.builder()
                    .id(purchase.getIdPurchase())
                    .date(purchase.getDatePurchase())
                    .address(purchase.getAddressPurchase())
                    .location(purchase.getLocationPurchase())
                    .typePurchase(purchase.getTypePurchase())
                    .subtotal(purchase.getSubtotalPurchase())
                    .build();

            List<ResponsePurchaseDetailsDto> productsPurchaseDto = purchase
                    .getPurchaseDetails().stream().map(productPurchase ->
                            ResponsePurchaseDetailsDto.builder()
                                    .name(productPurchase.getProduct().getNameProduct())
                                    .quantity(productPurchase.getQuantityPurchaseDetails())
                                    .price(productPurchase.getUnitPricePurchaseDetails())
                                    .description(productPurchase.getProduct().getDescriptionProduct())
                                    .image(productPurchase.getProduct().getImageProduct())
                                    .build()
                    ).toList();

            responsePurchaseDto.setProducts(productsPurchaseDto);
            return responsePurchaseDto;
        }).toList();

        return responsePurchasesDto;
    }
}
