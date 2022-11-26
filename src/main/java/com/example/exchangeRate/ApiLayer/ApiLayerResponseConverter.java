package com.example.exchangeRate.ApiLayer;

import com.example.exchangeRate.Interfaces.ResponseConverter;
import com.example.exchangeRate.Model.DailyRate;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class ApiLayerResponseConverter implements ResponseConverter {

    Logger LOGGER = LoggerFactory.getLogger(ApiLayerResponseConverter.class);

    @Override
    public List<DailyRate> convertResponse(ResponseEntity<String> response){
        JsonObject jsonObject = parseResponse(response);
        return getDailyRates(jsonObject);
    }

    public JsonObject parseResponse(ResponseEntity<String> response) {
        try{
            JsonObject jsonObject = JsonParser.parseString(response.getBody()).getAsJsonObject();
            return jsonObject.get("rates").getAsJsonObject();
        }
        catch (NullPointerException e) {
            LOGGER.error("Response body null");
            return new JsonObject ();
        }

    }
    public List <DailyRate> getDailyRates(JsonObject jsonRates){
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
