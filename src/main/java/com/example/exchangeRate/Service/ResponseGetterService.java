package com.example.exchangeRate.Service;

import com.example.exchangeRate.ApiLayer.ApiLayerParam;
import com.example.exchangeRate.Interfaces.ResponseGetter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ResponseGetterService implements ResponseGetter {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RequestMakerService requestMakerService;
    Logger LOGGER = LoggerFactory.getLogger(ResponseGetterService.class);
    public ResponseEntity<String> getResponse() {
        try {
            HttpEntity<Void> requestEntity = requestMakerService.makeRequest();
            String url = ApiLayerParam.createUrl();
            return restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        }
        catch (RuntimeException e){
            LOGGER.error("Couldn't get response from API");
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);

        }
    }
}
