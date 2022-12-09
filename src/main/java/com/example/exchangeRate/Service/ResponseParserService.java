package com.example.exchangeRate.Service;

import com.example.exchangeRate.Entity.DailyRate;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

//useless interface
@Service
public class ResponseParserService {

    public DailyRate getParsedResponse(ResponseEntity<String> response) {
        String responseString = response.getBody();
        JsonObject jsonObject = new Gson().fromJson(responseString, JsonObject.class);
        LocalDate date = LocalDate.parse(jsonObject.get("date").getAsString());
        Double value = jsonObject.getAsJsonObject("rates").get("USD").getAsDouble();
        return new DailyRate(date, value);
    }


}
