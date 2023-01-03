package com.example.exchangeRate.service;

import com.example.exchangeRate.exceptions.InsufficientDataException;
import com.example.exchangeRate.exceptions.RateAlreadyExistsException;
import com.example.exchangeRate.exceptions.RateNotFoundException;
import com.example.exchangeRate.models.DailyRate;
import com.example.exchangeRate.repository.DailyRateRepository;
import com.example.exchangeRate.security.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class DailyRateService {
    @Autowired
    private DailyRateRepository dailyRateRepository;


    public ResponseEntity<?> saveDailyRate(DailyRate dailyRate) {
        if (!DailyRateValidator.isDailyRate(dailyRate))
            throw new IllegalArgumentException("Wrong format");
        if (dailyRateRepository.findByDate(dailyRate.getDate()).isPresent())
            throw new RateAlreadyExistsException(dailyRate.getDate());
        dailyRateRepository.save(dailyRate);
        return ResponseEntity.ok(new MessageResponse("Daily rate successfully added"));
    }


    public Double getDifferenceBetweenLatestValues() throws InsufficientDataException {
        return dailyRateRepository.getDifferenceBetweenLatestValues().orElseThrow(() -> new InsufficientDataException());
    }

    public Iterable<DailyRate> getAllRates() {
        return dailyRateRepository.findAll();
    }

    public ResponseEntity<?> deleteDailyRate(Long dailyRateId) throws RateNotFoundException {
        dailyRateRepository.findById(dailyRateId).orElseThrow(() -> new RateNotFoundException());
        dailyRateRepository.deleteById(dailyRateId);
        return ResponseEntity.ok(new MessageResponse("Rate with id " + dailyRateId + " deleted successfully"));
    }

    @Transactional
    public ResponseEntity<?> updateExistingRate(Long dailyRateId, Double usd) throws RateNotFoundException {
        DailyRate dailyRate = dailyRateRepository.findById(dailyRateId).orElseThrow(() -> new RateNotFoundException());
        dailyRate.setUsd(usd);
        return ResponseEntity.ok(new MessageResponse("Rate with id " + dailyRateId + " updated successfully"));
    }

    public DailyRate getRateOnDay(String dateString) throws RateNotFoundException, IllegalArgumentException {
        if (!DailyRateValidator.isDate(dateString))
            throw new IllegalArgumentException("Date should follow the YYYY-MM-DD format");
        LocalDate date = LocalDate.parse(dateString);
        return dailyRateRepository.findByDate(date).orElseThrow(() -> new RateNotFoundException());

    }
}
