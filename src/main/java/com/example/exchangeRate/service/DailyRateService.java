package com.example.exchangeRate.service;

import com.example.exchangeRate.entity.DailyRate;
import com.example.exchangeRate.exceptions.InsufficientDataException;
import com.example.exchangeRate.exceptions.RateAlreadyExistsException;
import com.example.exchangeRate.exceptions.RateNotFoundException;
import com.example.exchangeRate.repository.DailyRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class DailyRateService {
    @Autowired
    private DailyRateRepository dailyRateRepository;


    public void saveDailyRate(DailyRate dailyRate) {
        if (!DailyRateValidator.isDailyRate(dailyRate))
            throw new IllegalArgumentException("Wrong format");
        if (dailyRateRepository.findByDate(dailyRate.getDate()).isPresent())
            throw new RateAlreadyExistsException(dailyRate.getDate());
        dailyRateRepository.save(dailyRate);
    }


    public Double getDifferenceBetweenLatestValues() {
        return dailyRateRepository.getDifferenceBetweenLatestValues().orElseThrow(InsufficientDataException::new);
    }

    public Iterable<DailyRate> getAllRates() {
        return dailyRateRepository.findAll();
    }

    public void deleteDailyRate(Long dailyRateId) {
        dailyRateRepository.findById(dailyRateId).orElseThrow(RateNotFoundException::new);
        dailyRateRepository.deleteById(dailyRateId);
    }

    @Transactional
    public void updateExistingRate(Long dailyRateId, Double usd) {
        DailyRate dailyRate = dailyRateRepository.findById(dailyRateId).orElseThrow(RateNotFoundException::new);
        dailyRate.setUsd(usd);
    }

    public DailyRate getRateOnDay(String dateString) {
        if (!DailyRateValidator.isDate(dateString))
            throw new IllegalArgumentException("Date should follow the YYYY-MM-DD format");
        LocalDate date = LocalDate.parse(dateString);
        return dailyRateRepository.findByDate(date).orElseThrow(RateNotFoundException::new);

    }
}
