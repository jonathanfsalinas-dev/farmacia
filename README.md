# Sistema de Control de Inventario de Farmacia

Aplicación backend desarrollada con **Java y Spring Boot** para la gestión del inventario de una farmacia.

El sistema permite administrar medicamentos, controlar el stock mediante movimientos de entrada y salida y gestionar usuarios con distintos niveles de acceso.

---

## Características principales

- Inicio de sesión con Spring Security.
- Gestión de usuarios.
- Gestión de medicamentos.
- Registro de entradas de stock.
- Registro de salidas de stock.
- Consulta de stock.
- Historial de movimientos.
- Control de acceso por roles.
- Documentación automática con Swagger/OpenAPI.

---

##  Arquitectura

La aplicación sigue una arquitectura monolítica en capas:

- Controller
- Service
- Repository
- Entity

Flujo de trabajo:

```
Cliente → Controller → Service → Repository → Base de Datos
```

---

##  Tecnologías utilizadas

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- Spring Security
- H2 Database
- Lombok
- Validation
- Swagger / OpenAPI
- Maven
- Git y GitHub

---

##  Entidades principales

### Usuario

- id
- username
- password
- rol
- activo
- bloqueado

### Medicamento

- id
- nombre
- tipo
- marca
- stock

### Movimiento

- id
- tipo
- cantidad
- fecha
- usuario
- medicamento

---

##  Roles del sistema

### ADMIN

Puede:

- Administrar usuarios.
- Gestionar medicamentos.
- Registrar movimientos.
- Eliminar movimientos.
- Realizar todas las operaciones del sistema.

### OPERADOR

Puede:

- Gestionar medicamentos.
- Registrar entradas y salidas.
- Consultar stock.
- Consultar movimientos.

No puede administrar usuarios ni eliminar movimientos.

---

##  Documentación de la API

Swagger UI:

```
http://localhost:8080/swagger-ui/index.html
```

OpenAPI JSON:

```
http://localhost:8080/v3/api-docs
```

---

##  Documentación

La carpeta `docs/` contiene:

- `Sistema_Control_Inventario_Farmacia.docx`
- `openapi.json`

---

##  Principios y Patrones Aplicados

### Principios SOLID

- Single Responsibility Principle (SRP)
- Interface Segregation Principle (ISP)
- Dependency Inversion Principle (DIP)

### Patrones de diseño

- Singleton
- Strategy
- Builder
- Adapter
- Repository

---

##  Pruebas

Las pruebas de la API fueron realizadas utilizando **Thunder Client**.

Thunder Client fue utilizado únicamente como cliente para consumir y probar la API REST. No representa el frontend del sistema.

---

##  Posibles mejoras futuras

- Exportación de reportes PDF.
- DTOs para ocultar información sensible.
- Exportación a Excel.
- Paginación.
- Filtros por fecha.
- JWT.
- Migración a MySQL o PostgreSQL.
- Desarrollo de una interfaz web o móvil.

---

##  Autor

Proyecto desarrollado con Java y Spring Boot como práctica de desarrollo backend y aplicación de buenas prácticas de diseño de software.
