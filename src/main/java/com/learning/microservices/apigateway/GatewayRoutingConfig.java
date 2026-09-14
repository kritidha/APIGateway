package com.learning.microservices.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutingConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // Route for Loan Service
                .route("loan-service-route", r -> r
                        .path("/loans", "/loans/**")
                        .filters(f -> f.addRequestHeader("X-Routed-By", "APIGateway"))
                        .uri("lb://LOAN-SERVICE"))

                // Route for Member Service
                .route("member-service-route", r -> r
                        .path("/members", "/members/**")
                        .uri("lb://MEMBER-SERVICE"))
                .build();
    }
}