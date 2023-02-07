package com.example.exchangeRate.service;

import com.example.exchangeRate.models.DailyRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service("apilayer")
public class ApiLayerDailyRateGetter implements DailyRateGetter {
    @Autowired
    private ApiLayerProxy apiLayerProxy;
    @Value("${exchange.symbol}")
    private String symbol;
    @Value("${exchange.api}")
    private String apiKey;

    @Override
    public DailyRate getDailyRate() {
        String date= String.valueOf(LocalDate.now());
        ResponseEntity<String> response = apiLayerProxy.getDailyRate(apiKey, date, symbol);
        return ApiLayerParser.getParsedResponse(response);
    }
}
