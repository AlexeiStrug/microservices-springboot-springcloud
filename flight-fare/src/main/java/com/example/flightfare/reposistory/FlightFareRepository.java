package com.example.flightfare.reposistory;

import com.example.flightfare.model.FlightFare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightFareRepository extends JpaRepository<FlightFare, Long> {
}
