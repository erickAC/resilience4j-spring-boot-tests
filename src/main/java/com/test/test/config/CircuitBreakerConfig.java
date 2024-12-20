package com.test.test.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer;

@Configuration
public class CircuitBreakerConfig {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerConfig.class);
    
    @Bean
    public CircuitBreakerRegistry testConstumizer() {
        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.ofDefaults();
        circuitBreakerRegistry.getEventPublisher()
            .onEvent(event -> {
                logger.info("CircuitBreaker Event: " + event.getEventType());
            });
        return circuitBreakerRegistry;
    }

}
