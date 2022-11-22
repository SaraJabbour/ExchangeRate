package com.example.exchangeRate.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public class ApiRequest {
    private static final String base="EUR";
    private static final String symbol="USD";
    private static final String API_KEY="UzajJ80FpVw05bm0LpyuO5LE14P0CoVL";
    private static final String url="https://api.apilayer.com/exchangerates_data/latest?symbols={symbol}";
    @Autowired
    private RestTemplate restTemplate;

    public HttpEntity<HttpHeaders> createRequest (){
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", API_KEY);
        return new HttpEntity<>(headers);
       // return restTemplate.exchange(url, HttpMethod.GET, requestEntity, ApiResponse.class);
    }

}
