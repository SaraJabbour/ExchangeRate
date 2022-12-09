package com.example.exchangeRate.Configuration;


import com.example.exchangeRate.Service.DailyRateGetterAndUpdater;
import com.example.exchangeRate.Service.RateVariationUpdater;
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
    private RateVariationUpdater rateVariationUpdater;
    @Autowired
    private DailyRateGetterAndUpdater dailyRateGetterAndUpdater;

    // Updates every day at 1 AM
    @Scheduled(cron = "0 0 1 * * ?")
    public void scheduledDataUpdater() {
        dailyRateGetterAndUpdater.getAndSaveDailyRate();
        rateVariationUpdater.updateRateVariation();
        LOGGER.info("Database updated at " + LocalDateTime.now());
    }
}
