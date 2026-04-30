# Dream Vacation (Spring Boot + React)

Aplicacion de ejemplo donde un usuario guarda un destino turistico y el sistema completa informacion del pais usando Rest Countries:
- pais
- ciudad capital
- poblacion actual

## Estructura del proyecto

- `backend`: API REST en Spring Boot
- `frontend`: app React (Vite)
- `docker-compose.yml`: orquestacion de base de datos, backend y frontend

## Integracion Rest Countries

Al crear o actualizar una vacacion, el backend consulta:

`https://restcountries.com/v3.1/name/{pais}?fields=name,capital,population`

Con eso, la API guarda automaticamente la capital y la poblacion actual del pais.

## Variables de entorno

Las credenciales de base de datos y puertos ya no estan hardcodeadas en el README.
Ahora se leen desde el archivo `.env` en la raiz del proyecto.

Variables usadas:
- `DB_HOST`
- `DB_PORT`
- `DB_NAME`
- `DB_USER`
- `DB_PASSWORD`
- `BACKEND_PORT`
- `FRONTEND_PORT`

## Ejecucion local sin Docker

### Backend

```bash
cd backend
mvn clean spring-boot:run
```

### Frontend

```bash
cd frontend
npm install
npm run dev
```

## Ejecucion con Docker Compose

```bash
docker compose up --build
```

Servicios:
- Frontend: `http://localhost:5173`
- Backend: `http://localhost:8080`
- PostgreSQL: `localhost:5432`

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
