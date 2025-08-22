package com.flavors.pe.flavorsfeast.repositories;

import com.flavors.pe.flavorsfeast.models.Purchase;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    @EntityGraph(attributePaths = {
            "purchaseDetails",
            "purchaseDetails.product"
    })
    @Query("SELECT p FROM Purchase p WHERE p.user.emailUser = :emailUser")
    List<Purchase> findAllByEmailUserWithPurchaseDetailsAndProducts(@Param("emailUser") String emailUser);
}
