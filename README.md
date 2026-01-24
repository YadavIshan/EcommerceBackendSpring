# Ecommerce Backend Spring

A Spring Boot backend application for an e-commerce platform, demonstrating integration with external APIs (FakeStore) using Retrofit, Gateway pattern, and clean architecture principles.

## Package Structure

The project is organized into the following packages under `com.ishan.ecommerce`:

*   **`api`**: Contains Retrofit interfaces for defining external API endpoints (e.g., `FakeStoreProductApi`, `FakeStoreCategoryApi`).
*   **`configuration`**: Spring configuration classes (e.g., `RetrofitConfig` for setting up Retrofit clients).
*   **`controllers`**: REST Controllers that handle incoming HTTP requests (e.g., `ProductController`, `CategoryController`).
*   **`dto`**: Data Transfer Objects used for type-safe data exchange between layers and API responses.
*   **`gateway`**: Implementation of the Gateway pattern to abstract external API interactions (e.g., `FakeStoreProductGateway`).
*   **`mapper`**: Utility classes for mapping between DTOs and internal models.
*   **`services`**: Contains business logic interfaces and implementations (e.g., `FakeStoreProductService`).

## Configuration

The application requires an `.env` file in the project root to configure environment variables.

Create a file named `.env` and add the following variables:

```ini
PORT=3000(Of your choice)
FAKESTORE_API_URL=https://fakestoreapi.com/
```

*   **`PORT`**: The port number on which the server will run.
*   **`FAKESTORE_API_URL`**: The base URL for the external FakeStore API.

## Database

The application is configured to use an **H2 In-Memory Database** by default, which is convenient for development and testing without needing a local database server.

*   **Database URL**: `jdbc:h2:mem:testdb;NON_KEYWORDS=USER`
*   **Driver Class**: `org.h2.Driver`
*   **Username**: `sa`
*   **Password**: (Empty)
*   **Console**: Enabled
*   **Bootstrap Mode**: Default
*   **Defer Datasource Init**: False
*   **Database Platform**: `org.hibernate.dialect.H2Dialect`

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
To clean the build directory and compile the Java source code:
```bash
./gradlew clean compileJava
```

### Run Tests
To execute the unit and integration tests:
```bash
./gradlew test
```

### Build Project
To build the executable JAR file:
```bash
./gradlew build
```

### Run Application
To start the application:
```bash
./gradlew bootRun
```

## API Testing (Postman)

The application runs on the port defined in the `.env` file (default `3000`). Below are the available endpoints for testing via Postman.

> **Note:** Most endpoints integrate directly with the external **FakeStore API** (`https://fakestoreapi.com/`). Data created via POST requests is handled by FakeStore API (which commonly returns the object but does not persist it permanently in their database). The "Create Category" endpoint is simulated locally.

### Categories
*   **Get All Categories**
    *   **Method**: `GET`
    *   **URL**: `http://localhost:3000/api/categories`
    *   **Query Param** (Optional): `name` (e.g., `?name=electronics`) - Filter by name.
*   **Create Category**
    *   **Method**: `POST`
    *   **URL**: `http://localhost:3000/api/categories`
    *   **Body**: JSON
        ```json
        {
            "name": "New Category"
        }
        ```
*   **Get Products in Category**
    *   **Method**: `GET`
    *   **URL**: `http://localhost:3000/api/categories/{id}/products`

### Products
*   **Get All Products**
    *   **Method**: `GET`
    *   **URL**: `http://localhost:3000/api/products`
    *   **Query Param** (Optional): `limit` (Default: 10)
*   **Get Product by ID**
    *   **Method**: `GET`
    *   **URL**: `http://localhost:3000/api/products/{id}`
*   **Create Product**
    *   **Method**: `POST`
    *   **URL**: `http://localhost:3000/api/products`
    *   **Body**: JSON
        ```json
        {
            "title": "Product Title",
            "price": 99.99,
            "description": "Description",
            "image": "https://example.com/image.jpg",
            "category": "electronics"
        }
        }
        ```

### Admin
*   **Create Product (Persistent)**
    *   **Method**: `POST`
    *   **URL**: `http://localhost:3000/api/admin/products`
    *   **Body**: JSON
        ```json
        {
            "title": "Test Product",
            "price": 29.99,
            "description": "Testing repository",
            "image": "https://i.pravatar.cc",
            "categoryId": 123
        }
        ```
*   **Get All Products (Persistent)**
    *   **Method**: `GET`
    *   **URL**: `http://localhost:3000/api/admin/products`
*   **Get Product By ID (Persistent)**
    *   **Method**: `GET`
    *   **URL**: `http://localhost:3000/api/admin/products/{id}`
*   **Delete Product (Persistent)**
    *   **Method**: `DELETE`
    *   **URL**: `http://localhost:3000/api/admin/products/{id}`
*   **Update Pricing (Persistent)**
    *   **Method**: `PATCH`
    *   **URL**: `http://localhost:3000/api/admin/products/{id}`
    *   **Body**: Raw (Content-Type: `application/json`)
        ```text
        999.99
        ```
