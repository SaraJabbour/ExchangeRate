package com.example.exchangeRate.Service;

import com.example.exchangeRate.Entity.DailyRate;
import com.example.exchangeRate.Repository.DailyRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DailyRateService {
    @Autowired
    private DailyRateRepository dailyRateRepository;

    public void saveDailyRate(DailyRate dailyRate) {
        dailyRateRepository.save(dailyRate);
    }

    public Double getDifferenceBetweenLatestValues() {
        return dailyRateRepository.getDifferenceBetweenLatestValues().orElseThrow();
    }
}
