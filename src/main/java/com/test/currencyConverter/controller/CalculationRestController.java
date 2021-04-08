package com.test.currencyConverter.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.currencyConverter.models.Calculation;
import com.test.currencyConverter.models.User;
import com.test.currencyConverter.services.CalculationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calculating")
public class CalculationRestController {

    @Autowired
    CalculationServiceImpl calculationService;

    @PostMapping("/calculate")
    public Float calculate (@RequestBody Calculation calculation, @AuthenticationPrincipal() User principal) throws JsonProcessingException {
        return calculationService.handleCalculation(principal, calculation);
    }

    @GetMapping("/getHistory")
    public List<Calculation> getAllCalculations(@AuthenticationPrincipal() User principal){
        return calculationService.findCalculationsByUser(principal);
    }
}
