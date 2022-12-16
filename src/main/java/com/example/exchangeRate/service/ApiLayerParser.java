package com.example.exchangeRate.service;

import com.example.exchangeRate.entity.DailyRate;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public class ApiLayerParser {

    public static DailyRate getParsedResponse(ResponseEntity<String> response) {
        String responseString = response.getBody();
        JsonObject jsonObject = new Gson().fromJson(responseString, JsonObject.class);
        LocalDate date = LocalDate.parse(jsonObject.get("date").getAsString());
        Double value = jsonObject.getAsJsonObject("rates").get("USD").getAsDouble();
        return new DailyRate(date, value);
    }

}
