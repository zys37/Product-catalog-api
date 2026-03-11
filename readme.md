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
```powershell
./mvnw spring-boot:run