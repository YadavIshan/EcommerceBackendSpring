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

The application runs on `http://localhost:8080` by default. Below are the available endpoints for testing via Postman.

### Categories
*   **Get All Categories**
    *   **Method**: `GET`
    *   **URL**: `http://localhost:8080/api/categories`
    *   **Query Param** (Optional): `name` (e.g., `?name=electronics`) - Filter by name.
*   **Create Category**
    *   **Method**: `POST`
    *   **URL**: `http://localhost:8080/api/categories`
    *   **Body**: JSON
        ```json
        {
            "name": "New Category"
        }
        ```
*   **Get Products in Category**
    *   **Method**: `GET`
    *   **URL**: `http://localhost:8080/api/categories/{id}/products`

### Products
*   **Get All Products**
    *   **Method**: `GET`
    *   **URL**: `http://localhost:8080/api/products`
    *   **Query Param** (Optional): `limit` (Default: 10)
*   **Get Product by ID**
    *   **Method**: `GET`
    *   **URL**: `http://localhost:8080/api/products/{id}`
*   **Create Product**
    *   **Method**: `POST`
    *   **URL**: `http://localhost:8080/api/products`
    *   **Body**: JSON
        ```json
        {
            "title": "Product Title",
            "price": 99.99,
            "description": "Description",
            "image": "https://example.com/image.jpg",
            "category": "electronics"
        }
        ```
