# Automatización de pruebas de integración en DynamoDB

El sistema de gestión de pedidos utiliza una tabla DynamoDB para la persistencia de datos. Es necesario automatizar las pruebas de integración que validen las lecturas y escrituras en esta tabla. El objetivo es cubrir el flujo completo de persistencia de pedidos, asegurando que los datos se almacenen y recuperen correctamente.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Automatizacion de pruebas de integracion contra DynamoDB |
| **Nivel** | advanced-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 2 semanas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y entregará cada archivo con su ruta.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y entregará cada archivo con su ruta. Guárdalos en la carpeta donde quieras trabajar, respetando las rutas.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no entrega todos los archivos al terminar el análisis, escríbele: "entrégame todos los archivos con su ruta".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Configuración del entorno de pruebas

**Objetivo:** Preparar el entorno para ejecutar pruebas de integración contra DynamoDB.

**Tiempo estimado:** 3 días

**Instrucciones:**

- Configurar el acceso a DynamoDB desde el entorno de pruebas.
- Asegurar que las pruebas puedan interactuar con la tabla DynamoDB de forma segura y eficiente.

**Entregable:** Entorno de pruebas configurado y listo para ejecutar pruebas contra DynamoDB.

<details>
<summary>Pistas de conocimiento</summary>

- Considera las mejores prácticas para la gestión de credenciales y acceso a servicios en la nube.

</details>

### Fase 2: Desarrollo de pruebas de escritura

**Objetivo:** Crear y ejecutar pruebas automatizadas que validen la escritura de datos en DynamoDB.

**Tiempo estimado:** 5 días

**Instrucciones:**

- Desarrollar pruebas que inserten datos en la tabla DynamoDB.
- Verificar que los datos se almacenen correctamente y que las pruebas fallen si hay errores en la escritura.

**Entregable:** Conjunto de pruebas automatizadas que validan la escritura de datos en DynamoDB.

<details>
<summary>Pistas de conocimiento</summary>

- Piensa en los diferentes casos de prueba que necesitas para cubrir todas las posibles formas de escritura en DynamoDB.

</details>

### Fase 3: Desarrollo de pruebas de lectura

**Objetivo:** Crear y ejecutar pruebas automatizadas que validen la lectura de datos desde DynamoDB.

**Tiempo estimado:** 5 días

**Instrucciones:**

- Desarrollar pruebas que lean datos desde la tabla DynamoDB.
- Verificar que los datos se recuperen correctamente y que las pruebas fallen si hay errores en la lectura.

**Entregable:** Conjunto de pruebas automatizadas que validan la lectura de datos desde DynamoDB.

<details>
<summary>Pistas de conocimiento</summary>

- Considera diferentes escenarios de lectura, incluyendo lecturas por clave primaria y secundaria.

</details>

### Fase 4: Integración y validación del flujo completo

**Objetivo:** Integrar y validar el flujo completo de persistencia de pedidos, incluyendo escritura y lectura.

**Tiempo estimado:** 3 días

**Instrucciones:**

- Integrar las pruebas de escritura y lectura en un flujo completo de persistencia de pedidos.
- Verificar que el flujo completo funcione correctamente y que las pruebas fallen apropiadamente en caso de errores.

**Entregable:** Flujo completo de persistencia de pedidos validado mediante pruebas automatizadas.

<details>
<summary>Pistas de conocimiento</summary>

- Asegúrate de que todas las pruebas se ejecuten en el orden correcto y que los datos persistidos se recuperen como se espera.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué son las pruebas de integración y por qué son importantes en este contexto?
- **paraQueSirve**: ¿Para qué sirven las pruebas de integración en el flujo de persistencia de pedidos?
- **comoSeUsa**: ¿Cómo se usan las pruebas de integración para validar la persistencia de datos en DynamoDB?
- **erroresComunes**: ¿Cuáles son los errores comunes que pueden ocurrir durante las pruebas de integración contra DynamoDB y cómo se manejan?
- **queDecisionesImplica**: ¿Qué decisiones implica el diseño y la ejecución de pruebas de integración contra DynamoDB?

## Criterios de Evaluacion

- Configuración correcta del entorno de pruebas para interactuar con DynamoDB.
- Desarrollo de pruebas automatizadas que validen la escritura de datos en DynamoDB.
- Desarrollo de pruebas automatizadas que validen la lectura de datos desde DynamoDB.
- Integración y validación exitosa del flujo completo de persistencia de pedidos.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT) si no tenes un agente en el IDE. Declara lo mismo que AGENTS.md; los archivos que te devuelva el chat los guardas en esta carpeta respetando las rutas.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Mapa de componentes

El repo tiene una carpeta por componente. Cada uno se instala y verifica dentro de la suya.

| Componente | Carpeta | Rol | Capa | Track | Tecnologias |
|---|---|---|---|---|---|
| `app` | (raiz) | practice, chapter | testing | Automatizador | - |
| `infra-local` | (raiz) | support | infra | Compose local | DynamoDB |

## Orden de arranque

1. `docker compose up -d` (en la raiz) — levanta la infraestructura local.
2. `mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package` — componente `app` (practice, chapter).

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package && docker compose config -q
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
