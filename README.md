
# Reservation System

This is a Spring Boot application for a reservation system. It includes user registration, login, JWT-based authentication, and CRUD operations for reservations.

## Prerequisites

- Java 17 or higher
- Maven 3.6.3 or higher
- MySQL database
- Git

## Getting Started

### 1. Clone the repository

First, clone the repository to your local machine:

```bash
git clone https://github.com/codewithsebas/reservation-system-backend.git
cd reservation-system-backend
```

### 2. Set up the MySQL database

Create a MySQL database called `reservation_db` (or the name you prefer) and set up the connection in the `application.properties` file.

```sql
CREATE DATABASE reservation_db;
```

### 3. Configure `application.properties`

In the `src/main/resources/application.properties` file, update the following properties with your local database configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/reservation_db
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

jwt.secret=mySecretKey
jwt.expiration=3600000
```

Replace `your_mysql_username` and `your_mysql_password` with your actual MySQL credentials.


### 4. Install Extensions Visual Studio Code or Use IntelliJ IDEA

`Extension Pack for Java`
`Spring Boot Extension Pack`

### 4. Build the project

Run the following Maven command to build the project:

`bash
it looks like in the main application file, right click and select Run Java
`

### Or

You can run the application using SPRING BOOT DASHBOARD, click on the left bar where you see a Spring Boot logo:

`run project`

### 6. Access the application

Once the application is running, you can access it at `http://localhost:8080`.

### Endpoints

- **POST** `/users/register`: Register a new user.
- **POST** `/users/login`: Log in with email and password, returns a JWT token.
- **POST** `/reservations`: Create a reservation (token required).
- **PUT** `/reservations/{id}`: Update a reservation (token required).
- **DELETE** `/reservations/{id}`: Delete a reservation (token required).
- **GET** `/reservations`: List all reservations (token required).
- **GET** `/reservations/{id}`: List reservation by id (token required).

### Documentation POSTMAN

- [Reservation System API Documentation](https://www.postman.com/satellite-geoscientist-17392290/workspace/reservation-system-api/collection/33340165-b781e3fa-427b-4272-bfda-183d4e198e97?action=share&creator=33340165)

### JWT Authentication

Once you log in, the application returns a JWT token. Use this token for subsequent requests by including it in the `Authorization` header as follows:

```
Key: Authorization
Value: Token
```

## Technologies

- Spring Boot
- MySQL
- JWT for authentication
- Maven
- Lombok

## Troubleshooting

- Ensure MySQL is running and accessible on `localhost:3306`.
- Check that the `application.properties` file contains the correct MySQL credentials.
