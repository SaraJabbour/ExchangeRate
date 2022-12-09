package com.example.exchangeRate.Configuration;


import com.example.exchangeRate.Service.DailyRateUpdaterService;
import com.example.exchangeRate.Service.RateVariationUpdateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Configuration
public class DataConfiguration {
    Logger LOGGER = LoggerFactory.getLogger(DataConfiguration.class);
    @Autowired
    private RateVariationUpdateService rateVariationUpdateService;
    @Autowired
    private DailyRateUpdaterService dailyRateUpdaterService;

    // Updates every day at 1 AM
    @Scheduled(cron = "0 0 1 * * ?")
    public void scheduledDataUpdater() {
        dailyRateUpdaterService.getAndSaveDailyRate();
        rateVariationUpdateService.updateRateVariation();
        LOGGER.info("Database updated at " + LocalDateTime.now());
    }
}
