package com.example.exchangeRate.Repository;

import com.example.exchangeRate.Model.Rate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateRepository extends CrudRepository<Rate,Long> {
}
