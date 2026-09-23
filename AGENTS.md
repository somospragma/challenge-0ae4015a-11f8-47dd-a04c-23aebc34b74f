# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Implementación de Resiliencia en Core de Pagos**.

| | |
|---|---|
| Tema | Construcción de Servicios REST Robustos y Escalables |
| Nivel | senior-l2 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java / Spring Boot 3.5 |
| Patron arquitectonico | hexagonal/clean con capas de dominio, aplicación e infraestructura |
| Tiempo estimado | 10 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-web 3.5.6
- org.springframework.boot:spring-boot-starter-data-jpa n/a
- org.springframework.boot:spring-boot-starter-validation n/a
- io.github.resilience4j:resilience4j-spring-boot3 2.2.0
- io.github.resilience4j:resilience4j-reactor 2.2.0
- org.postgresql:postgresql n/a
- org.springframework.boot:spring-boot-starter-test n/a
- org.springframework.boot:spring-boot-maven-plugin n/a

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Análisis de Requisitos de Resiliencia**: Documento de análisis que incluye los servicios interactuantes, puntos de fallo identificados y métodos de resiliencia seleccionados.
- **Fase 2 — Implementación de CircuitBreaker**: CircuitBreaker implementado y documentado, incluyendo umbrales de fallo y acciones configuradas.
- **Fase 3 — Implementación de Retry y Fallback**: Mecanismos de Retry y Fallback implementados y documentados, incluyendo configuración y comportamiento esperado.
- **Fase 4 — Integración y Validación**: Mecanismos de resiliencia integrados y validados, incluyendo documentación de pruebas y ajustes realizados.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Superficie de practica (NO completes)

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs. No toques la logica que el reto pide completar.

- [ ] `src/main/java/com/pragma/pagos/core/infrastructure/config/Resilience4jConfig.java` — El topic pide resiliencia: este archivo es el ejercicio.

## Lo que falta y tenes que completar

### 1. Referencias colgando (14)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/pragma/pagos/core/domain/port/FraudDetectionService.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/pagos/core/domain/port/LiquidationService.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/pagos/core/domain/port/PaymentGateway.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/pagos/core/application/service/PaymentService.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/pagos/core/infrastructure/adapter/FraudDetectionAdapter.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/pagos/core/infrastructure/adapter/LiquidationAdapter.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/pagos/core/infrastructure/adapter/PaymentGatewayAdapter.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/pragma/pagos/core/application/service/PaymentServiceTest.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/pragma/pagos/core/infrastructure/adapter/FraudDetectionAdapterTest.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/pagos/core/infrastructure/controller/PaymentController.java` — `PaymentService.createPayment`
      Se invoca `createPayment` sobre `PaymentService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/pagos/core/infrastructure/controller/PaymentController.java` — `PaymentService.getPaymentById`
      Se invoca `getPaymentById` sobre `PaymentService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/pagos/core/infrastructure/controller/PaymentController.java` — `PaymentService.updatePayment`
      Se invoca `updatePayment` sobre `PaymentService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/pagos/core/infrastructure/controller/PaymentController.java` — `PaymentService.deletePayment`
      Se invoca `deletePayment` sobre `PaymentService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/pagos/core/infrastructure/adapter/FraudDetectionAdapterTest.java` — `FraudDetectionAdapter.analyzeTransaction`
      Se invoca `analyzeTransaction` sobre `FraudDetectionAdapter`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (19)

- `pom.xml`
- `src/main/java/com/pragma/pagos/core/Application.java`
- `src/main/resources/application.yml`
- `src/main/java/com/pragma/pagos/core/domain/model/Payment.java`
- `src/main/java/com/pragma/pagos/core/domain/port/PaymentRepository.java`
- `src/main/java/com/pragma/pagos/core/domain/port/FraudDetectionService.java`
- `src/main/java/com/pragma/pagos/core/domain/port/LiquidationService.java`
- `src/main/java/com/pragma/pagos/core/domain/port/PaymentGateway.java`
- `src/main/java/com/pragma/pagos/core/application/service/PaymentService.java`
- `src/main/java/com/pragma/pagos/core/application/dto/PaymentRequest.java`
- `src/main/java/com/pragma/pagos/core/application/dto/PaymentResponse.java`
- `src/main/java/com/pragma/pagos/core/infrastructure/adapter/PaymentJpaRepository.java`
- `src/main/java/com/pragma/pagos/core/infrastructure/adapter/FraudDetectionAdapter.java`
- `src/main/java/com/pragma/pagos/core/infrastructure/adapter/LiquidationAdapter.java`
- `src/main/java/com/pragma/pagos/core/infrastructure/adapter/PaymentGatewayAdapter.java`
- `src/main/java/com/pragma/pagos/core/infrastructure/config/Resilience4jConfig.java`
- `src/main/java/com/pragma/pagos/core/infrastructure/controller/PaymentController.java`
- `src/test/java/com/pragma/pagos/core/application/service/PaymentServiceTest.java`
- `src/test/java/com/pragma/pagos/core/infrastructure/adapter/FraudDetectionAdapterTest.java`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/pragma/pagos/core`
- `src/main/java/com/pragma/pagos/core/domain`
- `src/main/java/com/pragma/pagos/core/domain/model`
- `src/main/java/com/pragma/pagos/core/domain/port`
- `src/main/java/com/pragma/pagos/core/application`
- `src/main/java/com/pragma/pagos/core/application/service`
- `src/main/java/com/pragma/pagos/core/application/dto`
- `src/main/java/com/pragma/pagos/core/infrastructure`
- `src/main/java/com/pragma/pagos/core/infrastructure/adapter`
- `src/main/java/com/pragma/pagos/core/infrastructure/config`
- `src/main/java/com/pragma/pagos/core/infrastructure/controller`
- `src/main/resources`
- `src/test/java/com/pragma/pagos/core`

## Verificacion

```bash
mvn clean compile
```

El comando tiene que pasar SIN implementar los archivos de la superficie de practica: solo andamiaje.

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **hexagonal/clean con capas de dominio, aplicación e infraestructura**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Backend, Especialidad Desarrollador, Tecnología Java, Senior L2
- Brecha que el reto ataca: Aplica al menos dos metodos de tolerancia a fallos a traves de codigo, como CircuitBreaker, Retry, Fallback y RateLimit
- Mision: Integrar un modelo de resiliencia en el core de pagos

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
