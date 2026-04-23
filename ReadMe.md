# 📑 Expense Tracker API

A robust RESTful API built with **Spring Boot** to help users track their personal expenses. This project features secure authentication using **JWT (JSON Web Tokens)** and handles complex relational data mapping with **JPA/Hibernate**.

---

## 🚀 Key Features

* **Secure Authentication:** User registration and login powered by Spring Security and JWT.
* **Expense Management:** Full CRUD (Create, Read, Update, Delete) operations for expenses.
* **Owner-Based Access:** Users can only view, edit, or delete their own data.
* **Optimized JSON Mapping:** Implementation of custom logic and Jackson annotations to prevent infinite recursion.
* **Partial Updates:** Efficient implementation of `PATCH` methods using `Optional` and DTOs for flexible data modification.

---

## 🛠️ Technologies Used

* **Java 17+**
* **Spring Boot 3.x**
* **Spring Data JPA**
* **Spring Security** (JWT implementation)
* **PostgreSQL** (Database)
* **Lombok**
* **Maven**

---

## 🏁 Getting Started

### Prerequisites

* JDK 17 or higher
* Maven 3.x
* A running instance of PostgreSQL (or H2/MySQL)

### Installation & Setup

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/your-username/expense-tracker.git](https://github.com/your-username/expense-tracker.git)
    cd expense-tracker
    ```

2.  **Configure the database:**
    Open `src/main/resources/application.properties` and update the database credentials:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/your_db_name
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    ```

3.  **Build the project:**
    ```bash
    mvn clean install
    ```

4.  **Run the application:**
    ```bash
    mvn spring-boot:run
    ```

---

## 🛣️ API Endpoints

### Authentication
| Method | Endpoint          | Description                          |
| :----- | :---------------- | :----------------------------------- |
| `POST` | `/auth/register`  | Create a new user account            |
| `POST` | `/auth/login`     | Authenticate and receive a JWT token |

### Expenses
| Method  | Endpoint         | Description                                |
| :------ | :--------------- | :----------------------------------------- |
| `GET`   | `/expenses`      | List all expenses for the logged-in user   |
| `POST`  | `/expenses`      | Create a new expense                       |
| `PATCH` | `/expenses/{id}` | Partially update an existing expense (UUID)|
| `DELETE`| `/expenses/{id}` | Remove an expense                          |

---

## 🧠 Technical Challenges & Learning

During the development of this first major project, I faced and solved several professional-level challenges:

* **Infinite Recursion:** I solved the bi-directional relationship loop between `User` and `Expense` by using `@JsonIgnore` and `DTO` patterns, ensuring clean and efficient API responses.
* **Data Integrity:** Implemented logic to ensure users can only interact with their own data, preventing unauthorized access across different accounts.
* **Advanced Patching:** Learned how to distinguish between "null" and "missing" fields in JSON using `Optional` wrappers in DTOs, allowing for true partial updates.

---

## 👨‍💻 Author

**Your Name** * GitHub: [@your-username](https://github.com/your-username)
* LinkedIn: [Your LinkedIn Profile](https://linkedin.com/in/yourprofile)
