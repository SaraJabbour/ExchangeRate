package com.example.exchangeRate.Controller;



import com.example.exchangeRate.Model.RateVariation;
import com.example.exchangeRate.Service.ExchangeVariationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.Optional;


@Controller
@RequestMapping("/")
public class ExchangeController {
    @Autowired
    private ExchangeVariationService exchangeVariationService;
    @GetMapping
    public String displayVariation(Model model){
        RateVariation rateVariation=exchangeVariationService.getRateVariation();
        model.addAttribute("rateVariation",rateVariation);
        return "index";
    }
}
