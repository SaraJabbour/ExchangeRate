package com.example.exchangeRate;

import com.example.exchangeRate.Model.DailyRate;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// TODO Exception check
@Component
public class ApiLayerParser implements ResponseParser{

    @Override
    public List<DailyRate> parseResponse(ResponseEntity<String> response) {
        // TODO Check response body
        JsonObject jsonObject = JsonParser.parseString(response.getBody()).getAsJsonObject();
        JsonObject jsonRates=jsonObject.get("rates").getAsJsonObject();
        Iterator<String> keys = jsonRates.keySet().iterator();
        ArrayList<DailyRate> dailyRates= new ArrayList<>();

        while(keys.hasNext()) {
            String key = keys.next();
            LocalDate date= LocalDate.parse(key);
            Double value=jsonRates.get(key).getAsJsonObject().get("USD").getAsDouble();
            DailyRate dailyRate=new DailyRate(LocalDate.parse(key),value);
            dailyRates.add(dailyRate);
        }
        return dailyRates;
    }
}
