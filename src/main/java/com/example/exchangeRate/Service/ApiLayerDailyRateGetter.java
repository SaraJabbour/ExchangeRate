package com.example.exchangeRate.Service;

import com.example.exchangeRate.Entity.DailyRate;
import com.example.exchangeRate.Interface.DailyRateGetter;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private RestTemplate restTemplate;
    @Value("${URL}")
    private String baseUrl;
    @Value("${Symbols}")
    private String symbol;
    @Value("${API_KEY}")
    private String api_key;

    public String createUrl() {
        return baseUrl + LocalDate.now() + "?symbols=" + symbol;
    }

    @Override
    public DailyRate getDailyRate() {
        ResponseEntity<String> response = sendRequest();
        return getParsedResponse(response);
    }

    public ResponseEntity<String> sendRequest() {
        HttpEntity<Void> requestEntity = createRequest();
        String url = createUrl();
        return restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
    }

    public HttpEntity<Void> createRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", api_key);
        return new HttpEntity<>(headers);
    }

    public DailyRate getParsedResponse(ResponseEntity<String> response) {
        String responseString = response.getBody();
        JsonObject jsonObject = new Gson().fromJson(responseString, JsonObject.class);
        LocalDate date = LocalDate.parse(jsonObject.get("date").getAsString());
        Double value = jsonObject.getAsJsonObject("rates").get("USD").getAsDouble();
        return new DailyRate(date, value);
    }
}
