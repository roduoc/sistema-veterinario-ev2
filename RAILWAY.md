# Despliegue En Railway

Railway debe desplegar este repositorio como 11 servicios de aplicacion y una base MySQL.

## 1. Crear Proyecto

1. Crear un proyecto nuevo en Railway.
2. Agregar una base de datos MySQL.
3. Agregar 11 servicios desde el mismo repositorio GitHub.
4. En cada servicio configurar `RAILWAY_DOCKERFILE_PATH` con el Dockerfile correspondiente.

## 2. Dockerfile Por Servicio

```text
gateway-service                 RAILWAY_DOCKERFILE_PATH=Dockerfile.gateway-service
listar-animales-service         RAILWAY_DOCKERFILE_PATH=Dockerfile.listar-animales-service
buscar-animal-service           RAILWAY_DOCKERFILE_PATH=Dockerfile.buscar-animal-service
crear-animal-service            RAILWAY_DOCKERFILE_PATH=Dockerfile.crear-animal-service
actualizar-animal-service       RAILWAY_DOCKERFILE_PATH=Dockerfile.actualizar-animal-service
borrar-animal-service           RAILWAY_DOCKERFILE_PATH=Dockerfile.borrar-animal-service
crear-dueno-service             RAILWAY_DOCKERFILE_PATH=Dockerfile.crear-dueno-service
listar-duenos-service           RAILWAY_DOCKERFILE_PATH=Dockerfile.listar-duenos-service
crear-veterinario-service       RAILWAY_DOCKERFILE_PATH=Dockerfile.crear-veterinario-service
listar-veterinarios-service     RAILWAY_DOCKERFILE_PATH=Dockerfile.listar-veterinarios-service
asignar-veterinario-service     RAILWAY_DOCKERFILE_PATH=Dockerfile.asignar-veterinario-service
```

Railway inyecta automaticamente `PORT`; los `application.yml` ya usan:

```text
server.port=${PORT:${SERVER_PORT:<puerto-local>}}
```

## 3. Variables MySQL

En cada microservicio con JPA configurar:

```text
DB_URL=jdbc:mysql://${{MySQL.MYSQLHOST}}:${{MySQL.MYSQLPORT}}/${{MySQL.MYSQLDATABASE}}?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=America/Santiago
DB_USERNAME=${{MySQL.MYSQLUSER}}
DB_PASSWORD=${{MySQL.MYSQLPASSWORD}}
```

No configurar estas variables en `gateway-service`, porque el Gateway no accede a la base de datos.

## 4. URLs Internas Entre Servicios

Primero desplegar los 10 microservicios de backend. Luego configurar estas variables en `gateway-service`.

Usar los dominios internos o publicos que Railway asigne a cada servicio:

```text
LISTAR_ANIMALES_URL=<url-listar-animales-service>
BUSCAR_ANIMAL_URL=<url-buscar-animal-service>
CREAR_ANIMAL_URL=<url-crear-animal-service>
ACTUALIZAR_ANIMAL_URL=<url-actualizar-animal-service>
BORRAR_ANIMAL_URL=<url-borrar-animal-service>
CREAR_DUENO_URL=<url-crear-dueno-service>
LISTAR_DUENOS_URL=<url-listar-duenos-service>
CREAR_VETERINARIO_URL=<url-crear-veterinario-service>
LISTAR_VETERINARIOS_URL=<url-listar-veterinarios-service>
ASIGNAR_VETERINARIO_URL=<url-asignar-veterinario-service>
```

En `asignar-veterinario-service` configurar:

```text
BUSCAR_ANIMAL_URL=<url-buscar-animal-service>
```

## 5. Servicio Publico Principal

Publicar dominio publico solo para `gateway-service`. Las rutas principales seran:

```text
GET    https://<gateway>.up.railway.app/api/animales/listar
GET    https://<gateway>.up.railway.app/api/animales/buscar?id=1
POST   https://<gateway>.up.railway.app/api/animales/crear?duenoId=1&veterinarioId=1
PUT    https://<gateway>.up.railway.app/api/animales/actualizar?id=1
DELETE https://<gateway>.up.railway.app/api/animales/borrar?id=1
POST   https://<gateway>.up.railway.app/api/duenos/crear
GET    https://<gateway>.up.railway.app/api/duenos/listar
POST   https://<gateway>.up.railway.app/api/veterinarios/crear
GET    https://<gateway>.up.railway.app/api/veterinarios/listar
PUT    https://<gateway>.up.railway.app/api/veterinarios/asignar?animalId=1&veterinarioId=1
```

## 6. Swagger

Cada microservicio expone Swagger en:

```text
https://<servicio>.up.railway.app/swagger-ui/index.html
```

Para defensa, se puede publicar temporalmente el dominio del microservicio que se quiera mostrar.
