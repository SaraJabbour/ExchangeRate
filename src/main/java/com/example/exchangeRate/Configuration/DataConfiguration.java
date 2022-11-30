package com.example.exchangeRate.Configuration;


import com.example.exchangeRate.Service.ResponseConverterService;
import com.example.exchangeRate.Service.ResponseGetterService;
import com.example.exchangeRate.Model.DailyRate;
import com.example.exchangeRate.Model.RateVariation;
import com.example.exchangeRate.Service.DailyRateService;
import com.example.exchangeRate.Service.RateVariationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;


import java.util.List;

@Configuration
public class DataConfiguration {
    @Autowired
    private ResponseGetterService responseGetterService;
    @Autowired
    private ResponseConverterService responseConverterService;
    @Autowired
    private DailyRateService dailyRateService;
    @Autowired
    private RateVariationService rateVariationService;

    private List<DailyRate> dailyRates;

    Logger LOGGER = LoggerFactory.getLogger(DataConfiguration.class);

    @EventListener
    public void populateTablesOnStartup(ApplicationReadyEvent event){
        populateDailyRates();
        populateRateVariation();
    }
    public void populateDailyRates(){
        try {
            ResponseEntity<String> responseEntity = responseGetterService.getResponse();
            dailyRates = responseConverterService.convertResponse(responseEntity);
            dailyRateService.saveDailyRates(dailyRates);
        }
        catch(RuntimeException e){
            LOGGER.error("Couldn't get, insert data");
        }
    }

    public void populateRateVariation(){
        try {
            RateVariation rateVariation = getRateVariation();
            rateVariationService.saveRateVariation(rateVariation);
        }
        catch (RuntimeException e){
            LOGGER.error("Couldn't get, insert data");
            throw e;
        }
    }

    private RateVariation getRateVariation() {
        Double previousValue=dailyRates.get(0).getUSD();
        Integer increases=0,decreases=0;
        for ( int i = 1; i < dailyRates.size();i++){
            Double currentValue=dailyRates.get(i).getUSD();
            if(previousValue < currentValue)
                increases++;
            else if (previousValue > currentValue)
                decreases++;
            previousValue=currentValue;
        }
        return new RateVariation(increases,decreases);
    }

}
