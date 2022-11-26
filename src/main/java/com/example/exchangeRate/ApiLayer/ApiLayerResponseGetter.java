package com.example.exchangeRate.ApiLayer;

import com.example.exchangeRate.ApiLayer.ApiLayerParam;
import com.example.exchangeRate.ApiLayer.ApiLayerRequestMaker;
import com.example.exchangeRate.Interfaces.ResponseGetter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static java.sql.JDBCType.NULL;

@Component
public class ApiLayerResponseGetter implements ResponseGetter {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ApiLayerRequestMaker apiLayerRequestMaker;

    Logger LOGGER = LoggerFactory.getLogger(ApiLayerResponseGetter.class);
    public ResponseEntity<String> getResponse() {
        try {
            HttpEntity<Void> requestEntity = apiLayerRequestMaker.makeRequest();
            String url = ApiLayerParam.createUrl();
            return restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        }
        catch (RuntimeException e){
            LOGGER.error("Couldn't get response from API");
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }
}
