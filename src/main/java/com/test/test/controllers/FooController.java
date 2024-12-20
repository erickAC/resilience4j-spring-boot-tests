package com.test.test.controllers;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("foo")
public class FooController {

    Logger logger = LoggerFactory.getLogger(FooController.class);

    @RateLimiter(name = "backendA", fallbackMethod = "fallback")
    @Retry(name = "backendA", fallbackMethod = "fallback")
    @GetMapping
    public String hello() throws Exception {
        // String response = RestClient.create("http://localhost:8080/teste").get().retrieve().body(String.class);
        // return response;
        return "Hello World";
    }

    @RateLimiter(name = "backendA", fallbackMethod = "fallback")    
    @CircuitBreaker(name = "circuit", fallbackMethod = "fallback")
    @GetMapping("/circuit")
    public String circuit() {
        return "Hello Circuit";
    }

    public String fallback(Exception e) {
        logger.info("Event Fallback");
        return "Erro " + e.getMessage();
    }

}
