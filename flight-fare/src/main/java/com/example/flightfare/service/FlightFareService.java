package com.example.flightfare.service;

import com.example.flightfare.config.CurrencyConversionServiceProxy;
import com.example.flightfare.controller.dto.CurrencyConversionDto;
import com.example.flightfare.model.FlightFare;
import com.example.flightfare.reposistory.FlightFareRepository;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class FlightFareService {

    private final EurekaClient eurekaClient;
    private final CurrencyConversionServiceProxy feignProxy;
    private final RestTemplate restTemplate;
    private final FlightFareRepository flightFareRepository;

    @Value("${use.eureka.client}")
    private boolean useEurekaClient;
    @Value("${use.ribbon.back.rest.template}")
    private boolean useRibbonBackRestTemplate;
    @Value("${use.feign.proxy}")
    private boolean useFeignProxy;
    @Value("${base.currency:USD}")
    private String baseCurrency;

    @Autowired
    public FlightFareService(EurekaClient eurekaClient, CurrencyConversionServiceProxy feignProxy, RestTemplate restTemplate, FlightFareRepository flightFareRepository) {
        this.eurekaClient = eurekaClient;
        this.feignProxy = feignProxy;
        this.restTemplate = restTemplate;
        this.flightFareRepository = flightFareRepository;
    }

    public void sleepRandomly() {
        Random random = new Random();
        int rnd = random.nextInt(3) + 1;
        if (rnd == 3) {
            System.out.println("It is a chance for demostraing Hystrix action.");
            try {
                System.out.println("Start sleeping... " + System.currentTimeMillis());
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Hystrix thread interupted... " + System.currentTimeMillis());
                e.printStackTrace();
            }
        }
    }

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "7000")
    })
    public FlightFare getFlightFare(String flightCode) {
        FlightFare flightFare = new FlightFare(null, flightCode, null);
        Example<FlightFare> flightFareExample = Example.of(flightFare);
        sleepRandomly();
        return flightFareRepository.findOne(flightFareExample).get();
    }

    public BigDecimal getConversion(String toCurrency) {
        if (useEurekaClient) {
            Application app = eurekaClient.getApplication("currency-conversion-service");
            List<InstanceInfo> instances = app.getInstances();

            String serviceUrl = String.format("%sapi/v1/currency/from/{from}/to/{to}", instances.get(0).getHomePageUrl());

            RestTemplate restTemplate = new RestTemplate();
            return getBigDecimalFromApi(restTemplate, toCurrency, serviceUrl);
        } else if (useRibbonBackRestTemplate) {
            return getBigDecimalFromApi(this.restTemplate, toCurrency, "http://currency-conversion-service/api/v1/currency/from/{from}/to/{to}");
        } else if (useFeignProxy) {
            CurrencyConversionDto conversion = feignProxy.currencyConverter(baseCurrency, toCurrency);
            return conversion.getConversionRate();
        } else {
            RestTemplate restTemplate = new RestTemplate();
            return getBigDecimalFromApi(restTemplate, toCurrency, "http://localhost:9002/api/v1/currency/from/{from}/to/{to}");
        }
    }

    public BigDecimal getBigDecimalFromApi(RestTemplate restTemplate, String toCurrency, String serviceUrl) {
        Map<String, String> urlPathVariable = new HashMap<>();
        urlPathVariable.put("from", baseCurrency);
        urlPathVariable.put("to", toCurrency);
        CurrencyConversionDto conversion = restTemplate.getForEntity(serviceUrl, CurrencyConversionDto.class, urlPathVariable).getBody();
        if (conversion != null) {
            return conversion.getConversionRate();
        }
        return null;
    }
}
