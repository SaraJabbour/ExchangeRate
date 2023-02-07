package com.example.exchangeRate.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity(name = "rateVariation")
public class RateVariation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "integer default 0")
    private Integer increases;
    @Column(columnDefinition = "integer default 0")
    private Integer decreases;


}
