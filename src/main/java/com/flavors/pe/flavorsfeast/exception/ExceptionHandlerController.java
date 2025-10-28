package com.flavors.pe.flavorsfeast.exception;

import com.flavors.pe.flavorsfeast.dto.ApiResponse;
import com.flavors.pe.flavorsfeast.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> resourceNotFoundException(ResourceNotFoundException ex) {
        var apiResponse = new ApiResponse<>(
                null,
                new ErrorDto(HttpStatus.NOT_FOUND.value(), ex.getMessage())
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse<?>> unauthorizedException(UnauthorizedException ex) {
        var apiResponse = new ApiResponse<>(
                null,
                new ErrorDto(HttpStatus.UNAUTHORIZED.value(), ex.getMessage())
        );

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<?>> badRequestException(BadRequestException ex) {
        var apiResponse = new ApiResponse<>(
                null,
                new ErrorDto(HttpStatus.BAD_REQUEST.value(), ex.getMessage())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<?>> badCredentialsException(BadCredentialsException ex) {
        var apiResponse = new ApiResponse<>(
                null,
                new ErrorDto(HttpStatus.BAD_REQUEST.value(), ex.getMessage())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> usernameNotFoundException(UsernameNotFoundException ex) {
        var apiResponse = new ApiResponse<>(
                null,
                new ErrorDto(HttpStatus.NOT_FOUND.value(), ex.getMessage())
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

}
