package com.example.exchangeRate.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RateVariationUpdater {
    @Autowired
    RateVariationService rateVariationService;
    @Autowired
    DailyRateService dailyRateService;

    public void updateRateVariation() {
        Double differenceBetweenLatestValues = dailyRateService.getDifferenceBetweenLatestValues();
        if (differenceBetweenLatestValues >= 0)
            rateVariationService.updateIncrease();
        else
            rateVariationService.updateDecrease();
    }

}
