package com.test.currencyConverter.services;

import com.test.currencyConverter.models.Currency;
import com.test.currencyConverter.repositories.CurrencyRepository;
import com.test.currencyConverter.services.interfaces.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;

    @Override
    public Currency save(Currency currency) {
        if (getCurrencyByCharCode(currency) == null) {
            return currencyRepository.save(currency);
        } else return currency;
    }

    @Override
    public Currency getCurrencyByCharCode(Currency currency) {
        return currencyRepository.getCurrenciesByCharCode(currency.getCharCode());
    }

    @Override
    public Map<String, String> getCurrenciesCharCodeAndNames() {
        List<Currency> currencyList = getAllCurrencies();
        return currencyList.stream().collect(Collectors.toMap(Currency::getName, Currency::getCharCode));
    }

    @Override
    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }
}
