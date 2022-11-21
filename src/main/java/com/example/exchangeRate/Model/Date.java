package com.example.exchangeRate.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Date {

    @JsonProperty("2012-05-01")
    private RateOfEurInUsd rate1;

    @JsonProperty("2012-05-02")
    private RateOfEurInUsd rate2;

    @JsonProperty("2012-05-03")
    private RateOfEurInUsd rate3;
}
