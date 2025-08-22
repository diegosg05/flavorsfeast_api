package com.flavors.pe.flavorsfeast.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponseDto<T> {
    private T response;
    private ErrorMessageDto error;
}
