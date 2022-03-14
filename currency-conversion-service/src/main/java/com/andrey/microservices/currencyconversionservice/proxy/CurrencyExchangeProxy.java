package com.andrey.microservices.currencyconversionservice.proxy;

import com.andrey.microservices.currencyconversionservice.models.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {

    @GetMapping(value = "/currency-exchange/from/{from}/to/{to}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CurrencyConversion retrieveExchangeValue(@PathVariable String from,
                                                    @PathVariable String to);
}
