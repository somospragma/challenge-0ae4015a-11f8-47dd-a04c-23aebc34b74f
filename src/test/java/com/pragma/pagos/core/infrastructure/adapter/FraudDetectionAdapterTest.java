package com.pragma.pagos.core.infrastructure.adapter;


import com.pragma.pagos.core.domain.port.FraudDetectionResult;
import com.pragma.pagos.core.domain.port.FraudDetectionService;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
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
import static org.mockito.Mockito.*;

@SpringBootTest
class FraudDetectionAdapterTest {

    @Mock
    private FraudDetectionService fraudDetectionService;

    @InjectMocks
    private FraudDetectionAdapter fraudDetectionAdapter;

    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSuccessfulFraudDetection() {
        when(fraudDetectionService.analyzeTransaction(anyString(), anyString(), any(BigDecimal.class), anyString())).thenReturn(Mono.just(new FraudDetectionService.FraudDetectionResult(true)));

        Mono<FraudDetectionService.FraudDetectionResult> result = fraudDetectionAdapter.analyzeTransaction("account-origin", "account-destination", new BigDecimal("100.00"), "USD");

        StepVerifier.create(result)
               .expectNextMatches(r -> r.isFraud() == true)
               .verifyComplete();
    }

    @Test
    void testCircuitBreakerTripped() {
        when(fraudDetectionService.analyzeTransaction(anyString(), anyString(), any(BigDecimal.class), anyString())).thenReturn(Mono.error(new RuntimeException("Simulated failure")));

        Mono<FraudDetectionService.FraudDetectionResult> result = fraudDetectionAdapter.analyzeTransaction("account-origin", "account-destination", new BigDecimal("100.00"), "USD");

        StepVerifier.create(result)
               .expectErrorMatches(e -> e instanceof CallNotPermittedException)
               .verify();
    }
}