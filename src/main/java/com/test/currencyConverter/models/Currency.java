package com.test.currencyConverter.models;

import com.test.currencyConverter.models.dto.XMLValute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Currency {
    @Id
    private String id;
    @Column
    private String charCode;
    @Column
    private String name;
    @Column
    private Integer nominal;
    @Column
    private Integer numCode;

    public Currency (XMLValute valute) {
        this.id = valute.getId();
        this.charCode = valute.getCharCode();
        this.name = valute.getName();
        this.nominal = valute.getNominal();
        this.numCode = valute.getNumCode();
    }
}
