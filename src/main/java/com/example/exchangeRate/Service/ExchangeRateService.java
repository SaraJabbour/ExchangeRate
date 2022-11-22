package com.example.exchangeRate.Service;

import com.example.exchangeRate.Model.ApiResponse;
import com.example.exchangeRate.Repository.ExchangeRateRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ExchangeRateService {
    private final static String API_KEY="UzajJ80FpVw05bm0LpyuO5LE14P0CoVL";
    private final static String uri="https://api.exchangeratesapi.io/v1/timeseries?access_key={api_key}&start_date=2012-05-01&end_date=2012-05-25";
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    //Testing API call response
    public void getExternalData() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("apikey", "UzajJ80FpVw05bm0LpyuO5LE14P0CoVL");
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
            ResponseEntity<ApiResponse> response = restTemplate.exchange(
                    "https://api.apilayer.com/exchangerates_data/timeseries?start_date=2022-01-01&end_date=2022-01-05&symbols=USD", HttpMethod.GET, requestEntity, ApiResponse.class);
            try{
                JSONObject jsondailyRates=response.getBody().getJsondailyRates();
                System.out.println(jsondailyRates);
            }
            catch(NullPointerException e){
                e.printStackTrace();
            }

        }
        catch (HttpClientErrorException e){
            System.out.println(e.getMessage());
        }
    }
}
