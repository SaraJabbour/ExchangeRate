package com.example.exchangeRate.service;

import com.example.exchangeRate.entity.DailyRate;
import com.example.exchangeRate.factory.ApiLayerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;


@Service("apilayer")
public class ApiLayerDailyRateGetter implements DailyRateGetter {
    @Autowired
    @Lazy
    private final HttpEntity<Void> httpEntity;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${exchange.url}")
    private String baseUrl;
    @Value("${exchange.symbol}")
    private String symbol;
    @Value("${exchange.api}")
    private String apiKey;

    private ApiLayerDailyRateGetter() {
        httpEntity = createRequest();
    }

    @Bean
    public HttpEntity<Void> createRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", apiKey);
        return new HttpEntity<>(headers);
    }

    @Override
    public DailyRate getDailyRate() {
        ResponseEntity<String> response = sendRequest();
        return ApiLayerFactory.getParsedResponse(response);
    }

    public ResponseEntity<String> sendRequest() {
        String url = createUrl();
        return restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
    }

    public String createUrl() {
        return baseUrl + LocalDate.now() + "?symbols=" + symbol;
    }

}
