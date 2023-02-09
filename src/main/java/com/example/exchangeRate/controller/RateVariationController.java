package com.example.exchangeRate.controller;


import com.example.exchangeRate.models.RateVariation;
import com.example.exchangeRate.service.RateVariationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RateVariationController {
    @Autowired
    private RateVariationService exchangeVariationService;

    @GetMapping
    public String displayVariation(Model model) {
        try {
            RateVariation rateVariation = exchangeVariationService.getRateVariation();
            model.addAttribute("rateVariation", rateVariation);
            System.out.println(rateVariation);
            return "index";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }
}
