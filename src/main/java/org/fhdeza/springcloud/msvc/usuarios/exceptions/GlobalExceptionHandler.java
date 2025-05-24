package org.fhdeza.springcloud.msvc.usuarios.exceptions;

import org.fhdeza.springcloud.msvc.usuarios.models.entity.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        ApiResponse<?> response = new ApiResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                "Validation failed",
                errors
        );

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<?> handleHttpException(HttpException ex) {
        ApiResponse<?> response = new ApiResponse<>(
                ex.getStatus().value(),
                ex.getMessage(),
                null
        );

        return ResponseEntity.status(ex.getStatus()).body(response);
    }


    @ExceptionHandler(CustomHttpException.class)
    public ResponseEntity<?> handleCustomException(CustomHttpException ex) {
        ApiResponse<?> response = new ApiResponse<>(
                ex.getStatus().value(),
                ex.getMessage(),
                null
        );

        return ResponseEntity.status(ex.getStatus()).body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleJsonParseException(HttpMessageNotReadableException ex) {
        ApiResponse<?> response = new ApiResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                "Invalid JSON request: " + ex.getMostSpecificCause().getMessage(),
                null
        );

        return ResponseEntity.badRequest().body(response);
    }


    // Puedes manejar más excepciones aquí
}
