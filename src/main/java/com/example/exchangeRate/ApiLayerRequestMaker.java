package com.example.exchangeRate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class ApiLayerRequestMaker implements RequestMaker{
    @Value("${API_KEY}")
    private String api_key;
    @Override
    public HttpEntity<Void> makeRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", api_key);
        return new HttpEntity<>(headers);
    }
}
