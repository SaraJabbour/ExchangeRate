package com.example.exchangeRate.Service;

import com.example.exchangeRate.Model.DailyRate;
import com.example.exchangeRate.Model.RateVariation;
import com.example.exchangeRate.Repository.DailyRateRepository;
import com.example.exchangeRate.Repository.RateVariationRepository;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class DailyRateService {
    @Autowired
    private DailyRateRepository dailyRateRepository;

    public void saveDailyRates(List <DailyRate> dailyRates) throws RuntimeException{
        dailyRateRepository.saveAll(dailyRates);
    }

}
