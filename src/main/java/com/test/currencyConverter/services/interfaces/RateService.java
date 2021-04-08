package com.test.currencyConverter.services.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.currencyConverter.models.Rate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface RateService {
    Rate save(Rate rate);

    Rate getRateByCurrencyIdAndDate(String id, LocalDate date) throws JsonProcessingException;

    void loadRateByDate(LocalDate date) throws JsonProcessingException;
}
