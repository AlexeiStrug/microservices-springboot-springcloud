package com.example.flightfare.config;

import com.example.flightfare.controller.dto.CurrencyConversionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "currency-conversion-service")
@RequestMapping(value = "/api/v1/currency")
public interface CurrencyConversionServiceProxy {

    @GetMapping(value = "/from/{from}/to/{to}")
    CurrencyConversionDto currencyConverter(@PathVariable String from, @PathVariable String to);

}
