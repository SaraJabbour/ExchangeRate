package com.example.exchangeRate.ApiLayer;

import java.time.LocalDate;
//Need to be moved to properties
public class ApiLayerParam {
    private static final String baseRate="EUR";
    private static final String symbol="USD";
    private static final LocalDate startDate=LocalDate.of(2021,1,1);
    private static final LocalDate endDate=LocalDate.of(2021,12,31);
    private static final String endpoint="timeseries";
    private static final String baseUrl="https://api.apilayer.com/exchangerates_data/";

    public static String createUrl(){
        return baseUrl+endpoint+"?start_date="+startDate+"&end_date="+endDate+"&symbols="+symbol;

    }

}
