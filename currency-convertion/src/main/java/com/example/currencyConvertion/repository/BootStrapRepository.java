package com.example.currencyConvertion.repository;

import com.example.currencyConvertion.models.CurrencyConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BootStrapRepository implements CommandLineRunner {

    private CurrencyConverterRepository currencyConverterRepository;

    @Autowired
    public BootStrapRepository(CurrencyConverterRepository currencyConverterRepository) {
        this.currencyConverterRepository = currencyConverterRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        CurrencyConverter currencyConverter1 = new CurrencyConverter(1L, "USD", "INR", BigDecimal.valueOf(74.10));
        CurrencyConverter currencyConverter2 = new CurrencyConverter(2L, "USD", "JPY", BigDecimal.valueOf(111.12));

        currencyConverterRepository.save(currencyConverter1);
        currencyConverterRepository.save(currencyConverter2);
    }
}
