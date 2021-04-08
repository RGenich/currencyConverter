package com.test.currencyConverter.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.currencyConverter.models.Calculation;
import com.test.currencyConverter.models.Currency;
import com.test.currencyConverter.models.Rate;
import com.test.currencyConverter.models.User;
import com.test.currencyConverter.repositories.CalculationRepository;
import com.test.currencyConverter.repositories.CurrencyRepository;
import com.test.currencyConverter.repositories.UserRepository;
import com.test.currencyConverter.services.interfaces.CalculationService;
import com.test.currencyConverter.services.interfaces.RateService;
import com.test.currencyConverter.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
public class CalculationServiceImpl implements CalculationService {
    @Autowired
    RateService rateService;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    CalculationRepository calculationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @Override
    public Float calculateOutput(Calculation calculation) throws JsonProcessingException {
        if (calculation.getDate() == null) {
            calculation.setDate(LocalDate.now());
        }
        Currency inputCurrency = currencyRepository.getCurrenciesByCharCode(calculation.getSelectInput());
        Currency outputCurrency = currencyRepository.getCurrenciesByCharCode(calculation.getSelectOutput());
        Integer inputNominal = inputCurrency.getNominal();

        Rate rateForInput = rateService.getRateByCurrencyIdAndDate(inputCurrency.getId(), calculation.getDate());
        Rate rateForOutput = rateService.getRateByCurrencyIdAndDate(outputCurrency.getId(), calculation.getDate());

        BigDecimal amountForInput = rateForInput.getValue();
        BigDecimal amountForOutput = rateForOutput.getValue();

        BigDecimal inputInRuble = amountForInput.multiply(new BigDecimal(calculation.getAmountInput() * inputNominal));
        BigDecimal result = inputInRuble.divide(amountForOutput, RoundingMode.DOWN);
        System.out.println(result);
        return result.floatValue();
    }

    @Override
    public Calculation save(Calculation calculation) {
        return calculationRepository.save(calculation);
    }

    public List<Calculation> getAllCalculations() {
        return calculationRepository.findAll();
    }

    @Override
    public List<Calculation> findCalculationsByUser(User principal) {
        User user = userService.getUserByLogin(principal.getLogin());
        return calculationRepository.findAllByUser(user);
    }

    public Float handleCalculation(User principal, Calculation calculation) throws JsonProcessingException {
        Float outputValue = calculateOutput(calculation);
        calculation.setAmountOutput(outputValue);
        calculation.setRequestDate(LocalDate.now());
        User user = userService.getUserByLogin(principal.getLogin());
        calculation.setUser(user);
        save(calculation);
        return outputValue;
    }
}
