package com.test.currencyConverter.repositories;

import com.test.currencyConverter.models.Calculation;
import com.test.currencyConverter.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalculationRepository extends JpaRepository<Calculation, Long> {
    Calculation save(Calculation calculation);

    List<Calculation> findAllByUser(User user);
}
