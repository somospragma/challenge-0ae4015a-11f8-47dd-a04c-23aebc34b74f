package com.pragma.pagos.core.application.dto;

import com.pragma.pagos.core.domain.model.PaymentStatus;
import java.math.BigDecimal;
import java.util.UUID;

public record PaymentRequest(
    UUID id,
    String transactionId,
    String accountOrigin,
    String accountDestination,
    BigDecimal amount,
    String currency,
    PaymentStatus status,
    String fraudDetectionReference
) {
}