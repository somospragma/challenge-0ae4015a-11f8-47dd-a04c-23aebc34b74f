package com.pragma.pagos.core.application.service;



import com.pragma.pagos.core.domain.port.FraudDetectionResult;
import com.pragma.pagos.core.domain.model.PaymentStatus;
import com.pragma.pagos.core.domain.model.Payment;
import com.pragma.pagos.core.domain.port.PaymentRepository;
import com.pragma.pagos.core.domain.port.FraudDetectionService;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;
import static org.mockito.Mockito.*;

@SpringBootTest
class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private FraudDetectionService fraudDetectionService;

    @InjectMocks
    private PaymentService paymentService;

    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSuccessfulPaymentProcessing() {
        UUID paymentId = UUID.randomUUID();
        Payment payment = new Payment(paymentId, "12345", "account-origin", "account-destination", new BigDecimal("100.00"), "USD", java.time.LocalDateTime.now(), Payment.PaymentStatus.PENDING, "fraud-ref");

        when(paymentRepository.save(any(Payment.class))).thenReturn(Mono.just(payment));
        when(fraudDetectionService.analyzeTransaction(anyString(), anyString(), any(BigDecimal.class), anyString())).thenReturn(Mono.just(new FraudDetectionService.FraudDetectionResult(true)));

        Mono<Payment> result = paymentService.processPayment(payment);

        StepVerifier.create(result)
               .expectNextMatches(p -> p.getId().equals(paymentId))
               .verifyComplete();
    }

    @Test
    void testCircuitBreakerTripped() {
        UUID paymentId = UUID.randomUUID();
        Payment payment = new Payment(paymentId, "12345", "account-origin", "account-destination", new BigDecimal("100.00"), "USD", java.time.LocalDateTime.now(), Payment.PaymentStatus.PENDING, "fraud-ref");

        when(paymentRepository.save(any(Payment.class))).thenReturn(Mono.error(new RuntimeException("Simulated failure")));
        when(fraudDetectionService.analyzeTransaction(anyString(), anyString(), any(BigDecimal.class), anyString())).thenReturn(Mono.error(new RuntimeException("Simulated failure")));

        Mono<Payment> result = paymentService.processPayment(payment);

        StepVerifier.create(result)
               .expectErrorMatches(e -> e instanceof CallNotPermittedException)
               .verify();
    }
}