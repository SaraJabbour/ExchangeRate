package com.example.exchangeRate.Service;

import com.example.exchangeRate.Entity.DailyRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class DailyRateUpdaterService {
    @Autowired
    private ResponseGetterService responseGetterService;
    @Autowired
    private ResponseParserService responseParserService;
    @Autowired
    private DailyRateService dailyRateService;
    @Autowired
    private RateVariationService rateVariationService;

    public void getAndUpdateDailyRate() {
        ResponseEntity<String> responseEntity = responseGetterService.getResponse();
        DailyRate dailyRate = responseParserService.getParsedResponse(responseEntity);
        dailyRateService.saveDailyRate(dailyRate);
    }
}
