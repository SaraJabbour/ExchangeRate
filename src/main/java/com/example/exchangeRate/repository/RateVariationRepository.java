package com.example.exchangeRate.repository;

import com.example.exchangeRate.entity.RateVariation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RateVariationRepository extends CrudRepository<RateVariation, Integer> {
    @Modifying
    @Query(value = "UPDATE rate_variation SET increases = increases + 1;", nativeQuery = true)
    void updateIncrease();

    @Modifying
    @Query(value = "UPDATE rate_variation SET decreases = decreases + 1;", nativeQuery = true)
    void updateDecrease();
}
