package com.example.exchangeRate.Repository;

import com.example.exchangeRate.Model.DailyRate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyRateRepository extends CrudRepository<DailyRate,Long> {
}
