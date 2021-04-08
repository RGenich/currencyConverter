package com.test.currencyConverter.repositories;

import com.test.currencyConverter.models.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface RateRepository extends JpaRepository <Rate, Long> {
    Rate save (Rate rate);

    @Query("select c.charCode from Currency c")
    Long getAmountBycharCode (String charCode);

    Rate getRateByCurrencyIdAndDate(String id, LocalDate date);
}
