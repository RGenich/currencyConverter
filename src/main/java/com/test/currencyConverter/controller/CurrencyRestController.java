package com.test.currencyConverter.controller;

import com.test.currencyConverter.services.interfaces.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("api/currencies")
public class CurrencyRestController {
    @Autowired
    CurrencyService currencyService;

    @GetMapping("/getAllCurrenciesNames")
    public Map<String, String> getCurrencies () {
        return currencyService.getCurrenciesCharCodeAndNames ();
    }

}
