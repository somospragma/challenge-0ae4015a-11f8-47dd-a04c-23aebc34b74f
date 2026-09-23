package com.pragma.pagos.core.infrastructure.adapter;


import com.pragma.pagos.core.domain.model.PaymentStatus;
import com.pragma.pagos.core.domain.port.PaymentGateway;
import com.pragma.pagos.core.domain.model.Payment;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Service;

@Service
public class PaymentGatewayAdapter implements PaymentGateway {

    private final PaymentGateway paymentGateway;

    public PaymentGatewayAdapter(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    @Override
    @CircuitBreaker(name = "paymentGateway", fallbackMethod = "paymentGatewayFallback")
    @Retry(name = "paymentGateway")
    @Bulkhead(name = "paymentGateway")
    public Mono<Payment> processPayment(Payment payment) {
        return paymentGateway.processPayment(payment);
    }

    public Mono<Payment> paymentGatewayFallback(Payment payment, Throwable t) {
        return Mono.just(payment.withStatus(Payment.PaymentStatus.PAYMENT_FAILED));
    }
}