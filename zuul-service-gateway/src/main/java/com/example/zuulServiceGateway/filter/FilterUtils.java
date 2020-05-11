package com.example.zuulServiceGateway.filter;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import java.util.Random;

public class FilterUtils {
    public static final String FLIGHT_SCHEDULE_AUTH_TOKEN_KEY = "token";
    public static final String FLIGHT_SCHEDULE_AUTH_TOKEN_VALUE = "secret";

    public static final String FLIGHT_SCHEDULE_SERVICE_NAME = "flight-schedule-service";
    public static final String CURRENCY_CONVERSION_SERVICE_NAME = "currency-conversion-service";

    public static final int AUTH_FILTER_ORDER = FilterConstants.PRE_DECORATION_FILTER_ORDER + 1;
    public static final int ROUTER_FILTER_ORDER = AUTH_FILTER_ORDER + 1;
    public static final int RESPONSE_AUDIT_FILTER_ORDER = ROUTER_FILTER_ORDER + 1;

    public static boolean useNewRoute() {
        Random random = new Random();
        int value = random.nextInt(10);
        System.out.println(value);
        return value < 3;
    }

}
