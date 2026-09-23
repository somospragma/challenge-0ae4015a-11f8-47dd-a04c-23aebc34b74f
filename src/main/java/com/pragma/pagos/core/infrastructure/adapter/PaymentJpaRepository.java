package com.pragma.pagos.core.infrastructure.adapter;

import com.pragma.pagos.core.domain.model.Payment;
import com.pragma.pagos.core.domain.port.PaymentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentJpaRepository extends JpaRepository<Payment, UUID>, PaymentRepository {
    Optional<Payment> findByTransactionId(String transactionId);
}