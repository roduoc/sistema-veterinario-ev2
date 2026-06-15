# Sistema Veterinario

Proyecto academico en Java con Spring Boot para la asignatura Desarrollo FullStack 1.

Hecho por Vicente Zapata y Rodrigo Salinas | DSY1103

## Descripcion

Sistema para gestionar una clinica veterinaria mediante una arquitectura inicial de microservicios. El proyecto se organiza como un Maven multi-modulo: un API Gateway centraliza las rutas y cada endpoint original queda separado en su propio microservicio.

## Tecnologias

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Cloud Gateway
- Bean Validation
- MySQL
- Maven multi-modulo
- YAML para configuracion de servicios y rutas

## Estructura

```text
gateway-service/
veterinaria-domain/
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

Cada microservicio mantiene una estructura CSR:

```text
src/main/java/cl/duoc/veterinaria/<servicio>/
  controller/
  service/
  model/
  repository/
```

El modulo `veterinaria-domain` centraliza las entidades JPA `Animal`, `Dueno`, `Veterinario`, los repositorios Spring Data y el manejo comun de errores HTTP.

## Puertos

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

## Ejecucion Local

Desde un IDE como IntelliJ IDEA se puede abrir el proyecto raiz y ejecutar cada clase `Application` de los microservicios necesarios.

Desde terminal, si Maven esta instalado:

```text
mvn clean package
mvn -pl gateway-service spring-boot:run
mvn -pl listar-animales-service spring-boot:run
```

Para probar una ruta mediante el Gateway, primero se debe iniciar `gateway-service` y el microservicio destino.

## Configuracion YAML

Cada microservicio define su configuracion en `src/main/resources/application.yml`, incluyendo:

```text
spring.application.name
server.port
spring.datasource.url
spring.datasource.username
spring.datasource.password
spring.jpa.hibernate.ddl-auto
```

Las propiedades principales aceptan variables de entorno:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
SERVER_PORT
```

`asignar-veterinario-service` tambien configura:

```text
BUSCAR_ANIMAL_URL
```

Ese servicio consume por REST a `buscar-animal-service` antes de asignar un veterinario, validando que el animal exista en otro microservicio.
