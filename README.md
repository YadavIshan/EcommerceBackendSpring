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

### 1. Public APIs (FakeStore Integration)
These endpoints interact with the external **FakeStore API**. They are intended for public browsing and demonstration.
> **Note:** Data created via these POST requests is simulated by FakeStore API and is **not persisted** in your local database.

#### Products (Public)
*   **Get All Products**
    *   `GET http://localhost:3000/api/products?limit=10`
*   **Get Product by ID**
    *   `GET http://localhost:3000/api/products/{id}`
*   **Create Product (Simulation)**
    *   `POST http://localhost:3000/api/products`
    *   **Body** (JSON):
        ```json
        {
            "title": "Public Product",
            "price": 13.5,
            "description": "lorem ipsum set",
            "image": "https://i.pravatar.cc",
            "category": "electronic"
        }
        ```

#### Categories (Public)
*   **Get All Categories**
    *   `GET http://localhost:3000/api/categories`
    *   **Query Param**: `?name=jewelery` (Filter by name)
*   **Create Category (Simulation)**
    *   `POST http://localhost:3000/api/categories`
    *   **Body** (JSON):
        ```json
        {
            "name": "New Collection"
        }
        ```
*   **Get Products in Category**
    *   `GET http://localhost:3000/api/categories/{id}/products`

---

### 2. Admin APIs (Internal Database)
These endpoints interact with your **local H2 database**. Data created here is **fully persisted** deeply in your application.

> **💡 Usage Tip:** To create a Product, you **must first create a Category** to get a valid `categoryId`.
>
> **Recommended Workflow:**
> 1.  Call `POST /api/admin/categories` to create a category (e.g., "Electronics").
> 2.  Note the `id` returned in the response (e.g., `1`).
> 3.  Call `POST /api/admin/products` using that `id` as the `categoryId`.

#### Admin Products (Database)
*   **Create Product**
    *   `POST http://localhost:3000/api/admin/products`
    *   **Body** (JSON):
        ```json
        {
            "title": "Database Stored Phone",
            "price": 699.99,
            "description": "This is stored in H2 DB",
            "image": "https://example.com/phone.jpg",
            "categoryId": 1
        }
        ```
    *   *Requirement: `categoryId` must exist in the database.*
*   **Get All Products (With Filter)**
    *   `GET http://localhost:3000/api/admin/products`
    *   **Query Param**: `?minPrice=100` (Optional: Filters products with price >= minPrice)
*   **Get Most Expensive Product in Category**
    *   `GET http://localhost:3000/api/admin/products/expensive/{categoryId}`
*   **Get Product By ID**
    *   `GET http://localhost:3000/api/admin/products/{id}`
*   **Delete Product**
    *   `DELETE http://localhost:3000/api/admin/products/{id}`
*   **Update Product Price**
    *   `PATCH http://localhost:3000/api/admin/products/{id}`
    *   **Body** (Raw Text/JSON):
        ```text
        599.99
        ```

#### Admin Categories (Database)
*   **Create Category**
    *   `POST http://localhost:3000/api/admin/categories`
    *   **Body** (JSON):
        ```json
        {
            "name": "Electronics"
        }
        ```
*   **Get All Categories**
    *   `GET http://localhost:3000/api/admin/categories`
*   **Get Products in Category**
    *   `GET http://localhost:3000/api/admin/categories/{id}/products`
