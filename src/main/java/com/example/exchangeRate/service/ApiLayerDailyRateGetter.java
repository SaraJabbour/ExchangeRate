package com.example.exchangeRate.service;

import com.example.exchangeRate.models.DailyRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service("apilayer")
public class ApiLayerDailyRateGetter implements DailyRateGetter {
    private final String symbol = "USD";
    @Autowired
    private ApiLayerProxy apiLayerProxy;
    @Value("${exchange.api}")
    private String apiKey;

    @Override
    public DailyRate getDailyRate() {
        String date = String.valueOf(LocalDate.now());
        try {
            ResponseEntity<String> response = apiLayerProxy.getDailyRate(apiKey, date, symbol);
            return ApiLayerParser.getParsedResponse(response);
        } catch (Exception e) {
            throw new RuntimeException("Error getting request");
        }

    }
}
