# Client API - Sistema de Gestión de Clientes y Cuentas

Esta es una API REST desarrollada con **Spring Boot** para la gestión de clientes y sus respectivas cuentas bancarias. También incluye una integración para consultar la cotización oficial del dólar.

## Requisitos Previos

*   **Java 21** o superior.
*   **Maven 3.x**.
*   **MySQL Server** (u otro motor compatible configurado en `application.properties`).

## Configuración

Antes de ejecutar la aplicación, asegúrate de tener creada la base de datos en MySQL y configurar las credenciales en `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/utn
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```

## Ejecución

Puedes ejecutar la aplicación desde la terminal utilizando Maven:

```bash
./mvnw spring-boot:run
```

O bien, compilar el archivo JAR y ejecutarlo:

```bash
./mvnw clean package
java -jar target/api-0.0.1-SNAPSHOT.jar
```

La aplicación estará disponible en `http://localhost:8080`.

## Documentación (Swagger)

Una vez iniciada la aplicación, puedes acceder a la interfaz de Swagger para probar los endpoints interactivamente:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## Endpoints y Ejemplos de uso (cURL)

### 1. Clientes (`/clientes`)

#### Listar todos los clientes
```bash
curl -X GET http://localhost:8080/clientes
```

#### Obtener un cliente por ID
```bash
curl -X GET http://localhost:8080/clientes/1
```

#### Crear un nuevo cliente
```bash
curl -X POST http://localhost:8080/clientes \
     -H "Content-Type: application/json" \
     -d '{
           "nombre": "Juan",
           "apellido": "Pérez",
           "tipoDocumento": "DNI",
           "direccion": "Calle Falsa 123",
           "telefono": "12345678",
           "email": "juan.perez@example.com",
           "fechaAlta": "2023-10-27"
         }'
```

#### Actualizar un cliente
```bash
curl -X PUT http://localhost:8080/clientes/1 \
     -H "Content-Type: application/json" \
     -d '{
           "nombre": "Juan Alberto",
           "apellido": "Pérez",
           "direccion": "Nueva Dirección 456"
         }'
```

#### Eliminar un cliente
```bash
curl -X DELETE http://localhost:8080/clientes/1
```

---

### 2. Cuentas (`/cuentas`)

#### Listar todas las cuentas
```bash
curl -X GET http://localhost:8080/cuentas
```

#### Obtener una cuenta por ID
```bash
curl -X GET http://localhost:8080/cuentas/1
```

#### Crear una cuenta para un cliente específico
Sustituye `{idCliente}` por el ID del cliente al que pertenece la cuenta.
```bash
curl -X POST http://localhost:8080/cuentas/{idCliente} \
     -H "Content-Type: application/json" \
     -d '{
           "numeroCuenta": "CA-987654",
           "moneda": "ARS",
           "saldo": 1500.50,
           "activo": true,
           "fechaCreacion": "2023-10-27T10:00:00"
         }'
```

#### Actualizar una cuenta
```bash
curl -X PUT http://localhost:8080/cuentas/1 \
     -H "Content-Type: application/json" \
     -d '{
           "saldo": 2000.00,
           "activo": false
         }'
```

#### Eliminar una cuenta
```bash
curl -X DELETE http://localhost:8080/cuentas/1
```

---

### 3. Dólar (`/dolar`)

#### Obtener cotización oficial del dólar
```bash
curl -X GET http://localhost:8080/dolar/cotizacion
```
