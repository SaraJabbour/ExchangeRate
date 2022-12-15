package com.example.exchangeRate.exceptions;

import java.time.LocalDate;

public class RateAlreadyExistsException extends RuntimeException {
    public RateAlreadyExistsException(LocalDate date) {
        super("Rate for the date " + date + " already exists");
    }

}
