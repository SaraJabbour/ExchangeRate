package com.example.exchangeRate.Service;

import com.example.exchangeRate.Model.DailyRate;
import com.example.exchangeRate.Repository.DailyRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyRateService {
    @Autowired
    private DailyRateRepository dailyRateRepository;

    public void saveDailyRates(List<DailyRate> dailyRates) throws RuntimeException {
        dailyRateRepository.saveAll(dailyRates);
    }

}
