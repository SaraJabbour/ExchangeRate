package com.example.exchangeRate;


import com.example.exchangeRate.Model.DailyRate;
import com.example.exchangeRate.Model.RateVariation;
import com.example.exchangeRate.Service.DailyRateService;
import com.example.exchangeRate.Service.RateVariationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;


import java.util.List;

@Configuration
public class DataConfiguration {
    @Autowired
    private ApiLayerResponseGetter apiLayerResponseGetter;

    @Autowired
    private ApiLayerParser apiLayerParser;

    @Autowired
    private DailyRateService dailyRateService;
    @Autowired
    private RateVariationService rateVariationService;

    private List<DailyRate> dailyRates;

    // TODO Exception Handling
    @EventListener
    public void populateTablesOnStartup(ApplicationReadyEvent event){
        populateDailyRates();
        populateRateVariation();
    }
    public void populateDailyRates(){
        try {
            ResponseEntity<String> responseEntity = apiLayerResponseGetter.getResponse();
            dailyRates = apiLayerParser.parseResponse(responseEntity);
            dailyRateService.saveDailyRates(dailyRates);
        }
        catch(NullPointerException e){
            e.printStackTrace();
        }
    }

    public void populateRateVariation(){
        RateVariation rateVariation=getRateVariation();
        rateVariationService.saveRateVariation(rateVariation);
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
        }
        return new RateVariation(increases,decreases);
    }

}
