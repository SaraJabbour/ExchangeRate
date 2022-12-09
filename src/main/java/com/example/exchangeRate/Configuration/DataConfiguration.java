package com.example.exchangeRate.Configuration;


import com.example.exchangeRate.Service.DailyRateUpdaterService;
import com.example.exchangeRate.Service.RateVariationUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Configuration
public class DataConfiguration {
    @Autowired
    private RateVariationUpdateService rateVariationUpdateService;
    @Autowired
    private DailyRateUpdaterService dailyRateUpdaterService;

    // Updates every day at 1 AM
    @Scheduled(cron = "0 0 1 * * ?")
    public void scheduledDataUpdater() {
        dailyRateUpdaterService.getAndUpdateDailyRate();
        rateVariationUpdateService.updateRateVariation();
        System.out.println("Updated at " + LocalDateTime.now());
    }


}
