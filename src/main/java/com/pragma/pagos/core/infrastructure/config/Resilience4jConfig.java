package com.pragma.pagos.core.infrastructure.config;


import com.pragma.pagos.core.application.service.PaymentService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Resilience4jConfig {

    @Bean
    @CircuitBreaker(name = "paymentService", fallbackMethod = "paymentServiceFallback")
    @Retry(name = "paymentService")
    @Bulkhead(name = "paymentService")
    public PaymentService paymentService() {
        return new PaymentService();
    }

    public PaymentService paymentServiceFallback(Throwable t) {
        return new PaymentService();
    }
}