
# Sistema de Atención con Turnos

Este proyecto es una API REST desarrollada con **Java Spring Boot** para gestionar un sistema de atención por turnos. Permite a los usuarios autenticarse, reservar espacios de atención, y a los administradores gestionar áreas, reservas y estados.

## 🚀 Características Principales

- Autenticación de usuarios con JWT
- Roles: CLIENTE y ADMINISTRADOR
- CRUD de áreas de atención
- Creación, consulta y cancelación de reservas
- Registro de logs de acciones en MongoDB
- Documentación de API con Swagger
- Pruebas con Postman

## 🧰 Tecnologías Utilizadas

- Java 17
- Spring Boot 3
- Spring Security + JWT
- PostgreSQL (para usuarios, reservas y áreas)
- MongoDB (para logs de auditoría)
- Maven (gestión de dependencias)
- Swagger (documentación)
- Postman (pruebas API)

## ⚙️ Configuración Inicial

1. **Base de Datos PostgreSQL:**
   - Crear la base de datos `reservasdb`
   - Usuario: `postgres`, Contraseña: `postgres` (modificable en `application.properties`)

2. **Base de Datos MongoDB:**
   - Crear la base de datos `logsdb` (se crea automáticamente si no existe)

3. **Archivo `application.properties`:**
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/reservasdb
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update

spring.data.mongodb.uri=mongodb://localhost:27017/logsdb
```

## ▶️ Ejecución del Proyecto

```bash
mvn clean install
mvn spring-boot:run
```

Accede a la documentación Swagger en:
```
http://localhost:8080/swagger-ui/index.html
```

## 📬 Endpoints Clave

| Método | Endpoint         | Descripción                         |
|--------|------------------|-------------------------------------|
| POST   | /login           | Autenticación de usuarios           |
| POST   | /register        | Registro de nuevos usuarios         |
| GET    | /areas           | Listar áreas disponibles            |
| POST   | /reservas        | Crear una reserva                  |
| GET    | /reservas        | Listar reservas del usuario         |
| PUT    | /reservas/{id}   | Cambiar estado de reserva           |
| DELETE | /reservas/{id}   | Cancelar reserva                    |

## 🔐 Seguridad

El acceso a todos los endpoints (excepto `/login` y `/register`) requiere un token JWT válido en la cabecera:

```
Authorization: Bearer <token>
```

## 🧪 Pruebas con Postman

Importar:
- `SistemaTurnos_PostmanCollection_Admin.json`
- `SistemaTurnos_PostmanEnvironment.json`

## 📁 Estructura del Proyecto

- `controller/`: Controladores REST
- `model/`: Entidades JPA y modelos Mongo
- `repository/`: Repositorios PostgreSQL y MongoDB
- `service/`: Lógica de negocio
- `security/`: Seguridad con JWT
- `config/`: Configuración de Swagger


