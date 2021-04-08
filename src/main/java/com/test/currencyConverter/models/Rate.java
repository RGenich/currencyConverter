package com.test.currencyConverter.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.propertyeditors.CurrencyEditor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Data
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column
    private LocalDate date;
    @OneToOne
    @JoinColumn
    private Currency currency;
    @Column
    private BigDecimal value;

    public Rate(LocalDate date, BigDecimal value, Currency currency) {
        this.date = date;
        this.value = value;
        this.currency = currency;
    }
}
