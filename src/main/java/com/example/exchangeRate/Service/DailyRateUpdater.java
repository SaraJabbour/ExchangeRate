package com.example.exchangeRate.Service;

import com.example.exchangeRate.Entity.DailyRate;
import com.example.exchangeRate.Interface.DailyRateGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class DailyRateUpdater {
    @Autowired
    private DailyRateService dailyRateService;

    private DailyRateGetter dailyRateGetter;

    @Autowired
    public DailyRateUpdater(@Qualifier("apilayer") ApiLayerDailyRateGetter dailyRateGetter) {
        this.dailyRateGetter = dailyRateGetter;
    }

    public void getAndSaveDailyRate() {
        DailyRate dailyRate = dailyRateGetter.getDailyRate();
        dailyRateService.saveDailyRate(dailyRate);
    }


}
