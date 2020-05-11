package com.example.currencyConvertion.repository;

import com.example.currencyConvertion.models.CurrencyConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyConverterRepository extends JpaRepository<CurrencyConverter, Long> {
}
