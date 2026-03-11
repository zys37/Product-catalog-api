# Product Catalog API

A Spring Boot 3.5 application for managing products with dynamic attributes and producers.

## Features
- **Dynamic Attributes**: Support for unlimited product attributes using a Key-Value storage strategy.
- **Relational Data**: Full support for Producers linked to Products.
- **Database Versioning**: Liquibase managed schema migrations.
- **In-Memory Storage**: Uses H2 database for quick testing and zero configuration.
- **Search**: Built-in search by product name.

## Tech Stack
- Java 21
- Spring Boot 3.5 (Spring Data JPA, Spring Web)
- Liquibase
- H2 Database
- Lombok

## Getting Started

### Prerequisites
- JDK 21 installed
- `JAVA_HOME` environment variable set

### Running the application
From the project root directory, run:
```bash
./mvnw spring-boot:run
```
The REST API will be available at `http://localhost:8080/api/products`.

### Database Access
The H2 Console is accessible at `http://localhost:8080/h2-console`.
- **JDBC URL:** `jdbc:h2:mem:catalogdb`
- **User Name:** `sa`
- **Password:** *(leave empty)*

## Testing the API

For your convenience, an **`api-tests.http`** file is included in the root directory. You can use it directly in IntelliJ IDEA or with the REST Client extension in VS Code to quickly test all endpoints (CRUD operations and search).

### Example: Create a Product with Dynamic Attributes
```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "MacBook Pro 16",
    "description": "M3 Max Chip",
    "price": 14999.00,
    "producerId": 1,
    "attributes": {
      "RAM": "36GB",
      "Disk": "1TB SSD",
      "Color": "Space Black"
    }
  }'
