package com.andrey.microservices.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route( predicate -> predicate.path("/get")
                        .filters(f -> f
                                .addRequestHeader("MyHeader", "MyURI")
                                .addRequestParameter("Param", "MyValue"))
                        .uri("http://httpbin.org:80"))
                .route(predicateSpec -> predicateSpec
                        .path("/currency-exchange/**")
                        .uri("lb://currency-exchange"))
                .route(predicateSpec -> predicateSpec
                        .path("/currency-conversion-feign/**")
                        .uri("lb://currency-conversion"))
                .build();
    }
}
