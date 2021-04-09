package com.test.currencyConverter.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.test.currencyConverter.models.Currency;
import com.test.currencyConverter.models.Rate;
import com.test.currencyConverter.models.dto.XMLValCurs;
import com.test.currencyConverter.models.dto.XMLValute;
import com.test.currencyConverter.services.interfaces.CurrencyService;
import com.test.currencyConverter.services.interfaces.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class CurrencyLoader {
    private final static String CURRENCY_URL = "http://www.cbr.ru/scripts/XML_daily.asp";
    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private RateService rateService;

    public XMLValCurs loadCurrency(LocalDate date) throws JsonProcessingException {
        String additionalUrlParam = "";
        if (date != null) {
            additionalUrlParam = "/?date_req=" + localDateToString(date);
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_XML);

        RestTemplate restTemplate = new RestTemplate();
        XMLValCurs xmlData = restTemplate.getForObject(CURRENCY_URL + additionalUrlParam, XMLValCurs.class);

        return xmlData;
    }

    public void createRates(XMLValCurs valCurs, LocalDate date) {

        List<Currency> currencyList = currencyService.getAllCurrencies();

        for (XMLValute valute : valCurs.getValutes()
        ) {
            Currency currency = new Currency(valute);
            if (!currencyList.contains(currency)) {
                currencyService.save(currency);
            }
            Rate rate = new Rate(date, valute.getValue(), currency);
            rateService.save(rate);
        }
        initRussianRuble(date);
    }

    public List<Currency> createCurrencies(XMLValCurs valCurs) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate day = LocalDate.parse(valCurs.getDate(), formatter);
        List<Currency> currencyList = new ArrayList<>();
        for (XMLValute valute : valCurs.getValutes()
        ) {
            Currency currency = new Currency(valute);
            currencyService.save(currency);
            currencyList.add(currency);

        }
        initRussianRuble(day);
        return currencyList;
    }

    public void initRussianRuble(LocalDate date) {
        Currency rubCurrency = new Currency("R00001", "RUR", "Российский рубль", 1, 643);
        Rate rubRate = new Rate(date, BigDecimal.ONE, rubCurrency);
        currencyService.save(rubCurrency);
        rateService.save(rubRate);
    }

    public String localDateToString(LocalDate localDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return localDate.format(dateTimeFormatter);
    }
}
