# Sistema Veterinario

Proyecto academico en Java con Spring Boot para la asignatura Desarrollo FullStack 1.

Integrantes: Vicente Zapata y Rodrigo Salinas | DSY1103

## Descripcion

Sistema de gestion para una clinica veterinaria implementado con arquitectura de microservicios. El sistema permite registrar y consultar duenos, veterinarios y animales, ademas de asignar veterinarios a animales registrados.

## Arquitectura

El proyecto usa Maven multi-modulo. Cada endpoint original fue separado en un microservicio independiente con estructura CSR: Controller, Service, Repository/Model.

```text
veterinaria-domain/
gateway-service/
listar-animales-service/
buscar-animal-service/
crear-animal-service/
actualizar-animal-service/
borrar-animal-service/
crear-dueno-service/
listar-duenos-service/
crear-veterinario-service/
listar-veterinarios-service/
asignar-veterinario-service/
```

`veterinaria-domain` centraliza entidades JPA, repositorios y manejo comun de errores:

```text
Animal
Dueno
Veterinario
AnimalRepository
DuenoRepository
VeterinarioRepository
GlobalExceptionHandler
```

## Tecnologias

- Java 17
- Spring Boot 3.3.5
- Spring Web
- Spring Data JPA
- Spring Cloud Gateway
- Spring WebClient
- MySQL
- Bean Validation
- Swagger/OpenAPI con Springdoc
- JUnit 5 y Mockito
- JaCoCo
- Docker y Docker Compose
- YAML para configuracion

## Microservicios

```text
gateway-service                 8080
listar-animales-service         8081
buscar-animal-service           8082
crear-animal-service            8083
actualizar-animal-service       8084
borrar-animal-service           8085
crear-dueno-service             8086
listar-duenos-service           8087
crear-veterinario-service       8088
listar-veterinarios-service     8089
asignar-veterinario-service     8090
```

## Rutas Del Gateway

```text
GET    /api/animales/listar
GET    /api/animales/buscar?id=1
POST   /api/animales/crear?duenoId=1&veterinarioId=1
PUT    /api/animales/actualizar?id=1
DELETE /api/animales/borrar?id=1
POST   /api/duenos/crear
GET    /api/duenos/listar
POST   /api/veterinarios/crear
GET    /api/veterinarios/listar
PUT    /api/veterinarios/asignar?animalId=1&veterinarioId=1
```

## Comunicacion REST

`asignar-veterinario-service` consume a `buscar-animal-service` mediante `WebClient` antes de asignar un veterinario. Esto valida interoperabilidad entre microservicios y manejo de errores remotos.

Variable usada:

```text
BUSCAR_ANIMAL_URL=http://localhost:8082
```

## Swagger/OpenAPI

Cada microservicio expone Swagger UI en:

```text
http://localhost:<puerto>/swagger-ui/index.html
```

Ejemplos:

```text
http://localhost:8081/swagger-ui/index.html
http://localhost:8082/swagger-ui/index.html
http://localhost:8086/swagger-ui/index.html
http://localhost:8090/swagger-ui/index.html
```

Los controllers incluyen descripciones de endpoints, codigos de respuesta y proposito funcional.

## Configuracion YAML

Cada servicio usa `application.yml` con:

```text
spring.application.name
server.port
spring.datasource.url
spring.datasource.username
spring.datasource.password
spring.jpa.hibernate.ddl-auto
```

Variables de entorno principales:

```text
SERVER_PORT
DB_URL
DB_USERNAME
DB_PASSWORD
BUSCAR_ANIMAL_URL
LISTAR_ANIMALES_URL
CREAR_ANIMAL_URL
ACTUALIZAR_ANIMAL_URL
BORRAR_ANIMAL_URL
CREAR_DUENO_URL
LISTAR_DUENOS_URL
CREAR_VETERINARIO_URL
LISTAR_VETERINARIOS_URL
ASIGNAR_VETERINARIO_URL
```

## Pruebas Unitarias

Las pruebas estan en `src/test/java` dentro de cada microservicio. Cubren servicios y reglas de negocio con estructura Given-When-Then, mocks de repositorios y asserts.

Ejecutar pruebas:

```text
mvn clean test
```

Ejecutar pruebas con reporte de cobertura JaCoCo:

```text
mvn clean verify
```

Los reportes quedan en:

```text
<microservicio>/target/site/jacoco/index.html
```

## Ejecucion Local Con Maven

Requisitos:

```text
JDK 17
Maven
MySQL local o Laragon
```

Base de datos local:

```text
Host: localhost
Puerto: 3306
Base: sistema_veterinario
Usuario: root
Password:
```

Ejecutar un servicio:

```text
mvn -pl listar-animales-service -am spring-boot:run
```

Ejecutar Gateway:

```text
mvn -pl gateway-service spring-boot:run
```

## Ejecucion Con Docker

Levantar MySQL, Gateway y todos los microservicios:

```text
docker compose up --build
```

Detener:

```text
docker compose down
```

Detener y borrar volumen de base de datos:

```text
docker compose down -v
```

## Despliegue Remoto

El proyecto incluye `render.yaml` como base para despliegue en Render usando Docker. Para un despliegue completo se deben crear servicios web por cada microservicio y configurar las variables de entorno equivalentes a las usadas en `docker-compose.yml`.

## Gestion De Versiones

Se recomienda subir el proyecto a GitHub con commits tecnicos y progresivos, por ejemplo:

```text
Implementa arquitectura base de microservicios
Agrega documentacion Swagger a endpoints
Agrega pruebas unitarias con Mockito
Configura despliegue local con Docker Compose
```

## Defensa Tecnica

Puntos clave para explicar:

- Cada endpoint se separo en un microservicio independiente.
- El Gateway centraliza rutas y reescribe paths hacia cada servicio.
- `veterinaria-domain` evita duplicar entidades y repositorios.
- Las reglas de negocio viven en servicios, no en controllers.
- `asignar-veterinario-service` demuestra comunicacion REST con WebClient.
- Swagger documenta endpoints, respuestas y modelos.
- Las pruebas usan mocks para repositorios y validan reglas de negocio.
- YAML separa puertos, datasource y URLs entre servicios.
- Docker Compose levanta MySQL y todos los microservicios localmente.
