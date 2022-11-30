package com.example.exchangeRate.Service;

import com.example.exchangeRate.Model.RateVariation;
import com.example.exchangeRate.Repository.RateVariationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RateVariationService {
    @Autowired
    private RateVariationRepository rateVariationRepository;

    public RateVariation getRateVariation(){
        Optional <RateVariation> optionalRateVariation= rateVariationRepository.findById(1);
        return optionalRateVariation.orElseThrow ();
    }
    public void saveRateVariation(RateVariation rateVariation) throws RuntimeException{
        rateVariationRepository.save(rateVariation);
    }

}
