package com.example.exchangeRate;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ResponseParser {
    List<?> parseResponse(ResponseEntity<String> responseEntity);
}
