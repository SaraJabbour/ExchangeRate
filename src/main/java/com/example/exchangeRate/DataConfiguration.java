package com.example.exchangeRate;

import com.example.exchangeRate.Service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DataConfiguration {
    @Autowired
    ExchangeRateService exchangeRateService;

    @EventListener
    public void getExternalDataOnStartup(ApplicationReadyEvent event) {
        exchangeRateService.getExternalData();
    }
}
