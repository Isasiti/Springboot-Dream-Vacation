# Dream Vacation (Spring Boot + React)

Aplicacion para gestionar vacaciones sonadas. Incluye backend REST con Spring Boot y frontend en React para login, registro y CRUD de vacaciones.

## Que hace cada parte

### Backend (`src/main/java/com/dreamvacation`)
- `DreamVacationApplication`: punto de arranque de Spring Boot.
- `controller/`: expone endpoints HTTP (`AuthController`, `VacacionController`).
- `service/`: contiene la logica de negocio (`AuthService`, `VacacionService`).
- `repository/`: acceso a datos con Spring Data JPA.
- `model/`: entidades JPA (`Usuario`, `Vacacion`).
- `dto/`: objetos para peticiones/respuestas de autenticacion.

### Configuracion (`src/main/resources`)
- `application.properties`: puerto del backend, conexion a PostgreSQL y parametros JPA.
- `data.sql`: datos de ejemplo iniciales.

### Frontend (`frontend`)
- App en React + Vite.
- Flujo incluido:
  - login de usuario
  - registro de usuario
  - listar vacaciones
  - crear, editar y eliminar vacaciones
- Archivo principal: `frontend/src/App.jsx`.

## Base de datos (PostgreSQL)

Se migro la configuracion de H2 a PostgreSQL con estos datos:

- Host: `localhost`
- Puerto: `5432`
- Base de datos: `dream_vacation`
- Usuario: `dream_vacation_user`
- Password: `IsjZbzOkzevnr1JMHREsj7zG3gaDJm9U`

Configuracion aplicada en `src/main/resources/application.properties`:

- `spring.datasource.url=jdbc:postgresql://localhost:5432/dream_vacation`
- `spring.datasource.username=dream_vacation_user`
- `spring.datasource.password=...`
- `spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect`

Tambien se actualizo `pom.xml` para usar el driver `org.postgresql:postgresql`.

## Endpoints principales

### Auth
- `POST /api/auth/login`
- `POST /api/auth/registrar`
- `GET /api/auth/usuario/{id}`

### Vacaciones
- `GET /api/vacaciones/usuario/{usuarioId}`
- `GET /api/vacaciones/{id}`
- `POST /api/vacaciones/usuario/{usuarioId}`
- `PUT /api/vacaciones/{id}`
- `DELETE /api/vacaciones/{id}`

## Como ejecutar

## 1) Backend

Requisitos:
- Java 17+
- Maven 3.6+
- PostgreSQL activo con la base/usuario indicados

Comandos:

```bash
mvn clean spring-boot:run
```

Backend disponible en:
- `http://localhost:8080`

## 2) Frontend React

```bash
cd frontend
npm install
npm run dev
```

Frontend disponible en:
- `http://localhost:5173`

## Notas

- El frontend consume la API en `http://localhost:8080/api`.
- Si cambias credenciales de PostgreSQL, actualiza `application.properties`.
- `target/` y `frontend/node_modules/` estan ignorados en git.
