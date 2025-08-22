package com.flavors.pe.flavorsfeast.repositories;

import com.flavors.pe.flavorsfeast.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmailUser(String emailUser);
}
