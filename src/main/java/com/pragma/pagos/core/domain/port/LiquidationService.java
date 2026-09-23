package com.pragma.pagos.core.domain.port;

import com.pragma.pagos.core.domain.model.Payment;
import reactor.core.publisher.Mono;

public interface LiquidationService {
    Mono<Payment> processLiquidation(Payment payment);
}