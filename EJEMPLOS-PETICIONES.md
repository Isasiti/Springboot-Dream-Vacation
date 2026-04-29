# Ejemplos de Peticiones HTTP - Dream Vacation API

## 1. AUTENTICACIÓN (Auth)

### 1.1 Login - Usuario Existente
```http
POST http://localhost:8080/api/auth/login
Content-Type: application/json

{
    "nombreUsuario": "juan",
    "contrasena": "password123"
}
```

**Respuesta Exitosa (200):**
```json
{
    "usuarioId": 1,
    "nombreUsuario": "juan",
    "email": "juan@example.com",
    "mensaje": "Login exitoso",
    "exitoso": true
}
```

### 1.2 Login - Contraseña Incorrecta
```http
POST http://localhost:8080/api/auth/login
Content-Type: application/json

{
    "nombreUsuario": "juan",
    "contrasena": "passwordIncorrecto"
}
```

**Respuesta (401):**
```json
{
    "usuarioId": null,
    "nombreUsuario": null,
    "email": null,
    "mensaje": "Contraseña incorrecta",
    "exitoso": false
}
```

### 1.3 Registrar Nuevo Usuario
```http
POST http://localhost:8080/api/auth/registrar
Content-Type: application/json

{
    "nombreUsuario": "pedro",
    "contrasena": "mipassword123",
    "email": "pedro@example.com"
}
```

**Respuesta (201 Created):**
```json
{
    "id": 4,
    "nombreUsuario": "pedro",
    "contrasena": "mipassword123",
    "email": "pedro@example.com",
    "vacaciones": []
}
```

### 1.4 Registrar - Usuario Duplicado
```http
POST http://localhost:8080/api/auth/registrar
Content-Type: application/json

{
    "nombreUsuario": "juan",
    "contrasena": "password123",
    "email": "otrogmail@example.com"
}
```

**Respuesta (400):**
```json
{
    "usuarioId": null,
    "nombreUsuario": null,
    "email": null,
    "mensaje": "El nombre de usuario ya existe",
    "exitoso": false
}
```

### 1.5 Obtener Información de Usuario
```http
GET http://localhost:8080/api/auth/usuario/1
```

**Respuesta (200):**
```json
{
    "id": 1,
    "nombreUsuario": "juan",
    "contrasena": "password123",
    "email": "juan@example.com",
    "vacaciones": [
        {
            "id": 1,
            "pais": "España",
            "ciudad": "Barcelona",
            "lugarTuristico": "Sagrada Familia",
            "descripcion": "Basílica icónica de Gaudí"
        },
        {
            "id": 2,
            "pais": "España",
            "ciudad": "Madrid",
            "lugarTuristico": "Museo del Prado",
            "descripcion": "Galería de arte con obras maestras"
        }
    ]
}
```

---

## 2. VACACIONES

### 2.1 Obtener Todas las Vacaciones de un Usuario
```http
GET http://localhost:8080/api/vacaciones/usuario/1
```

**Respuesta (200):**
```json
[
    {
        "id": 1,
        "pais": "España",
        "ciudad": "Barcelona",
        "lugarTuristico": "Sagrada Familia",
        "descripcion": "Basílica icónica de Gaudí"
    },
    {
        "id": 2,
        "pais": "España",
        "ciudad": "Madrid",
        "lugarTuristico": "Museo del Prado",
        "descripcion": "Galería de arte con obras maestras"
    }
]
```

### 2.2 Obtener Vacación Específica
```http
GET http://localhost:8080/api/vacaciones/1
```

**Respuesta (200):**
```json
{
    "id": 1,
    "pais": "España",
    "ciudad": "Barcelona",
    "lugarTuristico": "Sagrada Familia",
    "descripcion": "Basílica icónica de Gaudí"
}
```

### 2.3 Obtener Vacación No Existente
```http
GET http://localhost:8080/api/vacaciones/999
```

**Respuesta (404):**
```
Vacación no encontrada
```

### 2.4 Crear Nueva Vacación
```http
POST http://localhost:8080/api/vacaciones/usuario/1
Content-Type: application/json

{
    "pais": "Portugal",
    "ciudad": "Lisboa",
    "lugarTuristico": "Torre de Belém",
    "descripcion": "Torre histórica junto al Tejo"
}
```

**Respuesta (201 Created):**
```json
{
    "id": 7,
    "pais": "Portugal",
    "ciudad": "Lisboa",
    "lugarTuristico": "Torre de Belém",
    "descripcion": "Torre histórica junto al Tejo"
}
```

### 2.5 Crear Vacación para Usuario No Existente
```http
POST http://localhost:8080/api/vacaciones/usuario/999
Content-Type: application/json

{
    "pais": "Japón",
    "ciudad": "Tokio",
    "lugarTuristico": "Torre de Tokio",
    "descripcion": "Icónica torre de la ciudad"
}
```

**Respuesta (404):**
```
Error: Usuario no encontrado
```

### 2.6 Actualizar Vacación
```http
PUT http://localhost:8080/api/vacaciones/1
Content-Type: application/json

{
    "pais": "España",
    "ciudad": "Barcelona",
    "lugarTuristico": "Park Güell",
    "descripcion": "Parque modernista diseñado por Gaudí"
}
```

**Respuesta (200):**
```json
{
    "id": 1,
    "pais": "España",
    "ciudad": "Barcelona",
    "lugarTuristico": "Park Güell",
    "descripcion": "Parque modernista diseñado por Gaudí"
}
```

### 2.7 Eliminar Vacación
```http
DELETE http://localhost:8080/api/vacaciones/1
```

**Respuesta (200):**
```
Vacación eliminada correctamente
```

---

## USUARIOS DE PRUEBA PRECARGADOS

| ID | Usuario | Contraseña | Email | Vacaciones |
|----|---------|-----------|-------|-----------|
| 1 | juan | password123 | juan@example.com | 2 |
| 2 | maria | pass456 | maria@example.com | 2 |
| 3 | carlos | mipassword789 | carlos@example.com | 2 |

---

## INSTRUCCIONES PARA PROBAR

### Con Postman:
1. Crear un nueva colección "Dream Vacation"
2. Copiar cada petición
3. Importarla en Postman
4. Ejecutar

### Con cURL (Terminal):

**Login:**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"nombreUsuario":"juan","contrasena":"password123"}'
```

**Obtener Vacaciones:**
```bash
curl http://localhost:8080/api/vacaciones/usuario/1
```

**Crear Vacación:**
```bash
curl -X POST http://localhost:8080/api/vacaciones/usuario/1 \
  -H "Content-Type: application/json" \
  -d '{"pais":"Chile","ciudad":"Santiago","lugarTuristico":"Cerro San Cristóbal"}'
```

### Con VS Code (REST Client extension):
1. Instalar extensión "REST Client"
2. Crear archivo `requests.http`
3. Copiar las peticiones
4. Hacer click en "Send Request"

---

## NOTAS

- Reemplazar `localhost:8080` si la app corre en otro puerto
- Los datos se pierden si reinician la aplicación (H2 en memoria)
- Las contraseñas están en texto plano (usar BCrypt en producción)
- CORS está habilitado para desarrollo desde cualquier origen
