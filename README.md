# Task Management API

A robust, RESTful backend service for a Task Management application. This API provides comprehensive CRUD operations for tasks, built with a focus on clean code, maintainability, and scalability.

## 🛠 Tech Stack

* **Language:** Java 17+
* **Framework:** Spring Boot 3.x
* **Data Persistence:** Spring Data JPA / Hibernate
* **Database:** H2 In-Memory Database (Easily swappable to PostgreSQL/MySQL)
* **Testing:** JUnit 5, Mockito
* **Validation:** Jakarta Bean Validation

## 🏗 Architectural Decisions & Trade-offs

* **Layered Architecture:** The application strictly follows a classic 3-tier architecture (Controller -> Service -> Repository). This ensures a clear separation of concerns, keeping the business logic isolated from HTTP transport and database transaction details.
* **In-Memory Database (H2):** Chosen for ease of testing and zero-configuration setup for reviewers. The schema is auto-generated via JPA. For a production environment, this would be migrated to a persistent relational database like PostgreSQL via application properties.
* **Centralized Error Handling:** Implemented a `@ControllerAdvice` global exception handler to intercept runtime exceptions and return standardized, user-friendly JSON error responses instead of raw stack traces.
* **Data Validation:** Enforced at the controller level using `@Valid` to ensure data integrity (e.g., Title length constraints, non-null values) before the payload ever reaches the business tier.
* **Put Request Payload:** Designed the `PUT` endpoint to accept the identifier directly within the request body payload rather than the URL path, simplifying routing logic on the frontend.

## 🚀 Setup and Running Locally

### Prerequisites
* Java Development Kit (JDK) 17 or higher
* Maven (or you can use the included Maven wrapper)

### Installation Steps

1.  **Clone the repository and navigate to the backend directory:**
    ```bash
    cd backend
    ```

2.  **Build the application:**
    ```bash
    ./mvnw clean install
    ```

3.  **Run the application:**
    ```bash
    ./mvnw spring-boot:run
    ```

The server will start on `http://localhost:8080`.

## 🔌 API Endpoints

| Method | Endpoint | Description | Request Body |
| :--- | :--- | :--- | :--- |
| `GET` | `/api/tasks` | Retrieve all tasks | - |
| `GET` | `/api/tasks/{id}` | Retrieve a specific task | - |
| `POST` | `/api/tasks` | Create a new task | Task JSON (without ID) |
| `PUT` | `/api/tasks` | Update an existing task | Task JSON (with ID) |
| `DELETE`| `/api/tasks/{id}` | Delete a task | - |

## 🧪 Testing

Unit tests have been written for the business logic (`TaskService`) using **JUnit 5** and **Mockito** to verify isolated behavior without requiring a database connection.

To run the test suite:
```bash
./mvnw test