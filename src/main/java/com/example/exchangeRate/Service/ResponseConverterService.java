package com.example.exchangeRate.Service;

import com.example.exchangeRate.Model.DailyRate;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//useless interface
@Service
public class ResponseConverterService {
    Logger LOGGER = LoggerFactory.getLogger(ResponseConverterService.class);

    public List<DailyRate> convertResponse(ResponseEntity<String> response) {
        JsonObject jsonObject = parseResponse(response);
        return getDailyRates(jsonObject);
    }

    //gson
    public JsonObject parseResponse(ResponseEntity<String> response) {
        try {
            DailyRate rate = new Gson().fromJson(response.getBody(), DailyRate.class);
            JsonObject jsonObject = JsonParser.parseString(response.getBody()).getAsJsonObject();
            return jsonObject.get("rates").getAsJsonObject();
        } catch (NullPointerException e) {
            LOGGER.error("Response body null");
            return new JsonObject();
        }
    }

    public List<DailyRate> getDailyRates(JsonObject jsonRates) {
        Iterator<String> keys = jsonRates.keySet().iterator();
        ArrayList<DailyRate> dailyRates = new ArrayList<>();

        while (keys.hasNext()) {
            String key = keys.next();
            LocalDate date = LocalDate.parse(key);
            Double rate = jsonRates.get(key).getAsJsonObject().get("USD").getAsDouble();
            DailyRate dailyRate = new DailyRate(date, rate);
            dailyRates.add(dailyRate);
        }
        return dailyRates;
    }
}