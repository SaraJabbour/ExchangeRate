package com.example.exchangeRate.service;

import com.example.exchangeRate.models.DailyRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class DailyRateUpdater {
    private DailyRateGetter dailyRateGetter;

    @Autowired
    private DailyRateService dailyRateService;

    @Autowired
    public DailyRateUpdater(@Qualifier("apilayer") ApiLayerDailyRateGetter dailyRateGetter) {
        this.dailyRateGetter = dailyRateGetter;
    }

    public void getAndSaveDailyRate() {
        DailyRate dailyRate = dailyRateGetter.getDailyRate();
        dailyRateService.saveDailyRate(dailyRate);
    }


}
