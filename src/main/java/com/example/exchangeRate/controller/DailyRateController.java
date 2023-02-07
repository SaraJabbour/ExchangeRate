package com.example.exchangeRate.controller;

import com.example.exchangeRate.models.DailyRate;
import com.example.exchangeRate.service.DailyRateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/v1/dailyrate")
public class DailyRateController {
    @Autowired
    DailyRateService dailyRateService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public Iterable<DailyRate> getAllDailyRates() {
        return dailyRateService.getAllRates();
    }

    @GetMapping(path = "{date}")
    @PreAuthorize("hasRole('USER')")
    public DailyRate getRateOnDay(@PathVariable("date") String dateString) {
        return dailyRateService.getRateOnDay(dateString);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addNewDailyRate(@RequestBody @Valid DailyRate dailyRate) {
        return dailyRateService.saveDailyRate(dailyRate);
    }

    @PutMapping(path = "{dailyRateId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateExistingRate(@PathVariable("dailyRateId") Long dailyRateId,
                                                @RequestParam() Double usd) {
        return dailyRateService.updateExistingRate(dailyRateId, usd);
    }

    @DeleteMapping(path = "{dailyRateId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteDailyRate(@PathVariable("dailyRateId") Long dailyRateId) {
        return dailyRateService.deleteDailyRate(dailyRateId);
    }
}
