package com.pragma.pagos.core.domain.port;


import com.pragma.pagos.core.domain.model.PaymentStatus;
import com.pragma.pagos.core.domain.model.Payment;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Map;

public interface FraudDetectionService {
    Mono<FraudDetectionResult> analyzeTransaction(String accountOrigin, String accountDestination,
                                                 BigDecimal amount, String currency);

    class FraudDetectionResult {
        private final boolean isFraudulent;
        private final String referenceId;
        private final Map<String, String> riskFactors;

        public FraudDetectionResult(boolean isFraudulent, String referenceId, Map<String, String> riskFactors) {
            if (isFraudulent && (referenceId == null || referenceId.trim().isEmpty())) {
                throw new IllegalArgumentException("Fraud reference ID cannot be null or empty for fraudulent transactions");
            }
            this.isFraudulent = isFraudulent;
            this.referenceId = referenceId;
            this.riskFactors = riskFactors != null ? Map.copyOf(riskFactors) : Map.of();
        }

        public boolean isFraudulent() {
            return isFraudulent;
        }

        public String getReferenceId() {
            return referenceId;
        }

        public Map<String, String> getRiskFactors() {
            return riskFactors;
        }
    }

    default Mono<Payment> validatePayment(Payment payment) {
        if (payment == null) {
            return Mono.error(new IllegalArgumentException("Payment cannot be null"));
        }

        return analyzeTransaction(
                payment.getAccountOrigin(),
                payment.getAccountDestination(),
                payment.getAmount(),
                payment.getCurrency()
        ).flatMap(result -> {
            if (result.isFraudulent()) {
                return Mono.just(payment.withStatus(Payment.PaymentStatus.FRAUD_DETECTED));
            } else {
                return Mono.just(payment.withStatus(Payment.PaymentStatus.PROCESSING));
            }
        }).onErrorResume(e -> {
            // Fallback behavior when fraud detection fails
            return Mono.just(payment.withStatus(Payment.PaymentStatus.PENDING));
        });
    }
}