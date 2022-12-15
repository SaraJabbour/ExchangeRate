package com.example.exchangeRate.exceptions;

public class InsufficientDataException extends RuntimeException {
    public InsufficientDataException() {
        super("Insufficient data in table");
    }
}
