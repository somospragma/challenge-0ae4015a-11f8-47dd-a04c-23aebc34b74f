package com.pragma.pagos.core.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Payment {
    private final UUID id;
    private final String transactionId;
    private final String accountOrigin;
    private final String accountDestination;
    private final BigDecimal amount;
    private final String currency;
    private final LocalDateTime createdAt;
    private final PaymentStatus status;
    private final String fraudDetectionReference;

    public Payment(UUID id, String transactionId, String accountOrigin, String accountDestination,
                  BigDecimal amount, String currency, LocalDateTime createdAt,
                  PaymentStatus status, String fraudDetectionReference) {
        if (id == null) {
            throw new IllegalArgumentException("Payment ID cannot be null");
        }
        if (transactionId == null || transactionId.trim().isEmpty()) {
            throw new IllegalArgumentException("Transaction ID cannot be null or empty");
        }
        if (accountOrigin == null || accountOrigin.trim().isEmpty()) {
            throw new IllegalArgumentException("Account origin cannot be null or empty");
        }
        if (accountDestination == null || accountDestination.trim().isEmpty()) {
            throw new IllegalArgumentException("Account destination cannot be null or empty");
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (currency == null || currency.trim().isEmpty() || currency.length() != 3) {
            throw new IllegalArgumentException("Currency must be a 3-letter code");
        }
        if (createdAt == null) {
            throw new IllegalArgumentException("Created at date cannot be null");
        }
        if (status == null) {
            throw new IllegalArgumentException("Payment status cannot be null");
        }

        this.id = id;
        this.transactionId = transactionId;
        this.accountOrigin = accountOrigin;
        this.accountDestination = accountDestination;
        this.amount = amount;
        this.currency = currency;
        this.createdAt = createdAt;
        this.status = status;
        this.fraudDetectionReference = fraudDetectionReference;
    }

    public UUID getId() {
        return id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getAccountOrigin() {
        return accountOrigin;
    }

    public String getAccountDestination() {
        return accountDestination;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public String getFraudDetectionReference() {
        return fraudDetectionReference;
    }

    public Payment withStatus(PaymentStatus newStatus) {
        return new Payment(this.id, this.transactionId, this.accountOrigin, this.accountDestination,
                this.amount, this.currency, this.createdAt, newStatus, this.fraudDetectionReference);
    }

    public enum PaymentStatus {
        PENDING,
        PROCESSING,
        COMPLETED,
        FAILED,
        FRAUD_DETECTED,
        REJECTED
    }
}