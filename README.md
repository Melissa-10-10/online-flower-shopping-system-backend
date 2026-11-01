# Online Flower Shopping System (Backend)

**Student:** SANGWA KAMANZI MELISSA
**Student ID:** 27405
**Project name:** online-flower-shopping-system-backend

## About
A Spring Boot REST API for an online flower shop. It manages flower types,
outlets, florists, customers, flower orders and delivery proofs.

## Technologies
- Java 17
- Spring Boot, Spring Web, Spring Data JPA
- PostgreSQL
- Maven

## How to run
1. Install Java 17 and PostgreSQL.
2. Create an empty database named `flowershop`.
3. Open `src/main/resources/application.properties` and set your own
   PostgreSQL username, password and port in the `spring.datasource` lines.
4. Run the application:
   `mvnw.cmd spring-boot:run` (Windows) or `./mvnw spring-boot:run`
5. The API runs on `http://localhost:1014`.
   Hibernate creates the tables automatically.

## Main endpoints
| Part | Endpoint |
|---|---|
| A | CRUD under `/api/flower-types`, `/api/outlets`, `/api/florists`, `/api/customers`, `/api/flower-orders` |
| B | `GET /api/customers/by-last-name`, `GET /api/flower-orders/by-status`, `GET /api/flower-orders/between` |
| C | `GET /api/florists/by-flower-type`, `GET /api/outlets/free`, `GET /api/flower-types/unused`, `GET /api/customers/of-florist/{id}` |
| D | `POST /api/flower-orders/save`, `PATCH /api/flower-orders/{id}/status`, `PUT /api/florists/{id}/flower-types/{id}`, `PATCH /api/florists/{id}/outlet` |
| E | `/api/delivery-proofs/save`, `/customer/{phone}`, `/delete/{id}` |
| F | `GET /api/flower-orders/stats/by-status`, `PATCH /api/flower-orders/cancel-day` |

## Postman
Import `online-flower-shopping-system-midterm.postman_collection.json`
from the root of this repository to test every endpoint.

## Postman
Import `online-flower-shopping-system-midterm.postman_collection.json`
from the root of this repository to test every endpoint.
