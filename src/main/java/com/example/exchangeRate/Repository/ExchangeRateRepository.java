package com.example.exchangeRate.Repository;

import com.example.exchangeRate.Model.DailyRate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateRepository extends CrudRepository<DailyRate,Long> {
   // @Query(value="SELECT * ,CASE WHEN USD > LAG(USD,1,-1) over (order by date) THEN 'INCREASING' WHEN USD = LAG(USD,1,-1) over (order by date) THEN 'No change' ELSE 'DECREASING' END Variation FROM daily_rate",nativeQuery=true)
    //public void setVariations();
}
