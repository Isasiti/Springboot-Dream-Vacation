# Estructura del Proyecto Dream Vacation

```
Springboot-dream-vacation/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/dreamvacation/
│   │   │       ├── DreamVacationApplication.java      ✓ Clase principal
│   │   │       ├── model/
│   │   │       │   ├── Usuario.java                    ✓ Entidad JPA
│   │   │       │   └── Vacacion.java                   ✓ Entidad JPA
│   │   │       ├── repository/
│   │   │       │   ├── UsuarioRepository.java          ✓ Data Access
│   │   │       │   └── VacacionRepository.java         ✓ Data Access
│   │   │       ├── service/
│   │   │       │   ├── AuthService.java                ✓ Lógica de negocio
│   │   │       │   └── VacacionService.java            ✓ Lógica de negocio
│   │   │       ├── controller/
│   │   │       │   ├── AuthController.java             ✓ REST Endpoints
│   │   │       │   └── VacacionController.java         ✓ REST Endpoints
│   │   │       └── dto/
│   │   │           ├── LoginRequest.java               ✓ DTO
│   │   │           └── LoginResponse.java              ✓ DTO
│   │   └── resources/
│   │       ├── application.properties                  ✓ Configuración
│   │       └── data.sql                                ✓ Datos iniciales
│   └── test/
│       └── (para pruebas unitarias)
│
├── pom.xml                                              ✓ Dependencias Maven
├── README.md                                            ✓ Documentación
├── .gitignore                                           ✓ Control de versión
└── ESTRUCTURA.md                                        ✓ Este archivo

```

## Base de Datos

### Relación: Usuario (1:N) Vacación

```
┌─────────────────────┐
│     USUARIOS        │
├─────────────────────┤
│ id (PK)             │
│ nombre_usuario (U)  │
│ contrasena          │
│ email               │
└─────────────────────┘
         │ 1
         │
         │ N
         │
┌─────────────────────┐
│    VACACIONES       │
├─────────────────────┤
│ id (PK)             │
│ pais                │
│ ciudad              │
│ lugar_turistico     │
│ descripcion         │
│ usuario_id (FK)     │
└─────────────────────┘
```

## Diagrama de Capas

```
┌────────────────────────────────────┐
│      PRESENTACIÓN (WEB)            │
│   API REST (HTTP)                  │
├────────────────────────────────────┤
│   CONTROLLER LAYER                 │
│  • AuthController                  │
│  • VacacionController              │
├────────────────────────────────────┤
│   SERVICE LAYER (LÓGICA)           │
│  • AuthService                     │
│  • VacacionService                 │
├────────────────────────────────────┤
│   REPOSITORY LAYER (DAO)           │
│  • UsuarioRepository (JPA)         │
│  • VacacionRepository (JPA)        │
├────────────────────────────────────┤
│   PERSISTENCE LAYER                │
│  • H2 Database (En memoria)        │
└────────────────────────────────────┘
```

## Funcionalidades Implementadas

✓ **Autenticación**
  - Login con usuario y contraseña
  - Registro de nuevos usuarios
  - Validación de duplicados

✓ **Gestión de Vacaciones**
  - Crear vacaciones (país, ciudad, lugar turístico)
  - Listar vacaciones por usuario
  - Obtener vacación específica
  - Actualizar vacación
  - Eliminar vacación

✓ **Base de Datos**
  - Relación 1:N Usuario-Vacación
  - Datos iniciales precargados
  - Cascada de eliminación

✓ **API REST**
  - 8 endpoints bien documentados
  - CORS habilitado para desarrollo
  - Manejo de excepciones

## Próximos Pasos Recomendados

1. **Compilar el proyecto**
   ```bash
   mvn clean compile
   ```

2. **Ejecutar la aplicación**
   ```bash
   mvn spring-boot:run
   ```

3. **Probar los endpoints**
   - Usar Postman o curl
   - Acceder a H2 Console: http://localhost:8080/h2-console

4. **Implementar frontend**
   - HTML/CSS/JavaScript
   - React o Angular

5. **Mejorar seguridad**
   - Implementar BCrypt
   - Agregar JWT

6. **Cambiar base de datos**
   - PostgreSQL o MySQL en producción
