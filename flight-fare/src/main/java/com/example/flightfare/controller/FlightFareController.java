package com.example.flightfare.controller;

import com.example.flightfare.controller.dto.CurrencyConversionDto;
import com.example.flightfare.model.FlightFare;
import com.example.flightfare.reposistory.FlightFareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/flight/{flightCode}/fare/{currency}")
public class FlightFareController {

    @Autowired
    private FlightFareRepository flightFareRepository;

    @Value("${base.currency:USD}")
    private String baseCurrency;

    @GetMapping
    public FlightFare getSingleTicketFare(@PathVariable String flightCode, @PathVariable String currency) {
        FlightFare flightFare = this.getFlightFare(flightCode);
        flightFare.setCurrency(currency);
        if (!baseCurrency.equals(currency)) {
            //conversion fare
            BigDecimal conversionRate = this.getConversion(currency);
            BigDecimal conversionFare = flightFare.getFlightFare().multiply(conversionRate);
            flightFare.setFlightFare(conversionFare);
        }
        return flightFare;

    }

    private FlightFare getFlightFare(String flightCode) {
        FlightFare flightFare = new FlightFare(null, flightCode, null);
        Example<FlightFare> flightFareExample = Example.of(flightFare);
        return flightFareRepository.findOne(flightFareExample).get();
    }

    private BigDecimal getConversion(String toCurrency) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> urlPathVariable = new HashMap<>();
        urlPathVariable.put("from", baseCurrency);
        urlPathVariable.put("to", toCurrency);
        CurrencyConversionDto conversion = restTemplate.getForEntity("http://localhost:9002/api/v1/currency/from/{from}/to/{to}", CurrencyConversionDto.class, urlPathVariable).getBody();
        if (conversion != null) {
            return conversion.getConversionRate();
        }
        return null;
    }
}
