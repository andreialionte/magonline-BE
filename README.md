# MagazinOnline — Backend (magazinonline)

[![Build](https://img.shields.io/badge/build-Maven-blue)](./pom.xml) [![Java](https://img.shields.io/badge/java-25-orange)](https://www.oracle.com/java/)

## What this project does

MagazinOnline is the backend service for an e‑commerce demo application. It provides REST APIs for user authentication (register / login), product management (create, read, update, delete) and caching with Redis. The service uses Spring Boot, Spring Data JPA (PostgreSQL), Spring Security (JWT), and MapStruct for DTO mapping.

This repository contains only the backend. The frontend is a separate project: https://github.com/andreialionte/magonline-FE

## Key features

- User registration and login with JWT authentication
- JWT includes `userId`, `email` and `fullName` claims
- Product CRUD endpoints
- PostgreSQL persistence (via Spring Data JPA)
- Redis for caching
-- OpenAPI / Swagger UI: this project includes `springdoc` + `swagger-ui` and also exposes docs through the `scalar` UI.
	- Swagger UI: `/swagger-ui/index.html`
	- Scalar UI (when enabled): `/docs` (configured via `scalar.*` in `application.yaml`)
- Docker Compose file for local Postgres + Redis

## Quick start — Prerequisites

- Java 25 (JDK)
- Maven (optional: wrapper `./mvnw.cmd` included)
- Docker & Docker Compose (to run Postgres + Redis)

## Getting started (development)

1. Clone the repository:

```bash
git clone https://github.com/andreialionte/magonline-BE.git
cd magonline-BE
```

2. (Optional) Pre-pull images used by Docker Compose (speeds up first run):

```bash
docker-compose pull
```

3. Start dependent services with Docker Compose (Postgres + Redis):

```bash
docker compose up -d
```

The repository includes `compose.yaml` at the project root (relative link: `compose.yaml`). It will create a Postgres database named `ecommerceDB` and Redis.

4. Check database connection (optional):

```bash
docker compose exec postgres psql -U postgres -d ecommerceDB -c "SELECT 1;"
```

5. Application defaults

- Server port: `5000` (see `src/main/resources/application.yaml`)
- DB URL: configured in `src/main/resources/application.yaml` (default for local Docker setup: `jdbc:postgresql://localhost:5433/ecommerceDB` in this workspace)
- JWT secret: `spring configuration` in `src/main/resources/application.yaml` under `jwt.secret` (this project has a development secret filled in; change in production)

## Important endpoints (REST)

Base URL: `http://localhost:5000/api`

- POST `/api/auth/register` — register new user (body: `RegisterRequest`) 
- POST `/api/auth/login` — login (body: `LoginRequest`) → returns `{ token, user }` where token contains `userId`, `email`, `fullName`
- GET `/api/products/getproducts` — list all products
- GET `/api/products/getproduct?id={uuid}` — get product by id
- POST `/api/products/createproduct` — create product (body: `ProductDto`) 
- PUT `/api/products/updateproduct` — update product (body: `ProductDto`, include `id`)
- DELETE `/api/products/deleteProduct/{id}` — delete product by id

- API docs / UI: Swagger UI is available at `/swagger-ui/index.html`. The project also includes Scalar-based UI available at `/docs` when `scalar.enabled` is true (see `application.yaml`).

Refer to the controller classes for request/response DTO shapes: `src/main/java/ro/andreialionte/magazinonline/controller` and `src/main/java/ro/andreialionte/magazinonline/dto`.

## Security / JWT

- JWTs are created using `com.auth0:java-jwt`. The login flow adds claims `userId`, `email`, and `fullName`.
- The JWT secret is read from `src/main/resources/application.yaml` (`jwt.secret`). Replace it with a secure secret in production and do not commit secrets to source control.

- Password hashing: this project uses Argon2id for password hashing — see `AuthController` and `SecurityConfig` for the password encoder and authentication setup.

## Configuration files

- Main Spring config: `src/main/resources/application.yaml`
- Docker Compose for local services: `compose.yaml`
- Build: `pom.xml`

## Caching

- Redis is used for caching (configured in `application.yaml`). Spring Cache annotations are applied in `ProductService` to cache product lists and individual product entries.

## API Endpoints (screenshots)

The following images show the API endpoints and example responses (captured from the app/UI). All three screenshots show the same endpoints; they are included here for reference.

<p>
	<img src="phts-readme/Screenshot 2025-11-14 184720.png" alt="API endpoints — view 3"  />
</p>

<p>
	<img src="phts-readme/Screenshot 2025-11-14 184829.png" alt="API endpoints — view 2" style="max-width:720px; width:200%;" />
</p>

<p>
	<img src="phts-readme/Screenshot 2025-11-14 184918.png" alt="API endpoints — view 1" style="max-width:720px; width:200%;" />
</p>