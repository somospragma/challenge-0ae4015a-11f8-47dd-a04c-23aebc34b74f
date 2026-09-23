package com.pragma.pagos.core.domain.port;

import com.pragma.pagos.core.domain.model.Payment;
import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository {
    Payment save(Payment payment);
    Optional<Payment> findById(UUID paymentId);
    Optional<Payment> findByTransactionId(String transactionId);
}