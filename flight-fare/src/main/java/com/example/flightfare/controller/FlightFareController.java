package com.example.flightfare.controller;

import com.example.flightfare.model.FlightFare;
import com.example.flightfare.service.FlightFareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/api/v1/flight/{flightCode}/fare/{currency}")
public class FlightFareController {

    @Autowired
    private FlightFareService flightFareService;

    @Value("${base.currency:USD}")
    private String baseCurrency;

    @GetMapping
    public FlightFare getSingleTicketFare(@PathVariable String flightCode, @PathVariable String currency) {
        FlightFare flightFare = flightFareService.getFlightFare(flightCode);
        flightFare.setCurrency(currency);
        if (!baseCurrency.equals(currency)) {
            //conversion fare
            BigDecimal conversionRate = flightFareService.getConversion(currency);
            BigDecimal conversionFare = flightFare.getFlightFare().multiply(conversionRate);
            flightFare.setFlightFare(conversionFare);
        }
        return flightFare;
    }


}
