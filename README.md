
# Sistema de Reservas

Esta es una aplicación de Spring Boot para un sistema de reservas. Incluye registro de usuarios, inicio de sesión, autenticación basada en JWT y operaciones CRUD para las reservas.

## Requisitos previos

- Java 17 o superior
- Maven 3.6.3 o superior
- Base de datos MySQL
- Git

## Empezando

### 1. Clonar el repositorio

Primero, clona el repositorio en tu máquina local:

```bash
git clone https://github.com/codewithsebas/reservation-system-backend.git
cd reservation-system-backend
```

### 2. Configurar la base de datos MySQL

Crea una base de datos MySQL llamada reservation_db (o el nombre que prefieras) y configura la conexión en el archivo application.properties.

```sql
CREATE DATABASE reservation_db;
```

### 3. Configurar `application.properties`

En el archivo src/main/resources/application.properties, actualiza las siguientes propiedades con la configuración de tu base de datos local:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/reservation_db
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

jwt.secret=mySecretKey
jwt.expiration=3600000
```

Reemplaza tu_usuario_mysql y tu_contraseña_mysql con tus credenciales reales de MySQL.


### 4. Instalar extensiones de Visual Studio Code o usar IntelliJ IDEA

Instala las siguientes extensiones:


`Extension Pack for Java`
`Spring Boot Extension Pack`

### 4. Compilar el proyecto

Ejecuta el siguiente comando de Maven para compilar el proyecto:

`
haga clic con el botón derecho y seleccione Ejecutar Java
`

### O

Puedes ejecutar la aplicación usando el SPRING BOOT DASHBOARD, haciendo clic en la barra lateral izquierda donde ves el logotipo de Spring Boot:

`run project`


### Documentación POSTMAN

- [Reservation System API Documentation](https://www.postman.com/satellite-geoscientist-17392290/workspace/reservation-system-api/collection/33340165-8a12f120-9bfc-4f03-a51c-2b81e540494f?action=share&creator=33340165)

### Autenticación JWT

Una vez que inicies sesión, la aplicación devolverá un token JWT. Usa este token para solicitudes posteriores, incluyéndolo en el encabezado Authorization de la siguiente manera:

```
Key: Authorization
Value: Token
```

## Technologias

- Spring Boot
- MySQL
- JWT for authentication
- Maven
- Lombok

## Solución de problemas

- Asegúrate de que MySQL esté en funcionamiento y accesible en `localhost:3306`.
- Verifica que el archivo `application.properties` contenga las credenciales correctas de MySQL.
