package com.example.exchangeRate.Repository;

import com.example.exchangeRate.Model.RateOfEurInUsd;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateRepository extends CrudRepository<RateOfEurInUsd,Long> {
}
