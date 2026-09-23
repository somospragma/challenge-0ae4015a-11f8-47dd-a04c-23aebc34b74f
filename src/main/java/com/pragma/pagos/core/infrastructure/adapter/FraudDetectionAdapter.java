package com.pragma.pagos.core.infrastructure.adapter;


import com.pragma.pagos.core.domain.model.PaymentStatus;
import com.pragma.pagos.core.domain.port.FraudDetectionService;
import com.pragma.pagos.core.domain.model.Payment;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Service;

@Service
public class FraudDetectionAdapter implements FraudDetectionService {

    private final FraudDetectionService fraudDetectionService;

    public FraudDetectionAdapter(FraudDetectionService fraudDetectionService) {
        this.fraudDetectionService = fraudDetectionService;
    }

    @Override
    @CircuitBreaker(name = "fraudDetection", fallbackMethod = "fraudDetectionFallback")
    @Retry(name = "fraudDetection")
    @Bulkhead(name = "fraudDetection")
    public Mono<Payment> validatePayment(Payment payment) {
        return fraudDetectionService.analyzeTransaction(payment.getAccountOrigin(), payment.getAccountDestination(), payment.getAmount(), payment.getCurrency())
               .map(fraudDetectionResult -> payment.withStatus(fraudDetectionResult.isFraud()? Payment.PaymentStatus.FRAUD_DETECTED : Payment.PaymentStatus.VALIDATED));
    }

    public Mono<Payment> fraudDetectionFallback(Payment payment, Throwable t) {
        return Mono.just(payment.withStatus(Payment.PaymentStatus.VALIDATION_FAILED));
    }
}