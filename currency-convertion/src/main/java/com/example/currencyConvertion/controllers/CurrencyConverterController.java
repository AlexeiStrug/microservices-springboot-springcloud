package com.example.currencyConvertion.controllers;

import com.example.currencyConvertion.models.CurrencyConverter;
import com.example.currencyConvertion.repository.CurrencyConverterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/currency")
public class CurrencyConverterController {

    @Autowired
    private CurrencyConverterRepository currencyConverterRepository;

    @GetMapping(value = "/from/{from}/to/{to}")
    public CurrencyConverter currencyConverter(@PathVariable String from, @PathVariable String to) {
        CurrencyConverter currencyConverter = new CurrencyConverter(null, from, to, null);
        Example<CurrencyConverter> currencyFilter = Example.of(currencyConverter);
        return currencyConverterRepository.findOne(currencyFilter).get();
    }
}
