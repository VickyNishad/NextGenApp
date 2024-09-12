package com.nextgen.server.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("ms-login-service", r -> r.path("/login/**")
                        .uri("lb://ms-login-service")) // Route requests matching /users/** to http://localhost:8081
                .route("ms-email-mobile-service", r -> r.path("/otp/**")
                        .uri("http://localhost:8082")) // Route requests matching /products/** to http://localhost:8082
                .route("orders_route", r -> r.path("/api/v1/document/**")
                        .uri("http://localhost:7070")) // Route requests matching /orders/** to http://localhost:8083
                .route("login-service", r->r.path("/api/**")
                        .uri("lb://login-service"))
                .build();
    }
}
