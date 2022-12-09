package com.example.exchangeRate.Repository;

import com.example.exchangeRate.Entity.DailyRate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DailyRateRepository extends CrudRepository<DailyRate, Long> {

    @Query(value = "select usd-lag(usd,1,0) over (order by date) from daily_rate order by date desc limit 1",
            nativeQuery = true)
    Optional<Double> getDifferenceBetweenLatestValues();
}
