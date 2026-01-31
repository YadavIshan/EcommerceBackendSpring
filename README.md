
# Ecommerce Backend Service

A Spring Boot microservice capable of managing products via a local H2 database (Admin Flow) OR fetching from an external API (FakeStore Flow).

## Architecture

- **Service Name**: `EcommerceBackend`
- **Port**: `3000` (Configured via properties, originally 8080)
- **Database**: H2 In-Memory (volatlie). Data is lost on restart.
- **Discovery**: Eureka Client (Registers with Eureka Server).

## Prerequisites

- **Java**: JDK 21
- **Build Tool**: Gradle

## Configuration

The application uses `src/main/resources/application.properties`.
Key configurations:
- `spring.application.name`: Service ID for discovery.
- `spring.datasource.url`: H2 In-Memory DB URL.
- `eureka.client.service-url.defaultZone`: Eureka Server URL.

## Getting Started

### 1. Build and Run
```bash
./gradlew clean build bootRun
```

### 2. Verify Health (Actuator)
Check if the service is up:
```bash
curl http://localhost:3000/actuator/health
```

---

## API Reference

### 1. Admin Flow (Database Backed)
Used by internal services (e.g., Order Service) to manage products in the local database.

#### **Step 1: Seed Test Data (Required after every restart)**
Since the H2 database is in-memory, you must create a Category and Product to test the Order Service.

**1. Create "Electronics" Category (ID 1)**
```bash
curl -X POST http://localhost:3000/api/admin/categories \
-H "Content-Type: application/json" \
-d '{"name": "Electronics"}'
```

**2. Create "Test Smartphone" Product (ID 1)**
*Note: This links to Category ID 1 created above.*
```bash
curl -X POST http://localhost:3000/api/admin/products \
-H "Content-Type: application/json" \
-d '{
  "title": "Test Smartphone",
  "price": 999.99,
  "description": "High-end smartphone for testing",
  "image": "https://i.pravatar.cc",
  "categoryId": 1
}'
```

#### **Other Admin Endpoints**

| Method | Endpoint | Description |
|TM|TM|TM|
| GET | `/api/admin/products` | Get all products from DB |
| GET | `/api/admin/products/{id}` | Get specific product by ID |
| POST | `/api/admin/products` | Create a new product |
| DELETE| `/api/admin/products/{id}` | Delete a product |
| PATCH | `/api/admin/products/{id}` | Update product price |
| GET | `/api/admin/categories` | Get all categories |
| POST | `/api/admin/categories` | Create a new category |

### 2. Public Flow (Local DB)
Originally designed to use FakeStore API, this now defaults to the **Local H2 Database** for consistency with the Admin flow.
The `FakeStoreProductService` code is preserved in the codebase for reference.

| Method | Endpoint | Description |
|TM|TM|TM|
| GET | `/api/products` | Get all products (Local DB) |
| GET | `/api/products/{id}` | Get product by ID (Local DB) |
| GET | `/api/categories` | Get all categories (Local DB)

---

## Troubleshooting

### "n/a" in Eureka Dashboard
If you see "n/a" in the AMIs column, the application is reporting its default metadata.
Ensure `eureka.instance.metadata-map.ami-id=local` is set in properties.

### "EMERGENCY" in Eureka Server
This is a server-side warning (Self-Preservation Mode). It happens when instances disappear abruptly (like stopping this app).
To fix, query the Eureka Server admin or configure the client to send heartbeats more often.
