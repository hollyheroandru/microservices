package com.andrey.microservices.currencyexchangeservice.controllers;

import com.andrey.microservices.currencyexchangeservice.models.CurrencyExchange;
import com.andrey.microservices.currencyexchangeservice.repositories.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepository repository;

    @GetMapping(value = "/currency-exchange/from/{from}/to/{to}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from,
                                                  @PathVariable String to) {

        CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to)
                .orElseThrow(() -> new RuntimeException("Unable to find data for " + from + "to " + to));

        currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
        return currencyExchange;
    }
}
