package com.example.exchangeRate.Service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class RequestMakerService {
    @Value("${API_KEY}")
    private String api_key;

    public HttpEntity<Void> makeRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", api_key);
        return new HttpEntity<>(headers);
    }
}
