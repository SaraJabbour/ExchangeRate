package com.example.exchangeRate.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class ResponseGetterService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RequestMakerService requestMakerService;
    @Value("${URL}")
    private String baseUrl;
    @Value("${Symbols}")
    private String symbol;

    public String createUrl() {
        return baseUrl + LocalDate.now() + "?symbols=" + symbol;
    }

    public ResponseEntity<String> getResponse() {
        HttpEntity<Void> requestEntity = requestMakerService.makeRequest();
        String url = createUrl();
        return restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
    }
}
