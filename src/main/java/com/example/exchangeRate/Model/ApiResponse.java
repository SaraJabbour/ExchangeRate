package com.example.exchangeRate.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;

import java.time.LocalDate;

public class ApiResponse {

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate start_date;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate end_date;
    private String base;
    @JsonProperty("rates")
    JSONObject jsondailyRates;

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public JSONObject getJsondailyRates() {
        return jsondailyRates;
    }

    public void setJsondailyRates(JSONObject jsondailyRates) {
        this.jsondailyRates = jsondailyRates;
    }
}
