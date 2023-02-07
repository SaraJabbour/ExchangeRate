package com.example.exchangeRate.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "exchangeRateApi", url = "https://api.apilayer.com/exchangerates_data")
public interface ApiLayerProxy {
    @GetMapping("/{date}?symbols={symbol}")
    ResponseEntity<String> getDailyRate(@RequestHeader(value = "apikey", required = true) String apikey, @PathVariable("date") String date, @RequestParam("symbols") String symbol);
}
