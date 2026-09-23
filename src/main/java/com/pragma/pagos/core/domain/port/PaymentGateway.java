package com.pragma.pagos.core.domain.port;

import com.pragma.pagos.core.domain.model.Payment;
import reactor.core.publisher.Mono;

public interface PaymentGateway {
    Mono<Payment> processPayment(Payment payment);
}