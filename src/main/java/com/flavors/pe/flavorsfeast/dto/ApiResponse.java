package com.flavors.pe.flavorsfeast.dto;

public record ApiResponse<T>(
        T data,
        ErrorDto error
) {
}
