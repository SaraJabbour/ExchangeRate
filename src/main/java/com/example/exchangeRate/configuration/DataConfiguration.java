package com.example.exchangeRate.configuration;


import com.example.exchangeRate.service.DailyRateUpdater;
import com.example.exchangeRate.service.RateVariationUpdater;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@Log4j2
@EnableScheduling
//Set property to disable scheduler
@ConditionalOnProperty(name = "scheduler.enabled", matchIfMissing = true)
public class DataConfiguration {
    @Autowired
    private RateVariationUpdater rateVariationUpdater;
    @Autowired
    private DailyRateUpdater dailyRateUpdater;

    // Updates every day at 1 AM UTC
    @Scheduled(cron = "0 0 1 * * ?")
    public void scheduledDataUpdater() {
        dailyRateUpdater.getAndSaveDailyRate();
        rateVariationUpdater.updateRateVariation();
        log.info("Database updated");
    }
}
