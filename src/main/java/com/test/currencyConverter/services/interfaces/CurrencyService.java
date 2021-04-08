package com.test.currencyConverter.services.interfaces;

import com.test.currencyConverter.models.Currency;

import java.util.List;
import java.util.Map;

public interface CurrencyService {
    Currency save(Currency currency);

    Currency getCurrencyByCharCode(Currency currency);

    Map<String, String> getCurrenciesCharCodeAndNames();

    List<Currency> getAllCurrencies();
}
