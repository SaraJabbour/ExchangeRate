package com.example.exchangeRate.Interfaces;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ResponseConverter {
    List<?> convertResponse(ResponseEntity<String> responseEntity);
}
