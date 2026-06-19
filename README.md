MindLift Backend V3

Backend desarrollado en Spring Boot para la plataforma MindLift, orientada a la gestión de pacientes en rehabilitación, movilidad reducida y adultos mayores.

Descripción

MindLift es una plataforma que permite registrar pacientes y profesionales de la salud, gestionar usuarios y realizar autenticación mediante una API REST conectada a MySQL.

Tecnologías utilizadas
Java 21
Spring Boot 4
Spring Data JPA
Hibernate
MySQL 8
Maven
JUnit 5
Mockito
Postman
Arquitectura

Cliente Android
↓
API REST (Spring Boot)
↓
JPA / Hibernate
↓
MySQL

Funcionalidades implementadas
Autenticación
Inicio de sesión de pacientes
Inicio de sesión de profesionales
Validación de credenciales
Control de acceso mediante roles
Gestión de pacientes
Registro de pacientes
Consulta de pacientes registrados
Persistencia en MySQL
Gestión de profesionales
Registro de profesionales
Consulta de profesionales registrados
Persistencia en MySQL
Gestión de usuarios
Creación automática de usuario al registrar pacientes
Creación automática de usuario al registrar profesionales
Asociación de usuarios con perfiles clínicos
Endpoints principales

POST /api/auth/login

GET /api/pacientes
POST /api/pacientes

GET /api/profesionales
POST /api/profesionales

GET /api/usuarios

Evidencias de funcionamiento
Pruebas Postman
Login profesional exitoso
Login paciente exitoso
Login fallido (credenciales incorrectas)
Consulta de pacientes
Consulta de profesionales
Registro de profesionales
Creación automática de usuarios
Pruebas unitarias

Resultado obtenido:

Tests run: 5
Failures: 0
Errors: 0
BUILD SUCCESS

Pruebas realizadas:

AuthControllerTest
PacienteControllerTest
ProfesionalControllerTest
Base de datos

Motor utilizado:

MySQL 8

Tablas principales:

usuarios
pacientes
profesionales
rutinas
ejercicios
progreso
Ejecución local
Crear base de datos MySQL.
Configurar application.properties.
Ejecutar:

mvn spring-boot

Servidor disponible en:

http://localhost:8080

Autor

Damian Rigoletti
Benjamin Saenz


