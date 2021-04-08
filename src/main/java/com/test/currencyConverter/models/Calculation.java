package com.test.currencyConverter.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Calculations")
public class Calculation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private LocalDate date;
    @Column
    private LocalDate requestDate;
    @Column
    private Float amountInput;
    @Column
    private Float amountOutput;
    @Column
    private String selectInput;
    @Column
    private String selectOutput;
    @ManyToOne
    @JoinColumn
    private User user;
}
