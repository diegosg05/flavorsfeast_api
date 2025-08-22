package com.flavors.pe.flavorsfeast.exception;

import com.flavors.pe.flavorsfeast.dto.ErrorMessageDto;
import com.flavors.pe.flavorsfeast.dto.GenericResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public GenericResponseDto<String> resourceNotFoundException(ResourceNotFoundException ex) {
        ErrorMessageDto errorDto = ErrorMessageDto
                .builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .date(LocalDate.now())
                .build();

        GenericResponseDto<String> response = new GenericResponseDto<>();
        response.setError(errorDto);
        return response;
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public GenericResponseDto<String> unauthorizedException(UnauthorizedException ex) {
        ErrorMessageDto errorDto = ErrorMessageDto
                .builder()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .message(ex.getMessage())
                .date(LocalDate.now())
                .build();

        GenericResponseDto<String> response = new GenericResponseDto<>();
        response.setError(errorDto);
        return response;
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public GenericResponseDto<String> badRequestException(BadRequestException ex) {
        ErrorMessageDto errorDto = ErrorMessageDto
                .builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .date(LocalDate.now())
                .build();

        GenericResponseDto<String> response = new GenericResponseDto<>();
        response.setError(errorDto);
        return response;
    }
}
