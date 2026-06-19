# MindLift API Local

Backend local en Spring Boot para la app Android MindLift.

## Base de datos

Usa MySQL local:

- Base de datos: `mindlift_app_db`
- Puerto MySQL: `3306`
- Backend: `http://localhost:8080`

## Ejecutar

```bash
mvn spring-boot:run
```

## Endpoints principales

### Salud

```http
GET http://localhost:8080/api/health
```

### Pacientes

```http
GET    http://localhost:8080/api/pacientes
GET    http://localhost:8080/api/pacientes/{id}
POST   http://localhost:8080/api/pacientes
PUT    http://localhost:8080/api/pacientes/{id}
DELETE http://localhost:8080/api/pacientes/{id}
```

### Profesionales

```http
GET    http://localhost:8080/api/profesionales
POST   http://localhost:8080/api/profesionales
```

### Ejercicios

```http
GET    http://localhost:8080/api/ejercicios
POST   http://localhost:8080/api/ejercicios
```

### Rutinas

```http
GET    http://localhost:8080/api/rutinas
GET    http://localhost:8080/api/rutinas?pacienteId=1
POST   http://localhost:8080/api/rutinas
```

### Progreso

```http
GET    http://localhost:8080/api/progreso
GET    http://localhost:8080/api/progreso?pacienteId=1
POST   http://localhost:8080/api/progreso
```

### Revisión IA

```http
GET    http://localhost:8080/api/revision-ia
GET    http://localhost:8080/api/revision-ia?estado=PENDIENTE
POST   http://localhost:8080/api/revision-ia
PATCH  http://localhost:8080/api/revision-ia/{id}/estado?estado=APROBADA
```

### Cargar datos demo

```http
POST http://localhost:8080/api/demo/cargar
```

## URL para Android

- Emulador Android: `http://10.0.2.2:8080`
- Celular físico: `http://IP_DE_TU_PC:8080`

## Login local

Endpoint:

```http
POST http://localhost:8080/api/auth/login
```

Body:

```json
{
  "username": "profesional@test.cl",
  "password": "1234"
}
```

Usuarios de prueba en MySQL:

```sql
USE mindlift_app_db;

INSERT IGNORE INTO usuarios (username, password, rol, paciente_id, profesional_id, activo)
VALUES ('paciente@test.cl', '1234', 'PACIENTE', 1, NULL, TRUE);

INSERT IGNORE INTO usuarios (username, password, rol, paciente_id, profesional_id, activo)
VALUES ('profesional@test.cl', '1234', 'PROFESIONAL', NULL, 1, TRUE);
```

Si la tabla `profesionales` fue creada antes y falta `fecha_creacion`, ejecutar:

```sql
ALTER TABLE profesionales ADD COLUMN fecha_creacion DATETIME;
```

## Pruebas unitarias agregadas

Se agregaron pruebas unitarias con JUnit 5 y Mockito para validar la lógica principal sin depender de Postman ni de MySQL real:

- `AuthControllerTest`: valida login correcto, contraseña incorrecta y usuario inexistente.
- `ProfesionalControllerTest`: valida que al registrar profesional se cree automáticamente un usuario con rol `PROFESIONAL`.
- `PacienteControllerTest`: valida que al registrar paciente se cree automáticamente un usuario con rol `PACIENTE`.

Para ejecutar las pruebas:

```bash
mvn test
```

Para iniciar el backend local:

```bash
mvn spring-boot:run
```
