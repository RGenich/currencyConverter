package com.test.currencyConverter.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.currencyConverter.models.Rate;
import com.test.currencyConverter.models.dto.XMLValCurs;
import com.test.currencyConverter.repositories.CurrencyRepository;
import com.test.currencyConverter.repositories.RateRepository;
import com.test.currencyConverter.services.interfaces.RateService;
import com.test.currencyConverter.utils.CurrencyLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class RateServiceImpl implements RateService {
    @Autowired
    RateRepository rateRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    CurrencyLoader currencyLoader;

    @Override
    public Rate save(Rate rate) {
        if (rateRepository.getRateByCurrencyIdAndDate(rate.getCurrency().getId(), rate.getDate()) == null) {
            return rateRepository.save(rate);
        }
        return rate;
    }

    @Override
    public Rate getRateByCurrencyIdAndDate(String id, LocalDate date) throws JsonProcessingException {
        Rate rate = rateRepository.getRateByCurrencyIdAndDate(id, date);
        if (rate == null) {
            loadRateByDate(date);
            rate = rateRepository.getRateByCurrencyIdAndDate(id, date);
        }
        return rate;
    }

    public void loadRateByDate(LocalDate date) throws JsonProcessingException {
        XMLValCurs xmlValCurs = currencyLoader.loadCurrency(date);
        currencyLoader.createRates(xmlValCurs, date);

    }
}
