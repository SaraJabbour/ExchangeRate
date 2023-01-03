package com.example.exchangeRate.service;

import com.example.exchangeRate.models.DailyRate;
import org.apache.commons.validator.GenericValidator;

public class DailyRateValidator {
    public static boolean isDailyRate(DailyRate dailyRate) {
        if (dailyRate == null)
            return false;
        return isDate(dailyRate.getDate().toString()) && isDouble(dailyRate.getUsd());
    }

    public static boolean isDate(String date) {
        return GenericValidator.isDate(date, "yyyy-mm-dd", true);
    }

    public static boolean isDouble(Double value) {
        if (value == null)
            return false;
        return !value.isInfinite() && !value.isNaN();
    }
}
