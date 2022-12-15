package com.example.exchangeRate.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;


@Data
@Entity(name = "dailyRate")
@NoArgsConstructor
@RequiredArgsConstructor
public class DailyRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private LocalDate date;

    @NonNull
    private Double usd;
}
