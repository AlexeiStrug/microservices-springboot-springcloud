package com.example.flightfare.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class FlightFare {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String flightCode;
    private BigDecimal flightFare;
    private String currency;

    public FlightFare() {
    }

    public FlightFare(Long id, String flightCode, BigDecimal flightFare) {
        this.id = id;
        this.flightCode = flightCode;
        this.flightFare = flightFare;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public BigDecimal getFlightFare() {
        return flightFare;
    }

    public void setFlightFare(BigDecimal flightFare) {
        this.flightFare = flightFare;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightFare that = (FlightFare) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(flightCode, that.flightCode) &&
                Objects.equals(flightFare, that.flightFare) &&
                Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flightCode, flightFare, currency);
    }

    @Override
    public String toString() {
        return "FlightFare{" +
                "id=" + id +
                ", flightCode='" + flightCode + '\'' +
                ", flightFare=" + flightFare +
                ", currency='" + currency + '\'' +
                '}';
    }
}
