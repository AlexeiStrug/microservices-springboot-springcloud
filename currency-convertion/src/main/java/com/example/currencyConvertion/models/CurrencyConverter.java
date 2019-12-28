package com.example.currencyConvertion.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class CurrencyConverter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String currencyFrom;
    private String currencyTo;
    private BigDecimal conversionRate;

    public CurrencyConverter() {
    }

    public CurrencyConverter(Long id, String currencyFrom, String currencyTo, BigDecimal conversionRate) {
        this.id = id;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.conversionRate = conversionRate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    public BigDecimal getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(BigDecimal conversionRate) {
        this.conversionRate = conversionRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyConverter that = (CurrencyConverter) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(currencyFrom, that.currencyFrom) &&
                Objects.equals(currencyTo, that.currencyTo) &&
                Objects.equals(conversionRate, that.conversionRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, currencyFrom, currencyTo, conversionRate);
    }

    @Override
    public String toString() {
        return "CurrencyConverter{" +
                "id=" + id +
                ", currencyFrom='" + currencyFrom + '\'' +
                ", currencyTo='" + currencyTo + '\'' +
                ", conversionRate=" + conversionRate +
                '}';
    }
}
