package com.test.currencyConverter.repositories;

import com.test.currencyConverter.models.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository <Currency, Long> {
    Currency save (Currency valute);

    @Query ("select c.charCode from Currency c")
    List<String> getAllCurrencyNames();

    Currency getCurrenciesByCharCode(String charCode);

    List<Currency> findAll ();

}
