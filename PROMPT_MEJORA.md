# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/main/java/com/pragma/pagos/core/infrastructure/config/Resilience4jConfig.java` — El topic pide resiliencia: este archivo es el ejercicio.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/pragma/pagos/core/domain/port/FraudDetectionService.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/pagos/core/domain/port/LiquidationService.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/pagos/core/domain/port/PaymentGateway.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/pagos/core/application/service/PaymentService.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/pagos/core/infrastructure/adapter/FraudDetectionAdapter.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/pagos/core/infrastructure/adapter/LiquidationAdapter.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/pagos/core/infrastructure/adapter/PaymentGatewayAdapter.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/pragma/pagos/core/application/service/PaymentServiceTest.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/pragma/pagos/core/infrastructure/adapter/FraudDetectionAdapterTest.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/pagos/core/infrastructure/controller/PaymentController.java` — `PaymentService.createPayment`: Se invoca `createPayment` sobre `PaymentService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/pagos/core/infrastructure/controller/PaymentController.java` — `PaymentService.getPaymentById`: Se invoca `getPaymentById` sobre `PaymentService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/pagos/core/infrastructure/controller/PaymentController.java` — `PaymentService.updatePayment`: Se invoca `updatePayment` sobre `PaymentService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/pagos/core/infrastructure/controller/PaymentController.java` — `PaymentService.deletePayment`: Se invoca `deletePayment` sobre `PaymentService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/pagos/core/infrastructure/adapter/FraudDetectionAdapterTest.java` — `FraudDetectionAdapter.analyzeTransaction`: Se invoca `analyzeTransaction` sobre `FraudDetectionAdapter`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollador, Tecnología Java, Senior L2

### Brecha de conocimiento
Aplica al menos dos metodos de tolerancia a fallos a traves de codigo, como CircuitBreaker, Retry, Fallback y RateLimit

### Misión / candidato
Integrar un modelo de resiliencia en el core de pagos

### Datos adicionales
Candidato con 5 años de experiencia, equipo distribuido

### Reto
- Tema: Construcción de Servicios REST Robustos y Escalables
- Seniority: senior-l2
- Tipo: practical
- Título: Implementación de Resiliencia en Core de Pagos
- Tiempo estimado: 10 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Análisis de Requisitos de Resiliencia — objetivo: Identificar los puntos críticos de fallo en el core de pagos y definir los métodos de resiliencia a aplicar. — entregable (NO resolver): Documento de análisis que incluye los servicios interactuantes, puntos de fallo identificados y métodos de resiliencia seleccionados.
- Fase 2: Implementación de CircuitBreaker — objetivo: Integrar un CircuitBreaker en el core de pagos para manejar fallos temporales en servicios externos. — entregable (NO resolver): CircuitBreaker implementado y documentado, incluyendo umbrales de fallo y acciones configuradas.
- Fase 3: Implementación de Retry y Fallback — objetivo: Añadir mecanismos de Retry y Fallback para mejorar la resiliencia del core de pagos. — entregable (NO resolver): Mecanismos de Retry y Fallback implementados y documentados, incluyendo configuración y comportamiento esperado.
- Fase 4: Integración y Validación — objetivo: Integrar y validar los mecanismos de resiliencia en el core de pagos. — entregable (NO resolver): Mecanismos de resiliencia integrados y validados, incluyendo documentación de pruebas y ajustes realizados.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.pragma</groupId>
    <artifactId>pagos-core</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>pagos-core</name>
    <description>Core de pagos con resiliencia</description>

    <properties>
        <java.version>21</java.version>
        <resilience4j.version>2.2.0</resilience4j.version>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>

        <!-- Resilience4j -->
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-spring-boot3</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-reactor</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>

        <!-- Database -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- Testing -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>

//

// === ARCHIVO: src/main/java/com/pragma/pagos/core/Application.java ===
package com.pragma.pagos.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Clase principal para iniciar la aplicación Spring Boot.
 * Configura el escaneo de componentes para todas las capas del dominio.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.pragma.pagos.core"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

//

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: pagos-core
  datasource:
    url: jdbc:postgresql://localhost:5432/pagos_db
    username: postgres
    password: postgres
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        format_sql: true

# Configuración de Resilience4j
resilience4j:
  circuitbreaker:
    instances:
      fraudDetection:
        registerHealthIndicator: true
        slidingWindowSize: 10
        minimumNumberOfCalls: 5
        permittedNumberOfCallsInHalfOpenState: 3
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 5s
        failureRateThreshold: 50
        eventConsumerBufferSize: 10
      paymentGateway:
        registerHealthIndicator: true
        slidingWindowSize: 10
        minimumNumberOfCalls: 5
        permittedNumberOfCallsInHalfOpenState: 3
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 5s
        failureRateThreshold: 50
        eventConsumerBufferSize: 10
      liquidation:
        registerHealthIndicator: true
        slidingWindowSize: 10
        minimumNumberOfCalls: 5
        permittedNumberOfCallsInHalfOpenState: 3
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 5s
        failureRateThreshold: 50
        eventConsumerBufferSize: 10
  retry:
    instances:
      fraudDetection:
        maxAttempts: 3
        waitDuration: 100ms
        retryExceptions:
          - org.springframework.web.client.ResourceAccessException
          - java.util.concurrent.TimeoutException
      paymentGateway:
        maxAttempts: 3
        waitDuration: 100ms
        retryExceptions:
          - org.springframework.web.client.ResourceAccessException
          - java.util.concurrent.TimeoutException
      liquidation:
        maxAttempts: 3
        waitDuration: 100ms
        retryExceptions:
          - org.springframework.web.client.ResourceAccessException
          - java.util.concurrent.TimeoutException
  bulkhead:
    instances:
      fraudDetection:
        maxConcurrentCalls: 10
        maxWaitDuration: 10ms
      paymentGateway:
        maxConcurrentCalls: 10
        maxWaitDuration: 10ms
      liquidation:
        maxConcurrentCalls: 10
        maxWaitDuration: 10ms

# Actuator endpoints
management:
  endpoints:
    web:
      exposure:
        include: health,metrics,circuitbreakers
  endpoint:
    health:
      show-details: always
  health:
    circuitbreakers:
      enabled: true

// === ARCHIVO: src/main/java/com/pragma/pagos/core/domain/model/Payment.java ===
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

// === ARCHIVO: src/main/java/com/pragma/pagos/core/domain/port/PaymentRepository.java ===
package com.pragma.pagos.core.domain.port;

import com.pragma.pagos.core.domain.model.Payment;
import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository {
    Payment save(Payment payment);
    Optional<Payment> findById(UUID paymentId);
    Optional<Payment> findByTransactionId(String transactionId);
}

// === ARCHIVO: src/main/java/com/pragma/pagos/core/domain/port/FraudDetectionService.java ===
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

// === ARCHIVO: src/main/java/com/pragma/pagos/core/domain/port/LiquidationService.java ===
package com.pragma.pagos.core.domain.port;

import com.pragma.pagos.core.domain.model.Payment;
import reactor.core.publisher.Mono;

public interface LiquidationService {
    Mono<Payment> processLiquidation(Payment payment);
}

// === ARCHIVO: src/main/java/com/pragma/pagos/core/domain/port/PaymentGateway.java ===
package com.pragma.pagos.core.domain.port;

import com.pragma.pagos.core.domain.model.Payment;
import reactor.core.publisher.Mono;

public interface PaymentGateway {
    Mono<Payment> processPayment(Payment payment);
}

// === ARCHIVO: src/main/java/com/pragma/pagos/core/application/service/PaymentService.java ===
package com.pragma.pagos.core.application.service;

import com.pragma.pagos.core.domain.model.Payment;
import com.pragma.pagos.core.domain.port.FraudDetectionService;
import com.pragma.pagos.core.domain.port.LiquidationService;
import com.pragma.pagos.core.domain.port.PaymentGateway;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PaymentService {

    private final FraudDetectionService fraudDetectionService;
    private final LiquidationService liquidationService;
    private final PaymentGateway paymentGateway;

    @Autowired
    public PaymentService(FraudDetectionService fraudDetectionService, LiquidationService liquidationService, PaymentGateway paymentGateway) {
        this.fraudDetectionService = fraudDetectionService;
        this.liquidationService = liquidationService;
        this.paymentGateway = paymentGateway;
    }

    @CircuitBreaker(name = "paymentServiceCircuitBreaker", fallbackMethod = "processPaymentFallback")
    @Retry(name = "paymentServiceRetry")
    @Bulkhead(name = "paymentServiceBulkhead", type = Bulkhead.Type.THREADPOOL)
    public Mono<Payment> processPayment(Payment payment) {
        return fraudDetectionService.analyzeTransaction(payment.getAccountOrigin(), payment.getAccountDestination(), payment.getAmount(), payment.getCurrency())
           .flatMap(fraudDetectionResult -> {
                if (fraudDetectionResult.isFraud()) {
                    return Mono.error(new FraudException("Payment detected as fraud"));
                }
                return paymentGateway.processPayment(payment)
                   .flatMap(processedPayment -> liquidationService.processLiquidation(processedPayment));
            });
    }

    public Mono<Payment> processPaymentFallback(Payment payment, Throwable throwable) {
        return Mono.error(new PaymentProcessingException("Payment processing failed with fallback", throwable));
    }
}

// === ARCHIVO: src/main/java/com/pragma/pagos/core/application/dto/PaymentRequest.java ===
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

// === ARCHIVO: src/main/java/com/pragma/pagos/core/application/dto/PaymentResponse.java ===
package com.pragma.pagos.core.application.dto;

import com.pragma.pagos.core.domain.model.PaymentStatus;
import java.math.BigDecimal;
import java.util.UUID;

public record PaymentResponse(
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

// === ARCHIVO: src/main/java/com/pragma/pagos/core/infrastructure/adapter/PaymentJpaRepository.java ===
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

// === ARCHIVO: src/main/java/com/pragma/pagos/core/infrastructure/adapter/FraudDetectionAdapter.java ===
package com.pragma.pagos.core.infrastructure.adapter;


import com.pragma.pagos.core.domain.model.PaymentStatus;
import com.pragma.pagos.core.domain.port.FraudDetectionService;
import com.pragma.pagos.core.domain.model.Payment;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Service;

@Service
public class FraudDetectionAdapter implements FraudDetectionService {

    private final FraudDetectionService fraudDetectionService;

    public FraudDetectionAdapter(FraudDetectionService fraudDetectionService) {
        this.fraudDetectionService = fraudDetectionService;
    }

    @Override
    @CircuitBreaker(name = "fraudDetection", fallbackMethod = "fraudDetectionFallback")
    @Retry(name = "fraudDetection")
    @Bulkhead(name = "fraudDetection")
    public Mono<Payment> validatePayment(Payment payment) {
        return fraudDetectionService.analyzeTransaction(payment.getAccountOrigin(), payment.getAccountDestination(), payment.getAmount(), payment.getCurrency())
               .map(fraudDetectionResult -> payment.withStatus(fraudDetectionResult.isFraud()? Payment.PaymentStatus.FRAUD_DETECTED : Payment.PaymentStatus.VALIDATED));
    }

    public Mono<Payment> fraudDetectionFallback(Payment payment, Throwable t) {
        return Mono.just(payment.withStatus(Payment.PaymentStatus.VALIDATION_FAILED));
    }
}

// === ARCHIVO: src/main/java/com/pragma/pagos/core/infrastructure/adapter/LiquidationAdapter.java ===
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

// === ARCHIVO: src/main/java/com/pragma/pagos/core/infrastructure/adapter/PaymentGatewayAdapter.java ===
package com.pragma.pagos.core.infrastructure.adapter;


import com.pragma.pagos.core.domain.model.PaymentStatus;
import com.pragma.pagos.core.domain.port.PaymentGateway;
import com.pragma.pagos.core.domain.model.Payment;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Service;

@Service
public class PaymentGatewayAdapter implements PaymentGateway {

    private final PaymentGateway paymentGateway;

    public PaymentGatewayAdapter(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    @Override
    @CircuitBreaker(name = "paymentGateway", fallbackMethod = "paymentGatewayFallback")
    @Retry(name = "paymentGateway")
    @Bulkhead(name = "paymentGateway")
    public Mono<Payment> processPayment(Payment payment) {
        return paymentGateway.processPayment(payment);
    }

    public Mono<Payment> paymentGatewayFallback(Payment payment, Throwable t) {
        return Mono.just(payment.withStatus(Payment.PaymentStatus.PAYMENT_FAILED));
    }
}

// === ARCHIVO: src/main/java/com/pragma/pagos/core/infrastructure/config/Resilience4jConfig.java ===
package com.pragma.pagos.core.infrastructure.config;


import com.pragma.pagos.core.application.service.PaymentService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Resilience4jConfig {

    @Bean
    @CircuitBreaker(name = "paymentService", fallbackMethod = "paymentServiceFallback")
    @Retry(name = "paymentService")
    @Bulkhead(name = "paymentService")
    public PaymentService paymentService() {
        return new PaymentService();
    }

    public PaymentService paymentServiceFallback(Throwable t) {
        return new PaymentService();
    }
}

// === ARCHIVO: src/main/java/com/pragma/pagos/core/infrastructure/controller/PaymentController.java ===
package com.pragma.pagos.core.infrastructure.controller;

import com.pragma.pagos.core.application.dto.PaymentRequest;
import com.pragma.pagos.core.application.dto.PaymentResponse;
import com.pragma.pagos.core.application.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest paymentRequest) {
        return ResponseEntity.ok(paymentService.createPayment(paymentRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable UUID id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentResponse> updatePayment(@PathVariable UUID id, @RequestBody PaymentRequest paymentRequest) {
        return ResponseEntity.ok(paymentService.updatePayment(id, paymentRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable UUID id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}

// === ARCHIVO: src/test/java/com/pragma/pagos/core/application/service/PaymentServiceTest.java ===
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

// === ARCHIVO: src/test/java/com/pragma/pagos/core/infrastructure/adapter/FraudDetectionAdapterTest.java ===
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
```
