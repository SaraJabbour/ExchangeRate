package com.example.exchangeRate.factory;

import com.example.exchangeRate.entity.DailyRate;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public class ApiLayerFactory {
    @Value("${exchange.api}")
    private String apiKey;

    public static DailyRate getParsedResponse(ResponseEntity<String> response) {
        String responseString = response.getBody();
        JsonObject jsonObject = new Gson().fromJson(responseString, JsonObject.class);
        LocalDate date = LocalDate.parse(jsonObject.get("date").getAsString());
        Double value = jsonObject.getAsJsonObject("rates").get("USD").getAsDouble();
        return new DailyRate(date, value);
    }

}
