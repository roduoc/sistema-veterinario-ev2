# Sistema Veterinario

Proyecto academico simple en Java con Spring Boot para la asignatura Desarrollo FullStack 1. 

Hecho por Vicente Zapata y Rodrigo Salinas | DSY1103


## Descripcion

Aplicacion para gestionar una clinica veterinaria mediante una sola aplicacion Spring Boot.

## Tecnologias

- Spring Web
- Spring Data JPA
- Hibernate
- Bean Validation
- MySQL con Laragon
- SLF4J incluido en Spring Boot

## Estructura

```text
src/main/java/cl/duoc/veterinaria/
  VeterinariaApplication.java
  model/
  repository/
  service/
  controller/
  exception/
```

## Ejecucion

Desde un IDE como IntelliJ IDEA:

1. Abrir el proyecto.
2. Esperar que Maven descargue las dependencias del `pom.xml`.
3. Ejecutar `VeterinariaApplication`.

Desde terminal, si Maven esta instalado:

```text
mvn spring-boot:run
```

La aplicacion corre en:

```text
http://localhost:3006
```

## Base De Datos

La aplicacion esta configurada para MySQL en Laragon:

```text
Host: localhost
Puerto: 3306
Base de datos: sistema_veterinario
Usuario: root
Password:
```

Spring Boot puede crear la base automaticamente por la opcion `createDatabaseIfNotExist=true`.
Si prefieres crearla manualmente en Laragon, ejecuta:

```sql
CREATE DATABASE sistema_veterinario;
```

## Endpoints

```text
GET    /listar-animales
GET    /buscar-animal?id=1
POST   /crear-animal?duenoId=1&veterinarioId=1
PUT    /actualizar-animal?id=1
DELETE /borrar-animal?id=1
POST   /crear-dueno
GET    /listar-duenos
POST   /crear-veterinario
GET    /listar-veterinarios
PUT    /asignar-veterinario?animalId=1&veterinarioId=1
```
