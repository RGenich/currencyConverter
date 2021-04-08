package com.test.currencyConverter.services.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.currencyConverter.models.Calculation;
import com.test.currencyConverter.models.User;

import java.util.List;

public interface CalculationService {
    Float calculateOutput(Calculation calculation) throws JsonProcessingException;

    Calculation save (Calculation calculation);

//    List<Calculation> getAllCalculations();

    List<Calculation> findCalculationsByUser(User user);

}
