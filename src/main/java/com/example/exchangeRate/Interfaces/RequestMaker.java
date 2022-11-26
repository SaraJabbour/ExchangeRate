package com.example.exchangeRate.Interfaces;

import org.springframework.http.HttpEntity;

public interface RequestMaker {
    HttpEntity<?> makeRequest();
}
