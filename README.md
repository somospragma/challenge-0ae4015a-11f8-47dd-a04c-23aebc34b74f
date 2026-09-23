# Implementación de Resiliencia en Core de Pagos

El sistema de pagos de nuestra plataforma financiera necesita ser robusto y escalable para manejar picos de tráfico y fallos temporales. El core de pagos interactúa con múltiples servicios internos y externos, incluyendo el motor de fraude, el sistema de liquidación y la pasarela de pagos. El objetivo es integrar un modelo de resiliencia que incluya al menos dos métodos de tolerancia a fallos, como CircuitBreaker, Retry, Fallback y RateLimit.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Construcción de Servicios REST Robustos y Escalables |
| **Nivel** | senior-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 10 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Análisis de Requisitos de Resiliencia

**Objetivo:** Identificar los puntos críticos de fallo en el core de pagos y definir los métodos de resiliencia a aplicar.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Enumera los servicios internos y externos con los que interactúa el core de pagos.
- Identifica los posibles puntos de fallo y los impactos en el sistema.
- Selecciona al menos dos métodos de resiliencia para implementar.

**Entregable:** Documento de análisis que incluye los servicios interactuantes, puntos de fallo identificados y métodos de resiliencia seleccionados.

<details>
<summary>Pistas de conocimiento</summary>

- Considera la frecuencia y criticidad de las transacciones.
- Evalúa la latencia aceptable en diferentes escenarios.

</details>

### Fase 2: Implementación de CircuitBreaker

**Objetivo:** Integrar un CircuitBreaker en el core de pagos para manejar fallos temporales en servicios externos.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Diseña e implementa un CircuitBreaker que monitoree las llamadas a servicios externos.
- Define los umbrales de fallo y las acciones a tomar cuando se alcancen.
- Documenta la configuración y el comportamiento esperado del CircuitBreaker.

**Entregable:** CircuitBreaker implementado y documentado, incluyendo umbrales de fallo y acciones configuradas.

<details>
<summary>Pistas de conocimiento</summary>

- Considera la latencia aceptable y el número de intentos antes de abrir el circuito.
- Evalúa la recuperación automática del servicio externo.

</details>

### Fase 3: Implementación de Retry y Fallback

**Objetivo:** Añadir mecanismos de Retry y Fallback para mejorar la resiliencia del core de pagos.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Implementa una estrategia de Retry para servicios externos con fallos transitorios.
- Configura un Fallback para proporcionar una respuesta predeterminada en caso de fallo.
- Documenta la configuración y el comportamiento esperado de Retry y Fallback.

**Entregable:** Mecanismos de Retry y Fallback implementados y documentados, incluyendo configuración y comportamiento esperado.

<details>
<summary>Pistas de conocimiento</summary>

- Considera el tiempo de espera entre reintentos y el número máximo de intentos.
- Evalúa la respuesta predeterminada adecuada para el Fallback.

</details>

### Fase 4: Integración y Validación

**Objetivo:** Integrar y validar los mecanismos de resiliencia en el core de pagos.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Integra los mecanismos de CircuitBreaker, Retry y Fallback en el core de pagos.
- Realiza pruebas de carga y fallos para validar el comportamiento de los mecanismos de resiliencia.
- Documenta los resultados de las pruebas y cualquier ajuste realizado.

**Entregable:** Mecanismos de resiliencia integrados y validados, incluyendo documentación de pruebas y ajustes realizados.

<details>
<summary>Pistas de conocimiento</summary>

- Utiliza herramientas de simulación de fallos para realizar pruebas.
- Evalúa el impacto en la latencia y la disponibilidad del sistema.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es un CircuitBreaker y cómo funciona en el contexto del core de pagos?
- **paraQueSirve**: ¿Para qué sirve implementar un Fallback en el core de pagos?
- **comoSeUsa**: ¿Cómo se configura un Retry en el core de pagos para manejar fallos transitorios?
- **erroresComunes**: ¿Cuáles son los errores comunes al implementar mecanismos de resiliencia en el core de pagos?
- **queDecisionesImplica**: ¿Qué decisiones implica la selección de métodos de resiliencia para el core de pagos?

## Criterios de Evaluacion

- Identificación correcta de puntos críticos de fallo en el core de pagos.
- Selección adecuada de métodos de resiliencia.
- Implementación correcta de CircuitBreaker, Retry y Fallback.
- Documentación clara y completa de la configuración y el comportamiento esperado de los mecanismos de resiliencia.
- Realización de pruebas de carga y fallos para validar el comportamiento de los mecanismos de resiliencia.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
