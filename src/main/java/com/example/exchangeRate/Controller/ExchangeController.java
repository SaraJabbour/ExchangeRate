package com.example.exchangeRate.Controller;


import com.example.exchangeRate.Entity.RateVariation;
import com.example.exchangeRate.Service.RateVariationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class ExchangeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeController.class);
    @Autowired
    private RateVariationService exchangeVariationService;

    @GetMapping
    public String displayVariation(Model model) {
        try {
            RateVariation rateVariation = exchangeVariationService.getRateVariation();
            model.addAttribute("rateVariation", rateVariation);
            return "index";
        } catch (Exception e) {
            LOGGER.error("Couldn't fetch rate variation");
            return "error";
        }
    }
}
