package com.example.exchangeRate.service;

import com.example.exchangeRate.entity.RateVariation;
import com.example.exchangeRate.exceptions.NoDataFoundException;
import com.example.exchangeRate.repository.RateVariationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RateVariationService {
    @Autowired
    private RateVariationRepository rateVariationRepository;

    public RateVariation getRateVariation() {
        Optional<RateVariation> optionalRateVariation = rateVariationRepository.findById(1);
        return optionalRateVariation.orElseThrow(NoDataFoundException::new);
    }

    @Transactional
    public void updateIncrease() {
        rateVariationRepository.updateIncrease();
    }

    @Transactional
    public void updateDecrease() {
        rateVariationRepository.updateDecrease();
    }
}
