package com.example.exchangeRate;

import org.springframework.http.HttpEntity;

public interface RequestMaker {
    HttpEntity<?> makeRequest();
}
