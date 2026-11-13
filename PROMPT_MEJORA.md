# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener el proyecto completo y arrancable. Guarda los archivos que te devuelva en la
carpeta del repo, respetando exactamente las rutas.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/test/java/com/pragma/orders/config/DynamoDBConfig.java` — La fase "Configuración del entorno de pruebas" se implementa aqui: este archivo es el ejercicio.
- `src/test/java/com/pragma/orders/interactions/DynamoDBClient.java` — La fase "Desarrollo de pruebas de escritura" se implementa aqui: este archivo es el ejercicio.

## Mapa de componentes

El repo tiene una carpeta por componente. Cada uno se instala y verifica dentro de la suya.

| Componente | Carpeta | Rol | Capa | Track | Tecnologias |
|---|---|---|---|---|---|
| `app` | (raiz) | practice, chapter | testing | Automatizador | - |
| `infra-local` | (raiz) | support | infra | Compose local | DynamoDB |

## Orden de arranque

1. `docker compose up -d` (en la raiz) — levanta la infraestructura local.
2. `mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package` — componente `app` (practice, chapter).

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Boilerplate del stack que falta

Sin esto no compila ni arranca. Es andamiaje, no toca nada de lo pedagogico:

- **XSteps.java** — Sin los step definitions, los pasos Gherkin no tienen implementacion y Cucumber falla con "step undefined".

### Archivos que la arquitectura del reto declara y no estan

Creálos con implementacion real, en la capa que les corresponde:

- `src/test/java/resources/config/aws-config.properties`

## Como saber que terminaste

```bash
mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package && docker compose config -q
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Calidad de Software, Especialidad Automatizador, Tecnología Java Selenium, Advanced L2

### Brecha de conocimiento
Automatizar pruebas de integracion sobre una tabla DynamoDB, Automatiza pruebas de integracion que validan lecturas y escrituras en una tabla DynamoDB

### Misión / candidato
Cubrir el flujo de persistencia de pedidos

### Reto
- Tema: Automatizacion de pruebas de integracion contra DynamoDB
- Seniority: advanced-l2
- Tipo: practical
- Título: Automatización de pruebas de integración en DynamoDB
- Tiempo estimado: 2 semanas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Configuración del entorno de pruebas — objetivo: Preparar el entorno para ejecutar pruebas de integración contra DynamoDB. — entregable (NO resolver): Entorno de pruebas configurado y listo para ejecutar pruebas contra DynamoDB.
- Fase 2: Desarrollo de pruebas de escritura — objetivo: Crear y ejecutar pruebas automatizadas que validen la escritura de datos en DynamoDB. — entregable (NO resolver): Conjunto de pruebas automatizadas que validan la escritura de datos en DynamoDB.
- Fase 3: Desarrollo de pruebas de lectura — objetivo: Crear y ejecutar pruebas automatizadas que validen la lectura de datos desde DynamoDB. — entregable (NO resolver): Conjunto de pruebas automatizadas que validan la lectura de datos desde DynamoDB.
- Fase 4: Integración y validación del flujo completo — objetivo: Integrar y validar el flujo completo de persistencia de pedidos, incluyendo escritura y lectura. — entregable (NO resolver): Flujo completo de persistencia de pedidos validado mediante pruebas automatizadas.

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
- Después seguí al PASO 5 (entrega de archivos).

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
PASO 5 — Entrega de archivos
Entregá cada archivo con su ruta completa, respetando exactamente la estructura de rutas
indicada por los marcadores: un bloque por archivo, precedido por su ruta. No generes ZIPs
ni archivos adjuntos.
La entrega debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: Entregá los archivos completos al finalizar. No preguntes si el usuario
quiere recibirlos. Simplemente entregalos; No debes desplegar en el chat el resumen de lo que arreglaste, solo entregá los archivos.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del último archivo entregado
No preguntes si el usuario quiere los archivos: simplemente entregalos siempre
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
        <version>3.4.13</version>
        <relativePath/>
    </parent>

    <groupId>com.example</groupId>
    <artifactId>reto</artifactId>
    <version>1.0.0</version>
    <name>reto</name>

    <properties>
        <java.version>21</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>8.0.4</version>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-junit</artifactId>
            <version>8.0.4</version>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-core</artifactId>
            <version>3.9.8</version>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-cucumber</artifactId>
            <version>3.9.8</version>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-screenplay</artifactId>
            <version>3.9.8</version>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-screenplay-webdriver</artifactId>
            <version>3.9.8</version>
        </dependency>
        <dependency>
            <groupId>org.assertj</groupId>
            <artifactId>assertj-core</artifactId>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
        </dependency>
        <dependency>
            <groupId>software.amazon.awssdk</groupId>
            <artifactId>auth</artifactId>
            <version>2.59.0</version>
        </dependency>
        <dependency>
            <groupId>software.amazon.awssdk</groupId>
            <artifactId>dynamodb</artifactId>
            <version>2.59.0</version>
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

// === ARCHIVO: src/main/java/com/example/reto/Application.java ===
package com.example.reto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

// === ARCHIVO: src/test/resources/config/aws-config.properties ===
# Configuración de AWS para pruebas de integración con DynamoDB
# Las credenciales se obtienen desde variables de entorno o perfil AWS por defecto
# Nunca hardcodear credenciales en el repositorio

# Región donde se encuentra la tabla DynamoDB
aws.region=us-east-1

# Nombre de la tabla DynamoDB para pruebas
aws.dynamodb.table-name=OrdersTestTable

# Configuración para acceso local (opcional, solo para desarrollo)
# aws.dynamodb.endpoint=http://localhost:8000

// === ARCHIVO: src/test/java/resources/config/serenity.conf ===
webdriver {
    driver = chrome
    auto.download = true
    timeouts {
        implicit = 10000
        script = 30000
        page.load = 30000
    }
    capabilities {
        "goog:chromeOptions" {
            args = ["headless", "disable-gpu", "no-sandbox", "disable-dev-shm-usage"]
        }
    }
}

serenity {
    take.screenshots = AFTER_EACH_STEP
    compress.filenames = false
    project.name = "Reto Automatizacion DynamoDB"
    test.skip = false
    
    report {
        show.step.details = true
        generate.xlsx.reports = true
        generate.json.reports = true
        generate.html.reports = true
        generate.javadoc.reports = false
    }
    
    core {
        draft.report = true
        dry.run = false
    }
    
    logging {
        verbosity = NORMAL
        show.suite.labels = true
    }
    
    screenplay {
        cast = default
        actor.instructor = Instructor
        actor.librarian = Librarian
        actor.notifier = Notifier
    }
}

cucumber {
    plugin = ["pretty", "json:target/cucumber.json", "html:target/cucumber.html"]
    glue = ["com.pragma.orders.steps", "com.pragma.orders.runners"]
    features = "src/test/java/resources/features"
}

dynamodb {
    endpoint = ${DYNAMODB_ENDPOINT:http://localhost:8000}
    region = ${AWS_REGION:us-east-1}
    table.name = ${DYNAMODB_TABLE:orders}
    access.key = ${AWS_ACCESS_KEY_ID:local}
    secret.key = ${AWS_SECRET_ACCESS_KEY:local}
}

logging {
    level {
        root = INFO
        software.amazon.awssdk = WARN
        net.serenitybdd = DEBUG
    }
}

// === ARCHIVO: src/test/java/resources/features/orders_persistence.feature ===
# language: es
Característica: Persistencia de Pedidos en DynamoDB
  Como automatizador de pruebas
  Quiero validar las operaciones de escritura y lectura de pedidos en DynamoDB
  Para asegurar la integridad de los datos del sistema de gestión de pedidos

  # Fondo: configuración del entorno de pruebas
  Antecedentes:
    Dado que el sistema de gestión de pedidos está configurado para usar DynamoDB
    Y la tabla de pedidos está disponible en el endpoint configurado

  # Escenario 1: Escritura exitosa de un nuevo pedido
  @escritura
  @orden-exitosa
  Escenario: Guardar un nuevo pedido en DynamoDB
    Cuando creo un pedido con los siguientes datos:
      | orderId   | customerId | status    | productId | quantity | price    |
      | ORD-001   | CUST-001   | PENDING   | PROD-001  | 5        | 150.00   |
    Y guardo el pedido en DynamoDB
    Entonces el pedido debe ser almacenado correctamente
    Y debo poder recuperar el pedido por su orderId "ORD-001"
    Y los datos del pedido recuperado deben coincidir con los datos originales

  # Escenario 2: Escritura de pedido con múltiples items
  @escritura
  @orden-multiple-items
  Escenario: Guardar un pedido con múltiples productos
    Cuando creo un pedido con múltiples items:
      | orderId   | customerId | status    |
      | ORD-002   | CUST-002   | CONFIRMED |
    Y el pedido contiene los siguientes productos:
      | productId | quantity | price    |
      | PROD-001  | 3        | 100.00   |
      | PROD-002  | 2        | 250.50   |
      | PROD-003  | 1        | 75.25    |
    Y guardo el pedido en DynamoDB
    Entonces el pedido debe ser almacenado correctamente
    Y debo poder recuperar el pedido por su orderId "ORD-002"
    Y la cantidad total de items debe ser 3

  # Escenario 3: Lectura de pedido existente
  @lectura
  @pedido-existente
  Escenario: Recuperar un pedido existente de DynamoDB
    Dado que existe un pedido guardado en DynamoDB:
      | orderId   | customerId | status    | productId | quantity | price    |
      | ORD-003   | CUST-003   | SHIPPED   | PROD-003  | 10       | 50.00    |
    Cuando busco el pedido por orderId "ORD-003"
    Entonces debo obtener los datos completos del pedido
    Y el customerId debe ser "CUST-003"
    Y el status debe ser "SHIPPED"

  # Escenario 4: Actualización de estado de pedido
  @actualizacion
  @cambio-estado
  Escenario: Actualizar el estado de un pedido existente
    Dado que existe un pedido guardado en DynamoDB:
      | orderId   | customerId | status    |
      | ORD-004   | CUST-004   | PENDING   |
    Cuando actualizo el estado del pedido "ORD-004" a "PROCESSING"
    Y busco el pedido por orderId "ORD-004"
    Entonces el estado del pedido debe ser "PROCESSING"
    Y los demás datos deben permanecer sin cambios

  # Escenario 5: Eliminación de pedido
  @eliminacion
  @borrar-pedido
  Escenario: Eliminar un pedido de DynamoDB
    Dado que existe un pedido guardado en DynamoDB:
      | orderId   | customerId | status    |
      | ORD-005   | CUST-005   | CANCELLED |
    Cuando elimino el pedido con orderId "ORD-005"
    Y busco el pedido por orderId "ORD-005"
    Entonces no debo obtener ningún resultado
    Y el pedido no debe existir en la tabla

  # Escenario 6: Flujo completo de persistencia
  @flujo-completo
  @integracion
  Escenario: Validar el flujo completo de persistencia de pedidos
    Cuando creo un nuevo pedido con los siguientes datos:
      | orderId   | customerId | status    | productId | quantity | price    |
      | ORD-006   | CUST-006   | PENDING   | PROD-006  | 8        | 200.00   |
    Y guardo el pedido en DynamoDB
    Entonces el pedido debe ser almacenado correctamente
    Cuando actualizo el estado del pedido "ORD-006" a "CONFIRMED"
    Y busco el pedido por orderId "ORD-006"
    Entonces el estado del pedido debe ser "CONFIRMED"
    Cuando actualizo el estado del pedido "ORD-006" a "SHIPPED"
    Y busco el pedido por orderId "ORD-006"
    Entonces el estado del pedido debe ser "SHIPPED"
    Y los datos del cliente deben ser "CUST-006"

  # Escenario 7: Validación de datos con valores límite
  @escritura
  @valores-limite
  Escenario: Guardar pedido con cantidad máxima y precio mínimo
    Cuando creo un pedido con los siguientes datos:
      | orderId   | customerId | status    | productId | quantity | price    |
      | ORD-007   | CUST-007   | PENDING   | PROD-007  | 9999     | 0.01     |
    Y guardo el pedido en DynamoDB
    Entonces el pedido debe ser almacenado correctamente
    Y debo poder recuperar el pedido por su orderId "ORD-007"
    Y la cantidad debe ser 9999
    Y el precio debe ser 0.01

// === ARCHIVO: src/test/java/com/pragma/orders/runners/RunCucumberTest.java ===
package com.pragma.orders.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.pragma.orders.steps", "com.pragma.orders.hooks"},
        plugin = {
                "pretty",
                "html:target/serenity-reports/cucumber-report.html",
                "json:target/serenity-reports/cucumber-report.json",
                "rerun:target/rerun.txt"
        },
        tags = "@integration",
        dryRun = false
)
public class RunCucumberTest {
}

// === ARCHIVO: src/test/java/com/pragma/orders/config/DynamoDBConfig.java ===
// TODO(reto): archivo stubbeado; implementa los cuerpos.
package com.pragma.orders.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

@Configuration
public class DynamoDBConfig {

    @Value("${aws.access.key.id}")
    private String accessKeyId;

    @Value("${aws.secret.access.key}")
    private String secretAccessKey;

    @Value("${aws.region}")
    private String region;

    @Value("${aws.dynamodb.endpoint:#{null}}")
    private String dynamoDbEndpoint;

    @Bean
    public DynamoDbClient dynamoDbClient() {
      throw new UnsupportedOperationException("TODO(reto): dynamoDbClient");
    }
}

// === ARCHIVO: src/test/java/com/pragma/orders/models/OrderData.java ===
package com.pragma.orders.models;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record OrderData(
    String orderId,
    String customerId,
    List<OrderItem> items,
    String status,
    Instant createdAt
) {
    public record OrderItem(
        String productId,
        Integer quantity,
        BigDecimal price
    ) {}
}

// === ARCHIVO: src/test/java/com/pragma/orders/interactions/DynamoDBClient.java ===
package com.pragma.orders.interactions;

import com.pragma.orders.models.OrderData;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

public class DynamoDBClient implements Interaction {

    private final String tableName;
    private final DynamoDbClient dynamoDbClient;

    public DynamoDBClient(String tableName) {
        this.tableName = tableName;
        this.dynamoDbClient = DynamoDbClient.builder()
                .region(Region.US_EAST_1)
                .build();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        throw new UnsupportedOperationException("TODO(reto): implementa");
    }

    public void saveOrder(OrderData order) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("orderId", AttributeValue.builder().s(order.orderId()).build());
        item.put("customerId", AttributeValue.builder().s(order.customerId()).build());
        item.put("status", AttributeValue.builder().s(order.status()).build());
        item.put("createdAt", AttributeValue.builder().s(order.createdAt().toString()).build());

        List<Map<String, AttributeValue>> itemsList = new ArrayList<>();
        for (OrderData.OrderItem orderItem : order.items()) {
            Map<String, AttributeValue> itemMap = new HashMap<>();
            itemMap.put("productId", AttributeValue.builder().s(orderItem.productId()).build());
            itemMap.put("quantity", AttributeValue.builder().n(orderItem.quantity().toString()).build());
            itemMap.put("price", AttributeValue.builder().n(orderItem.price().toString()).build());
            itemsList.add(itemMap);
        }

        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(item)
                .build();

        dynamoDbClient.putItem(request);
    }

    public OrderData getOrder(String orderId) {
        GetItemRequest request = GetItemRequest.builder()
                .tableName(tableName)
                .key(Map.of("orderId", AttributeValue.builder().s(orderId).build()))
                .build();

        Map<String, AttributeValue> result = dynamoDbClient.getItem(request).item();

        if (result == null || result.isEmpty()) {
            return null;
        }

        String customerId = result.get("customerId").s();
        String status = result.get("status").s();
        Instant createdAt = Instant.parse(result.get("createdAt").s());

        List<OrderData.OrderItem> items = new ArrayList<>();
        if (result.containsKey("items")) {
            List<AttributeValue> itemsAttr = result.get("items").l();
            for (AttributeValue itemAttr : itemsAttr) {
                Map<String, AttributeValue> itemMap = itemAttr.m();
                String productId = itemMap.get("productId").s();
                Integer quantity = Integer.parseInt(itemMap.get("quantity").n());
                BigDecimal price = new BigDecimal(itemMap.get("price").n());
                items.add(new OrderData.OrderItem(productId, quantity, price));
            }
        }

        return new OrderData(orderId, customerId, items, status, createdAt);
    }

    public void deleteOrder(String orderId) {
        DeleteItemRequest request = DeleteItemRequest.builder()
                .tableName(tableName)
                .key(Map.of("orderId", AttributeValue.builder().s(orderId).build()))
                .build();

        dynamoDbClient.deleteItem(request);
    }

    public boolean tableExists() {
        try {
            DescribeTableRequest request = DescribeTableRequest.builder()
                    .tableName(tableName)
                    .build();
            dynamoDbClient.describeTable(request);
            return true;
        } catch (ResourceNotFoundException e) {
            return false;
        }
    }

    public void createTableIfNotExists() {
        if (!tableExists()) {
            CreateTableRequest request = CreateTableRequest.builder()
                    .tableName(tableName)
                    .keySchema(KeySchemaElement.builder()
                            .attributeName("orderId")
                            .keyType(KeyType.HASH)
                            .build())
                    .attributeDefinitions(AttributeDefinition.builder()
                            .attributeName("orderId")
                            .attributeType(ScalarAttributeType.S)
                            .build())
                    .billingMode(BillingMode.PAY_PER_REQUEST)
                    .build();

            dynamoDbClient.createTable(request);

            waitForTableToBeActive();
        }
    }

    private void waitForTableToBeActive() {
        int maxRetries = 30;
        int retryCount = 0;

        while (retryCount < maxRetries) {
            try {
                DescribeTableRequest request = DescribeTableRequest.builder()
                        .tableName(tableName)
                        .build();
                String status = dynamoDbClient.describeTable(request).table().tableStatusAsString();

                if ("ACTIVE".equals(status)) {
                    return;
                }

                Thread.sleep(1000);
                retryCount++;
            } catch (Exception e) {
                retryCount++;
                if (retryCount >= maxRetries) {
                    throw new RuntimeException("Table did not become active: " + tableName, e);
                }
            }
        }
    }

    public void cleanupTable() {
        ScanRequest scanRequest = ScanRequest.builder()
                .tableName(tableName)
                .build();

        ScanResponse response = dynamoDbClient.scan(scanRequest);

        for (Map<String, AttributeValue> item : response.items()) {
            String orderId = item.get("orderId").s();
            deleteOrder(orderId);
        }
    }
}

// === ARCHIVO: src/test/java/com/pragma/orders/tasks/CreateOrderTask.java ===
package com.pragma.orders.tasks;

import com.pragma.orders.interactions.DynamoDBClient;
import com.pragma.orders.models.OrderData;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class CreateOrderTask implements Task {

    private final String orderId;
    private final String customerId;
    private final List<OrderData.OrderItem> items;
    private final String status;
    private final Instant createdAt;
    private final String tableName;

    public CreateOrderTask(String orderId, String customerId, List<OrderData.OrderItem> items, 
                           String status, Instant createdAt, String tableName) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.items = items;
        this.status = status;
        this.createdAt = createdAt;
        this.tableName = tableName;
    }

    public static CreateOrderBuilder withOrderId(String orderId) {
        return new CreateOrderBuilder(orderId);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        DynamoDBClient dynamoDBClient = new DynamoDBClient(tableName);
        
        OrderData orderData = new OrderData(orderId, customerId, items, status, createdAt);
        dynamoDBClient.saveOrder(orderData);
    }

    public static class CreateOrderBuilder {
        private final String orderId;
        private String customerId;
        private List<OrderData.OrderItem> items = new ArrayList<>();
        private String status = "PENDING";
        private Instant createdAt = Instant.now();
        private String tableName = "orders";

        public CreateOrderBuilder(String orderId) {
            this.orderId = orderId;
        }

        public CreateOrderBuilder forCustomer(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public CreateOrderBuilder withItems(List<OrderData.OrderItem> items) {
            this.items = items;
            return this;
        }

        public CreateOrderBuilder withItem(String productId, int quantity, BigDecimal price) {
            this.items.add(new OrderData.OrderItem(productId, quantity, price));
            return this;
        }

        public CreateOrderBuilder withStatus(String status) {
            this.status = status;
            return this;
        }

        public CreateOrderBuilder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public CreateOrderBuilder inTable(String tableName) {
            this.tableName = tableName;
            return this;
        }

        public CreateOrderTask build() {
            return new CreateOrderTask(orderId, customerId, items, status, createdAt, tableName);
        }
    }
}

// === ARCHIVO: docker-compose.yml ===
# Infraestructura local del reto. SOLO desarrollo: credenciales de ejemplo (local-dev-only), puertos en loopback.
# Levantar: docker compose up -d --wait
name: infra-local
services:
  dynamodb-local:
    image: "amazon/dynamodb-local:2.5.2"
    command: ["-jar","DynamoDBLocal.jar","-sharedDb","-inMemory"]
    ports:
      - "127.0.0.1:8000:8000"
    healthcheck:
      test: ["CMD-SHELL","bash -c ':> /dev/tcp/127.0.0.1/8000' || exit 1"]
      interval: 10s
      timeout: 5s
      retries: 10
      start_period: 5s


// === ARCHIVO: infra-local/README.md ===
# Infraestructura local

Servicios de soporte para el reto, definidos en `docker-compose.yml` (raiz del repo).

```bash
docker compose up -d --wait
```

| Servicio | Imagen | Puertos (host) |
|---|---|---|
| DynamoDB Local | `amazon/dynamodb-local:2.5.2` | 8000 |

Credenciales: solo de desarrollo local (`local-dev-only`, `local`). No son secretos ni deben reutilizarse.
Los scripts de `infra-local/` (tablas, topics) se ejecutan con el compose en estado healthy.


// === ARCHIVO: infra-local/dynamodb/create-tables.sh ===
#!/usr/bin/env bash
# Crea las tablas en DynamoDB Local (idempotente). Credenciales dummy: DynamoDB Local no valida firmas.
set -euo pipefail

export AWS_ACCESS_KEY_ID=local
export AWS_SECRET_ACCESS_KEY=local
export AWS_REGION=us-east-1
ENDPOINT="${DYNAMODB_ENDPOINT:-http://localhost:8000}"

create_table() {
  local name="$1" pk="$2" sk="${3:-}"
  if aws dynamodb describe-table --table-name "$name" --endpoint-url "$ENDPOINT" >/dev/null 2>&1; then
    echo "Tabla $name ya existe"
    return
  fi
  local attrs="AttributeName=$pk,AttributeType=S"
  local keys="AttributeName=$pk,KeyType=HASH"
  if [ -n "$sk" ]; then
    attrs="$attrs AttributeName=$sk,AttributeType=S"
    keys="$keys AttributeName=$sk,KeyType=RANGE"
  fi
  # shellcheck disable=SC2086
  aws dynamodb create-table --table-name "$name" --attribute-definitions $attrs --key-schema $keys \
    --billing-mode PAY_PER_REQUEST --endpoint-url "$ENDPOINT" >/dev/null
  echo "Tabla $name creada"
}

create_table "app-items" "id"

```
