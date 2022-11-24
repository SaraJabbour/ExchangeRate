package com.example.exchangeRate.Service;

import com.example.exchangeRate.Model.RateVariation;
import com.example.exchangeRate.Repository.ExchangeVariationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExchangeVariationService {
    @Autowired
    private ExchangeVariationRepository exchangeVariationRepository;

    public RateVariation getRateVariation() {
        Optional <RateVariation> optionalRateVariation= exchangeVariationRepository.findById(1);
        return optionalRateVariation.orElse(new RateVariation(0,0));
    }

}
