# Despliegue En Railway

Railway debe recibir este proyecto como repositorio GitHub. La arquitectura usa 11 servicios de aplicacion y una base MySQL.

## 1. Subir El Proyecto A GitHub

Desde la carpeta del proyecto:

```text
git init
git add .
git commit -m "Prepara despliegue Railway de microservicios"
git branch -M main
git remote add origin <url-del-repo-del-companero>
git push -u origin main
```

## 2. Crear Proyecto En Railway

1. Crear un proyecto nuevo en Railway.
2. Agregar una base de datos MySQL.
3. Agregar un servicio desde GitHub por cada microservicio.
4. Cada servicio debe apuntar al mismo repositorio, pero con distinto `RAILWAY_DOCKERFILE_PATH`.

## 3. Dockerfile Por Servicio

Configurar estas variables por servicio:

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

## 4. Variables De Base De Datos

En cada microservicio que usa JPA configurar:

```text
DB_URL=jdbc:mysql://<MYSQLHOST>:<MYSQLPORT>/<MYSQLDATABASE>?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=America/Santiago
DB_USERNAME=<MYSQLUSER>
DB_PASSWORD=<MYSQLPASSWORD>
```

Los valores se copian desde las variables del servicio MySQL de Railway.

No se configura `DB_URL` en `gateway-service` porque no accede directo a la base de datos.

## 5. URLs Entre Servicios

Despues de crear los servicios de backend, generar dominio publico o usar las URLs internas disponibles en Railway y configurar el Gateway:

```text
LISTAR_ANIMALES_URL=<url-de-listar-animales-service>
BUSCAR_ANIMAL_URL=<url-de-buscar-animal-service>
CREAR_ANIMAL_URL=<url-de-crear-animal-service>
ACTUALIZAR_ANIMAL_URL=<url-de-actualizar-animal-service>
BORRAR_ANIMAL_URL=<url-de-borrar-animal-service>
CREAR_DUENO_URL=<url-de-crear-dueno-service>
LISTAR_DUENOS_URL=<url-de-listar-duenos-service>
CREAR_VETERINARIO_URL=<url-de-crear-veterinario-service>
LISTAR_VETERINARIOS_URL=<url-de-listar-veterinarios-service>
ASIGNAR_VETERINARIO_URL=<url-de-asignar-veterinario-service>
```

En `asignar-veterinario-service` configurar tambien:

```text
BUSCAR_ANIMAL_URL=<url-de-buscar-animal-service>
```

## 6. Dominio Publico Principal

Solo `gateway-service` necesita dominio publico para la defensa. Las rutas principales quedan:

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

## 7. Swagger

Cada backend expone Swagger en:

```text
https://<servicio>.up.railway.app/swagger-ui/index.html
```

Si no se publican dominios para todos los backends, Swagger se puede mostrar localmente o publicando temporalmente el servicio que se quiera defender.
