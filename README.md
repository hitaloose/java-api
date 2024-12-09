# Create User API

This project implements a basic Java application for creating and managing users. It uses a modular architecture, adhering to principles of clean architecture and separation of concerns. The API exposes an endpoint for user creation, handles validation, and simulates data storage in memory.

---

## Features

- **User Registration**: Create new user accounts with validation for required fields and password matching.
- **Layered Architecture**: Separates domain logic, data access, and presentation for maintainability.
- **Error Handling**: Comprehensive error responses for invalid input or unexpected failures.
- **Dependency Injection**: Factories create instances of use cases and controllers to enhance flexibility.

---

## Project Structure

The project is organized into the following key packages:

1. **Domain**: Core business logic and entities.

   - `entities`: Contains the `User` entity.
   - `usecases`: Contains interfaces and data models for use cases.

2. **Data**: Implements the use cases and interfaces for external data.

   - `usecases`: Contains `CreateUser`, which encapsulates user creation logic.
   - `contracts`: Interfaces for cryptography and repository operations.

3. **Infra**: Infrastructure-related code for data storage and cryptography.

   - `criptography`: Implements password hashing.
   - `repositories`: In-memory user repository.

4. **Presentation**: Handles API requests and responses.

   - `contracts`: Interfaces for request and response structures.
   - `controllers`: Processes incoming requests and invokes use cases.

5. **Main**: Application entry point and dependency wiring.
   - `factories`: Creates instances of use cases, controllers, and HTTP handlers.
   - `adapters`: Adapts HTTP requests for the application.
   - `decorators`: Adds error handling to HTTP handlers.

---

## Installation

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd <repository-folder>
   ```
2. **Compile the project:**
   Use your favorite Java IDE (e.g., IntelliJ IDEA) or run:

   ```bash
   javac -d out src/**/*.java
   ```

3. **Run the application:**
   ```bash
   java -cp out src.App
   ```

---

## Usage

The API listens on port `4000` by default. It exposes the following endpoint:

### Create User

- **Endpoint**: `POST /user`
- **Request Body** (JSON):
  ```json
  {
    "name": "John Doe",
    "username": "johndoe",
    "email": "john.doe@example.com",
    "password": "password123",
    "password_confirmation": "password123"
  }
  ```
- **Response** (JSON):
  ```json
  {
    "id": "1671036800000",
    "name": "John Doe",
    "username": "johndoe",
    "email": "john.doe@example.com"
  }
  ```

---

## Design Highlights

1. **Clean Architecture**:

   - Business logic is independent of external frameworks or UI.
   - Each layer is well-separated for maintainability and testability.

2. **Error Handling**:

   - `HttpHandlerErrorDecorator` provides detailed error responses in JSON format.
   - Stack traces are included in responses for debugging purposes.

3. **Extensibility**:
   - Adding new features, like additional user operations, is straightforward.
   - Replace in-memory storage with a database by modifying the repository layer.

---

## Code Samples

### Application Entry Point

```java
public class App {
    public static void main(String[] args) throws Exception {
        int port = 4000;

        InetSocketAddress netSocket = new InetSocketAddress(port);
        HttpServer httpServer = HttpServer.create(netSocket, 0);

        httpServer.createContext("/user", CreateUserHttpHandlerFactory.create());

        httpServer.setExecutor(null);
        httpServer.start();
        System.out.println("Server running on " + port);
    }
}
```

### User Creation Use Case

```java
public class CreateUser implements ICreateUser {
    private IHasher hasher;
    private CreateUserRepository userRepository;

    public CreateUser(IHasher hasher, CreateUserRepository userRepository) {
        this.hasher = hasher;
        this.userRepository = userRepository;
    }

    @Override
    public User run(CreateUserParams params) {
        String hashedPassword = this.hasher.hash(params.getPassword());

        CreateUserRepositoryParams repositoryParams = new CreateUserRepositoryParams();
        repositoryParams.setEmail(params.getEmail());
        repositoryParams.setUsername(params.getUsername());
        repositoryParams.setName(params.getName());
        repositoryParams.setHashedPassword(hashedPassword);

        return this.userRepository.create(repositoryParams);
    }
}
```

---

## Contributing

Contributions are welcome! Please submit a pull request or open an issue for any changes or feature requests.
