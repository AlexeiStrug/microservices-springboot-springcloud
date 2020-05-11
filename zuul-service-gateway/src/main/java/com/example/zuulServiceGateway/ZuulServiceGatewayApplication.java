package com.example.zuulServiceGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

@SpringBootApplication
@EnableZuulProxy
//@EnableZuulServer
public class ZuulServiceGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulServiceGatewayApplication.class, args);
	}

}
