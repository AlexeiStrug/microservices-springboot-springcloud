package com.example.flightSchedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableHystrix
@EnableEurekaClient
public class FlightScheduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightScheduleApplication.class, args);
	}

}
