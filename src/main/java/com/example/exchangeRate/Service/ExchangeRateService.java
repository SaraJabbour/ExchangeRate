package com.example.exchangeRate.Service;

import com.example.exchangeRate.Model.DailyRate;
import com.example.exchangeRate.Model.RateVariation;
import com.example.exchangeRate.Repository.ExchangeRateRepository;
import com.example.exchangeRate.Repository.ExchangeVariationRepository;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;

@Service
public class ExchangeRateService {
    private final static String API_KEY="UzajJ80FpVw05bm0LpyuO5LE14P0CoVL";
    private final static String uri="https://api.exchangeratesapi.io/v1/timeseries?access_key={api_key}&start_date=2012-05-01&end_date=2012-05-25";
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ExchangeRateRepository exchangeRateRepository;
    @Autowired
    private ExchangeVariationRepository exchangeVariationRepository;

    public void getExternalData() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("apikey", "UzajJ80FpVw05bm0LpyuO5LE14P0CoVL");
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
            //Didn't work trying to get it as a JsonObject
            ResponseEntity<String> response = restTemplate.exchange(
                    "https://api.apilayer.com/exchangerates_data/timeseries?start_date=2022-01-01&end_date=2022-11-23&symbols=USD", HttpMethod.GET, requestEntity, String.class);
            JsonObject jsonObject = JsonParser.parseString(response.getBody()).getAsJsonObject();
            JsonObject jsonRates=jsonObject.get("rates").getAsJsonObject();
            Iterator<String> keys = jsonRates.keySet().iterator();
            ArrayList<DailyRate> dailyRates=new ArrayList<>();
            while(keys.hasNext()) {
                String key = keys.next();
                LocalDate date= LocalDate.parse(key);
                Double value=jsonRates.get(key).getAsJsonObject().get("USD").getAsDouble();
                DailyRate dailyRate=new DailyRate(LocalDate.parse(key),value);
                dailyRates.add(dailyRate);
            }
            exchangeRateRepository.saveAll(dailyRates);
            getAndImplement(dailyRates);
        }
        catch (HttpClientErrorException e){
            System.out.println(e.getMessage());
        }
    }
    public void getAndImplement(ArrayList<DailyRate>dailyRates){
        Double previousValue=dailyRates.get(0).getUSD();
        RateVariation rateVariation=new RateVariation();
        Integer increases=0,decreases=0;
        for ( int i = 1; i < dailyRates.size();i++){
            Double currentValue=dailyRates.get(i).getUSD();
            if(previousValue < currentValue)
                increases++;
            else if (previousValue > currentValue)
                decreases++;
        }
        rateVariation.setIncreases(increases);
        rateVariation.setDecreases(decreases);
        exchangeVariationRepository.save(rateVariation);
    }
}
