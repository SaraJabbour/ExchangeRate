package com.example.exchangeRate.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class ResponseGetterService {
    Logger LOGGER = LoggerFactory.getLogger(ResponseGetterService.class);
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RequestMakerService requestMakerService;

    @Value("${URL}")
    private String baseUrl;
    @Value("${Symbols}")
    private String symbol;

    public String createUrl() {
        return baseUrl + LocalDate.now() + "?symbols=" + symbol;
    }

    public ResponseEntity<String> getResponse() {
        System.out.println(createUrl());
        HttpEntity<Void> requestEntity = requestMakerService.makeRequest();
        String url = createUrl();
        return restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        /*} catch (RuntimeException e) {
            LOGGER.error("Couldn't get response from API");
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);

        }*/
    }
}
