package com.example.exchangeRate.exceptions;

public class RateNotFoundException extends RuntimeException {
    public RateNotFoundException() {
        super("Rate doesn't exist");
    }
}
