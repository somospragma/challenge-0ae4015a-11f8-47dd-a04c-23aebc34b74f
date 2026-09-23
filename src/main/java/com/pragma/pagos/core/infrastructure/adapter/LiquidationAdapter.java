package com.pragma.pagos.core.infrastructure.adapter;


import com.pragma.pagos.core.domain.model.PaymentStatus;
import com.pragma.pagos.core.domain.port.LiquidationService;
import com.pragma.pagos.core.domain.model.Payment;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Service;

@Service
public class LiquidationAdapter implements LiquidationService {

    private final LiquidationService liquidationService;

    public LiquidationAdapter(LiquidationService liquidationService) {
        this.liquidationService = liquidationService;
    }

    @Override
    @CircuitBreaker(name = "liquidation", fallbackMethod = "liquidationFallback")
    @Retry(name = "liquidation")
    @Bulkhead(name = "liquidation")
    public Mono<Payment> processLiquidation(Payment payment) {
        return liquidationService.processLiquidation(payment);
    }

    public Mono<Payment> liquidationFallback(Payment payment, Throwable t) {
        return Mono.just(payment.withStatus(Payment.PaymentStatus.LIQUIDATION_FAILED));
    }
}