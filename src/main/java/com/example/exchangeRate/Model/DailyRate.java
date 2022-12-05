package com.example.exchangeRate.Model;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity(name = "dailyRate")
public class DailyRate {
    @Id
    private LocalDate date;
    private Double USD;

    public DailyRate() {

    }

    public DailyRate(LocalDate date, Double value) {
        this.date = date;
        this.USD = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getUSD() {
        return USD;
    }

    public void setUSD(Double USD) {
        this.USD = USD;
    }
}
