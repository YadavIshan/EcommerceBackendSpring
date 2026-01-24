# Ecommerce Backend Spring

A Spring Boot backend application for an e-commerce platform. It demonstrates a transition from a Gateway pattern (using Retrofit for external FakeStore API) to a fully persisted, database-backed architecture using JPA and Hibernate.

## Package Structure

The project is organized into the following packages under `com.ishan.ecommerce`:

*   **`api`**: Contains Retrofit interfaces for defining external API endpoints.
*   **`configuration`**: Spring configuration classes.
*   **`controllers`**: REST Controllers that handle incoming HTTP requests (e.g., `AdminProductController`, `CategoryController`).
*   **`dto`**: Data Transfer Objects used for type-safe data exchange.
*   **`entity`**: JPA Entities representing database tables (e.g., `ProductEntity`, `CategoryEntity`).
*   **`gateway`**: Implementation of the Gateway pattern to abstract external API interactions.
*   **`mapper`**: Utility classes for mapping between DTOs and internal entities.
*   **`repository`**: Spring Data JPA repositories for database access (`ProductRepository`, `CategoryRepository`).
*   **`services`**: Contains business logic interfaces and implementations (e.g., `AdminProductService`, `CategoryService`).

## Configuration

The application requires an `.env` file in the project root to configure environment variables.

Create a file named `.env` and add the following variables:

```ini
PORT=3000
FAKESTORE_API_URL=https://fakestoreapi.com/
```

*   **`PORT`**: The port number on which the server will run.
*   **`FAKESTORE_API_URL`**: The base URL for the external FakeStore API (used by legacy components).

## Database

The application is configured to use an **H2 In-Memory Database** by default. Both **Products** and **Categories** are now fully persisted entities with a `@ManyToOne` relationship (Product -> Category).

*   **Database URL**: `jdbc:h2:mem:testdb;NON_KEYWORDS=USER`
*   **Driver Class**: `org.h2.Driver`
*   **Username**: `sa`
*   **Password**: (Empty)
*   **Console**: Enabled

### Accessing H2 Console
When the application is running, you can access the H2 console at:
`http://localhost:3000/h2-console`

**Login Settings:**
*   **JDBC URL**: `jdbc:h2:mem:testdb`
*   **User Name**: `sa`
*   **Password**: (Leave empty)

> **Note:** Being an in-memory database, all data will be lost when the application is restarted.

## Build and Test

This project uses Gradle for build and dependency management.

### Clean and Compile
```bash
./gradlew clean compileJava
```

### Run Tests
```bash
./gradlew test
```

### Build Project
```bash
./gradlew build
```

### Run Application
```bash
./gradlew bootRun
```

## API Testing (Postman)

The application runs on the port defined in the `.env` file (default `3000`).

### Categories (Database Backed)
*   **Get All Categories**
    *   **Method**: `GET`
    *   **URL**: `http://localhost:3000/api/categories`
*   **Create Category**
    *   **Method**: `POST`
    *   **URL**: `http://localhost:3000/api/categories`
    *   **Body**: JSON
        ```json
        {
            "name": "Electronics"
        }
        ```
*   **Get Products in Category**
    *   **Method**: `GET`
    *   **URL**: `http://localhost:3000/api/categories/{id}/products`

### Admin Products (Database Backed)
*   **Create Product (Persistent)**
    *   **Method**: `POST`
    *   **URL**: `http://localhost:3000/api/admin/products`
    *   **Body**: JSON
        ```json
        {
            "title": "Smartphone",
            "price": 699.99,
            "description": "High-end smartphone",
            "image": "https://example.com/phone.jpg",
            "categoryId": 1
        }
        ```
    *   **Note**: `categoryId` must correspond to an existing Category ID.
*   **Get All Products**
    *   **Method**: `GET`
    *   **URL**: `http://localhost:3000/api/admin/products`
*   **Get Product By ID**
    *   **Method**: `GET`
    *   **URL**: `http://localhost:3000/api/admin/products/{id}`
*   **Delete Product**
    *   **Method**: `DELETE`
    *   **URL**: `http://localhost:3000/api/admin/products/{id}`
*   **Update Pricing**
    *   **Method**: `PATCH`
    *   **URL**: `http://localhost:3000/api/admin/products/{id}`
    *   **Body**: Raw (Content-Type: `application/json`)
        ```text
        599.99
        ```
