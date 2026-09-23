package com.pragma.pagos.core.application.service;

import com.pragma.pagos.core.domain.model.Payment;
import com.pragma.pagos.core.domain.port.FraudDetectionService;
import com.pragma.pagos.core.domain.port.LiquidationService;
import com.pragma.pagos.core.domain.port.PaymentGateway;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PaymentService {

    private final FraudDetectionService fraudDetectionService;
    private final LiquidationService liquidationService;
    private final PaymentGateway paymentGateway;

    @Autowired
    public PaymentService(FraudDetectionService fraudDetectionService, LiquidationService liquidationService, PaymentGateway paymentGateway) {
        this.fraudDetectionService = fraudDetectionService;
        this.liquidationService = liquidationService;
        this.paymentGateway = paymentGateway;
    }

    @CircuitBreaker(name = "paymentServiceCircuitBreaker", fallbackMethod = "processPaymentFallback")
    @Retry(name = "paymentServiceRetry")
    @Bulkhead(name = "paymentServiceBulkhead", type = Bulkhead.Type.THREADPOOL)
    public Mono<Payment> processPayment(Payment payment) {
        return fraudDetectionService.analyzeTransaction(payment.getAccountOrigin(), payment.getAccountDestination(), payment.getAmount(), payment.getCurrency())
           .flatMap(fraudDetectionResult -> {
                if (fraudDetectionResult.isFraud()) {
                    return Mono.error(new FraudException("Payment detected as fraud"));
                }
                return paymentGateway.processPayment(payment)
                   .flatMap(processedPayment -> liquidationService.processLiquidation(processedPayment));
            });
    }

    public Mono<Payment> processPaymentFallback(Payment payment, Throwable throwable) {
        return Mono.error(new PaymentProcessingException("Payment processing failed with fallback", throwable));
    }
}