package com.example.exchangeRate.controller;

import com.example.exchangeRate.entity.DailyRate;
import com.example.exchangeRate.service.DailyRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/dailyrate")
public class DailyRateController {

    @Autowired
    DailyRateService dailyRateService;

    @GetMapping
    public Iterable<DailyRate> getAllDailyRates() {
        return dailyRateService.getAllRates();
    }

    @GetMapping(path = "{date}")
    public DailyRate getRateOnDay(@PathVariable("date") String dateString) {
        return dailyRateService.getRateOnDay(dateString);
    }

    @PostMapping
    public void addNewDailyRate(@RequestBody DailyRate dailyRate) {
        dailyRateService.saveDailyRate(dailyRate);
    }

    @PutMapping(path = "{dailyRateId}")
    public void updateExistingRate(@PathVariable("dailyRateId") Long dailyRateId,
                                   @RequestParam() Double usd) {
        dailyRateService.updateExistingRate(dailyRateId, usd);
    }

    @DeleteMapping(path = "{dailyRateId}")
    public void deleteDailyRate(@PathVariable("dailyRateId") Long dailyRateId) {
        dailyRateService.deleteDailyRate(dailyRateId);
    }
}
