package com.example.exchangeRate.controller;


import com.example.exchangeRate.entity.RateVariation;
import com.example.exchangeRate.service.RateVariationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@Log4j2
public class RateVariationController {
    @Autowired
    private RateVariationService exchangeVariationService;

    @GetMapping
    public String displayVariation(Model model) {
        RateVariation rateVariation = exchangeVariationService.getRateVariation();
        model.addAttribute("rateVariation", rateVariation);
        return "index";
    }
}
