package com.example.flightfare.controller.dto;

import java.math.BigDecimal;

public class CurrencyConversionDto {

    private String currencyFrom;
    private String currencyTo;
    private BigDecimal conversionRate;

    public CurrencyConversionDto() {
    }

    public CurrencyConversionDto(String currencyFrom, String currencyTo, BigDecimal conversionRate) {
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.conversionRate = conversionRate;
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
}
