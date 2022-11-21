package com.example.exchangeRate.Model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ApiResponse {

    @JsonProperty("rates")
    private Date dailyRates;
}
