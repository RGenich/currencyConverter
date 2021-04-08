package com.test.currencyConverter.configs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.currencyConverter.models.dto.XMLValCurs;
import com.test.currencyConverter.utils.CurrencyLoader;
import com.test.currencyConverter.utils.UserInitializer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
@AllArgsConstructor
public class DataInitializer {
    @Autowired
    CurrencyLoader currencyLoader;
    @Autowired
    UserInitializer userInitializer;

    @PostConstruct
    public void init() throws JsonProcessingException {
        XMLValCurs xmlValCurs = currencyLoader.loadCurrency(LocalDate.now());
        currencyLoader.createCurrencies(xmlValCurs);
        currencyLoader.createRates(xmlValCurs, LocalDate.now());
        userInitializer.initUsers();
    }
}
