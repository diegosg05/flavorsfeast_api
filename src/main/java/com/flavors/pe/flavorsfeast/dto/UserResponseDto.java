package com.flavors.pe.flavorsfeast.dto;

import java.util.Date;

public record UserResponseDto(
        String uid,
        String firstname,
        String lastname,
        String email,
        String phone,
        String role,
        Date createdAt
) {
}
