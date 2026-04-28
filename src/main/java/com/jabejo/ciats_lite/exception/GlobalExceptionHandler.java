package com.jabejo.ciats_lite.exception;

import com.jabejo.ciats_lite.dto.WebResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<WebResponse<String>> handleNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(
                WebResponse.<String>builder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .status("NOT FOUND")
                        .message(ex.getMessage())
                        .build(),
                HttpStatus.NOT_FOUND
        );
    }
}