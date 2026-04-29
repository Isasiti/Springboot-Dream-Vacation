# Dream Vacation - Proyecto Spring Boot

Aplicación web para gestionar sueños de vacaciones. Los usuarios pueden registrarse, hacer login y gestionar sus vacaciones soñadas con información de país, ciudad y lugar turístico.

## Características

- **Autenticación de Usuario**: Login seguro con usuario y contraseña
- **Registro**: Crear nuevas cuentas de usuario
- **Gestión de Vacaciones**: Crear, leer, actualizar y eliminar vacaciones
- **Base de Datos Relacional**: Usuario (1:N) Vacación
- **API RESTful**: Endpoints bien definidos para todas las operaciones

## Estructura del Proyecto

```
src/
├── main/
│   ├── java/com/dreamvacation/
│   │   ├── DreamVacationApplication.java      # Clase principal
│   │   ├── model/
│   │   │   ├── Usuario.java                   # Entidad Usuario
│   │   │   └── Vacacion.java                  # Entidad Vacación
│   │   ├── repository/
│   │   │   ├── UsuarioRepository.java         # DAO para Usuario
│   │   │   └── VacacionRepository.java        # DAO para Vacación
│   │   ├── service/
│   │   │   ├── AuthService.java               # Lógica de autenticación
│   │   │   └── VacacionService.java           # Lógica de vacaciones
│   │   ├── controller/
│   │   │   ├── AuthController.java            # Endpoints de auth
│   │   │   └── VacacionController.java        # Endpoints de vacaciones
│   │   └── dto/
│   │       ├── LoginRequest.java              # DTO para login
│   │       └── LoginResponse.java             # DTO para respuesta
│   └── resources/
│       ├── application.properties              # Configuración
│       └── data.sql                            # Datos iniciales
└── pom.xml                                     # Dependencias Maven
```

## Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.1.5**
- **Spring Data JPA**
- **H2 Database** (Base de datos en memoria)
- **Lombok** (Reducir boilerplate)
- **Maven**

## Instalación y Ejecución

### Requisitos Previos
- JDK 17+
- Maven 3.6+

### Pasos

1. **Clonar o navegar al proyecto**
```bash
cd Springboot-dream-vacation
```

2. **Compilar el proyecto**
```bash
mvn clean compile
```

3. **Ejecutar la aplicación**
```bash
mvn spring-boot:run
```

La aplicación estará disponible en: `http://localhost:8080`

## Endpoints de la API

### Autenticación

#### Login
```
POST /api/auth/login
Content-Type: application/json

{
    "nombreUsuario": "juan",
    "contrasena": "password123"
}
```

**Respuesta (200 OK):**
```json
{
    "usuarioId": 1,
    "nombreUsuario": "juan",
    "email": "juan@example.com",
    "mensaje": "Login exitoso",
    "exitoso": true
}
```

#### Registrar Usuario
```
POST /api/auth/registrar
Content-Type: application/json

{
    "nombreUsuario": "nuevouser",
    "contrasena": "mipassword",
    "email": "nuevo@example.com"
}
```

#### Obtener Usuario
```
GET /api/auth/usuario/{id}
```

### Vacaciones

#### Obtener Vacaciones de un Usuario
```
GET /api/vacaciones/usuario/{usuarioId}
```

#### Obtener Vacación Específica
```
GET /api/vacaciones/{id}
```

#### Crear Vacación
```
POST /api/vacaciones/usuario/{usuarioId}
Content-Type: application/json

{
    "pais": "Argentina",
    "ciudad": "Buenos Aires",
    "lugarTuristico": "Teatro Colón",
    "descripcion": "Magnífico teatro histórico"
}
```

#### Actualizar Vacación
```
PUT /api/vacaciones/{id}
Content-Type: application/json

{
    "pais": "Argentina",
    "ciudad": "Mendoza",
    "lugarTuristico": "Cerro de los Siete Colores",
    "descripcion": "Cerro colorido en Purmamarca"
}
```

#### Eliminar Vacación
```
DELETE /api/vacaciones/{id}
```

## Usuarios de Prueba

La base de datos se precarga con los siguientes usuarios:

| Usuario | Contraseña | Email |
|---------|-----------|-------|
| juan | password123 | juan@example.com |
| maria | pass456 | maria@example.com |
| carlos | mipassword789 | carlos@example.com |

## Base de Datos

### Esquema

#### Tabla: usuarios
| Campo | Tipo | Restricciones |
|-------|------|--------------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT |
| nombre_usuario | VARCHAR(100) | NOT NULL, UNIQUE |
| contrasena | VARCHAR(255) | NOT NULL |
| email | VARCHAR(100) | NOT NULL |

#### Tabla: vacaciones
| Campo | Tipo | Restricciones |
|-------|------|--------------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT |
| pais | VARCHAR(100) | NOT NULL |
| ciudad | VARCHAR(100) | NOT NULL |
| lugar_turistico | VARCHAR(150) | NOT NULL |
| descripcion | VARCHAR(255) | - |
| usuario_id | BIGINT | NOT NULL, FOREIGN KEY |

## Próximos Pasos para Desarrollo

1. **Seguridad**
   - Implementar BCrypt para encriptación de contraseñas
   - Agregar JWT (JSON Web Tokens) para sesiones
   - Implementar Spring Security

2. **Frontend**
   - Crear interfaz web con HTML/CSS/JavaScript
   - Integrar con React o Angular
   - Formularios de login y registro

3. **Validación**
   - Agregar validaciones con Jakarta Validation
   - Mensajes de error más detallados

4. **Testing**
   - Crear pruebas unitarias
   - Pruebas de integración

5. **Base de Datos**
   - Cambiar de H2 a PostgreSQL/MySQL en producción
   - Implementar migraciones con Flyway

6. **Funcionalidades Adicionales**
   - Editar perfil de usuario
   - Calificaciones y comentarios en vacaciones
   - Búsqueda de vacaciones por país/ciudad
   - Galería de imágenes

## Console H2

Para acceder a la consola H2 (interfaz web para ver la BD):
```
http://localhost:8080/h2-console
```

- URL JDBC: `jdbc:h2:mem:dreamvacationdb`
- Usuario: `sa`
- Contraseña: (dejar vacío)

## Notas de Desarrollo

- La aplicación usa H2 Database (en memoria) para desarrollo. Los datos se pierden al reiniciar.
- Las contraseñas se almacenan en texto plano. En producción, usar BCrypt o similar.
- Se incluye CORS habilitado para desarrollo (`@CrossOrigin(origins = "*")`).

## Licencia

Proyecto educativo - Dream Vacation 2026
