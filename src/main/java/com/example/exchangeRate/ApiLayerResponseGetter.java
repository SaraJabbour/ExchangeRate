package com.example.exchangeRate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// TODO check exceptions
@Component
public class ApiLayerResponseGetter implements ResponseGetter{
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ApiLayerRequestMaker apiLayerRequestMaker;

    public ResponseEntity<String> getResponse() {
        HttpEntity<Void> requestEntity = apiLayerRequestMaker.makeRequest();
        String url= ApiLayerParamConfiguration.createUrl();
        return restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
    }
}
