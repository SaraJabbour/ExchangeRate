package com.example.exchangeRate.Entity;

import javax.persistence.*;


@Entity(name = "rateVariation")
public class RateVariation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "integer default 0")
    private Integer increases;
    @Column(columnDefinition = "integer default 0")
    private Integer decreases;

    public RateVariation(Integer increases, Integer decreases) {
        this.increases = increases;
        this.decreases = decreases;
    }

    public RateVariation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIncreases() {
        return increases;
    }

    public void setIncreases(Integer increases) {
        this.increases = increases;
    }

    public Integer getDecreases() {
        return decreases;
    }

    public void setDecreases(Integer decreases) {
        this.decreases = decreases;
    }
}
