package com.example.zuulServiceGateway.filter.config;

import com.example.zuulServiceGateway.filter.AuthFilter;
import com.example.zuulServiceGateway.filter.ResponseAuditFilter;
import com.example.zuulServiceGateway.filter.RouteFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulFilter {

    @Bean
    public AuthFilter getAuthFilter() {
        return new AuthFilter();
    }

    @Bean
    public RouteFilter getRouterFilter() {
        return new RouteFilter();
    }

    @Bean
    public ResponseAuditFilter getResponseAuditFilter() {
        return new ResponseAuditFilter();
    }
}
