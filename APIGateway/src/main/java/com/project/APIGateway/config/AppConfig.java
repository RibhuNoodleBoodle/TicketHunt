package com.project.APIGateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes().
                route(p -> p
                        .path("/niit/auth/**")
                        .uri("http://authentication-service:8084/")
                )
                .route(p -> p
                        .path("/bookings/**")
                        .uri("http://booking-service:8081/")
                )
                .route(p -> p
                        .path("/eventData/**")
                        .uri("http://event-service:8088/")
                )
                .route(p -> p
                        .path("/payment/**")
                        .uri("http://payment-service:8085/")
                )
                .route(p -> p
                        .path("/api/v4/**")
                        .uri("http://recommendation-service:8086/")
                )
                .route(p -> p
                        .path("/userData/**")
                        .uri("http://user-service:8082/")
                )
                .route(p -> p
                        .path("/**")
                        .uri("http://webapp-service:4200/")
                )
                .build();
    }
}
